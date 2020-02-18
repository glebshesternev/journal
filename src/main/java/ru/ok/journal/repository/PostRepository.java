package ru.ok.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthor(User user);
}
