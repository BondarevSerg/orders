package ru.bondarev.orders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bondarev.orders.dto.OrderDTO;
import ru.bondarev.orders.kafka.KafkaProducer;

/**
 * контролер заказа
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final  KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public ResponseEntity<HttpStatus> create(@RequestBody OrderDTO orderDTO) {

        kafkaProducer.sendMessage(orderDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }
}
