package ru.ok.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAuthor(User user);
}
