package dev.archimedes.enities.supplier;

import dev.archimedes.enities.shipment.Order;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "product_details")
@Table(name = "product_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;
    private String product_name;
    private double price;
    private String category;
    private String imageUrl;
    @CreationTimestamp
    private LocalDateTime creationTime;
    private String expiry;
    private String origin;

    @ManyToMany(mappedBy = "products")
    private Set<Branch> branches = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_order_map",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> orders = new HashSet<>();

    @Override
    public String toString() {
        return "Product{" +
                "product_name='" + product_name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", expiry='" + expiry + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
