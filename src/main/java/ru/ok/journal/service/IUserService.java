package ru.ok.journal.service;

import ru.ok.journal.dto.UserDto;
import ru.ok.journal.model.User;

public interface IUserService {
    User currentUser();
    UserDto getProfile();
}
