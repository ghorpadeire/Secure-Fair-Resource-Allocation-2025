<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="control.GlobalFunction"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="control.ConnectionManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cloud Resource Sharing</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
</head>
<body>

	<div class="main">
		<div class="header">
			<div class="header_resize">
				<div class="logo">
					<h2>
						<a href="index.jsp" class="nobg">Data Resource Sharing for Cloud Brokers in Cloud Environment</a>
					</h2>
				</div>
				<div class="menu_nav">
					<ul>
						<li><a href="Homepage.jsp">Home</a></li>
                        <li><a href="Rspace.jsp">Required Space</a></li>
                        <li><a href="RecReq.jsp">Receiver Request</a></li>
                        <!-- <li><a href="Upload.jsp">Upload File</a></li> -->
                        <li><a href="notification.jsp">Request</a></li>
                        <li><a href="logout.jsp">Logout</a></li>
					</ul>				
				</div>
				<div class="clr"></div>
			</div>
		</div>

		<div class="article">
			<%@include file="WelcomeName.jsp"%>

			<div class="content">


				<h2 align="center">Welcome Admin Home</h2>
				<div class="article" style="height: 20px"></div>

			<table border="2" align="center" cellpadding="4" cellspacing="4">
		<tr>            
             <td>Sr. No.</td>
            <!--  <td align="center">Id</td> -->
             <td align="center">User Name</td>
              <td align="center">Receiver Name</td>
             <td align="center">Required Space</td>
             <td align="center">Duration</td>
             <td align="center">Total Cost</td>
             <td align="center">Status</td>
             <td align="center">Upload</td>
        </tr>
                   
		 <%
				int srno=1;
		 		String userid=session.getAttribute("username").toString();
		 		String uid=session.getAttribute("username").toString();
				Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from spacereq where username='"+userid+"' ");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					HttpSession session1 = request.getSession();
					session.setAttribute("receiver", rs.getString("receiver"));
					String status = rs.getString("status");
			%>
			
			<tr align="center">
				<td><%=srno++%></td>
	           	<td><%=rs.getString("username")%></td>
	           	<td><%=rs.getString("receiver")%></td>
	           	<td><%=rs.getString("rspace") %></td>
	           	<td><%=rs.getString("duration") %></td>
	           	<td><%=rs.getString("total_cost") %></td>
	           	<td><%=rs.getString("status")%></td>
	            <td><a href="Upload.jsp?id=<%= rs.getString("id")%>&receiver=<%=rs.getString("receiver")%>">
	            <button class="button button4" input type='submit' name='Submit' value="Submit">File Upload</button></a></td>
				<%-- <td><a href="result.jsp?id=<%= rs.getString("id") %>&fullname=<%=fullname %>&email=<%=rs.getString("email") %>" style="background: yellow;">See Result</a></td> --%>
				<%
					//}
	      		} 
	    	  %>
 			</tr>
</table>
			
	
			</div>

			<div class="clr"></div>
		</div>
		<div class="clr"></div>

		<div class="footer">
			<%@include file="footer.jsp"%>

		</div>
	</div>

</body>
</html>