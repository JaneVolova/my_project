package ru.barysheva.user.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.barysheva.user.dto.UserForm;
import ru.barysheva.user.repository.UsersRepository;
import ru.barysheva.user.repository.UsersRepositoryImpl;
import ru.barysheva.user.services.UserService;
import ru.barysheva.user.services.UserServiceImpl;

public class Main {

    private static final String DB_USERS = "postgres";
    private static final String DB_PASSWORD = "28679854";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/user";

    public static void main(String[] args) {


        HikariConfig config = new HikariConfig();
        config.setUsername(DB_USERS);
        config.setPassword(DB_PASSWORD);
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(DB_URL);
        config.setMaximumPoolSize(20);

        HikariDataSource dataSource = new HikariDataSource(config);

        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        UserService userService = new UserServiceImpl(usersRepository);


        UserForm form = UserForm.builder()
                .firstName("Lena")
                .lastName("Komarova")
                .age(28)
                .build();
        userService.createUser(form);

        System.out.println(usersRepository.findAll());
    }
}
