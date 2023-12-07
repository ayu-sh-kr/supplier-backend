package dev.archimedes.enities.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link dev.archimedes.enities.supplier.Branch}
 */
@Value
public class BranchDto implements Serializable {
    int branchId;
    String name;
    String email;
    String address;
}