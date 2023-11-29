package dev.archimedes.controllers;

import dev.archimedes.enities.Employee;
import dev.archimedes.repositories.EmployeeRepository;
import dev.archimedes.service.crud.RegistrationService;
import dev.archimedes.service.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final EmployeeRepository employeeRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userMap){
        System.out.println(userMap);
        try {
            Employee employee = new Employee(userMap.get("name"), userMap.get("email"), userMap.get("password"));
            if(registrationService.doRegistration(employee))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> logMap){
        Map<String, String> responseMap = new HashMap<>();
        Authentication authentication = new UsernamePasswordAuthenticationToken(logMap.get("email"), logMap.get("password"));
        try {
            Authentication responseAuth = authenticationManager.authenticate(authentication);
            if(responseAuth.isAuthenticated()){
                String token = tokenService.generateToken(responseAuth);
                responseMap.put("jwt", token);
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
