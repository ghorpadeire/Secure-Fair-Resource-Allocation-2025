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

					<h2 align="center">
						Request Form<br/>
					</h2><br/>
					<form action="PSpace" name="f1" id="f1" method="post">

						<table align="center" border="10" cellspacing="8px" cellpadding="8px">
							<tr>
								<td>Required Space in MB</td>
								<td><input type="number" name="rspace" id="rspace" required/>
								<label id="t1" class="style6"></label></td>
							</tr>
							<tr>
							<td>No. of Days (Duration)</td>
								<td><input type="number" class="style5" name="duration" id="duration" required/>
								<label id="t2"></label></td>
							</tr>
							
							<tr>
								<td align="center"><input type="submit" value="Insert" class="style5"
									onclick="checkButton()" style="height:45px; width:120px" /></td>
															
								<td align="center"><input type="reset" value="Reset" class="style5"
									style="height:45px; width:120px" /></td>
							</tr>
							
							</table>
							</form>
							


                        
                        
<br /><br />

                    </div>
                    
                </div>
                
                <div class="clr"></div>
            </div>
            <div class="clr"></div>
           
            <div class="footer">
             <%@include file="footer.jsp" %>
       			        
            </div>
        </div>


</body>
</html>