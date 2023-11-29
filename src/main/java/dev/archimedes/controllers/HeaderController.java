package dev.archimedes.controllers;

import dev.archimedes.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/header")
public class HeaderController {

    private final EmployeeRepository employeeRepository;

    public HeaderController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/name")
    public ResponseEntity<?> getUserName(Principal principal){
        String name = employeeRepository.findByEmail(principal.getName()).get().getName();
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
}
