package ru.job4j.servlets.http.servlet;

import ru.job4j.servlets.http.User;
import ru.job4j.servlets.http.validate.Validate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
class Dispatcher {
    private final Map<String, BiFunction<Validate, HttpServletRequest, Boolean>> dispatch = new HashMap<>();

    Dispatcher() {
        this.init();
    }

    void get(String task, Validate validate, HttpServletRequest req) {
        dispatch.get(task).apply(validate, req);
    }

    /**
     * Loader task to Map
     * @param task param task
     * @param handle param hanle
     */
    private void load(String task, BiFunction<Validate, HttpServletRequest, Boolean> handle) {
        this.dispatch.put(task, handle);
    }

    /**
     * Task initialisation
     */
    private void init() {
        this.load("add", toAdd());
        this.load("delete", toDelete());
        this.load("update", toUpdate());
    }

    /**
     * Add user task
     * @return task
     */
    private BiFunction<Validate, HttpServletRequest, Boolean> toAdd() {
        return (validate, req) ->
                validate.add(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
    }

    /**
     * Delete user task
     * @return task
     */
    private BiFunction<Validate, HttpServletRequest, Boolean> toDelete() {
        return (validate, req) ->
                validate.delete(req.getParameter("id"));
    }

    /**
     * Update user task
     * @return
     */
    private BiFunction<Validate, HttpServletRequest, Boolean> toUpdate() {
        return (validate, req) -> {
            User u = validate.findById(req.getParameter("id"));
            u.setName(req.getParameter("name"));
            u.setLogin(req.getParameter("login"));
            u.setEmail(req.getParameter("email"));
            return validate.update(u);
        };
    }
}
