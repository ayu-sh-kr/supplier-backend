package dev.archimedes.repositories;

import dev.archimedes.enities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select (count(r) > 0) from roles r where r.name = ?1")
    boolean existsByName(String name);

    @Query("select r from roles r where r.name = ?1")
    Role findByName(String name);


}
