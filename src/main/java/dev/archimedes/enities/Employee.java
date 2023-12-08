package dev.archimedes.enities;

import dev.archimedes.enities.supplier.Branch;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "employee_details")
@Table(name = "employee_details")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToOne
    private Address address;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Employee(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void addRoles(Set<Role> roles){
        this.roles.addAll(roles);
    }
}
