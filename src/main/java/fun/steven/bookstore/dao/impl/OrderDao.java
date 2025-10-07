package fun.steven.bookstore.dao.impl;

import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.pojo.entity.Order;
import fun.steven.bookstore.repository.OrderRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDao implements IOrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findByTitle(String title, Pageable pageable) {
        return orderRepository.findByTitle(title, pageable);
    }

    @Override
    public Page<Order> findByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return orderRepository.findByDateRange(startDate, endDate, pageable);
    }

    @Override
    public Page<Order> findByDateRangeAndTitle(LocalDateTime startDate, LocalDateTime endDate, String title,
            Pageable pageable) {
        return orderRepository.findByDateRangeAndTitle(startDate, endDate, title, pageable);
    }

    @Override
    public Page<Order> findByUserId(Long userId, Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<Order> findByUserIdAndTitle(Long userId, String title, Pageable pageable) {
        return orderRepository.findByUserIdAndTitle(userId, title, pageable);
    }

    @Override
    public Page<Order> findByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate,
            Pageable pageable) {
        return orderRepository.findByUserIdAndDateRange(userId, startDate, endDate, pageable);
    }

    @Override
    public Page<Order> findByUserIdAndDateRangeAndTitle(Long userId, LocalDateTime startDate, LocalDateTime endDate,
            String title, Pageable pageable) {
        return orderRepository.findByUserIdAndDateRangeAndTitle(userId, startDate, endDate, title, pageable);
    }

    @Override
    public List<Object[]> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Object[]> findByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByUserIdAndDateRange(userId, startDate, endDate);
    }

    @Override
    public List<Object[]> findTop10Books() {
        return orderRepository.findTop10Book();
    }

    @Override
    public List<Object[]> findTop10BooksByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findTop10BookByDateRange(startDate, endDate);
    }

    @Override
    public List<Object[]> findTop10Users() {
        return orderRepository.findTop10User();
    }

    @Override
    public List<Object[]> findTop10UsersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findTop10UserByDateRange(startDate, endDate);
    }
}