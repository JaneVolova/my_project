package ru.barysheva.user.services;

import ru.barysheva.user.dto.UserForm;

public interface UserService {
    void createUser(UserForm form);
    boolean isDeleteUser(Long userId);
}
