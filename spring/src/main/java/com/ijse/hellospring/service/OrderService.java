package com.ijse.hellospring.service;

import com.ijse.hellospring.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order>getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
    Order addProductToOrder(Long orderId,Long productId,int quantity);
    Order deleteOrderProduct(Long orderId,Long productId);
}
