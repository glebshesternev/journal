package ru.ok.journal.service;

import org.springframework.stereotype.Service;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.GetPostsDto;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostControllerService implements IPostControllerService {

    private PostRepository postRepository;
    private IPostService postService;
    private IUserService userService;
    private IDtoService dtoService;

    public PostControllerService(PostRepository postRepository, IPostService postService, IUserService userService,
                                 IDtoService dtoService){
        this.postRepository = postRepository;
        this.postService = postService;
        this.userService = userService;
        this.dtoService = dtoService;
    }

    @Override
    public Post createPost(NewPostDto newPostDto) {
        Post post = dtoService.convertToEntity(newPostDto);
        return postService.add(userService.currentUser(), post);
    }

    @Override
    public List<GetPostsDto> getPostsPage() {
        List<Post> posts = postService.getPostsPage(0, 8, "asc", "id");
        return posts.stream().map(dtoService::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsPage(Long id) {
        List<Comment> comments = postService
                .getCommentsPageByPost(postRepository.getOne(id),0, 10, "asc", "id");
        return comments.stream().map(dtoService::convertToDto).collect(Collectors.toList());
    }
}
