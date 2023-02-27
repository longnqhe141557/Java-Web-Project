<%-- 
    Document   : viewBill1
    Created on : Jul 22, 2021, 3:48:29 AM
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
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            String title = request.getAttribute("title").toString();
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
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
                    <td><%=rs.getString(6)%></td>
                    <td><a href="billdetail?bid=<%=rs.getString(1)%>">Bill detail</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
