package ru.ok.journal.service;

import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.dto.ShowPostsDto;
import ru.ok.journal.model.Post;

import java.util.List;

public interface IPostControllerService {
    Post createPost(NewPostDto newPostDto);
    List<ShowPostsDto> showPostsPage();
    List<CommentDto> showCommentsPage(Long id);
}
