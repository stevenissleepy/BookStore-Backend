package fun.steven.bookstore.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dao.IOrderDao;
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
    private IBookDao bookDao;
    @Autowired
    private IOrderDao orderDao;

    @Override
    @Transactional
    public boolean createOrder(CreateOrderRequest request) {

        /* 获取用户所有的 CartItem */
        Long userId = request.getUserId();
        User user = userDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<CartItem> cartItems = user.getCart().getCartItems();

        /* 只保留选中的 cartItem */
        List<Long> bookIds = request.getBookIds();
        List<CartItem> selectedCartItems = cartItems.stream()
                .filter(item -> bookIds.contains(item.getBook().getId()))
                .toList();

        /* 创建订单 */
        Order order = new Order();
        order.setUser(user);
        order.setReceiver(request.getReceiver());
        order.setTel(request.getTel());
        order.setAddress(request.getAddress());
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
            Book book = item.getBook();
            book.setStock(book.getStock() - item.getQuantity());
            bookDao.save(book);
        });

        /* 从购物车移除已经购买的商品 */
        user.getCart().getCartItems().removeIf(item -> bookIds.contains(item.getBook().getId()));

        /* 计算总价格 */
        Integer totalPrice = selectedCartItems.stream().mapToInt(
                item -> item.getBook().getPrice() * item.getQuantity()).sum();
        order.setTotalPrice(totalPrice);

        /* 保存订单 */
        user.getOrders().add(order);
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
