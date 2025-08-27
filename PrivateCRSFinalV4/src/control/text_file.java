package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class text_file
 */
@WebServlet("/text_file")
public class text_file extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public text_file() {
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
		System.out.println("heloooooooooo");
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		String hidden=request.getParameter("hidden");
		String username=(String) session.getAttribute("username");
		System.out.println(username);
		Connection con=ConnectionManager.getConnection();
		
			try {
				
				if(hidden.equals("1"))
				{
					PreparedStatement ps;
				ps = con.prepareStatement("update file set status1='yes' where userid=?");
				//ps.setString(1, file);
				ps.setString(1, username);
				
				ps.executeUpdate();
				
				 
				
				System.out.println("File hack Successfully1?succ");
				response.sendRedirect("text_file.jsp");
				
				// out.println("</script>");
	            }else if(hidden.equals("2"))
			{
				PreparedStatement ps = con.prepareStatement("update file set status1='yes' where userid=?");
				//ps.setString(1, file);
				ps.setString(1, username);
				
				ps.executeUpdate();
				
				response.setContentType("text/html");
				System.out.println("File hack Successfully2");
				response.sendRedirect("text_file.jsp?succ");
				
			}else if(hidden.equals("3"))
			{
				PreparedStatement ps = con.prepareStatement("update dedup_data set status1='yes' where userid=?");
				//ps.setString(1, file);
				ps.setString(1, username);
				
				ps.executeUpdate();
				
				response.setContentType("text/html");
				System.out.println("File hack Successfully3");
				response.sendRedirect("text_file.jsp?succ");
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
