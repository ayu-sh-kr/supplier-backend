package dev.archimedes.controllers;

import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Product;
import dev.archimedes.enities.supplier.Supplier;
import dev.archimedes.repositories.shipment.BranchRepository;
import dev.archimedes.repositories.shipment.ProductRepository;
import dev.archimedes.repositories.SupplierRepository;
import dev.archimedes.service.crud.AdminCRUD;
import dev.archimedes.service.crud.SupplierCRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class AdminController {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final SupplierCRUD supplierCRUD;
    private final AdminCRUD adminCRUD;

    @PostMapping("/create")
    public ResponseEntity<?> createSupplier(@RequestBody  Map<String, String> request){
        Supplier supplier = Supplier.builder()
                .name(request.get("name"))
                .email(request.get("email"))
                .password("password")
                .build();
        try {
            supplierCRUD.saveSupplier(supplier);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Created the supplier", HttpStatus.CREATED);
    }

    @PostMapping("/branch/create")
    public ResponseEntity<?> createBranch(@RequestBody Map<String, String> request, @Param("supId") String supId){
        System.out.println("Supplier id in param is: " + supId);
        long supplierId = Long.parseLong(supId);
        Branch branch = Branch.builder()
                .branch_name(request.get("branch_name"))
                .branch_email(request.get("branch_email"))
                .password(request.get("password"))
                .address(request.get("address"))
                .build();
        try {
            supplierCRUD.saveBranch(branch, supplierId);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Created the branch", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getSupplierById(@Param("supplierId")long supplierId){
        System.out.println("Supplier id in param is: "+supplierId);
        if(supplierRepository.findById(supplierId).isPresent()){
            Supplier supplier = supplierRepository.findById(supplierId).get();
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/findAll")
    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }

    @GetMapping("/branch/findAll")
    public ResponseEntity<?> getAllBranch(@Param("supplierId") int supplierId){
        try {
            List<Branch> branches = branchRepository.findBySupplier_Id(supplierId);
            return new ResponseEntity<>(branches, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/branch/get")
    public ResponseEntity<?> getAllBranches(){
        return new ResponseEntity<>(branchRepository.findAllBranchInfo(), HttpStatus.OK);
    }

    @PatchMapping("/employee/update-role")
    public ResponseEntity<?> updateRole(@Param("id") int id, String roleName){
        if (adminCRUD.updateRole(id, roleName)){
            return new ResponseEntity<>("Role updated successfully", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Invalid role", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/employee/get")
    public ResponseEntity<?> getEmployee(@Param("id") int id){
        return adminCRUD.getEmployee(id);
    }

    @PostMapping("/employee/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product product, @Param("branchId") long branchId){
        if(branchRepository.existsById(branchId)){
            return adminCRUD.addProduct(product, branchId);
        }
        return new ResponseEntity<>("Unexpected error", HttpStatus.CONFLICT);
    }

    @PostMapping("/product/create")
    public ResponseEntity<?> addProductDirect(@RequestBody Product product){
        System.out.println(product);
        return adminCRUD.directProductCreate(product);
    }

    @GetMapping("/product/get-all")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }
}
