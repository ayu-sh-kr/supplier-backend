package dev.archimedes.repositories.shipment;

import dev.archimedes.enities.projections.OrderProjection;
import dev.archimedes.enities.shipment.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from orders o")
    List<OrderProjection.OrderInfo> getAllOrders();

}
