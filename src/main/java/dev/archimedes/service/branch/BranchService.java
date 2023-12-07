package dev.archimedes.service.branch;

import dev.archimedes.enities.Employee;
import dev.archimedes.enities.Role;
import dev.archimedes.enities.shipment.Buyer;
import dev.archimedes.enities.shipment.Cart;
import dev.archimedes.enities.shipment.Order;
import dev.archimedes.enities.supplier.Branch;
import dev.archimedes.enities.supplier.Product;
import dev.archimedes.enums.OrderStatus;
import dev.archimedes.enums.RoleType;
import dev.archimedes.repositories.AddressRepository;
import dev.archimedes.repositories.BuyerRepository;
import dev.archimedes.repositories.EmployeeRepository;
import dev.archimedes.repositories.RoleRepository;
import dev.archimedes.repositories.shipment.BranchRepository;
import dev.archimedes.repositories.shipment.OrderRepository;
import dev.archimedes.repositories.shipment.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final BuyerRepository buyerRepository;
    private final OrderRepository orderRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;

    private boolean validRole(String roleName){
        try {
            RoleType.valueOf(roleName);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ResponseEntity<?> createProduct(Product product, int branchId){
        if(branchRepository.findById(branchId).isPresent() && !productRepository.existsByProduct_name(product.getProduct_name())){
            Branch branch = branchRepository.findById(branchId).get();
            branch.addProduct(product);
            branchRepository.save(branch);
            return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
        }else{
            if(productRepository.existsByProduct_name(product.getProduct_name())){
                Branch branch = branchRepository.getReferenceById(branchId);
                branch.addProduct(product);
                branchRepository.save(branch);
                return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("Product can't be added", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> createEmployee(Employee employee, int branchId){
        if(employeeRepository.existsByEmailAndBranch_BranchId(employee.getEmail(), branchId)){
            return new ResponseEntity<>("Employee already exist", HttpStatus.ACCEPTED);
        }else if(branchRepository.findById(branchId).isPresent()){
            employee.setBranch(branchRepository.findById(branchId).get());
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Employee can't be created", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> removeEmployee(int empId, int branchId){
        if(employeeRepository.existsById(empId) && employeeRepository.existsByIdAndBranch_BranchId(empId, branchId)){
            employeeRepository.deleteById(empId);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee can't be deleted", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> updateEmployee(Employee employee, int branchId){
        if(employeeRepository.existsByIdAndBranch_BranchId(employee.getId(), branchId)){
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Employee can't be updated", HttpStatus.CONFLICT);
        }
    }

    public boolean updateEmployeeRoles(String role, Employee employee){
        if(roleRepository.existsByName(role)){
            Role role1 = roleRepository.findByName(role);
            employee.addRole(role1);
            employeeRepository.save(employee);
            return true;
        }else if(!roleRepository.existsByName(role)  && validRole(role)){
            Role role1 = new Role(role);
            employee.addRole(role1);
            return true;
        }
        return false;
    }

    public ResponseEntity<?> updateProductPrice(long prodId, double price){
        if(productRepository.existsById(prodId)){
            Product product = productRepository.getReferenceById(prodId);
            product.setPrice(price);
            return new ResponseEntity<>("Product price changed", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Product does not exist", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> updateProduct(Product product){
        if(productRepository.existsById(product.getId())){
            productRepository.save(product);
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> createOrder(Cart cart, int branchId, long buyerId){
        if(branchRepository.existsById(branchId) && buyerRepository.existsById(buyerId)){
            Order order = new Order();
            order.setBuyer(cart.getBuyer());
            order.setProducts(List.of(productRepository.getReferenceById(cart.getProductId())));
            order.setBranch(branchRepository.getReferenceById(branchId));
            order.setOrderStatus(OrderStatus.PLACED.name());
            orderRepository.save(order);
            Buyer buyer = buyerRepository.getReferenceById(buyerId);
            buyer.addOrder(order);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error creating order", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<?> updateOrderStatus(long orderId, OrderStatus orderStatus){
        if(orderRepository.existsById(orderId)){
            Order order = orderRepository.getReferenceById(orderId);
            order.setOrderStatus(orderStatus.name());
            orderRepository.save(order);
            return new ResponseEntity<>("Order status updated", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Error updating status", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> getEmployee(Principal principal) {
        if(employeeRepository.existsByEmail(principal.getName())){
            return new ResponseEntity<>(employeeRepository.getEmployeeByEmail(principal.getName()), HttpStatus.OK);
        }
        return new ResponseEntity<>("No user found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getEmployeeAddress(String name) {
        if(employeeRepository.existsByEmail(name)){
            System.out.println(employeeRepository.findAddressByEmail(name).getState());
            return new ResponseEntity<>(employeeRepository.findAddressByEmail(name), HttpStatus.OK);
        }
        return new ResponseEntity<>("No such user exist", HttpStatus.NO_CONTENT);
    }
}
