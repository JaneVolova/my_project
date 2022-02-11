package ru.barysheva.user.services;

import ru.barysheva.user.dto.UserForm;
import ru.barysheva.user.model.User;
import ru.barysheva.user.repository.UsersRepository;

public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void createUser(UserForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .age(form.getAge())
                .isDeleted(false)
                .build();

        usersRepository.save(user);
    }

    @Override
    public boolean isDeleteUser(Long userId) {
        return usersRepository.findById(userId).isDeleted();
    }
}
