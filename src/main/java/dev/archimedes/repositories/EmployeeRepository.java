package dev.archimedes.repositories;

import dev.archimedes.enities.Address;
import dev.archimedes.enities.Employee;
import dev.archimedes.enities.Role;
import dev.archimedes.enities.projections.EmployeeInfo;
import dev.archimedes.enities.projections.EmployeeProjection;
import dev.archimedes.enities.projections.EmployeeRows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Query("select e.id, e.name, e.email, e.roles, e.branch.branchId from employee_details e")
    List<EmployeeInfo> getAllEmployeeDetails();

    @Query("select e from employee_details e")
    List<EmployeeRows> getEmployeeRows();

    @Query("select e from employee_details e")
    List<EmployeeProjection.EmployeeInfo> findAllEmployee();

    @Query("select (count(e) > 0) from employee_details e where e.email = ?1 and e.branch.branchId = ?2")
    boolean existsByEmailAndBranch_BranchId(String email, int branchId);

    @Query("select (count(e) > 0) from employee_details e where e.id = ?1 and e.branch.branchId = ?2")
    boolean existsByIdAndBranch_BranchId(int id, int branchId);

    @Query("select e from employee_details e where e.email = :email")
    EmployeeProjection.EmployeeInfo getEmployeeByEmail(@Param("email") String email);

    @Query("select e.address from employee_details e where e.email = ?1")
    Address findAddressByEmail(String email);


}
