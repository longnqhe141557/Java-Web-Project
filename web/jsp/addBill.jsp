<%-- 
    Document   : addBill
    Created on : Jul 22, 2021, 2:43:49 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ControllerBill" method="post">
            <table border="0">
                <tr>
                    <td>ORDER ID:</td>
                    <td><input type="text" name="oid" value="" /></td>
                </tr>
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
                    <td><input type="text" name="total" value="" /></td>
                </tr>
                <tr>
                    <td><input type="submit"  value="ADD"/></td>   
                    <td><input type="reset" value="Reset"></td>
                </tr>
                <input type="hidden" name="service" value="added"/>
            </table>
        </form>
    </body>
</html>
