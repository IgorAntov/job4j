package ru.job4j.servlets.http.servlet;

import ru.job4j.servlets.http.validate.Validate;
import ru.job4j.servlets.http.validate.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UserController extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    private Dispatcher dispatcher = new Dispatcher();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("task") != null) {
            dispatcher.getJSTL(request.getParameter("task"), validate, request, response);
        }
        request.setAttribute("users", validate.findAll());
        request.getRequestDispatcher("/WEB-INF/views/indexJSTL.jsp").forward(request, response);
    }
}
