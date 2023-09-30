package ru.bondarev.orders.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.bondarev.orders.dto.OrderDTO;

/**
 * продюсер пишет в топик
 */
@Service
public class KafkaProducer {

    @Value("${spring.kafka.topic-json.name}")
    private String topicName;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private   KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderDTO orderDTO){

        LOGGER.info(String.format("Message sent -> %s", orderDTO.toString()));

        Message<OrderDTO> message = MessageBuilder
                .withPayload(orderDTO)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }
}
