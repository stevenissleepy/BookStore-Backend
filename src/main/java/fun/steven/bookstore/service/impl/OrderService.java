package fun.steven.bookstore.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.dao.IOrderItemDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.pojo.dto.order.CreateOrderRequest;
import fun.steven.bookstore.pojo.dto.order.FindOrdersResponse;
import fun.steven.bookstore.pojo.dto.order.SearchAllOrdersRequest;
import fun.steven.bookstore.pojo.dto.order.SearchUserOrdersRequest;
import fun.steven.bookstore.service.IOrderService;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.pojo.entity.CartItem;
import fun.steven.bookstore.pojo.entity.Order;
import fun.steven.bookstore.pojo.entity.OrderItem;
import fun.steven.bookstore.pojo.entity.User;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderItemDao orderItemDao;

    @Override
    @Transactional
    public boolean createOrder(CreateOrderRequest request) {

        /* 获取用户和它所有的 CartItem */
        Long userId = request.getUserId();
        User user = userDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<CartItem> cartItems = user.getCart().getCartItems();

        /* 筛选出本次下单的 CartItem */
        List<Long> bookIds = request.getBookIds();
        cartItems = cartItems.stream()
                .filter(item -> bookIds.contains(item.getBook().getId()))
                .toList();

        /* 创建 Order */
        Order order = new Order();
        order.setUser(user);
        order.setReceiver(request.getReceiver());
        order.setTel(request.getTel());
        order.setAddress(request.getAddress());
        order.setDate(java.time.LocalDateTime.now());
        Integer totalPrice = cartItems.stream().mapToInt(
                item -> item.getBook().getPrice() * item.getQuantity()).sum();
        order.setTotalPrice(totalPrice);

        /* 保存 Order, 这是事务中的第一个操作 */
        Order savedOrder = orderDao.save(order);

        /* 将 CartItem 转换为 OrderItem */
        List<OrderItem> orderItems = cartItems.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            return orderItem;
        }).toList();

        /* 保存 OrderItem, 这是事务中的第二个操作 */
        orderItemDao.saveAll(orderItems);

        /* 更新书籍库存 */
        cartItems.forEach(item -> {
            Book book = item.getBook();
            book.setStock(book.getStock() - item.getQuantity());
        });

        /* 从购物车移除已购买的商品 */
        user.getCart().getCartItems().removeIf(item -> bookIds.contains(item.getBook().getId()));
        userDao.save(user);

        return true;
    }

    @Override
    public FindOrdersResponse searchUserOrders(SearchUserOrdersRequest request) {
        Long userId = request.getUserId();
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        String title = request.getTitle();
        Integer page = request.getPage();
        Integer limit = request.getLimit();
        Pageable pageable = Pageable.ofSize(limit).withPage(page);

        Page<Order> orders;

        boolean hasStartDate = startDateStr != null && !startDateStr.isEmpty();
        boolean hasEndDate = endDateStr != null && !endDateStr.isEmpty();
        boolean hasTitle = title != null && !title.isEmpty();

        // 有日期和书名
        if (hasStartDate && hasEndDate && hasTitle) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderDao.findByUserIdAndDateRangeAndTitle(userId, startDate, endDate, title,
                    pageable);

        }
        // 有日期范围，没有书名
        else if (hasStartDate && hasEndDate) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderDao.findByUserIdAndDateRange(userId, startDate, endDate, pageable);

        }
        // 有书名，没有日期范围
        else if (hasTitle) {
            orders = orderDao.findByUserIdAndTitle(userId, title, pageable);

        }
        // 没有任何条件
        else {
            orders = orderDao.findByUserId(userId, pageable);
        }

        return new FindOrdersResponse(orders);
    }

    @Override
    public FindOrdersResponse searchAllOrders(SearchAllOrdersRequest request) {
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        String title = request.getTitle();
        Integer page = request.getPage();
        Integer limit = request.getLimit();
        Pageable pageable = Pageable.ofSize(limit).withPage(page);

        Page<Order> orders;

        boolean hasStartDate = startDateStr != null && !startDateStr.isEmpty();
        boolean hasEndDate = endDateStr != null && !endDateStr.isEmpty();
        boolean hasBookTitle = title != null && !title.isEmpty();

        // 有日期和书名
        if (hasStartDate && hasEndDate && hasBookTitle) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderDao.findByDateRangeAndTitle(startDate, endDate, title, pageable);

            // 有日期范围，没有书名
        } else if (hasStartDate && hasEndDate) {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            orders = orderDao.findByDateRange(startDate, endDate, pageable);

            // 有书名，没有日期范围
        } else if (hasBookTitle) {
            orders = orderDao.findByTitle(title, pageable);

            // 没有任何条件
        } else {
            orders = orderDao.findAll(pageable);
        }

        return new FindOrdersResponse(orders);
    }

}
