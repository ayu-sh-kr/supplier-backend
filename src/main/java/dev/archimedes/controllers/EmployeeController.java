package dev.archimedes.controllers;

import dev.archimedes.enums.OrderStatus;
import dev.archimedes.repositories.EmployeeRepository;
import dev.archimedes.service.branch.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final BranchService branchService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/get")
    public ResponseEntity<?> getUserDetails(Principal principal){
        return branchService.getEmployee(principal);
    }

    @GetMapping("/address")
    public ResponseEntity<?> getUserAddress(Principal principal){
        return branchService.getEmployeeAddress(principal.getName());
    }
    @PatchMapping("/order/update-status")
    public ResponseEntity<?> updateStatus(@RequestParam("orderId") long id, @RequestBody Map<String, String> map){
        try{
            System.out.println(map.get("status") + " id: " + id);
            OrderStatus status1 = OrderStatus.valueOf(map.get("status"));
            System.out.println(status1.name());
            return branchService.updateOrderStatus(id, status1);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
