package ru.ok.journal.service;

import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

import java.util.List;

public interface IPostService {
    Post add(User user, NewPostDto newPostDto);
    List<Post> getPostsList(int page, int size, String sortDir, String sort);
    List<Post> getAll();
}
