package dev.archimedes.enities.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.archimedes.enities.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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
    private int id;
    private String branch_name;
    private String branch_email;

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

    public boolean addProduct(Product product){
        return this.products.add(product);
    }

    public boolean addProducts(Set<Product> products){
        return this.products.addAll(products);
    }

}
