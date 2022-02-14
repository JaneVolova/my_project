package ru.barysheva.user.services;

import ru.barysheva.user.dto.UserForm;
import ru.barysheva.user.model.User;

import java.util.List;

public interface UserService {

    void createUser(UserForm form);

    void updateUser(User user);

    void deleteById(Long userId);

    User findById(Long userId);

    List<User> getAll();

    Boolean isDeleteUser(Long userId);
}
