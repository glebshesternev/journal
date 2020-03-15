package ru.ok.journal.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String login;
    private Boolean enabled;
}