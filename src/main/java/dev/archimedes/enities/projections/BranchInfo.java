package dev.archimedes.enities.projections;

import dev.archimedes.enities.dtos.SupplierInfo;

/**
 * Projection for {@link dev.archimedes.enities.supplier.Branch}
 */
public interface BranchInfo {
    int getId();

    String getBranch_name();

    String getBranch_email();

    String getAddress();

    SupplierInfo getSupplier();
}