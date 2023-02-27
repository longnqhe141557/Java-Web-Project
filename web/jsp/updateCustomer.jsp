<%-- 
    Document   : updateCustomer
    Created on : Jun 16, 2021, 10:29:08 PM
    Author     : User
--%>

<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customer customer = (Customer) request.getAttribute("customer");
        %>
        <form action="ControllerCustomer" method="POST">
            <table border="0">

                <tr>
                    <td>CUSTOMER ID:</td>
                    <td><input type="text" name="customerID" value="<%=customer.getCid()%>" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td>CUSTOMER NAME:</td>
                    <td><input type="text" name="customerName" value="" /></td>
                </tr>
                <tr>
                    <td>CUSTOMER PHONE:</td>
                    <td><input type="text" name="customerPhone" value="" /></td>
                </tr>
                <tr>
                    <td>CUSTOMER ADDRESS:</td>
                    <td><input type="text" name="customerAddress" value="" /></td>
                </tr>
                <tr>
                    <td>USERNAME:</td>
                    <td><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>PASSWORD:</td>
                    <td><input type="text" name="password" value="" /></td>
                </tr>
                <tr>
                    <td>STATUS</td>
                    <td>
                        <input type="radio" name="status" value="1" />Enable
                        <input type="radio" name="status" value="0" />Disable
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
