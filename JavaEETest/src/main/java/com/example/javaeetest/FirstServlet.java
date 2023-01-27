package com.example.javaeetest;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "firstServlet", value = "/first-servlet")
public class FirstServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        получаем сессию
        HttpSession session = request.getSession();

        String user = (String) session.getAttribute("current_user");

        if (user == null) {
//            response для анонимного пользователя
//            авторизация
//            регистрация
//            session.setAttribute("current_user", ID);
        } else {
//            response для авторизированного пользователя
        }

//        Cart cart = (Cart) session.getAttribute("cart");
//
//        String name = request.getParameter("name");
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//
//        if (cart == null) {
//            cart = new Cart();
//            cart.setName(name);
//            cart.setQuantity(quantity);
//        }
//
//        session.setAttribute("cart", cart);

////        получаем значение count
//        Integer count = (Integer) session.getAttribute("count");
////        если count == null
//        if (count==null) {
////            создаем count со значением 1
//            session.setAttribute("count", 1);
//            count = 1;
//        } else {
////            иначе инкрементируем
//            session.setAttribute("count", count+1);
//        }


//        PrintWriter pw = response.getWriter();
//
//        pw.println("<html><body>");
////        pw.println("<h1> You count is: " + count + "</h1>");
//        pw.println("</body></html>");

        getServletContext().getRequestDispatcher("/showCart.jsp").forward(request, response);

    }
}