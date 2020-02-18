package ru.ok.journal.service;

import ru.ok.journal.dto.PostDto;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

import java.util.List;

public interface IPostService {
    Post add(User user, PostDto postDto);
    List<Post> getAll();

}
