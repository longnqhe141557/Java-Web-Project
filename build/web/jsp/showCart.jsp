<%-- 
    Document   : showCart
    Created on : Jun 28, 2021, 2:33:07 AM
    Author     : User
--%>

<%@page import="entity.Product"%>
<%@page import="model.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table, td, th {  
                border: 2px solid #ddd;
                text-align: left;
            }

            table {
                border-collapse: collapse;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <table>
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
        <%
            out.println("<h2>=> Tổng số tiền: <b style=\"color: red\">$" + total + "</b></h2>");
        %>
        <h2> <a style="text-decoration: none; color: red" href="ControllerProduct?service=removeAll">=> Remove All</a></h2>
        <h2><a style="text-decoration: none; color: black" href="home">=> Quay trở về trang chủ</a></h2>
        <br>
        <h2><a style="text-decoration: none; color: black" href="ControllerProduct?service=checkout">=> Thanh toán giỏ hàng</a></h2>


    </body>
</html>
