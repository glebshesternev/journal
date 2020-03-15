package ru.ok.journal.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;
import ru.ok.journal.repository.CommentRepository;
import ru.ok.journal.repository.PostRepository;

import java.util.List;

@Service
public class PostService implements IPostService {
    final private PostRepository postRepository;
    final private CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Post add(User user, Post post) {
        Post postCreated = new Post();
        postCreated.setAuthor(user);
        postCreated.setName(post.getName());
        postCreated.setData(post.getData());
        postRepository.save(postCreated);
        return postCreated;
    }

    @Override
    public List<Post> getPostsPage(int page, int size, String sortDir, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return postRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Comment> getCommentsPageByPost(Post post, int page, int size, String sortDir, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        //return commentRepository.findAllByPost(post, pageRequest).getContent();
        return commentRepository.findAllByPost(post);
    }

    @Override
    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
