package dev.archimedes.repositories;

import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("select s from suppliers s where s.email = ?1")
    Optional<Supplier> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update suppliers s set s.password = ?1 where s.email = ?2")
    int updatePasswordByEmail(String password, String email);

    @Query("select s.branches from suppliers s where s.id = ?1")
    List<Branch> findAllBySupplierId(int id);
}
