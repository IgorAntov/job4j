package ru.job4j.servlets.http.servlet;

import ru.job4j.servlets.http.User;
import ru.job4j.servlets.http.validate.Validate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private final Map<String, JSTLFunction<Validate, HttpServletRequest, HttpServletResponse, Boolean>> dispatchJSTL = new HashMap<>();

    @FunctionalInterface
    public interface JSTLFunction<T, U, E, R> {
        R apply(T t, U u, E e) throws ServletException, IOException;
    }

    Dispatcher() {
        this.init();
    }

    void get(String task, Validate validate, HttpServletRequest req) {
        dispatch.get(task).apply(validate, req);
    }

    void getJSTL(String task, Validate validate, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchJSTL.get(task).apply(validate, req, resp);
    }

    /**
     * Loader task to Map
     * @param task param task
     * @param handle param hanle
     */
    private void load(String task, BiFunction<Validate, HttpServletRequest, Boolean> handle) {
        this.dispatch.put(task, handle);
    }

    private void loadJSTL(String task, JSTLFunction<Validate, HttpServletRequest, HttpServletResponse, Boolean> handle) {
        this.dispatchJSTL.put(task, handle);
    }
    /**
     * Task initialisation
     */
    private void init() {
        this.load("add", toAdd());
        this.load("delete", toDelete());
        this.load("update", toUpdate());
        this.loadJSTL("add", toAddJSTL());
        this.loadJSTL("delete", toDeleteJSTL());
        this.loadJSTL("update", toUpdateJSTL());
    }

    /**
     * Add user task
     * @return task
     */
    private BiFunction<Validate, HttpServletRequest, Boolean> toAdd() {
        return (validate, req) ->
                validate.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
    }

    /**
     * Add JSTL user task
     * @return task
     */
    private JSTLFunction<Validate, HttpServletRequest, HttpServletResponse, Boolean> toAddJSTL() {
        return (validate, request, response) -> {
            boolean result = false;
            if (request.getParameter("task") != null && request.getParameter("task").equals("add")) {
                if (request.getParameter("name") != null) {
                    result = validate.add(new User(request.getParameter("name"), request.getParameter("login"), request.getParameter("email")));
                    forwardToUserList(validate, request, response);
                }
                request.getRequestDispatcher("/WEB-INF/views/createJSTL.jsp").forward(request, response);
            }
            return result;
        };
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
     * Delete JSTL user task
     * @return task
     */
    private JSTLFunction<Validate, HttpServletRequest, HttpServletResponse, Boolean> toDeleteJSTL() {
        return (validate, request, response) -> {
            boolean result = false;
            if (request.getParameter("task") != null && request.getParameter("task").equals("delete")) {
                if (request.getParameter("id") != null) {
                    result = validate.delete(request.getParameter("id"));
                    forwardToUserList(validate, request, response);
                }
            }
            return result;
        };
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

    /**
     * Update JSTL user task
     * @return
     */
    private JSTLFunction<Validate, HttpServletRequest, HttpServletResponse, Boolean> toUpdateJSTL() {
        return (validate, request, response) -> {
            boolean result = false;
            if (request.getParameter("task") != null && request.getParameter("task").equals("update")) {
                if (request.getParameter("name") != null) {
                    User u = validate.findById(request.getParameter("id"));
                    u.setName(request.getParameter("name"));
                    u.setLogin(request.getParameter("login"));
                    u.setEmail(request.getParameter("email"));
                    result =  validate.update(u);
                    forwardToUserList(validate, request, response);
                }
                request.setAttribute("user", validate.findById(request.getParameter("id")));
                request.getRequestDispatcher("/WEB-INF/views/updateJSTL.jsp").forward(request, response);
            }
            return result;
        };
    }

    private void forwardToUserList(Validate validate, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", validate.findAll());
        request.getRequestDispatcher("/WEB-INF/views/indexJSTL.jsp").forward(request, response);
    }
}
