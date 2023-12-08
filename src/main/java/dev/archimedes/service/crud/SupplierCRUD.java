package dev.archimedes.service.crud;

import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Supplier;
import dev.archimedes.repositories.SupplierRepository;
import dev.archimedes.repositories.shipment.BranchRepository;
import dev.archimedes.repositories.shipment.OrderRepository;
import dev.archimedes.repositories.shipment.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierCRUD {
    private final SupplierRepository supplierRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

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
        if(branchRepository.findByBranch_email(branch.getEmail()).isPresent()){
            throw new RuntimeException("Branch Already Exist");
        }else {
            branch.setSupplier(supplier);
            branchRepository.save(branch);
            return true;
        }
    }

    public ResponseEntity<?> getAllOrders(){
        try {
//            List<OrderProjection.OrderInfo> orders = orderRepository.getAllOrders();
            return new ResponseEntity<>(orderRepository.getAllOrders(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
