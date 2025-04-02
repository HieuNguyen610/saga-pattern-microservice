package hieu.paymentservice.service;

import hieu.paymentservice.entity.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventListener {

    private final PaymentService paymentService;

    @KafkaListener(topics = "order-events")
    public void handleOrderEvent(OrderEvent orderEvent) {
        if ("ORDER_CREATED".equals(orderEvent.getEventType())) {
            paymentService.processPayment(orderEvent);
        }
    }
}
