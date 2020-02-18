package ru.ok.journal.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ru.ok.journal.dto.PostDto;
import ru.ok.journal.model.Post;


public interface IMainControllerService {
    String createPost(PostDto postDto, Model model);
    ModelAndView showAllPosts();
    ModelAndView showCurrentPost(Post post);
}
