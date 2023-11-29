package dev.archimedes.service.crud;

import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Supplier;
import dev.archimedes.repositories.shipment.BranchRepository;
import dev.archimedes.repositories.shipment.ProductRepository;
import dev.archimedes.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierCRUD {
    private final SupplierRepository supplierRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public boolean saveSupplier(Supplier supplier){
        if(supplierRepository.findByEmail(supplier.getEmail()).isPresent()){
            throw new RuntimeException("Email already exist");
        }else{
            supplierRepository.save(supplier);
            return true;
        }
    }

    public boolean saveBranch(Branch branch, long supplier_id){
        Supplier supplier = supplierRepository.findById(supplier_id).get();
        if(branchRepository.findByBranch_email(branch.getBranch_email()).isPresent()){
            throw new RuntimeException("Branch Already Exist");
        }else {
            branch.setSupplier(supplier);
            branchRepository.save(branch);
            return true;
        }
    }
}
