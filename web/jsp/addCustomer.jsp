<%-- 
    Document   : addCustomer
    Created on : Jun 15, 2021, 9:55:55 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD CUSTOMER</title>
    </head>
    <body>
        <h1 style="font-size: 30px; color: purple">ADD CUSTOMER HERE!</h1>
        <form action="ControllerCustomer" method="post">
            <table border="0">
                <!--<tr>
                        <td>customer ID</td>
                        <td><input type="text" name="customerID" value="" /></td>
                    </tr>
                -->
                <tr>
                    <td>Customer Name</td>
                    <td><input type="text" name="cname" value="" /></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="cphone" value="" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="cAddress" value="" /></td>
                </tr>
                <tr>
                    <td>username</td>
                    <td><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>password</td>
                    <td><input type="text" name="password" value="" /></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td> <input type="radio" name="st" value="1" checked />Enable
                        <input type="radio" name="st" value="0" />disable</td>
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
