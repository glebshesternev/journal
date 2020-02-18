package ru.ok.journal.service;

import org.springframework.stereotype.Service;
import ru.ok.journal.dto.PostDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;
import ru.ok.journal.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    final private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post add(User user, PostDto postDto) {
        Post post = new Post();
        post.setAuthor(user);
        post.setName(postDto.getName());
        post.setData(postDto.getData());
        post.setComments(new ArrayList<>());
        postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }
}
