package dev.archimedes.enities.shipment;

import dev.archimedes.enities.supplier.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oder_id")
    private long id;
    private String orderStatus;
    @ManyToOne
    private Buyer buyer;
    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();
}
