<%-- 
    Document   : viewBill
    Created on : Jun 17, 2021, 7:10:06 AM
    Author     : User
--%>

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
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            String title = request.getAttribute("tieuDe").toString();
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
            <a style="font-size: 20px; " href="./ControllerBill?service=addBill" target="_blank">ADD BILL</a>
        </div>
        <table style="width: 100%" border="1">
            <thead>
                <tr>
                    <th>ORDER ID</th>
                    <th>DATE CREATE</th>
                    <th>CUSTOMER NAME</th>
                    <th>CUSTOMER PHONE</th>
                    <th>CUSTOMER ADDRESS</th>
                    <th>TOTAL</th>
                    <th>STATUS</th>
                    <th>DELETE</th>
                    <th>UPDATE</th>
                    <th>BILL DETAIL</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td>$<%=rs.getDouble(6)%></td>
                    <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="ControllerBill?service=delete&bid=<%=rs.getString(1)%>">delete</a></td>
                    <td><a href="ControllerBill?service=update&bid=<%=rs.getString(1)%>">update</a></td>                   
                    <td><a href="ControllerBill?service=viewdetail&bid=<%=rs.getString(1)%>">Bill detail</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
