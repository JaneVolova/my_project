package ru.barysheva.user.servlet;

import ru.barysheva.user.model.User;
import ru.barysheva.user.repository.UsersRepository;
import ru.barysheva.user.repository.UsersRepositoryImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {

    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = usersRepository.findAll();
        request.setAttribute("userList", userList);
        getServletContext().getRequestDispatcher("/jsp/createUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        DataSource dataSource = (DataSource) servletContext.getAttribute("dataSource");
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
    }
}
