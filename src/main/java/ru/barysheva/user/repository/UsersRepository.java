package ru.barysheva.user.repository;

import ru.barysheva.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {

    void save(User user);

    void updateUser(User user);

    void deleteById(Long userId);

    User findById(Long userId);

    List<User> findAll();
}
