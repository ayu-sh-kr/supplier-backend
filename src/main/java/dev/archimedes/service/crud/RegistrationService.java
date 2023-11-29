package dev.archimedes.service.crud;

import dev.archimedes.enities.Employee;
import dev.archimedes.enities.Role;
import dev.archimedes.enums.RoleType;
import dev.archimedes.repositories.EmployeeRepository;
import dev.archimedes.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    public boolean doRegistration(Employee employee){
        if(roleRepository.existsByName(RoleType.USER.name())){
            Role role = roleRepository.findByName(RoleType.USER.name());
            employee.addRole(role);
            employeeRepository.save(employee);
            return true;
        }else if(!roleRepository.existsByName(RoleType.USER.name())){
            Role role = new Role(RoleType.USER.name());
            roleRepository.save(role);
            employee.addRole(role);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }
}
