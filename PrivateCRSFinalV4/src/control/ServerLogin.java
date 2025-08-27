/** Designed and developed by Swapnil Kumawat
 * 
 */
package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Master
 *
 */
@WebServlet(name = "ServerLogin", urlPatterns = {"/ServerLogin"})
public class ServerLogin extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        try {
        	
            
            String user=request.getParameter("uid");
            String password=request.getParameter("pwd");
            System.out.println("1 "+user);
            System.out.println("2 "+password);
            
            
          if(user.equalsIgnoreCase("admin") && password.equals("admin"))
          {
            	  session.setAttribute("Userid", 3); 
            	  session.setAttribute("usertype", "admin");
            	  session.setAttribute("username", "admin");
                  responce.sendRedirect("AdminHome.jsp");
              }else{                
                
            	  responce.sendRedirect("ServerLogin.jsp?loginfail='LoginFail'");
            }
        } catch (Exception e) { e.getMessage();
        }
        
    }
     
}