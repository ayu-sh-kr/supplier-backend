package dev.archimedes.enities;

import dev.archimedes.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String addressType = AddressType.HOME.toString();
    private String street;
    private String city;
    private String state;
    private String country;
    private Long pincode;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
