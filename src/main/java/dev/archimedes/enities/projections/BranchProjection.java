package dev.archimedes.enities.projections;

import org.springframework.beans.factory.annotation.Value;

public class BranchProjection {
    public interface BranchProfile{
        int getBranchId();
        String getName();
        String getEmail();
        @Value("#{target.branch.supplier.id}")
        int getSupplierId();

        @Value("#{target.branch.employees.size()}")
        int getEmployeeCount();
    }
}
