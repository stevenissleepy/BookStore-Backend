package fun.steven.bookstore.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.order.createOrderRequestDto;
import fun.steven.bookstore.pojo.dto.order.OrdersResponseDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.ResultDto.ResultItemDto;
import fun.steven.bookstore.pojo.dto.stats.DateRangeDto;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.pojo.entity.CartItem;
import fun.steven.bookstore.pojo.entity.Order;
import fun.steven.bookstore.pojo.entity.OrderItem;
import fun.steven.bookstore.pojo.entity.User;
import fun.steven.bookstore.repository.BookRepository;
import fun.steven.bookstore.repository.OrderRepository;
import fun.steven.bookstore.repository.UserRepository;

@Repository
public class OrderDao implements IOrderDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean createOrder(Long userId, createOrderRequestDto addOrderDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<CartItem> cartItems = user.getCart().getCartItems();

        /* 只保留选中的 cartItem */
        List<Long> bookIds = addOrderDto.getBookIds();
        List<CartItem> selectedCartItems = cartItems.stream()
                .filter(item -> bookIds.contains(item.getBook().getId()))
                .toList();

        /* 创建订单 */
        Order order = new Order();
        order.setUser(user);
        order.setReceiver(addOrderDto.getReceiver());
        order.setTel(addOrderDto.getTel());
        order.setAddress(addOrderDto.getAddress());
        order.setDate(java.time.LocalDateTime.now());

        /* 将购物车中的商品加到订单中 */
        List<OrderItem> orderItems = selectedCartItems.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            return orderItem;
        }).toList();
        order.setOrderItems(orderItems);

        /* 从库存中删去已经被购买的书 */
        selectedCartItems.forEach(item -> {
            Book book = bookRepository.findById(item.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            book.setStock(book.getStock() - item.getQuantity());
            bookRepository.save(book);
        });

        /* 计算总价格 */
        Integer totalPrice = selectedCartItems.stream().mapToInt(
                item -> item.getBook().getPrice() * item.getQuantity()).sum();
        order.setTotalPrice(totalPrice);

        /* 保存订单 */
        user.getOrders().add(order);
        userRepository.save(user);

        return true;
    }

    @Override
    public OrdersResponseDto searchUserOrders(Long userId, SearchDto searchDto) {
        String startDateStr = searchDto.getStartDate();
        String endDateStr = searchDto.getEndDate();
        String bookTitle = searchDto.getBookTitle();
        Integer page = searchDto.getPage();
        Integer limit = searchDto.getLimit();
        Pageable pageable = Pageable.ofSize(limit).withPage(page);

        Page<Order> orders;

        boolean hasStartDate = startDateStr != null && !startDateStr.isEmpty();
        boolean hasEndDate = endDateStr != null && !endDateStr.isEmpty();
        boolean hasBookTitle = bookTitle != null && !bookTitle.isEmpty();

        // 有日期和书名
        if (hasStartDate && hasEndDate && hasBookTitle) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderRepository.findByUserIdAndDateRangeAndBookTitle(userId, startDate, endDate, bookTitle,
                    pageable);

            // 有日期范围，没有书名
        } else if (hasStartDate && hasEndDate) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderRepository.findByUserIdAndDateRange(userId, startDate, endDate, pageable);

            // 有书名，没有日期范围
        } else if (hasBookTitle) {
            orders = orderRepository.findByUserIdAndBookTitle(userId, bookTitle, pageable);

            // 没有任何条件
        } else {
            orders = orderRepository.findByUserId(userId, pageable);
        }
        return new OrdersResponseDto(orders);
    }

    @Override
    public OrdersResponseDto searchAllOrders(SearchDto searchDto) {
        String startDateStr = searchDto.getStartDate();
        String endDateStr = searchDto.getEndDate();
        String bookTitle = searchDto.getBookTitle();
        Integer page = searchDto.getPage();
        Integer limit = searchDto.getLimit();
        Pageable pageable = Pageable.ofSize(limit).withPage(page);

        Page<Order> orders;

        boolean hasStartDate = startDateStr != null && !startDateStr.isEmpty();
        boolean hasEndDate = endDateStr != null && !endDateStr.isEmpty();
        boolean hasBookTitle = bookTitle != null && !bookTitle.isEmpty();

        // 有日期和书名
        if (hasStartDate && hasEndDate && hasBookTitle) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderRepository.findByDateRangeAndBookTitle(startDate, endDate, bookTitle, pageable);

            // 有日期范围，没有书名
        } else if (hasStartDate && hasEndDate) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderRepository.findByDateRange(startDate, endDate, pageable);

            // 有书名，没有日期范围
        } else if (hasBookTitle) {
            orders = orderRepository.findByBookTitle(bookTitle, pageable);

            // 没有任何条件
        } else {
            orders = orderRepository.findAll(pageable);
        }

        return new OrdersResponseDto(orders);
    }

    @Override
    public ResultDto<BookDto> statsBooks(Long userId, DateRangeDto dateRangeDto) {
        String startDateStr = dateRangeDto.getStartDate();
        String endDateStr = dateRangeDto.getEndDate();
        List<Object[]> bookList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            bookList = orderRepository.findBooksByUserId(userId);
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            bookList = orderRepository.findBooksByUserIdAndDateRange(userId, startDate, endDate);
        }

        List<ResultItemDto<BookDto>> bookItems = bookList.stream()
                .map(item -> {
                    Book book = (Book) item[0];
                    Integer sales = ((Number) item[1]).intValue();
                    BookDto bookDto = new BookDto(book);
                    return new ResultItemDto<>(bookDto, sales);
                })
                .toList();
        return new ResultDto<>(bookItems);
    }

    @Override
    public ResultDto<String> searchTop10Books(DateRangeDto dateRangeDto) {
        String startDateStr = dateRangeDto.getStartDate();
        String endDateStr = dateRangeDto.getEndDate();
        List<Object[]> salesList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            salesList = orderRepository.findTop10Book();
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            salesList = orderRepository.findTop10BookByDateRange(startDate, endDate);
        }

        List<ResultItemDto<String>> salesItems = salesList.stream()
                .map(sale -> new ResultItemDto<>((String) sale[0], ((Number) sale[1]).intValue()))
                .toList();

        return new ResultDto<>(salesItems);
    }

    @Override
    public ResultDto<String> searchTop10Users(DateRangeDto dateRangeDto) {
        String startDateStr = dateRangeDto.getStartDate();
        String endDateStr = dateRangeDto.getEndDate();
        List<Object[]> salesList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            salesList = orderRepository.findTop10User();
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            salesList = orderRepository.findTop10UserByDateRange(startDate, endDate);
        }

        List<ResultItemDto<String>> salesItems = salesList.stream()
                .map(sale -> new ResultItemDto<>((String) sale[0], ((Number) sale[1]).intValue()))
                .toList();
        return new ResultDto<>(salesItems);
    }
}
