package ru.ok.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    Page<Comment> findAllByPost(Post post, Pageable pageable);
    List<Comment> findAllByPost(Post post);
    List<Comment> findAllByAuthor(User user);
}