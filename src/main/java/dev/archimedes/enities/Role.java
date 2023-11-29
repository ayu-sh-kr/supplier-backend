package dev.archimedes.enities;

import dev.archimedes.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private int id;
    private String name = RoleType.USER.name();

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    public void addEmployees(Set<Employee> employees){
        this.employees.addAll(employees);
    }
}
