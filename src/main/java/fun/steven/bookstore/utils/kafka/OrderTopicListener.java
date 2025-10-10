package fun.steven.bookstore.utils.kafka;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.order.CreateOrderRequest;
import fun.steven.bookstore.service.IOrderService;
import fun.steven.bookstore.utils.websocket.WebSocketServer;

@Component
public class OrderTopicListener {

    private static final Logger log = Logger.getLogger(OrderTopicListener.class.getName());

    @Autowired
    private KafkaTemplate<String, ResponseMessage<?>> resultKafkaTemplate;

    @Autowired
    private IOrderService orderService;

    @KafkaListener(topics = KafkaTopicConfig.NEW_ORDER_TOPIC, groupId = "bookstore-group")
    public void handleNewOrder(CreateOrderRequest request) {
        ResponseMessage<Long> response;
        Long userId = request.getUserId();
        try {
            orderService.createOrder(request);
            response = ResponseMessage.success("订单创建成功", userId);
        } catch (Exception e) {
            response = new ResponseMessage<Long>(400, "订单创建失败: " + e.getMessage(), userId);
        }

        resultKafkaTemplate.send(KafkaTopicConfig.ORDER_RESULT_TOPIC, response);
    }

    @KafkaListener(topics = KafkaTopicConfig.ORDER_RESULT_TOPIC, groupId = "bookstore-result-group")
    public void handleOrderResult(ResponseMessage<Long> response) {
        Long userId = ((Number) response.getData()).longValue();
        log.info("[Order Result][User " + userId + "]: " + response.getMessage());

        WebSocketServer.sendMessage(userId, response);
    }
}
