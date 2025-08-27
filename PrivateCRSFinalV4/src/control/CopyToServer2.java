package control;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyToServer2 
{
	
	public void CopyToFolde2(String fileName){
		InputStream inStream = null;
		OutputStream outStream = null;
			
		
	    	try{
	    		
	    	    File afile =new File("E:\\upload\\"+fileName);
	    	    File bfile =new File("E:\\upload\\DownloadReceiver\\enc_"+fileName);
	    		
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	        	
	    	    byte[] buffer = new byte[1024];
	    		
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	    	  
	    	    	outStream.write(buffer, 0, length);
	    	 
	    	    }
	    	 
	    	    inStream.close();
	    	    outStream.close();
	    	    
	    	   
	    	    
	    	    System.out.println("File is Regenerate successfully!");
	    	    
	    	}catch(IOException e){
	    	    e.printStackTrace();
	    	}
	}
	
	public void CopyToFolde1(String fileName){
		InputStream inStream = null;
		OutputStream outStream = null;
			
		
	    	try{
	    		
	    	    File afile =new File("E:\\upload\\Plain_"+fileName);
	    	    File bfile =new File("E:\\upload\\DownloadUser\\"+fileName);
	    		
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	        	
	    	    byte[] buffer = new byte[1024];
	    		
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	    	  
	    	    	outStream.write(buffer, 0, length);
	    	 
	    	    }
	    	 
	    	    inStream.close();
	    	    outStream.close();
	    	    
	    	   
	    	    
	    	    System.out.println("File is Regenerate successfully!");
	    	    
	    	}catch(IOException e){
	    	    e.printStackTrace();
	    	}
	}
    public static void main(String[] args)
    {	
    	CopyToServer2 c = new CopyToServer2();
    	c.CopyToFolde2("design.txt");
    	
    }
}
