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

/**
 * Servlet implementation class hack_req
 */
@WebServlet("/hack_req")
public class hack_req extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Services method");
		String sts = request.getParameter("hack_req");
		System.out.println(sts);
		String username = request.getParameter("username");
		System.out.println(username);

		if (sts != null) {

			

			System.out.println("username " + username);
			System.out.println("Status " + sts);

			Connection con = ConnectionManager.getConnection();
			Statement st1;
			try {
				st1 = con.createStatement();

				st1.executeUpdate("Update file set hack_req='" + sts
						+ "' where userid='" + username + "'");

				System.out.println("done");
				response.sendRedirect("AllFiles.jsp?request=done");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
			
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
