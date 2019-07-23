package ru.job4j.servlets.http.servlet;

import ru.job4j.servlets.http.store.DbStore;
import ru.job4j.servlets.http.validate.Validate;
import ru.job4j.servlets.http.validate.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
@WebServlet("/UploadServlet, /Download")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UserController extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    private Dispatcher dispatcher = new Dispatcher();
    private final static String SAVE_DIR = "uploadFiles";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("countries", DbStore.getInstance().getCountries());
        if (request.getParameter("task") != null) {
            dispatcher.getJSTL(request.getParameter("task"), validate, request, response);
        }
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("multipart/form-data")) {
            dispatcher.getJSTL("upload", validate, request, response);
        }
        if (contentType != null && contentType.startsWith("image/png")) {
            request.getRequestDispatcher("/image").forward(request, response);
        }
        if (request.getParameter("fileName") != null) {
            request.getRequestDispatcher("/download").forward(request, response);
        }
        request.setAttribute("listFiles", getFileList(request));
        request.setAttribute("users", validate.findAll());
        if(!response.isCommitted()) {
            request.getRequestDispatcher("/WEB-INF/views/indexJSTL.jsp").forward(request, response);
        }
    }

    private File[] getFileList(HttpServletRequest request) {
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        return new File(savePath).listFiles();
    }
}
