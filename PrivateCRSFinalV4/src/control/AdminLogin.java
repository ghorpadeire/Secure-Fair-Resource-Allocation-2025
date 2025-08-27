package control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException 
	{
		

	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("uid");
		if(uid.equals("admin") &&pwd.equals("admin"))
		{
			HttpSession session=request.getSession();
			session.setAttribute("username", uid);
			
			response.sendRedirect("AdminHome.jsp?login=done");
		}
		else{
			response.sendRedirect("ServerLogin.jsp?login=done");
		}
	}

}
