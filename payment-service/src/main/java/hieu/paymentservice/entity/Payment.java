package hieu.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "payments")
public class Payment {

    @Id
    private String id;
    private String orderId;
    private double amount;
    private String status;
}
