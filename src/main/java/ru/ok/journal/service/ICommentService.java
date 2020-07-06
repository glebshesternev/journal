package ru.ok.journal.service;

import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

public interface ICommentService {
    Comment add(User user, Post post, CommentDto commentDto);
    void delete(Long commentId);
}
