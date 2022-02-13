package ru.barysheva.user.services;

import ru.barysheva.user.dto.UserForm;
import ru.barysheva.user.model.User;
import ru.barysheva.user.repository.UsersRepository;

import java.util.List;

import static ru.barysheva.user.dto.UserForm.from;

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

    public Boolean isDeleteUser(Long userId) {
        return usersRepository.findById(userId).isPresent();
    }

    @Override
    public List<UserForm> getAll() {
        return from(usersRepository.findAll());
    }


}

