package dev.archimedes.enities.projections;

import dev.archimedes.repositories.BranchId;
import dev.archimedes.repositories.RoleId;

import java.util.Set;

/**
 * Projection for {@link dev.archimedes.enities.Employee}
 */
public interface EmployeeRows {
    int getId();

    String getName();

    String getEmail();

    Set<RoleId> getRoles();

    BranchId getBranch();
}