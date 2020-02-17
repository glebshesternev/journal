package ru.ok.journal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.ok.journal.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}