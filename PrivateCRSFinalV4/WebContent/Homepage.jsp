<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@page import="control.ConnectionManager" %>
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
    <div align="right">
        <%
            String user_id = null;
            if (session.getAttribute("username") != null) {
                user_id = (String) session.getAttribute("username");

        %>
       </div> 
        <%
		if (request.getParameter("Done") != null) {
			out.println("<script>alert('Data Inserted Successfully!')</script>");
		}
		%>
		<%
		if (request.getParameter("donee") != null) {
			out.println("<script>alert('File Downloaded Successfully!')</script>");
		}
		%>
		
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
<br></br>
	       <marquee> <h2>Welcome <%=session.getAttribute("username")%> to <%=session.getAttribute("user")%> Home Module</h2></marquee>
                        
                        
<br /><br />

                    </div>
                    
                </div>
                
                <div class="clr"></div>
            </div>
            <div class="clr"></div>
           
            <div class="footer">
             <%@include file="footer.jsp" %>
       			        
            </div>
        
        <%
            } else {
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>