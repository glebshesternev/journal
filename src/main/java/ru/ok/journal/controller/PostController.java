package ru.ok.journal.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.model.Post;
import ru.ok.journal.service.IPostControllerService;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {
    private IPostControllerService postControllerService;

    public PostController(IPostControllerService postControllerService) {
        this.postControllerService = postControllerService;
    }

//    @PostMapping("/newPost")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Post createPost(@RequestBody NewPostDto newPostDto) {
//        return postControllerService.createPost(newPostDto);
//    }

    @GetMapping("posts/{id}")
    @ResponseBody
    public List<CommentDto> showPostComments(@PathVariable("id") Long id) {
        return postControllerService.getCommentsPage(id);
    }

//    @GetMapping(value = "posts", params = "model")
//    @ResponseBody
//    public List<ShowPostsDto> showPosts(Model model) {
//        List<ShowPostsDto> showPostsDto = postControllerService.showPostsPage();
//        model.addAttribute("postsPage", showPostsDto);
//        return showPostsDto;
//    }

    @MessageMapping("/createPost")
    @SendTo("/topic/activity")
    public Post createPost(NewPostDto newPostDto) {
        return postControllerService.createPost(newPostDto);
    }
}
