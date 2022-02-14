package ru.barysheva.user.servlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.barysheva.user.dto.UserForm;
import ru.barysheva.user.dto.UserForm.UserFormBuilder;
import ru.barysheva.user.model.User;
import ru.barysheva.user.repository.UsersRepository;
import ru.barysheva.user.repository.UsersRepositoryImpl;
import ru.barysheva.user.services.UserService;
import ru.barysheva.user.services.UserServiceImpl;


@WebServlet("/")
public class CreateUserServlet extends HttpServlet { // http://localhost:8081/my_project_1_0_SNAPSHOT_war/create

    private HikariDataSource dataSource;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {


        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("28679854");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.1.107:5432/userdb");
        hikariConfig.setMaximumPoolSize(20);
        System.out.println("Database connection inits success.");
        this.dataSource = new HikariDataSource(hikariConfig);

        System.out.println("Database has been connected.");
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        this.userService = new UserServiceImpl(usersRepository);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        try {
            switch (action) {
                case "/create":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insert(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userService.getAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Post method was called in " + this.getClass().getSimpleName());

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");

        UserFormBuilder builder = UserForm.builder();
        if (firstName != null) {
            builder.firstName(firstName);
        }
        if (lastName != null) {
            builder.lastName(lastName);
        }
        if (age != null) {
            int ageValue = Integer.parseInt(age);
            builder.age(ageValue);
        }

        UserForm userForm = builder.build();
        userService.createUser(userForm);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Long userId = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Integer age = Integer.parseInt(request.getParameter("age"));

//        UserFormBuilder builder = UserForm.builder();
//        if (firstName != null) {
//            builder.firstName(firstName);
//        }
//        if (lastName != null) {
//            builder.lastName(lastName);
//        }
//        if (age != null) {
//            int ageValue = Integer.parseInt(age);
//            builder.age(ageValue);
//        }
//        UserForm userForm = builder.build();
        User newUser = new User(userId, firstName, lastName, age, false);
        userService.updateUser(newUser);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.deleteById(id);
        response.sendRedirect("list");
    }

    @Override
    public void destroy() {
        System.out.println("Database connection in " + this.getClass().getSimpleName()
                + " has been destroyed success.");
        dataSource.close();
    }
}