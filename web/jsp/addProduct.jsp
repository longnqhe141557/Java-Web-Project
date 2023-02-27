<%-- 
    Document   : addProduct
    Created on : Jun 15, 2021, 3:34:31 AM
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD PRODUCT</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
        %>
        <h1 style="font-size: 30px; color: purple">ADD PRODUCT HERE!</h1>
        <form action="ControllerProduct" method="post">
            <table border="0">
                <tr>
                    <td>Product ID</td>
                    <td><input type="text" name="ProductID" value="" /></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="" /></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" value="" /></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="Price" value="" /></td>
                </tr>
                <tr>
                    <td>image</td>
                    <td><input type="text" name="image" value="" /></td>
                </tr>
                <tr>
                    <td>description</td>
                    <td><input type="text" name="description" value="" /></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td> <input type="radio" name="st" value="1" checked />Enable
                        <input type="radio" name="st" value="0" />disable</td>
                </tr>
                <tr>
                    <td><b> Choose Category Name:</b></td>
                    <td>
                        <select name="Cate">
                            <%while (rs.next()) {%> 
                            <option value="<%=rs.getInt(1)%>"><%=rs.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit"  value="Insert"  /></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
                <input type="hidden" name="service" value="added"/>
            </table>
        </form>
    </body>
</html>
