package hieu.orderservice.service;

import hieu.orderservice.model.Order;
import hieu.orderservice.model.OrderEvent;
import hieu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public Order createOrder(Order order) {
        order.setStatus("CREATED");
        orderRepository.save(order);
        kafkaTemplate.send("order-events", new OrderEvent(order.getId(), "ORDER_CREATED"));
        return order;
    }

    public void updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
