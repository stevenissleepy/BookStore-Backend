package fun.steven.bookstore.utils.kafka;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import fun.steven.bookstore.pojo.dto.order.CreateOrderRequest;
import fun.steven.bookstore.service.IOrderService;

@Component
public class OrderTopicListener {

    private static final Logger log = Logger.getLogger(OrderTopicListener.class.getName());

    @Autowired
    private KafkaTemplate<String, Map<String, Object>> resultKafkaTemplate;

    @Autowired
    private IOrderService orderService;

    @KafkaListener(topics = KafkaTopicConfig.NEW_ORDER_TOPIC, groupId = "bookstore-group")
    public void handleNewOrder(CreateOrderRequest request) {
        Map<String, Object> result;
        Long userId = request.getUserId();
        try {
            orderService.createOrder(request);
            result = Map.of(
                    "userId", userId,
                    "status", "success",
                    "message", "订单创建成功");
        } catch (Exception e) {
            result = Map.of(
                    "userId", userId,
                    "status", "error",
                    "message", "订单创建失败，库存不足或其他错误");
        }

        resultKafkaTemplate.send(KafkaTopicConfig.DEAL_ORDER_TOPIC, result);
    }

    @KafkaListener(topics = KafkaTopicConfig.DEAL_ORDER_TOPIC, groupId = "bookstore-deal-group")
    public void handleDealOrder(Map<String, Object> result) {
        log.info("[Deal Order][User " + result.get("userId") + "]: " + result.get("message"));
    }
}
