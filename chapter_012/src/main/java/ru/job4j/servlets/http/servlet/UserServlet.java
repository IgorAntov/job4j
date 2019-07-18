package ru.job4j.servlets.http.servlet;

import ru.job4j.servlets.http.servlet.models.User;
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
public class UserServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    private Dispatcher dispatcher = new Dispatcher();

    /**
     * Do Get
     * @param req Get param req
     * @param resp Get param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html><html lang = \" en \"><head><meta  charset= \" UTF-8\">"
                + "<title> User list.</title></head>"
                + "<p> User List</p>"
                + "<body>")
                .append(doUserListHText(req))
                .append("</body></html>");
        writer.flush();
    }

    /**
     * Do Post
     * @param req Post param req
     * @param resp Post param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.get("delete", validate, req);
        resp.sendRedirect(String.format("%s/userList", req.getContextPath()));
    }

    /**
     * User list in HText
     * @param req param req
     * @param sb string sb for respond
     */
    private StringBuilder doUserListHText(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table cellspacing=\"2\" border=\"1\" cellpadding=\"5\">");
        for (User user : this.validate.findAll()) {
            sb.append("<tr><td>")
                    .append(user.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(user.getEmail()).append("</td><td>")
                    .append("<form action='")
                    .append(req.getContextPath())
                    .append("/userList' method=\"post\"> ")
                    .append("<input type=\"hidden\" name=\"id\" value=")
                    .append(user.getId()).append("> ")
                    .append("<input type = 'submit' value = \"delete\"></form></td><td> ")
                    .append("<form action='").append(req.getContextPath())
                    .append("/userUpdate' method=\"get\"> ")
                    .append("<input type=\"hidden\" name=\"id\" value=")
                    .append(user.getId()).append("> ")
                    .append("<input type = 'submit' value = \"update\"><")
                    .append("/form></td></tr>");
        }
        sb.append("</table>")
                .append("<form action = '")
                .append(req.getContextPath())
                .append("/userCreate' method = 'get'>")
                .append("<input type = 'submit' value = \"Add User\">")
                .append("</form>");
        return sb;
    }
}

