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
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    private final Map<String, BiFunction<Validate, HttpServletRequest, Boolean>> dispatch = new HashMap<>();

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
        StringBuilder sb = new StringBuilder();
        doUserListHText(req, sb);
        doUpdateUserHText(req, sb);
        writer.append("<!DOCTYPE html><html lang = \" en \"><head><meta  charset= \" UTF-8\">"
                + "<title> User list.</title></head>"
                + "<p> User List</p>"
                + "<body>")
                .append(sb.toString())
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
        dispatch.get(req.getParameter("task")).apply(validate, req);
        resp.sendRedirect(String.format("%s/userList", req.getContextPath()));
    }

    /**
     * User list in HText
     * @param req param req
     * @param sb string sb for respond
     */
    private void doUserListHText(HttpServletRequest req, StringBuilder sb) {
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
                    .append("<input type=\"hidden\" name=\"task\" value=\"delete\"> ")
                    .append("<input type = 'submit' value = \"delete\"></form></td><td> ")
                    .append("<form action='").append(req.getContextPath())
                    .append("/userList' method=\"get\"> ")
                    .append("<input type=\"hidden\" name=\"id\" value=")
                    .append(user.getId()).append("> ")
                    .append("<input type=\"hidden\" name=\"task\" value=\"update\"> ")
                    .append("<input type = 'submit' value = \"update\"><")
                    .append("/form></td></tr>");
        }
        sb.append("</table>");
    }

    /**
     * Update/Add form in HText
     * @param req param req
     * @param sb string for respond
     */
    private void doUpdateUserHText(HttpServletRequest req, StringBuilder sb) {
        if (!req.getParameterMap().isEmpty() && req.getParameter("task").equals("update")) {
            User u = validate.findById(req.getParameter("id"));
            sb.append("<form action = '").append(req.getContextPath())
                    .append("/userList' method = 'post'>")
                    .append("Name: <input type = 'text' value = '")
                    .append(u.getName())
                    .append("' name = 'name'/></br>")
                    .append("Login: <input type = 'text' value = '")
                    .append(u.getLogin())
                    .append("' name = 'login'/></br>")
                    .append("Email: <input type = 'text' value = '")
                    .append(u.getEmail())
                    .append("' name = 'email'/></br>")
                    .append("<input type=\"hidden\" name=\"id\" value=")
                    .append(u.getId())
                    .append("> ")
                    .append("<input type=\"hidden\" name=\"task\" value=\"update\"> ")
                    .append("<input type = 'submit' value = \" Save\">")
                    .append("</form>");
        } else {
            sb.append("<form action = '")
                    .append(req.getContextPath())
                    .append("/userList' method = 'post'>")
                    .append("Name: <input type = 'text' value = 'name1' name = 'name'/></br>")
                    .append("Login: <input type = 'text' value = 'login1' name = 'login'/></br>")
                    .append("Email: <input type = 'text' value = 'email1' name = 'email'/></br>")
                    .append("<input type=\"hidden\" name=\"task\" value=\"add\"> ")
                    .append("<input type = 'submit' value = \"Add User\">")
                    .append("</form>");
        }
    }

    /**
     * Loader task to Map
     * @param task param task
     * @param handle param hanle
     */
    public void load(String task, BiFunction<Validate, HttpServletRequest, Boolean> handle) {
        this.dispatch.put(task, handle);
    }

    /**
     * Task initialisation
     */
    public void init() {
        this.load("add", toAdd());
        this.load("delete", toDelete());
        this.load("update", toUpdate());
    }

    /**
     * Add user task
     * @return task
     */
    public BiFunction<Validate, HttpServletRequest, Boolean> toAdd() {
        return (validate, req) ->
                validate.add(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
    }

    /**
     * Delete user task
     * @return task
     */
    public BiFunction<Validate, HttpServletRequest, Boolean> toDelete() {
        return (validate, req) ->
                validate.delete(req.getParameter("id"));
    }

    /**
     * Update user task
     * @return
     */
    public BiFunction<Validate, HttpServletRequest, Boolean> toUpdate() {
        return (validate, req) -> {
            User u = validate.findById(req.getParameter("id"));
            u.setName(req.getParameter("name"));
            u.setLogin(req.getParameter("login"));
            u.setEmail(req.getParameter("email"));
            return validate.update(u);
        };
    }
}

