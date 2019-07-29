package ru.job4j.servlets.http.html;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class JsonServlet extends HttpServlet {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(JsonServlet.class);
    private final ConcurrentHashMap<String, JsonUser> store = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Call GET request");
        ObjectMapper mapper = new ObjectMapper();
        Writer wr = resp.getWriter();
        mapper.writeValue(wr, store.values());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doPost run");
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String aux;
        while ((aux = reader.readLine()) != null) {
            json.append(aux);
        }
        LOG.info(String.format("Got json object: %s", json));
        JsonUser user = mapper.readValue(json.toString(), JsonUser.class);
        LOG.info(store.size());
        store.put(String.valueOf(store.size() + 1), user);
        LOG.info(String.format("Last user in store: %s", store.get(String.valueOf(store.size())).getName()));
        resp.setContentType("text/json");
        Writer wr = resp.getWriter();
        mapper.writeValue(wr, user);
        LOG.info("Sent Json object back to client.");
    }
}



