package hieu.paymentservice.service;

import hieu.paymentservice.entity.OrderEvent;
import hieu.paymentservice.entity.Payment;
import hieu.paymentservice.entity.PaymentEvent;
import hieu.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void processPayment(OrderEvent orderEvent) {
        Payment payment = new Payment();
        payment.setOrderId(orderEvent.getOrderId());
        payment.setAmount(100); // Assume a fixed amount for simplicity
        payment.setStatus("COMPLETED");
        paymentRepository.save(payment);
        kafkaTemplate.send("payment-events", new PaymentEvent(payment.getOrderId(), "PAYMENT_COMPLETED"));
    }

}
