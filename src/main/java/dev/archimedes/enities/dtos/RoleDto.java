package dev.archimedes.enities.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link dev.archimedes.enities.Role}
 */
@Value
public class RoleDto implements Serializable {
    int id;
    String name;
}