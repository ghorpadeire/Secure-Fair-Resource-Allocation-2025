package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.ConnectionManager;


@WebServlet("/PSpace")
public class PSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps;
	ResultSet rs;


	public void init(ServletConfig config) throws ServletException 
	{
		try
		{
			con = ConnectionManager.getConnection();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rspace = request.getParameter("rspace");
		String duration = request.getParameter("duration");
		
		HttpSession session = request.getSession();
		String userid = session.getAttribute("username").toString();
		String id = session.getAttribute("id").toString();
		
		//String username = request.getParameter("username");
		System.out.println("username="+userid);
		Connection con = ConnectionManager.getConnection();
		
		try 
		{
			 			
			 PreparedStatement ps = con.prepareStatement("insert into spacereq(uid, username, rspace, duration) values('"+id+"','"+userid+"', '"+rspace+"', '"+duration+"')");
			 int result=ps.executeUpdate();
			 
			 if (result > 0)
				
			{
				System.out.println("Add successfull");
				response.sendRedirect("Homepage.jsp?Done");		
			}
			else
			{
				System.out.println("Failed to Add");
				response.sendRedirect("Rspace.jsp?regFail");
			}
		}
		
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
}