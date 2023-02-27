<%-- 
    Document   : ViewProduct
    Created on : Jun 15, 2021, 12:18:46 AM
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRODUCT</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            String title = request.getAttribute("tieuDe").toString();
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
            <a style="font-size: 20px; " href="./ControllerProduct?service=addProduct" target="_blank">ADD PRODUCT</a>
        </div>
            <div style="text-align: center">
                <h2><a style="color: red" href="ControllerProduct?service=showCart">Hiển thị giỏ hàng</a></h2>
            </div>
        <table border="1">                   
            <thead>
                <tr>
                    <th>PRODUCT ID</th>
                    <th>PRODUCT NAME</th>
                    <th>QUANTITY</th>
                    <th>PRICE</th>
                    <th>IMAGE</th>
                    <th>DESCRIPTION</th>
                    <th>STATUS</th>
                    <th>DELETE</th>
                    <th>UPDATE</th>
                    <th>ADD TO CART</th>

                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><img style="width: 200px; height: 140px;" src="./<%=rs.getString(5)%>"></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=(rs.getInt("status") == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="ControllerProduct?service=delete&pid=<%=rs.getString(1)%>">delete</a></td>
                    <td><a href="ControllerProduct?service=update&pid=<%=rs.getString(1)%>">update</a></td>
                    <td><a href="ControllerProduct?service=add2Cart&pid=<%=rs.getString(1)%>">ADD TO CART</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
