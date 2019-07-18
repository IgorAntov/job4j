package ru.job4j.servlets.http.servlet;

import ru.job4j.servlets.http.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SigninController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/loginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = DbStore.getInstance().getRole(name, password);
        if (role != null) {
            HttpSession session = req.getSession();
            session.setAttribute("role", role);
    //        resp.sendRedirect(String.format("%s/", req.getContextPath()));
            req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req, resp);
        } else {
            req.setAttribute("error", "Credential invalid.");
            doGet(req, resp);
        }
    }
}
