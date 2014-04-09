<%-- 
    Document   : index
    Created on : 2014-4-8, 23:18:55
    Author     : nightwolf
--%>

<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="db.ConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           ConnectionManager cMgr = new ConnectionManager();
           Connection con = cMgr.getConnection();
           if(con != null){
               out.println("Database connected!");
           }else{
               out.println("Failed to connect!");
           }
        %>
    </body>
</html>
