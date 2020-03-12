package ru.ok.journal.service;

import org.springframework.stereotype.Service;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;
import ru.ok.journal.repository.CommentRepository;

import javax.persistence.*;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    private CommentRepository commentRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment add(User user, Post post, CommentDto commentDto) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ru.ok.journal_persistence");
//        entityManager = emf.createEntityManager();
//        entityManager.getTransaction().begin();
        Comment comment = new Comment();
        comment.setData(commentDto.getData());
        comment.setPost(post);
        comment.setAuthor(user);
        commentRepository.save(comment);
//        entityManager.persist(comment);
//        entityManager.getTransaction().commit();
        return comment;
    }

    @Override
    public List<Comment> getByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }
}
