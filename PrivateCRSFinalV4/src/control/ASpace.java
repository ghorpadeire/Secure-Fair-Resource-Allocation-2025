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


@WebServlet("/ASpace")
public class ASpace extends HttpServlet {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String aspace = request.getParameter("aspace");
		String cost = request.getParameter("cost");
		
		int p=Integer.parseInt(aspace);
		int q=Integer.parseInt(cost);
		int total_cost = (p * q);
		
		HttpSession session = request.getSession();
		String userid = session.getAttribute("username").toString();
		
		System.out.println("Total Cost="+total_cost);

		System.out.println("username="+userid);
		Connection con = ConnectionManager.getConnection();
		Statement st1;
		try 
		{
			 st1 = con.createStatement();
			//st1.executeUpdate("Update reg set privilege='" + privilege
			//		+ "' where userid='" + username + "'");
			
			 int res = st1.executeUpdate("Update reg set aspace='"+aspace+"', cost='"+cost+"',total_cost='"+total_cost+"' where userid='"+userid+"'");
			//int result=ps.executeUpdate();
			 
			if (res > 0)
			{
				
				//session.setAttribute("total_cost", total_cost);
				session.setAttribute("total_cost", total_cost);
				System.out.println("total_cost="+total_cost);
				System.out.println("Add successfull");
				response.sendRedirect("Homepage2.jsp?totalcost="+total_cost+"");		
			}
			else
			{
				System.out.println("Failed to Add");
				response.sendRedirect("Aspace.jsp?regFail");
			}
		}
		
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
}