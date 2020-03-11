package ru.ok.journal.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.dto.ShowPostsDto;
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

    private final ModelMapper modelMapper;

    public PostControllerService(PostRepository postRepository, IPostService postService, IUserService userService,
                                 ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.postService = postService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    private ShowPostsDto convertToDto(Post post) {
        return modelMapper.map(post, ShowPostsDto.class);
    }
    private CommentDto convertToDto(Comment comment) {return modelMapper.map(comment, CommentDto.class);}

    private Post convertToEntity(NewPostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    @Override
    public Post createPost(NewPostDto newPostDto) {
        Post post = convertToEntity(newPostDto);
        return postService.add(userService.currentUser(), post);
    }

    @Override
    public List<ShowPostsDto> showPostsPage() {
        List<Post> posts = postService.getPostsList(0, 8, "asc", "id");
        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> showCommentsPage(Long id) {
        List<Comment> comments = postService
                .getCommentsByPost(postRepository.getOne(id),0, 10, "asc", "id");
        return comments.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
