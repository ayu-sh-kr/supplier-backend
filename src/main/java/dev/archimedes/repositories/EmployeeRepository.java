package dev.archimedes.repositories;

import dev.archimedes.enities.Employee;
import dev.archimedes.enities.Role;
import dev.archimedes.enities.projections.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from employee_details e where e.id = ?1")
    Optional<EmployeeInfo> getEmployeeDetails(int id);

    Optional<Employee> findByName(String name);

    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update employee_details e set e.password = ?1 where e.email = ?2")
    int updatePasswordByEmail(String password, String email);

    long deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query("update employee_details e set e.roles = ?1 where e.email = ?2")
    int updateRolesByEmail(Role roles, String email);



}
