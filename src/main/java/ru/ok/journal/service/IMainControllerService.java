package ru.ok.journal.service;

import org.springframework.web.servlet.ModelAndView;


public interface IMainControllerService {
    ModelAndView showAllPosts();
    ModelAndView showCurrentPost();
}
