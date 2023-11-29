package dev.archimedes.service.branch;

import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Product;
import dev.archimedes.repositories.BuyerRepository;
import dev.archimedes.repositories.shipment.BranchRepository;
import dev.archimedes.repositories.shipment.OrderRepository;
import dev.archimedes.repositories.shipment.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final BuyerRepository buyerRepository;
    private final OrderRepository orderRepository;

    public ResponseEntity<?> createProduct(Product product, long branch_id){
        if(branchRepository.existsById(branch_id) && !productRepository.existsByProduct_name(product.getProduct_name())){
            Branch branch = branchRepository.findById(branch_id).get();
            branch.addProduct(product);
            branchRepository.save(branch);
            return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product can't be added", HttpStatus.NOT_ACCEPTABLE);
    }


}
