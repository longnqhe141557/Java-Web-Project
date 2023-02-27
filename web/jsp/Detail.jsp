<%-- 
    Document   : Detail
    Created on : Jun 22, 2021, 11:42:41 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String username = (String) request.getAttribute("username");
        %>
        <div style="text-align: center; font-size: 40px">
            <h1>Hello <%=username%></h1>
            <a style="text-decoration: none; font-size: 30px" href="ControllerProduct">MANAGE PRODUCT</a><br>
            <a style="text-decoration: none; font-size: 30px" href="ControllerCategory">MANAGE CATEGORY</a><br>
            <a style="text-decoration: none; font-size: 30px" href="ControllerCustomer">MANAGE CUSTOMER</a><br>
            <a style="text-decoration: none; font-size: 30px" href="ControllerBill">MANAGE BILL</a>
        </div>
    </body>
</html>
