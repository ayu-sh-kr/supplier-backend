package dev.archimedes.repositories.shipment;

import dev.archimedes.enities.shipment.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
