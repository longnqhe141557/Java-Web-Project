<%-- 
    Document   : checkout
    Created on : Jul 22, 2021, 12:22:46 AM
    Author     : User
--%>

<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ControllerProduct" method="post">
            <table border="1" style="width: 50%;">
                <tr>
                    <td style="font-size: 20px; font-weight: bolder">Mã sản phẩm</td>
                    <td style="font-size: 20px; font-weight: bolder">Tên sản phẩm</td>
                    <td style="font-size: 20px; font-weight: bolder">Số lượng</td>
                    <td style="font-size: 20px; font-weight: bolder">Hình ảnh</td>
                    <td style="font-size: 20px; font-weight: bolder">Giá</td>
                    <td style="font-size: 20px; font-weight: bolder">Tổng</td>
                    <td style="font-size: 20px; font-weight: bolder">Xóa</td>
                </tr>
                <%
                    java.util.Enumeration em = session.getAttributeNames();
                    double total = 0;
                    while (em.hasMoreElements()) {
                        String id = em.nextElement().toString();
                        Product pro = (Product) session.getAttribute(id);
                        int count = pro.getQuantity();
                        out.println("<tr>");
                        out.println("<td style=\"font-weight:bolder\">" + id + "</td>");
                        out.println("<td style=\"font-weight:bolder\">" + pro.getPname() + "</td>");
                        out.println("<td style=\"font-weight:bolder\">" + count + "</td>");
                        out.println("<td><img style =\"width: 200px; height: 140px;\" src =\"" + pro.getImage() + "\"></td>");
                        out.println("<td style=\"font-weight:bolder\">$" + pro.getPrice() + "</td>");
                        out.println("<td style=\"font-weight:bolder\">$" + pro.getPrice() * count + "</td>");
                        out.println("<td style=\"font-weight:bolder\"><a style=\"text-decoration: none;color: red;\" href=ControllerProduct?service=remove&id1=" + id + ">REMOVE</a></td>");
                        out.println("</tr>");
                        total = total + (pro.getPrice() * count);
                    }
                %>
            </table>
            <table border="0">
                <tr>
                    <td>NAME:</td>
                    <td><input type="text" name="name" value="" /></td>
                </tr>
                <tr>
                    <td>PHONE:</td>
                    <td><input type="text" name="phone" value="" /></td>
                </tr>
                <tr>
                    <td>ADDRESS:</td>
                    <td><input type="text" name="address" value="" /></td>
                </tr>
                <tr>
                    <td>TOTAL:</td>
                    <td><input type="text" name="total" value="<%=total%>" /></td>
                </tr>
                <tr>
                    <td><input type="submit"  value="THANH TOÁN"/></td>        
                </tr>
                <input type="hidden" name="service" value="checkouted"/>
            </table>
        </form>       
    </body>
</html>
