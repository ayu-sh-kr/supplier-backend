package dev.archimedes.enities;

import dev.archimedes.enities.dtos.BranchDto;
import dev.archimedes.enities.dtos.RoleDto;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Employee}
 */
@Value
public class EmployeeDto implements Serializable {
    int id;
    String name;
    String email;
    Set<RoleDto> roles;
    BranchDto branch;
}