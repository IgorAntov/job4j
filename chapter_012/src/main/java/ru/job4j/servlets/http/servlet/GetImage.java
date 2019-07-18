package ru.job4j.servlets.http.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "GetImage")
/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class GetImage extends HttpServlet {
    private final static String SAVE_DIR = "uploadFiles";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        String fileName = request.getParameter("fileName");
        File file = new File(savePath, fileName);
        if (file.exists()) {
            ServletContext sc = getServletContext();
            InputStream is = sc.getResourceAsStream(fileName);

            BufferedImage bi = ImageIO.read(is);
            OutputStream os = response.getOutputStream();
            ImageIO.write(bi, "png", os);
        }
    }
}


