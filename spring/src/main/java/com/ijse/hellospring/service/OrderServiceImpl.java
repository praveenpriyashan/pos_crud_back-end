package com.ijse.hellospring.service;

import com.ijse.hellospring.entity.Order;
import com.ijse.hellospring.entity.Product;
import com.ijse.hellospring.repository.OrderRepository;
import com.ijse.hellospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    @Override
    public Order addProductToOrder(Long orderId, Long productId, int quantity) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return null;
        }

        order.getOrderProducts().add(product);
        order.setTotalPrice(order.getTotalPrice() + product.getPrice() * quantity);
        return orderRepository.save(order);
    }

    @Override
    public Order deleteOrderProduct(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            return null;
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return null;
        }
        order.getOrderProducts().remove(product);
        order.setTotalPrice(order.getTotalPrice() - product.getPrice());
        return orderRepository.save(order);
    }

}
