package com.example.javaeetest;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class FirstServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
//    private String message;
//
//    public void init() {
//        message = "Hello World!";
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
//        response.setContentType("text/html");



        PrintWriter pw = response.getWriter();

        pw.println("<html><body>");
        pw.println("<h1>" + name + " " + surname + "</h1>");
        pw.println("</body></html>");
    }

//    public void destroy() {
//    }
}