package ru.bondarev.orders.dto;

import lombok.Data;


import java.io.Serializable;

/**
 * dto заказа
 */
@Data
public class OrderDTO implements Serializable {

    private Long userId;

    private Long orderId;

    private String userEmail;


}
