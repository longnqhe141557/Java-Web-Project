<%-- 
    Document   : add2Cart
    Created on : Jun 28, 2021, 2:12:05 AM
    Author     : User
--%>

<%@page import="entity.Product"%>
<%@page import="model.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String title = (String) request.getAttribute("title");
        %>
        <h1 style="color: red"><%=title%></h1>
        <h2><a style="text-decoration: none; font-size: 40px; margin-left: 500px" href="ControllerProduct?service=showCart">Hiển thị giỏ hàng của bạn</a></h2>
    </body>
</html>
