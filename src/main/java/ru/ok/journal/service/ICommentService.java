package ru.ok.journal.service;

import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

import java.util.List;

public interface ICommentService {
    Comment add(User user, Post post, CommentDto commentDto);
    List<Comment> getByPost(Post post);
}
