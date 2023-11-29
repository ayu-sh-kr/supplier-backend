package dev.archimedes.repositories.shipment;

import dev.archimedes.enities.supplier.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select (count(p) > 0) from product_details p where p.product_name = ?1")
    boolean existsByProduct_name(String product_name);

    @Query("select p from product_details p where p.category = ?1")
    Optional<List<Product>> findByCategory(String category);


    @Query("select p from product_details p where p.product_name = ?1")
    Product findByProduct_name(String product_name);


}
