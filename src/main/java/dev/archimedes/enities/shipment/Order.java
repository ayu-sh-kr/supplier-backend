package dev.archimedes.enities.shipment;

import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "buyerId")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
