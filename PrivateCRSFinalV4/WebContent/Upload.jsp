<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="control.GlobalFunction"%>

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
     <%
        if(request.getParameter("status")!=null){
        out.println("<script>alert('Successfully Uploaded!')</script>");
        }
        
	      GlobalFunction GF = new GlobalFunction();
	      GF.DeleteFile();
        %>
        <%
        	String receiver=request.getParameter("receiver");
        session.setAttribute("rec",receiver);
        	System.out.println("Refff:"+receiver);
            String user_id = null;
            if (session.getAttribute("username") != null) {
                user_id = (String) session.getAttribute("username");

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
                        <li><a href="Upload.jsp">Upload File</a></li>
                        <li><a href="logout.jsp">Logout</a></li>
                    </ul>
				</div>
				<div class="clr"></div>
			</div>
		</div>

		<div class="article">
			<%@include file="WelcomeName.jsp"%>

			<div class="content">                
                
                    <div class="article" style="height:200px">
 
                      <form class="form1" action="UploadServlet" method="post" enctype="multipart/form-data">
                        <table width="40%" align="center" cellpadding="5" cellspacing="5">    
                            <tr>
                            	<td colspan="3"><h2>Choose File To Upload</h2></td>
                            </tr>
                            <tr>
                            	<td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
                            	<td>Select File</td><td>:</td><td>
                            	<input type="hidden" name="user" value="<%=user_id%>" /> <%-- <%=user_id%> --%>
                            	<input type="hidden" name="receiver" value="<%=receiver%>" />
                            	<input type="file" name="file" id="file" size="30" maxlength="2048" /></td>

                            </tr>
                            <tr>
                            	<td>Destination (CRS Receiver)</td><td><%=receiver %></td>
                            	
                            	<% 
                            	//String id = (String) session.getAttribute("id");
                            	//String id = session.getAttribute("id").toString();
                            	String id = request.getParameter("id");
                            	//String receiver = request.getParameter("receiver");
                            	
                            	System.out.println("id "+receiver);
                            try{
                            	Connection con = ConnectionManager.getConnection();
                            	//PreparedStatement ps = con.prepareStatement("SELECT * FROM `reg` where id='"+id+"' and fname='"+fname+"' ");
                            	PreparedStatement ps = con.prepareStatement("SELECT * FROM `spacereq` where receiver='"+receiver+"' ");
                            	System.out.println("SQL "+ps);
								String userid="";
								ResultSet rs = ps.executeQuery();
								if(rs.next()) 
								{
									userid = rs.getString("userid");
									//fname = rs.getString("fname");
									receiver = rs.getString("receiver");
									System.out.println("AAre " + receiver);
								}
                            	%>
                            	<td>
                            		<%-- <input type="text" name="userid" value="<%=userid%>" /> --%>
                            		<input type="text" name="receiver" value="<%=receiver%>" />
                            	</td>
                            </tr>
                            
                            <%
							}
								catch(Exception e){	
						}
						 %>
								
                           <%-- <td><select name="receiver" required
							class="required input_field">
								<option value="">----- Select Receiver -----</option>
								<%
									String username = (String) session.getAttribute("firstname");
									System.out.println("username : " + username);
									Connection con = ConnectionManager.getConnection();
									String sql = "select * from reg where fname !='" + username
											+ "'";
									PreparedStatement ps = con.prepareStatement(sql);
									ResultSet rs = ps.executeQuery();
									while (rs.next()) {
								%>

								<option value="<%=rs.getString("fname")%>"><%=rs.getString("fname")%></option>
								<%
									String ss = rs.getString("fname");
										System.out.println("re " + ss);
								%> --%>

								
						<!-- </select></td></tr> -->
						<tr></tr>
						
                            <tr>
                            	<td colspan="3"><input name="submit" class="form-login" type="submit" title="Submit" value="Submit" size="30" maxlength="2048" /></td>
                            </tr>
                            
                        </table>
                        </form>
                         
                    </div>
                    
                </div>
                
                <div class="clr"></div>
            </div>
            <div class="clr"></div>
           
            <div class="footer">
             <%@include file="footer.jsp" %>
       			        
            </div>
        </div>
        
        <%
            } else {
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>