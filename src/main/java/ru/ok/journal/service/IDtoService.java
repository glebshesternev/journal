package ru.ok.journal.service;

import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.dto.ShowPostsDto;
import ru.ok.journal.dto.UserDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

public interface IDtoService {  
    ShowPostsDto convertToDto(Post post);

    CommentDto convertToDto(Comment comment);

    UserDto convertToDto(User user);

    Post convertToEntity(NewPostDto postDto);
}
