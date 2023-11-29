package dev.archimedes.repositories.shipment;

import dev.archimedes.enities.projections.BranchInfo;
import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Product;
import dev.archimedes.enities.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    @Query("select s from supplier_branch s where s.branch_name = ?1")
    List<Product> findByBranch_name(String branch_name);

    @Query("select s from supplier_branch s where s.branch_email = ?1")
    Optional<Supplier> findByBranch_email(String branch_email);

    @Query("select s from supplier_branch s where s.supplier.id = ?1")
    List<Branch> findBySupplier_Id(int id);

    @Query("select s from supplier_branch s")
    List<BranchInfo> findAllBranchInfo();


}
