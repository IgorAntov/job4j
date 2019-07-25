package ru.job4j.servlets.http.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.http.html.JsonServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class PlaceControl extends HttpServlet {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(JsonServlet.class);
    private final HallValidate validate = ValidationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("PlaceControlServlet json: in doGet");
        ObjectMapper mapper = new ObjectMapper();
        Writer wr = resp.getWriter();
        HttpSession session = req.getSession();
        Place place = (Place) session.getAttribute("place");
        if (place != null) {
            mapper.writeValue(wr, place);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("place", new Place());
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String aux;
        while ((aux = reader.readLine()) != null) {
            json.append(aux);
        }
        LOG.info("Json String from client: " + json.toString());
        if (json.toString().contains("place")) {
            Place place = mapper.readValue(json.toString(), Place.class);
            LOG.info("Object Place.place in json: " + place.getPlace());
            LOG.info("Object Place.status in json: " + place.getStatus());
            LOG.info("Object Place.desc in json: " + place.getDesc());
            session.setAttribute("place", place);
        }
        reader.close();

    }
}
