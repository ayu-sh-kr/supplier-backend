package dev.archimedes.enities.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.archimedes.enities.Employee;
import dev.archimedes.enities.shipment.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "supplier_branch")
@Table(name = "supplier_branch")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int branchId;
    @Column(name = "branch_name")
    private String name;
    @Column(name = "branch_email")
    private String email;

    private String password;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Supplier supplier;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "branch_products",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public boolean addProduct(Product product){
        return this.products.add(product);
    }

    public boolean addProducts(Set<Product> products){
        return this.products.addAll(products);
    }

}
