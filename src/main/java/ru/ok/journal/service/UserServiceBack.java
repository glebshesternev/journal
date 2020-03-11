package ru.ok.journal.service;

import org.springframework.stereotype.Service;
import ru.ok.journal.model.User;
import ru.ok.journal.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceBack implements IUserServiceBack {
    private UserRepository userRepository;

    public UserServiceBack(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
