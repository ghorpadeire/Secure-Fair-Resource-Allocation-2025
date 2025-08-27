package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class hacksearch
 */
@WebServlet("/hacksearch")
public class hacksearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hacksearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session=request.getSession(true);
		
		System.out.println("hiiiiiiiiii");
		Connection cn=ConnectionManager.getConnection();
		String search=request.getParameter("search");
		System.out.println("hi search "+search);
		//HttpSession session=request.getSession();
		//String userid=session.getAttribute("userid").toString();
		//System.out.println("hiiiii"+userid);
		//String username=session.getAttribute("username").toString();
		//System.out.println("user name"+userid);
		try {
			PreparedStatement ps=cn.prepareStatement("select * from file where userid='"+search+"'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
			     String  userid=rs.getString("userid");
				System.out.println("user name "+userid);
				session.setAttribute("username",userid);
				
				response.sendRedirect("file_view.jsp?succ");
			}
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		 
		
	}

}
