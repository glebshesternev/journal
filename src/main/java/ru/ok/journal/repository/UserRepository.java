package ru.ok.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ok.journal.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByLogin(String login);
}