package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/AcceptRequest")

@WebServlet(name = "AcceptRequest", urlPatterns = { "/AcceptRequest" })
public class AcceptRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Services method");
		String sts = request.getParameter("status");
		String username = request.getParameter("username");

		if (sts != null) {

			

			System.out.println("username " + username);
			System.out.println("Status " + sts);

			Connection con = ConnectionManager.getConnection();
			Statement st1;
			try {
				st1 = con.createStatement();

				st1.executeUpdate("Update reg set status='" + sts
						+ "' where userid='" + username + "'");

				System.out.println("done");
				response.sendRedirect("userPrivileges.jsp?request=done");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			String privilege=request.getParameter("privilege");

			System.out.println("username " + username);
			System.out.println("Privilege " + privilege);

			Connection con = ConnectionManager.getConnection();
			Statement st1;
			try {
				st1 = con.createStatement();

				st1.executeUpdate("Update reg set privilege='" + privilege
						+ "' where userid='" + username + "'");

				System.out.println("done2");
				response.sendRedirect("userPrivileges.jsp?request=done");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet method");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoPost method");

	}
}
