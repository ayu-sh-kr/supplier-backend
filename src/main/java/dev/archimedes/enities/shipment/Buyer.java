package dev.archimedes.enities.shipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.archimedes.enities.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "buyers")
@Table(name = "buyers")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buyer")
    @JsonIgnore
    private List<Cart> cart;
    @OneToOne
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
