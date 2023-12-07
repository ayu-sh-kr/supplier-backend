package dev.archimedes.enities;

import dev.archimedes.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "address")
@Table(name = "address")
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
}
