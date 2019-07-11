package ru.job4j.servlets.http.servlet;

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
public class UserCreateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    private Dispatcher dispatcher = new Dispatcher();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html><html lang = \" en \">"
                + "<head>"
                + "<meta  charset= \" UTF-8\">"
                + "<title> My page </title>"
                + "</head>"
                + "<body>"
                + "<p>New User</p></br>"
                + "<form action = '")
                .append(req.getContextPath())
                .append("/userCreate' method = 'post'>")
                .append("Name: <input type = 'text' value = 'name' name = 'name'/></br>")
                .append("Login: <input type = 'text' value = 'login' name = 'login'/></br>")
                .append("Email: <input type = 'text' value = 'email' name = 'email'/></br>")
                .append("<input type = 'submit' value = \"Save\">")
                .append("</form>")
                .append("</body>")
                .append("</html>");
        writer.flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.get("add", validate, req);
            resp.sendRedirect(String.format("%s/userList", req.getContextPath()));
    }
}
