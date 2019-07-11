package ru.job4j.servlets.http.servlet;

import ru.job4j.servlets.http.User;
import ru.job4j.servlets.http.validate.Validate;
import ru.job4j.servlets.http.validate.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UserUpdateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    private Dispatcher dispatcher = new Dispatcher();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User user = this.validate.findById(req.getParameter("id"));
        writer.append("<!DOCTYPE html><html lang = \" en \"><head><meta  charset= \" UTF-8\">"
                + "<title> My page </title>"
                + "</head>"
                + "<body> <p> User update</p></br>"
                + "<form action = '")
                .append(req.getContextPath())
                .append("/userUpdate' method = 'post'>")
                .append("<input type=\"hidden\" name=\"id\" value=")
                .append(user.getId())
                .append("> Name: <input type = 'text' name = 'name' value = '")
                .append(user.getName())
                .append("' /></br>Login: <input type = 'text' name = 'login'  value = '")
                .append(user.getLogin())
                .append("' /></br>Email: <input type = 'text' name = 'email'  value = '")
                .append(user.getEmail())
                .append("' /></br><input type = 'submit' value = \"update\">"
                        + "</form>"
                        + "</body></html>");
        writer.flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.get("update", validate, req);
        resp.sendRedirect(String.format("%s/userList", req.getContextPath()));
    }
}
