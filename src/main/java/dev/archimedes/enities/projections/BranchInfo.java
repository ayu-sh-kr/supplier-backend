package dev.archimedes.enities.projections;

import dev.archimedes.enities.dtos.SupplierInfo;

/**
 * Projection for {@link dev.archimedes.enities.supplier.Branch}
 */
public interface BranchInfo {
    int getBranchId();

    String getName();

    SupplierInfo getSupplier();

    String getEmail();

    String getAddress();
}