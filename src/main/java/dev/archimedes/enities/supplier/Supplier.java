package dev.archimedes.enities.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "suppliers")
@Table(name = "suppliers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Branch> branches = new HashSet<>();

    public void addBranch(Branch branch){
        this.branches.add(branch);
    }

    public void addBranches(List<Branch> branches){
        this.branches.addAll(branches);
    }
}
