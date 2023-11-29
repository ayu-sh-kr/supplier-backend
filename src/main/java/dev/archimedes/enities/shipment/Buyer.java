package dev.archimedes.enities.shipment;

import dev.archimedes.enities.Address;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "buyers")
@Table(name = "buyers")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private long id;
    private String name;
    private String email;
    private String password;
    @OneToOne
    private Cart cart;
    @OneToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    private Set<Order> orders = new HashSet<>();
}
