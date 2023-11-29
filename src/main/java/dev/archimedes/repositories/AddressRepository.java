package dev.archimedes.repositories;

import dev.archimedes.enities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("select a from address a where a.city = ?1")
    List<Address> findByCity(String city);

    boolean existsByStreet(String street);

    long countByCity(String city);


}
