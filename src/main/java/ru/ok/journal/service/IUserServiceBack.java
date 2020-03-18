package ru.ok.journal.service;

import ru.ok.journal.model.User;

import java.util.List;

public interface IUserServiceBack {
    public List<User> findAll();
}