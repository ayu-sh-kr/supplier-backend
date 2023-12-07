package dev.archimedes.service.crud;

import dev.archimedes.enities.Employee;
import dev.archimedes.enities.Role;
import dev.archimedes.enities.projections.EmployeeInfo;
import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Product;
import dev.archimedes.enums.RoleType;
import dev.archimedes.repositories.*;
import dev.archimedes.repositories.shipment.BranchRepository;
import dev.archimedes.repositories.shipment.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCRUD {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    private boolean validRole(String roleName){
        try {
            RoleType.valueOf(roleName);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean updateRole(int id, String roleName){
        if(validRole(roleName) && employeeRepository.findById(id).isPresent()){
            Employee employee = employeeRepository.findById(id).get();
            if(roleRepository.existsByName(roleName)){
                Role role = roleRepository.findByName(roleName);
                employee.addRole(role);
                employeeRepository.save(employee);
                return true;
            }else{
                Role role = new Role(roleName);
                roleRepository.save(role);
                employee.addRole(role);
                employeeRepository.save(employee);
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<?> getEmployee(int id){
        if(employeeRepository.getEmployeeDetails(id).isPresent()){
            EmployeeInfo employeeInfo = employeeRepository.getEmployeeDetails(id).get();
            return new ResponseEntity<>(employeeInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> addProduct(Product product, int branchId){
        if(productRepository.existsByProduct_name(product.getProduct_name())){
            return new ResponseEntity<>("Product already exist", HttpStatus.BAD_REQUEST);
        }else{
            productRepository.save(product);
            Product product1 = productRepository.findByProduct_name(product.getProduct_name());
            Branch branch = branchRepository.getReferenceById(branchId);
            branch.addProduct(product1);
            branchRepository.save(branch);
            return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> updateProject(long id, Product product){
        if(productRepository.existsById(id)){
            product.setId(id);
            productRepository.save(product);
            return new ResponseEntity<>("Product updated successfully", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Product do not exist", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> directProductCreate(Product product){
        if(productRepository.existsByProduct_name(product.getProduct_name())){
            return new ResponseEntity<>("Product already exist", HttpStatus.NOT_ACCEPTABLE);
        }else{
            productRepository.save(product);
            return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> getAllEmployee(){
        return new ResponseEntity<>(employeeRepository.getEmployeeRows(), HttpStatus.OK);
    }

}
