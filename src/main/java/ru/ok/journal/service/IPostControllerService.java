package ru.ok.journal.service;

import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.GetPostsDto;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.model.Post;

import java.util.List;

public interface IPostControllerService {
    Post createPost(NewPostDto newPostDto);
    List<GetPostsDto> getPostsPage();
    List<CommentDto> getCommentsPage(Long id);
}
