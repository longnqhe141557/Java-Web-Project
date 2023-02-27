<%-- 
    Document   : addCategory
    Created on : Jun 14, 2021, 11:11:04 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD CATEGORY</title>
    </head>
    <body>
        <h1 style="font-size: 30px; color: purple">ADD CATEGORY HERE!</h1>
        <form action="ControllerCategory" method="POST">
            <table border="0">               
                <tr>
                    <th><label for="cate">CATEGORY NAME: </label></th>
                    <th><input type="text" name="cateName" id="cate" required></th>
                </tr>


                <tr>
                    <td><label for="status">STATUS: </label></td>
                    <td>
                        <input type="radio" name="status" id="status" value="1" checked>Active
                        <input type="radio" name="status" id="status" value="0">DeActive
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="ADD"></td>
                    <td><input type="reset" value="RESET"></td>
                    <input type="hidden" name="service" value="added"/>
                </tr>

            </table>

        </form>
    </body>
</html>
