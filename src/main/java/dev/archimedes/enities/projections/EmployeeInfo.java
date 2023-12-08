package dev.archimedes.enities.projections;

import dev.archimedes.enities.Role;

import java.util.List;

/**
 * Projection for {@link dev.archimedes.enities.Employee}
 */
public interface EmployeeInfo {
    int getId();

    String getName();

    String getEmail();

    int getBranchId();

    List<Role> getRoles();
}