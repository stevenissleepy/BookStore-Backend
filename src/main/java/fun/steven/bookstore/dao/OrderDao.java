package fun.steven.bookstore.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.dto.book.SalesDto;
import fun.steven.bookstore.pojo.dto.book.SalesDto.SalesItemDto;
import fun.steven.bookstore.pojo.dto.book.SearchSalesDto;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
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
    public boolean createOrder(Long userId, AddOrderDto addOrderDto) {
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
    public GetOrdersDto getOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId).orElse(List.of());
        return new GetOrdersDto(orders);
    }

    @Override
    public GetOrdersDto searchAllOrders(SearchDto searchDto) {
        String startDateStr = searchDto.getStartDate();
        String endDateStr = searchDto.getEndDate();
        String bookTitle = searchDto.getBookTitle();

        boolean hasStartDate = startDateStr != null && !startDateStr.isEmpty();
        boolean hasEndDate = endDateStr != null && !endDateStr.isEmpty();
        boolean hasBookTitle = bookTitle != null && !bookTitle.isEmpty();

        List<Order> orders;

        // 有日期和书名
        if (hasStartDate && hasEndDate && hasBookTitle) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderRepository.findByDateRangeAndBookTitle(startDate, endDate, bookTitle);

        // 有日期范围，没有书名
        } else if (hasStartDate && hasEndDate) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderRepository.findByDateRange(startDate, endDate);

        // 有书名，没有日期范围
        } else if (hasBookTitle) {
            orders = orderRepository.findByBookTitle(bookTitle);

        // 没有任何条件
        } else { 
            orders = orderRepository.findAll();
        }

        return new GetOrdersDto(orders);
    }

    @Override
    public SalesDto searchTop10Books(SearchSalesDto searchSalesDto) {
        String startDateStr = searchSalesDto.getStartDate();
        String endDateStr = searchSalesDto.getEndDate();
        List<Object[]> salesList;

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            salesList = bookRepository.findTop10Book();
        } else {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            salesList = bookRepository.findTop10BookByDateRange(startDate, endDate);
        }

        List<SalesItemDto> salesItems = salesList.stream()
                .map(sale -> new SalesItemDto((String) sale[0], ((Number) sale[1]).intValue()))
                .toList();

        return new SalesDto(salesItems);
    }
}
