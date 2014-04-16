/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import db.ConnectionManager;
import db.OnlineDatabaseAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.User;
import org.json.JSONException;
import org.json.JSONObject;
import util.JSONPRequestProccessor;

/**
 *
 * @author nightwolf
 */
public class UserInfo extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, JSONException {
        
        response.setContentType("text/xscript;charset=UTF-8");
        
        String userid = request.getParameter("userid");
        
        PrintWriter out = response.getWriter();
        
        try {
            

            List<User> users = new ArrayList<User>();

            ConnectionManager conMgr = new ConnectionManager();
            Connection con = conMgr.getConnection();
            Statement stmt = OnlineDatabaseAccessor.createStatement(con);
            
            String sql = "select * from users ";
            
            if(userid != null && !userid.equals("")){
                sql += " where userid='"+userid+"'";
            }
            
            ResultSet rs = OnlineDatabaseAccessor.select(stmt,sql);

            
            while (rs.next()) {
                
                User user = new User();
                user.setUserid(rs.getString("userid"));
                user.setMajor(rs.getString("major"));
                user.setCollege(rs.getString("college"));
                users.add(user);
            }

            JSONObject json = new JSONObject();
            json.accumulate("userinfo", users);
          
            JSONPRequestProccessor.proccessJSONPRequest(request, response, json.toString());
            
            rs.close();
            stmt.close();
            con.close();

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
