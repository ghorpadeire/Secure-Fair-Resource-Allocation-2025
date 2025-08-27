package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/StoreContent")
public class StoreContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long s1,s2,s3;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("username");
		
		int FileID = Integer.parseInt(request.getParameter("FileID"));
		String fileName = request.getParameter("fileName");
		
		String Block1 = request.getParameter("Block1");
		String Block2 = request.getParameter("Block2");
		String Block3 = request.getParameter("Block3");

		String firstBlock = request.getParameter("firstBlock");
		String middleBlock = request.getParameter("middleBlock");
		String lastBlock = request.getParameter("lastBlock");
		
		String b1 = request.getParameter("b1");
		String b2 = request.getParameter("b2");
		String b3 = request.getParameter("b3");
	
		
		
		GlobalFunction GF = new GlobalFunction();
		//ConnectionManager con = new ConnectionManager();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			Statement st = con.createStatement();
			
			
			for(int i=1;i<=3;i++)
			{
				if(i==1){
					Statement stmt=con.createStatement();
					String qry = "SELECT * FROM blocked_files where diegest_key='"+b1+"'";
					System.out.println("Query 70:"+qry);
//					File file = new File("D:\\upload\\block1.txt");
//                    
//                    if ( file.createNewFile() ) {
//                    	FileWriter fw = new FileWriter(file.getAbsoluteFile());
//                		BufferedWriter bw = new BufferedWriter(fw);
//                		bw.write(firstBlock);
//                		bw.close();
//                     } else {
//                        System.out.println("Failure!");
//                     }
					
					ResultSet rst=stmt.executeQuery(qry);
                    if(rst.next())
                    {
                    	String query1 = "INSERT INTO debug_block (ref_fid,userid,blockid) VALUES("+FileID+",'"+rst.getString("user_id")+"',"+rst.getString("id")+")";
                    	System.out.println("Query 86:"+query1);
//    					
                    	st.executeUpdate(query1);
                    	
                    	
                    }else{
                    	
//                    	 FileUtil fu = new FileUtil();
//                         byte[] b11 = fu.createChecksum("D:\\upload\\block1.txt", "MD5");
//                         System.out.println("b1 lenght" + b11.length);
//                         System.out.println("b1 length" + b11.length);
//                         System.out.println("b11::" + b11);
                         
                    	FileWriter fw=new FileWriter("E:\\upload\\Server1\\"+user_id+"_b1.txt");
                    	String efb1=AESFile.encrypt(firstBlock);
                    	  fw.write(efb1);  
                    	   fw.close();  
                    	   
                    	File f1=new File("E:\\upload\\Server1\\"+user_id+"_b1.txt");
                    	s1=f1.length();
                    	
                    	String query = "INSERT INTO blocked_files (fileName,fileID,block,blockData,diegest_key,user_id) VALUES ('"+fileName+"',"+FileID+",'"+Block1+"','"+firstBlock+"','"+b1+"','"+user_id+"')";
                    	System.out.println("Query 108:"+query);
//    					
                    	st.executeUpdate(query);
                    }
					
                    
                    
                   
            		
				
				}else if(i==2){
					
					Statement stmt=con.createStatement();
					String qry = "SELECT * FROM blocked_files where diegest_key='"+b2+"'";
					System.out.println("Query 121:"+qry);
//					
					ResultSet rst=stmt.executeQuery(qry);
                    if(rst.next())
                    {
                    	String query1 = "INSERT INTO debug_block (ref_fid,userid,blockid) VALUES("+FileID+",'"+rst.getString("user_id")+"',"+rst.getString("id")+")";
                    	System.out.println("Query 128:"+query1);
//    					
                    	st.executeUpdate(query1);
                    	
                    	
                    }else
                    {
                    
                    FileWriter fw=new FileWriter("E:\\upload\\Server2\\"+user_id+"_b2.txt");  
                    String emb2=AESFile.encrypt(middleBlock);
              	  fw.write(emb2);    
                  	   fw.close();  
                  	   
                  	 File f2=new File("E:\\upload\\Server2\\"+user_id+"_b2.txt");
                 	s2=f2.length();
                  	
                    	String query = "INSERT INTO blocked_files (fileName,fileID,block,blockData,diegest_key,user_id) VALUES ('"+fileName+"',"+FileID+",'"+Block2+"','"+middleBlock+"','"+b2+"','"+user_id+"')";
                    	System.out.println("Query 145:"+query);
//    					
                    	st.executeUpdate(query);
                    }
					
					
//                    File file = new File("D:\\upload\\block2.txt");
//                    
//                    if ( file.createNewFile() ) {
//                    	FileWriter fw = new FileWriter(file.getAbsoluteFile());
//                		BufferedWriter bw = new BufferedWriter(fw);
//                		bw.write(middleBlock);
//                		bw.close();
//                     } else {
//                        System.out.println("Failure!");
//                     }
					
					
					
				}else if(i==3){
					
					Statement stmt=con.createStatement();
					String qry = "SELECT * FROM blocked_files where diegest_key='"+b3+"'";
					System.out.println("Query 168:"+qry);
//					
					ResultSet rst=stmt.executeQuery(qry);
                    if(rst.next())
                    {
                    	String query1 = "INSERT INTO debug_block (ref_fid,userid,blockid) VALUES("+FileID+",'"+rst.getString("user_id")+"',"+rst.getString("id")+")";
                    	System.out.println("Query 173:"+query1);
//    					
                    	st.executeUpdate(query1);
                    	
                    	
                    }else{
                    	
                    	FileWriter fw=new FileWriter("E:\\upload\\Server3\\"+user_id+"_b3.txt");  
                    	String elb3=AESFile.encrypt(lastBlock);
                  	  fw.write(elb3);    
                    	   fw.close();
                    	   
                    	   File f3=new File("E:\\upload\\Server3\\"+user_id+"_b3.txt");
                        	s3=f3.length();
                        	
                    	String query = "INSERT INTO blocked_files (fileName,fileID,block,blockData,diegest_key,user_id) VALUES ('"+fileName+"',"+FileID+",'"+Block3+"','"+lastBlock+"','"+b3+"','"+user_id+"')";
                    	System.out.println("Query 190:"+query);
//    					
                    	st.executeUpdate(query);
                    }
					
                    //File file = new File("D:\\upload\\block3.txt");
                    
//                    if ( file.createNewFile() ) {
//                    	FileWriter fw = new FileWriter(file.getAbsoluteFile());
//                		BufferedWriter bw = new BufferedWriter(fw);
//                		bw.write(lastBlock);
//                		bw.close();
//                     } else {
//                        System.out.println("Failure!");
//                     }
					
					
				}
				
			
				
			}
			File fl= new File("E:\\upload\\"+fileName);
			
			 Statement ss = con.createStatement();
			 //String qry="insert into graphtb3 values fileid='"+FileID+"',file1='"+fl.length()+"',block1='"+s1+"',block2='"+s2+"',block3='"+s3+"',user_id='"+user_id+"'";
			 String qry="insert into graphtb3(fileid,file1,block1,block2,block3,user_id) values ('"+FileID+"','"+fl.length()+"','"+s1+"','"+s2+"','"+s3+"','"+user_id+"')";
			 System.out.println("Query 217:"+qry);
//				
			 ss.executeUpdate(qry);
			 
			//GF.DeleteFile();
			
			Statement stmt21=con.createStatement();
			String qry21 = "SELECT * FROM reg where userid='"+user_id+"'";
			System.out.println("Query 225:"+qry21);
//			
			ResultSet rst21=stmt21.executeQuery(qry21);
			
			while (rst21.next()) {
				
				String privilege=rst21.getString("privilege");
				System.out.println("privilege "+privilege);
				if(privilege.equals("Upload"))
				{
					response.sendRedirect("UploadSuccess1.jsp");
				}
				else if(privilege.equals("Download"))
				{
					System.out.println("here");
					response.sendRedirect("UploadSuccess2.jsp");
				}
				else if(privilege.equals("BOTH"))
				{
					response.sendRedirect("UploadSuccess.jsp");
				}
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
