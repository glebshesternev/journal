package ru.ok.journal.service;

import org.modelmapper.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.dto.ShowPostsDto;
import ru.ok.journal.model.Post;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainControllerService implements IMainControllerService {

    private IPostService postService;
    private ICommentService commentService;
    private IUserService userService;

    private final ModelMapper modelMapper;

    public MainControllerService(IPostService postService, ICommentService commentService, IUserService userService, ModelMapper modelMapper){
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    @Override
    public String createPost(NewPostDto newPostDto, Model model) {
        postService.add(userService.currentUser(), newPostDto);
        return "newPost";
    }

    private ShowPostsDto convertToDto(Post post) {
        return modelMapper.map(post, ShowPostsDto.class);
    }

    @Override
    public ModelAndView showAllPosts() {
        List<Post> posts = postService.getPostsList(0, 8, "desc", "name");
        List<ShowPostsDto> dtoPosts = posts.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ModelAndView("posts", "allPosts", dtoPosts);
    }

    @Override
    public ModelAndView showCurrentPost(Post post) {
        return new ModelAndView("result", "post", post);
    }
}
