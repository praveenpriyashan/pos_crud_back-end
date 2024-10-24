package com.ijse.hellospring.controller;

import com.ijse.hellospring.dto.OrderProductDto;
import com.ijse.hellospring.entity.Order;
import com.ijse.hellospring.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/orders")
    public Order createOrder() {
        Order order = new Order();
        order.setTotalPrice(0.0);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderProducts(null);
        return orderService.createOrder(order);
    }

    @PostMapping("/orders/{id}/addProduct")
    public Order addProductToOrder(@PathVariable Long id, @RequestBody OrderProductDto orderProductDto) {
        return orderService.addProductToOrder(id, orderProductDto.getProductId(), orderProductDto.getQuantity());
    }

    @DeleteMapping("/orders/{OrderId}/products/{productId}")
    public Order deleteProductFromOrder(@PathVariable Long OrderId, @PathVariable Long productId){
        return orderService.deleteOrderProduct(OrderId, productId);
    }
}
