

package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;


public class GlobalFunction {
    
    Connection con = ConnectionManager.getConnection();
   
    Statement st = null;
    Statement st1 = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    private boolean flag;
    
    public String getRole(int id) throws SQLException{
    
        String data = "";
        
        st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from role where id="+id);
        while(rs.next()){
            
            data = rs.getString("roleName");
        
        }
        return data;
    
    }
    public int getTotalCountUpload(String uname) throws SQLException{
        
        int a=0,b=0;
        
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT count(filename) FROM `dedup_data` where userid='"+uname+"'");
        if(rs.next())
        {
            a=rs.getInt(1);
        }
        
        Statement st1 = con.createStatement();
        ResultSet rs1 = st1.executeQuery("SELECT count(filename) FROM `file` where userid='"+uname+"'");
        if(rs1.next())
        {
            b=rs1.getInt(1);
        }
        
        int total=a+b;
        return total;
    
    }
    
    
public int getTotalCountDownload(String uname) throws SQLException
{
        int a=0;
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT count(filename) FROM `downloadedfile` where username='"+uname+"'");
        if(rs.next())
        {
            a=rs.getInt(1);
        }
        return a;
    }
    

    
    
    public static void copyFile(File sourceFile, File destFile) throws IOException {
    	if (!sourceFile.exists()) {
    		return;
    	}
    	if (!destFile.exists()) {
    		destFile.createNewFile();
    	}
    	FileChannel source = null;
    	FileChannel destination = null;
    	source = new FileInputStream(sourceFile).getChannel();
    	destination = new FileOutputStream(destFile).getChannel();
    	if (destination != null && source != null) {
    		destination.transferFrom(source, 0, source.size());
    	}
    	if (source != null) {
    		source.close();
    	}
    	if (destination != null) {
    		destination.close();
    	}
    }
    
    
    public void DeleteFile() throws IOException {
    	
    	File dir = new File("D://upload//");

    	if(dir.isDirectory()){
			
    		if(dir.list().length>0){
    				
    			for (File file: dir.listFiles()) {
    	            if (!file.isDirectory()) file.delete();
    	        }
    				
    		}else{
    				
    			System.out.println("Directory is empty!");
    				
    		}
    	
    	
    	}
    	
    }
    
    
    
