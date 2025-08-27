/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.ConnectionManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Godwit
 */
public class FileSend extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        doPost(request, response);
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet FileSend</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FileSend at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        
        
        
        
         String useremail= "";
        String friendemail= "";
        HttpSession session  = request.getSession(true);
                String email = session.getAttribute("emailid").toString(); 
                System.out.println("Your Mail Id : "+email );
            try{    
                
                PrintWriter out = response.getWriter();
                
                ConnectionManager cm = new ConnectionManager();
            Statement st = cm.statement();
            
                     
                      DiskFileItemFactory factory1 = new DiskFileItemFactory();
                       ServletFileUpload sfu1  = new ServletFileUpload(factory1);
            
                       System.out.println(sfu1);
                
                       
                 if (! ServletFileUpload.isMultipartContent(request)) 
                    {
                System.out.println("sorry. No file uploaded");
                return;
                 }

             // parse request
                      List items = sfu1.parseRequest(request);

            // get uploaded file
                           FileItem file = (FileItem) items.get(0);
                        
            String fileName = file.getName();
     
            
//String itemName = file.getName();
   File savedFile = new File("E:\\cc.txt");//config.getServletContext().getRealPath("/")+"emp_image\\image\\"+itemName);
   file.write(savedFile);
   
   FileReader f= new FileReader("E:\\cc.txt");
        BufferedReader br = new BufferedReader(f);
        StringBuffer sb = new StringBuffer();
        String line ="";
        while((line = br.readLine()) != null)
                       {
            sb.append(line);
        }
        String message = sb.toString();
//        encrypt("E:\\cc.txt");
        
      // File fil = new File("E:\\cc.txt.txt");
        String pthj = "E:\\cc.txt.txt";
        
        
        
         FileReader fr = new FileReader(new File("E:\\cc.txt"));
                           BufferedReader brre = new BufferedReader(fr);
//                           String  line =null;
                           String  line1 =null;
                           StringBuffer ssb = new StringBuffer();
                                                              
                           while((line=brre.readLine())!= null)
                             {
                              ssb.append(line) ;
                           }
                                 line1 = ssb.toString();    
                        System.out.println("buferd bakeuvd "+line1);
                        
//    sss = session.getAttribute("emailid").toString(); 
        
        
        
         byte[] encr=null;
                          try {
                               encryptData ed=new encryptData();
                                encr= ed.enData(line1,email);
                                System.out.println(line1);
                                /////////encrypt data first
                              } catch (IOException ex) {
                               ex.printStackTrace(); //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                           } 
     System.out.println("encr"+encr);
                         ////////////////////////////save encrypted file
                         //  String path=System.getProperty("user.dir");
                           File pub = new File("D://serv");


                           if(!pub.isDirectory())
                           {
                               pub.mkdir();
                           }
                           FileOutputStream fos = new FileOutputStream("D://aa_encrypt.txt");
                           
                           //System.out.println("fos:"+pub+"\\"+sessionemail+"_encrypt.txt");
                           fos.write(encr);
                           fos.close();
                           File fil = new File("D://aa_encrypt.txt");
                           FileInputStream fis = new FileInputStream(new File("D://aa_encrypt.txt"));
        
//        new FileEncryptor("DES/ECB/PKCS5Padding","E:\\cc.txt").encrypt();
   
//response.sendRedirect("reply11.jsp");
        
        
        
        
        
//         ArrayList<String> friendList = new ArrayList<String>();
//        friendList.clear();
////        friendList fl = new friendList();
//        fl.doGet(request, response);
//        friendList = fl.friendList;
//        if(friendList.size()==0)
//        {
//       
//            System.out.println("No Friend is in List");
//       
//        }
//        else
//        {
//            for(int i=0;i<friendList.size();i++)
//            {
//                String friendli = friendList.get(i);
//                String[] sp = friendli.split("-");
//                String friendname=sp[0];
//                String friendemail1=sp[1];
//                FileInputStream fis;
//                        
//                File fil = new File(pthj);
//      fis = new FileInputStream(fil);
//            
////                
                     Class.forName("com.mysql.jdbc.Driver");
                        String connectionURL = "jdbc:mysql://localhost:3306/cloud_deduplication?relaxAutoCommit=true";
                          Connection con = DriverManager.getConnection(connectionURL, "root", "");

                         PreparedStatement ps = con.prepareStatement("insert into data_file(file_title,file) values(?,?)");
          
                          //String query3 = " insert into image() ";
            
                      //  PreparedStatement ps = con.prepareStatement("insert into image values(?,?,?) where sender_mail=?");

                         System.out.println(ps);
                 
                        ps.setString(1, email);  
//                          ps.setString(2, friendemail1);  
                          
                           ps.setAsciiStream(2, fis, (int) fil.length());
 
            
                          
                    // ps.setBinaryStream(3, fil.getInputStream(), (int) fil.getSize());
                 //    ps.setInt(4, 25);
                      ps.executeUpdate();
                    con.commit();
                      con.close(); 
//                        
//                
//                
//                
//            
             
            
   
    
   
         out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
                            "        <title>alert</title>" +
                            "        <script>" +
                            "            function fun(){alert('Photo Send To Wall Successfully...'); window.location.href='homepage.jsp'}" +
                            "        </script>" +
                            "    </head><body onload='fun();'></body></html>");
        
        
        
        
        
            } catch (Exception ex) { 
            Logger.getLogger(FileSend.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        
        
        
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
     public void encrypt(String path) throws Exception{
         //generating key
         String algo="DES/ECB/PKCS5Padding";
         byte k[] = "HignDlPs".getBytes();  
         SecretKeySpec key = new SecretKeySpec(k,algo.split("/")[0]); 
         //creating and initialising cipher and cipher streams
         Cipher encrypt =  Cipher.getInstance(algo); 
         encrypt.init(Cipher.ENCRYPT_MODE, key);
         //opening streams
         FileOutputStream fos =new FileOutputStream(path+".txt");
         System.out.println("FOS:"+fos);
         System.out.println("FOS:"+path);
            try{
                FileInputStream fis =new FileInputStream(path);
                CipherOutputStream cout=new CipherOutputStream(fos, encrypt);
                System.out.println(cout);
                copy(fis,cout);
              
         }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
     }
     private void copy(InputStream is,OutputStream os) throws Exception{
     byte buf[] = new byte[4096];  //4K buffer set
     int read = 0;
     while((read = is.read(buf)) != -1)  //reading
        os.write(buf,0,read);  //writing
  }
      
}
