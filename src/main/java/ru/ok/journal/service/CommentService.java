package ru.ok.journal.service;

import org.springframework.stereotype.Service;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;
import ru.ok.journal.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment add(User user, Post post, CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setData(commentDto.getData());
        comment.setPost(post);
        comment.setAuthor(user);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<Comment> getByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }
}
