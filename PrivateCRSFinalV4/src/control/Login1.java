/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mahesh-5
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login1 extends HttpServlet {
    HttpSession session=null;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
        out.println("hello");
        session = request.getSession(true);
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Login1</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Login1 at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
        execute(request,response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
         execute(request,response);
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
    
    public void execute(HttpServletRequest request,HttpServletResponse response)
    {
        try
        {
        	 SendMailSSL SendMail = new SendMailSSL();
            String user = request.getParameter("user");
            String password = request.getParameter("pass");
            session.setAttribute("emailid", user);
            
            
            ConnectionManager cm = new ConnectionManager();
            Statement st = cm.statement();
            String query = "select username,password from registration where username='"+user+"' and password='"+password+"'";
            ResultSet res = st.executeQuery(query);
            if(res.next())
            {
            	String userid=res.getString("userid");
            	session.setAttribute("userid", userid);
                response.sendRedirect("homepage.jsp");                           
            }
//            Loginbean logbean = new Loginbean();
//            logbean.setEmail(email);
//            logbean.setPassword(password);
            
            
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
