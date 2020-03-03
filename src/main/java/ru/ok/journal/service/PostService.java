package ru.ok.journal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.ok.journal.dto.NewPostDto;
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
    public Post add(User user, NewPostDto newPostDto) {
        Post post = new Post();
        post.setAuthor(user);
        post.setName(newPostDto.getName());
        post.setData(newPostDto.getData());
        post.setComments(new ArrayList<>());
        postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> getPostsList(int page, int size, String sortDir, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<Post> postsPage = postRepository.findAll(pageRequest);
        return postsPage.getContent();
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }
}
