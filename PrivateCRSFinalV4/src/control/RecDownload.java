package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RecDownload")
public class RecDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 public RecDownload() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String filename = request.getParameter("file");
			String type = request.getParameter("type");
			String fid = request.getParameter("id");
			CopyToServer2 cp = new CopyToServer2();
			if(type.equals("user"))
			{
				cp.CopyToFolde1(filename);
			}else if(type.equals("rec")){
				try {
					Connection con = ConnectionManager.getConnection();
					PreparedStatement ps = con.prepareStatement("update file set flag='1' where id=?");
					ps.setString(1, fid);
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				cp.CopyToFolde2(filename);
			}
			
			response.sendRedirect("Homepage2.jsp?download");
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
		}

	}
