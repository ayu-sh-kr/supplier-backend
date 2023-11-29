package dev.archimedes.repositories;

import dev.archimedes.enities.shipment.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
