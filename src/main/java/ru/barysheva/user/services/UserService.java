package ru.barysheva.user.services;

import ru.barysheva.user.dto.UserForm;

import java.util.List;

public interface UserService {

    void createUser(UserForm form);

    void updateUser(UserForm form);

    void deleteById(Long userId);

    List<UserForm> getAll();

    Boolean isDeleteUser(Long userId);
}
