package ru.ok.journal.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.GetPostsDto;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.dto.UserDto;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;

@Service
public class DtoService implements IDtoService{
    private ModelMapper modelMapper;

    public DtoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GetPostsDto convertToDto(Post post) { return modelMapper.map(post, GetPostsDto.class); }
    public CommentDto convertToDto(Comment comment) { return modelMapper.map(comment, CommentDto.class); }
    public UserDto convertToDto(User user) { return modelMapper.map(user, UserDto.class); }
    public Post convertToEntity(NewPostDto postDto) { return modelMapper.map(postDto, Post.class); }
}
