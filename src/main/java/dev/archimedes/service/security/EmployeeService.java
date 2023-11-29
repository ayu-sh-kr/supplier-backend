package dev.archimedes.service.security;

import dev.archimedes.enities.Employee;
import dev.archimedes.repositories.EmployeeRepository;
import dev.archimedes.service.security.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(employeeRepository.findByEmail(username).isPresent()){
            Employee employee = employeeRepository.findByEmail(username).get();
            return new EmployeeDetails(employee);
        }
        throw new RuntimeException("Email not found");
    }
}
