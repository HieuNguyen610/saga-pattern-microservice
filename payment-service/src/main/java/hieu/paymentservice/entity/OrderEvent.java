package hieu.paymentservice.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String orderId;
    private String eventType;
}
