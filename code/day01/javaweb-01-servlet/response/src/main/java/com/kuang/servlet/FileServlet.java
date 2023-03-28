package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String realPath = "C:\\Users\\asus\\IdeaProjects\\javaweb-01-servlet\\response\\target\\response-1.0-SNAPSHOT\\WEB-INF\\classes\\liyixin.jpg";
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
        resp.setHeader("Content-Disposition","attachment;filename="+fileName);
        FileInputStream fis = new FileInputStream(realPath);
        int len = 0;
        byte[] bytes = new byte[1024];
        ServletOutputStream outputStream = resp.getOutputStream();
        int read = fis.read(bytes);
        while (read!=-1){
            outputStream.write(bytes,0,len);
            read = fis.read(bytes);
        }
        fis.close();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
