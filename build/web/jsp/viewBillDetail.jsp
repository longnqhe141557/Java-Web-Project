<%-- 
    Document   : viewBillDetail
    Created on : Jun 17, 2021, 8:11:30 AM
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
            ResultSet rs1 = (ResultSet) request.getAttribute("ketQua1");
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
            <!--<a style="font-size: 20px; " href="./ControllerBill?service=addBill" target="_blank">ADD BILL</a>-->
        </div>
        <%while (rs1.next()) {%>
             <p>Customer Name: <%=rs1.getString(3)%></p>
             <p>Customer Phone:<%=rs1.getString(4)%></p>
             <p>Customer Address:<%=rs1.getString(5)%></p>
        <%}%>
        <table style="width: 100%" border="1">
            <thead>
                <tr>
                    <th>ORDER ID</th>
                    <th>PRODUCT ID</th>
                    <th>QUANTITY</th>
                    <th>PRICE</th>
                    <th>TOTAL</th>
                    <th>DATE CREATE</th>
                    <th>IMAGE</th>
                    <th>CATEGORY NAME</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getInt(3)%></td>
                    <td><%=rs.getDouble(4)%></td>
                    <td><%=rs.getDouble(5)%></td>
                    <td><%=rs.getString(6)%></td>
                    <td><img style="width: 200px; height: 140px;" src="./<%=rs.getString(7)%>"></td>
                    <td><%=rs.getString(8)%></td>
                  
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
