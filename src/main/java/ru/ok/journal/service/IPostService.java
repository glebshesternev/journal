package ru.ok.journal.service;

import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

import java.util.List;

public interface IPostService {
    Post add(User user, Post post);
    List<Post>
    getPostsPage(int page, int size, String sortDir, String sort);
    List<Comment> getCommentsPageByPost(Post post, int page, int size, String sortDir, String sort);
    List<Post> getAllPosts();
    List<Comment> getCommentsByPost(Post post);
}
