<%-- 
    Document   : category
    Created on : Jun 13, 2021, 9:33:17 AM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Category"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CATEGORY</title>
    </head>
    <body>
        <%// trên trang jsp này chỉ lấy thông tin dưới dạng object từ servlet
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            ArrayList<Category> arr = (ArrayList<Category>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
        %>
        <table style="width: 1200px; text-align: center; margin-left: 150px;" border="1">
            <caption style="font-size: 50px"><%=title%></caption>
            <thead style="padding: 20px">
                <tr>
                    <th style="padding: 20px">CATEGORY ID</th>
                    <th>CATEGORY NAME</th>
                    <th>STATUS</th>
                    <th>DELETE</th>
                    <th>UPDATE</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td style="padding: 20px"><%=rs.getInt(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=(rs.getInt(3) == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="ControllerCategory?service=delete&id=<%=rs.getInt(1)%>">delete</a></td>
                    <td><a href="ControllerCategory?service=update&id=<%=rs.getInt(1)%>">update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <div> <a style="font-size: 30px; text-decoration: none;margin-left: 600px" href="./ControllerCategory?service=addCategory" target="_blank">ADD CATEGORY</a></div>

    </body>
</html>
