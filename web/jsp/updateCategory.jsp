<%-- 
    Document   : updateCategory
    Created on : Jun 13, 2021, 9:54:21 AM
    Author     : User
--%>

<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Category cate =(Category) request.getAttribute("cate");       
        %>
        <form action="ControllerCategory" method="POST">
            <p>CATE ID: <input type="text" name="cateID" value="<%=cate.getCateID()%>" readonly /></p>
            <p>CATE NAME: <input type="text" name="cateName" value="<%=cate.getCateName()%>" /></p>
            <p>STATUS: <input type="radio" name="status" value="1" <%=(cate.getStatus())==1?"checked":""%>/>Enable
                <input type="radio" name="status" value="0" <%=(cate.getStatus())==0?"checked":""%>/>Disable
            </p> 
            <p>
                <input type="submit" value="update" />
                <input type="submit" value="reset" />
                <input type="hidden" name="service" value="updated"/>
            </p>
            
        </form>
    </body>
</html>
