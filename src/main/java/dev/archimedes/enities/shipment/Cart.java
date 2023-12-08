package dev.archimedes.enities.shipment;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;
    private String productName;
    @Column(nullable = false)
    private long productId;
    private int quantity = 1;
    @ManyToOne
    @JoinColumn(name = "buyerId")
    private Buyer buyer;
    public void addMore(int count){
        this.quantity += count;
    }

    public void addMore(){
        this.quantity += 1;
    }

    public void removeOne(){
        this.quantity -= 1;
    }
}
