<%-- 
    Document   : updateProduct
    Created on : Jun 15, 2021, 12:59:34 AM
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Product product = (Product) request.getAttribute("product");
            ResultSet rs1 = (ResultSet) request.getAttribute("ketQua1");
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
        %>
        <form action="ControllerProduct" method="POST">
            <table border="0">

                <tr>
                    <td>PRODUCT ID:</td>
                    <td><input type="text" name="productID" value="<%= product.getPid()%>" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td>PRODUCT NAME:</td>
                    <td><input type="text" name="productName" value="" /></td>
                </tr>
                <tr>
                    <td>QUANTITY:</td>
                    <td><input type="text" name="quantity" value="" /></td>
                </tr>
                <tr>
                    <td>PRICE:</td>
                    <td><input type="text" name="price" value="" /></td>
                </tr>
                <tr>
                    <td>IMAGE:</td>
                    <td><input type="text" name="image" value="" /></td>
                </tr>
                <tr>
                    <td>DESCRIPTION:</td>
                    <td><input type="text" name="description" value="" /></td>
                </tr>
                <tr>
                    <td>STATUS</td>
                    <td>
                        <input type="radio" name="status" value="1" />Enable
                        <input type="radio" name="status" value="0" />Disable
                    </td>
                </tr>
                <tr>
                    <td>CATEGORY NAME:</td>
                    <td>
                        <select name="cate">
                            <%while (rs1.next()) {%> 
                            <option value="<%=rs1.getInt(1)%>"><%=rs1.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit"  value="UPDATE" /></td>
                    <td><input type="reset"  value="RESET" /></td>
                </tr>
                <input type="hidden" name="service" value="updated"/>
            </table>
        </form>
    </body>
</html>
