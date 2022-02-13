package ru.barysheva.user.servlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.barysheva.user.dto.UserForm;
import ru.barysheva.user.model.User;
import ru.barysheva.user.repository.UsersRepository;
import ru.barysheva.user.repository.UsersRepositoryImpl;
import ru.barysheva.user.services.UserService;
import ru.barysheva.user.services.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Properties;


@WebServlet
public class CreateUserServlet extends HttpServlet {

    private HikariDataSource dataSource;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {

//        Properties properties = new Properties();
//        try {
//            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e);
//        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("28679854");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.1.107:5432/userdb");
        hikariConfig.setMaximumPoolSize(20);

        this.dataSource = new HikariDataSource(hikariConfig);

        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        this.userService = new UserServiceImpl((usersRepository));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserForm> userFormList = userService.getAll();
        request.setAttribute("userList", userFormList);
        request.getRequestDispatcher("jsp/createUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserForm form = UserForm.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .age(Integer.valueOf(req.getParameter("age")))
                .build();

        userService.createUser(form);
//        resp.sendRedirect("/create");
    }


    @Override
    public void destroy() {
        dataSource.close();
    }
}
