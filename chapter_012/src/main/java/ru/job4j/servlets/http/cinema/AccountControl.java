package ru.job4j.servlets.http.cinema;

import ru.job4j.servlets.http.html.JsonServlet;

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
public class AccountControl extends HttpServlet {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(JsonServlet.class);
    private final HallValidate validate = ValidationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("name") != null) {
            Account account = new Account();
            account.setName(req.getParameter("name"));
            account.setPhone(req.getParameter("phone"));
            LOG.info("AccountControlServlet doPost.");
            LOG.info("Account.name: " + account.getName());
            LOG.info("Account.phone: " + account.getPhone());
            session.setAttribute("account", account);
            validate.addPlace(account, (Place) session.getAttribute("place"));
            resp.sendRedirect("/chapter_012/index.html");
        }
    }
}
