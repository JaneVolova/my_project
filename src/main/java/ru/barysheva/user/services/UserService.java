package ru.barysheva.user.services;

import ru.barysheva.user.dto.UserForm;
import ru.barysheva.user.model.User;

import java.util.List;

public interface UserService {
    void createUser(UserForm form);
    Boolean isDeleteUser(Long userId);
    List<UserForm> getAll();
}
