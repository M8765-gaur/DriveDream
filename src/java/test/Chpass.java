/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tusha
 */
@WebServlet(name = "Chpass", urlPatterns = {"/Chpass"})
public class Chpass extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          int f=0;
        try
        {
            Connection con=DB.createConn();
            Statement st=con.createStatement();
            String em=request.getParameter("em");
            String cp=request.getParameter("cpass");
            String np=request.getParameter("npass");
            String cnp=request.getParameter("conpass");
            String q1="select * from test";
            ResultSet rs=st.executeQuery(q1);
            while(rs.next())
            {
                if(em.equals(rs.getString(1)) && cp.equals(rs.getString(2)))
                {
                    f=1;
                    break;
                }
                else
                {
                    f=0;
                    break;
                }
            }
            if(f==1)
            {
                if(np.equals(cnp))
                {
                    String q2="update test set password='"+np+"' where email='"+em+"'";
                    st.executeUpdate(q2);
                    out.print("Password has been successfully changed");
                }
                else
                {
                    out.print("Password does not match");
                }
            }
            else
            {
                out.print("Password changed successfully");
            }
        }
        catch(Exception e)
        {
            out.print(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