   public String getFullName(int Userid) throws SQLException{
   
       String data="";
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select * from user where id='"+Userid+"'");
       if(rs.next()){
       data = rs.getString("Fullname");
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
       
   }
   
   
  public int getLatestFileID() throws SQLException{
   
       int data=0;
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select id from file ORDER BY id DESC LIMIT 1");
       if(rs.next()){
       data = rs.getInt("id");
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
       
   }
  
  public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
			throws NoSuchAlgorithmException, IOException {

		md.reset();
		byte[] bytes = new byte[byteArraySize];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			md.update(bytes, 0, numBytes);
		}
		byte[] digest = md.digest();
		String result = new String(Hex.encodeHex(digest));
		return result;
	}
  
  public String getLatestUserID() throws SQLException{
	   
      String data="";
      try{
      st = con.createStatement();
      ResultSet rs = st.executeQuery("select id from user ORDER BY id DESC LIMIT 1");
      if(rs.next()){
      data = rs.getString("id");
      }
  }catch (SQLException e) {
           System.out.println("Error:" + e);
       } finally {
           if (st != null) {
               st.close();
           }
           if (rs != null) {
               rs.close();
           }
       }
       return data;
      
  }
  public static void CreateDirectory(String FolderName)
  {	
	File file = new File("C:\\upload\\"+FolderName);
	if (!file.exists()) {
		if (file.mkdir()) {
			System.out.println("Directory is created!");
		} else {
			System.out.println("Failed to create directory!");
		}
	}
  }
   
      public String getUserEmail(int Userid) throws SQLException{
   
       String data="";
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select email from user where id='"+Userid+"'");
       if(rs.next()){
       data = rs.getString("email");
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
       
   }
   
   public String getRoleName(int RoleID) throws SQLException{
   System.out.println("---------RoleID------"+RoleID);
       String data="";
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select roleName from role where id="+RoleID);
       if(rs.next()){
       data = rs.getString("roleName");
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
       
   }
      
   public String getUserRole(int Userid) throws SQLException{
   
       String data="";
       int UserRoleID=0;
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select UserRole from user where id="+Userid);
       if(rs.next()){
       UserRoleID = rs.getInt("UserRole");
       
       
       data = getRoleName(UserRoleID);
       }
       
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
       
   }
   
    public int getUserRoleID(int Userid) throws SQLException{
   
       int data=0;
       int UserRoleID=0;
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select UserRole from user where id="+Userid);
       if(rs.next()){
       data = rs.getInt("UserRole");
       
       
       //data = getRoleName(UserRoleID);
       }
       
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
       
   }
      
   
      
   public String getFileName(int Fileid) throws SQLException{
   
       String data="";
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select fileName from userfile where id="+Fileid);
       if(rs.next()){
       data = rs.getString("fileName");
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
       
   }
      
   public String getFileExtention(File file){
   
       String name = file.getName();
       try{
       return name.substring(name.lastIndexOf(".") + 1);
       } catch(Exception e){
       return "";
       }
       
       
   }
   
   public boolean checkAdminLogin(String username,String password) throws SQLException{
   
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select * from admin where name='"+username+"' and password='"+password+"'");
       if(rs.next()){
       return true;
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;       
   }
   
   public int checkAuthorised(int fileID,int UserRol) throws SQLException{
       int data=0;
       try{
       st = con.createStatement();
       String strss1 = Integer.valueOf(UserRol).toString();
       ResultSet rs = st.executeQuery("select * from userfile where id="+fileID);
       if(rs.next()){
           String[] Authorised = rs.getString("Authorised").split(",");
          for(int i=0;i<Authorised.length;i++){
            
              
              String strss = Authorised[i];
              if(strss.equalsIgnoreCase(strss1)){
              
                  data = Integer.parseInt(strss);
                  
               return data;
              }
              
             
              //if(data==UserRol){                  
                  
              
             // } 
              
          }         
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;       
   }


   public boolean VerifySecretCode(int id,String SecretCode) throws SQLException{
   
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select * from user where id="+id+" and SecretKey='"+SecretCode+"'");
       if(rs.next()){
       return true;
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;       
   }
   
   public boolean VerifyFileSecretCode(int Fileid,String SecretCode) throws SQLException{
   
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select * from userfile where id="+Fileid+" and SecretKey='"+SecretCode+"'");
       if(rs.next()){
       return true;
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;       
   }
   
   
   public void setEncryptionTime(int fid,float ExcutionTime) throws SQLException{
   
       try{
       st = con.createStatement();
       String sqlss = "INSERT INTO encryption(fileId,ExcutionTime) VALUE("+fid+","+ExcutionTime+")";
       System.out.println("enr========="+sqlss);
       st.executeUpdate(sqlss);
       
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        
   }
   
   public void setDecryptionTime(int fid,float ExcutionTime) throws SQLException{
   
       try{
       st = con.createStatement();
       String sqlss = "INSERT INTO decryption(fileId,ExcutionTime) VALUE("+fid+","+ExcutionTime+")";
       System.out.println("enr========="+sqlss);
       st.executeUpdate(sqlss);
       
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        
   }
   
   
   public String getSecretCode(int id) throws SQLException{
   
       String data="";
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select SecretKey from user where id="+id);
       if(rs.next()){
           data = rs.getString("SecretKey");
       return data;
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;       
   }
   
   
   public int getFromUser(int Requestid) throws SQLException{
   
       int data=0;
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select fromUser from filerequest where id="+Requestid);
       if(rs.next()){
           data = rs.getInt("fromUser");
       return data;
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;       
   }
   
   public int getRequestStatus(int Requestid) throws SQLException{
   
       int data=0;
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select status from filerequest where id="+Requestid);
       if(rs.next()){
           data = rs.getInt("status");
       return data;
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;       
   }
   public String getRequestDate(int Requestid) throws SQLException{
   
       String data="";
       try{
       st = con.createStatement();
       ResultSet rs = st.executeQuery("select req_date from filerequest where id="+Requestid);
       if(rs.next()){
           data = rs.getString("req_date");
       return data;
       }
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;       
   }
   
   public void setGraphTime(int fid,float uploadTime,float EncryptTime,String user_id) throws SQLException{
 	   
       try{
       st = con.createStatement();
       String sqlss = "INSERT INTO graphtbl(fileid,uploadTime,EncryptTime,user_id) VALUE("+fid+","+uploadTime+","+EncryptTime+",'"+user_id+"')";
       //System.out.println("enr========="+sqlss);
       st.executeUpdate(sqlss);
       
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        
   }
 public void setGraphDownloadTime(int fid,float uploadTime,float EncryptTime,String user_id) throws SQLException{
 	   
       try{
    	   
    	   
    	   st1 = con.createStatement();
           ResultSet rs1 = st1.executeQuery("select * from graphtbl2 where fileid="+fid);
           if(!rs1.next()){
        	   
           
       st = con.createStatement();
       String sqlss = "INSERT INTO graphtbl2(fileid,downloadTime,DecryptTime,user_id) VALUE("+fid+","+uploadTime+","+EncryptTime+",'"+user_id+"')";
       //System.out.println("enr========="+sqlss);
       st.executeUpdate(sqlss);
           }
       
   }catch (SQLException e) {
            System.out.println("Error:" + e);
        }
       
        
   }
 
 public String getStatus(String id)
	{
		String pname = "";
		try 
		{
			Connection con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM `spacereq` where id='"+ id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				id = rs.getString("status");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception " + e);
		}
		return pname;
	}
 
 
}