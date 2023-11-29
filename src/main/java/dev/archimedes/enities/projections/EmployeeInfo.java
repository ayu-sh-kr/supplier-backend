package dev.archimedes.enities.projections;

import java.sql.Timestamp;

/**
 * Projection for {@link dev.archimedes.enities.Employee}
 */
public interface EmployeeInfo {
    int getId();

    String getName();

    String getEmail();

    Timestamp getTimestamp();
}