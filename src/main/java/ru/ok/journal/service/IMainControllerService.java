package ru.ok.journal.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.dto.ShowPostsDto;
import ru.ok.journal.model.Post;

import java.util.List;


public interface IMainControllerService {
    String createPost(NewPostDto newPostDto, Model model);
    ModelAndView showAllPosts();
    ModelAndView showCurrentPost(Post post);
}
