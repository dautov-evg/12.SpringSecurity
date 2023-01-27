<%@ page import="com.example.javaeetest.Cart" %><%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 27.01.2023
  Time: 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show cart</title>
</head>
<body>

    <%@ page import="com.example.javaeetest.Cart" %>

    <% Cart cart = (Cart) session.getAttribute("cart"); %>

    <p> Наименование: <%= cart.getName()%> </p>
    <p> Количество: <%= cart.getQuantity()%> </p>
</body>
</html>
