<%-- 
    Document   : debug
    Created on : 2014-4-9, 19:24:57
    Author     : nightwolf
--%>

<%@page import="java.nio.charset.Charset"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="object.Book"%>
<%@page import="db.OnlineDatabaseAccessor"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="db.ConnectionManager"%>
<%@page import="org.json.JSONObject"%>
<%@page import="recommendation.UserDataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DEBUG</title>
    </head>
    <body>
        <%
            out.print( Charset.defaultCharset()+"<br>");
            String userid = request.getParameter("userid");
            ConnectionManager cMgr = new ConnectionManager();
            Connection con = cMgr.getConnection();
            con.setCharacterEncoding("utf8");
            
            Statement stmtUser = OnlineDatabaseAccessor.createStatement(con);
            Statement stmtBook =  OnlineDatabaseAccessor.createStatement(con);
            ResultSet borrowSet = stmtUser.executeQuery("select * from user_book where userid='" + userid + "'");

            
            while (borrowSet.next()) {
                String bookName = borrowSet.getString("bookname").replaceAll("'", "''");
                String author = borrowSet.getString("author").replaceAll("'", "''");
                out.print(bookName+"|"+author+"-->");
                
               ResultSet bookSet = stmtBook.executeQuery("select * from books where bookname='"
                        + bookName + "' and author='" + author + "'");
               
                 out.print(stmtBook.toString()+"<br>");

                if (bookSet.next()) {
                    Book aBook = Book.getBookFromResultSet(bookSet);
                   out.print(aBook);
                   out.println("<br>");
                }
            }

           
        %>
    </body>
</html>
