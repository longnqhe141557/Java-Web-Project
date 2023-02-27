<%-- 
    Document   : ViewCustomer
    Created on : Jun 15, 2021, 9:41:28 AM
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CUSTOMER</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            String title = request.getAttribute("tieuDe").toString();
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
            <a style="font-size: 20px; " href="./ControllerCustomer?service=addCustomer" target="_blank">ADD CUSTOMER</a>
        </div>
        
            <table style="width: 100%" border="1">                   
                <thead>
                    <tr>
                        <th>CUSTOMER ID</th>
                        <th>CUSTOMER NAME</th>
                        <th>CUSTOMER PHONE</th>
                        <th>CUSTOMER ADDRESS</th>
                        <th>USERNAME</th>
                        <th>PASSWORD</th>
                        <th>STATUS</th>
                        <th>DELETE</th>
                        <th>UPDATE</th>
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
                        <td><%=(rs.getInt("status") == 1 ? "Enable" : "Disable")%></td>
                        <td><a href="ControllerCustomer?service=delete&cid=<%=rs.getString(1)%>">delete</a></td>
                        <td><a href="ControllerCustomer?service=update&cid=<%=rs.getString(1)%>">update</a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
      
    </body>
</html>
