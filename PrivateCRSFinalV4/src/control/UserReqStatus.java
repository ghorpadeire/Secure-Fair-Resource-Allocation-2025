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


@SuppressWarnings("serial")
@WebServlet("/UserReqStatus")
public class UserReqStatus extends HttpServlet 
	{

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
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			
			HttpSession session = request.getSession();
			String attrib=request.getParameter("attrib");
			String id = request.getParameter("id");
			String fname = session.getAttribute("fname").toString();
		//	Integer total_cost = (Integer) request.getAttribute("total_cost");
			String totalcost=request.getParameter("totalcost");
			GlobalFunction gf=new GlobalFunction();
			System.out.println("RID= "+id);
			System.out.println("Total Cost= "+totalcost);
						
			int i=-1;
			
			try 
			{
				
				if (attrib.equals("accept")) 
				{
					
					ps = con.prepareStatement("update `spacereq` SET status='Accept', receiver='"+fname+"', total_cost='"+totalcost+"' where id='"+id+"' ");
					i = ps.executeUpdate();
				} 
				else if (attrib.equals("reject")) 
				{
					ps = con.prepareStatement("update `spacereq` SET status='Reject', receiver='"+fname+"' where id='"+id+"' ");
					i = ps.executeUpdate();
				}
				if (i > 0) 
				{
					
					System.out.println("Status Update Successfully "+id);
					response.sendRedirect("Homepage2.jsp?Accept");
				} else {
					System.out.println("Data Update Fails");
				}

			} catch (Exception e) {
				System.out.println("EXp " + e);
			}
			
		}

	}


		
		/*HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		System.out.println("userid: "+ id);
			try {
				Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement("update spacereq set status='Accept' where id=?");
				ps.setString(1, id);
				ps.executeUpdate();
				System.out.println("Status Update Successfully "+id);
				response.sendRedirect("Homepage2.jsp?Accept");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
*/