package ru.job4j.servlets.http.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.http.html.JsonServlet;
import ru.job4j.servlets.http.servlet.models.City;
import ru.job4j.servlets.http.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CityList extends HttpServlet {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(JsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String aux;
        while ((aux = reader.readLine()) != null) {
            json.append(aux);
        }
        LOG.info("json: " + json.toString());
        if (json.toString().contains("name")) {
            City city = mapper.readValue(json.toString(), City.class);
            LOG.info("City id: " + city.getId());
            LOG.info("City name: " + city.getName());
            DbStore.getInstance().addCity(city);
        } else {
            String id = json.toString().replaceAll("\\D+", "");
            resp.setContentType("text/json");
            Writer wr = resp.getWriter();
            LOG.info("Get id: " + id);
            mapper.writeValue(wr, DbStore.getInstance().getCities(id));
            LOG.info("Sent Json object back to client.");
        }
    }

}
