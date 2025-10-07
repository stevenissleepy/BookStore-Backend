package fun.steven.bookstore.utils.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    public static final String NEW_ORDER_TOPIC = "new_order_topic";
    public static final String ORDER_RESULT_TOPIC = "order_result_topic";

    @Bean
    public NewTopic newOrderTopic() {
        return TopicBuilder.name(NEW_ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic dealOrderTopic() {
        return TopicBuilder.name(ORDER_RESULT_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
