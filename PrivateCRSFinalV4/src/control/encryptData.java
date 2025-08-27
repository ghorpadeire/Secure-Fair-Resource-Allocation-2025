package control;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.crypto.Cipher;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Godwit-3
 */
public class encryptData {
    String email1;
    
    public byte[] enData(String data,String name) throws IOException {
  System.out.println("\n----------------ENCRYPTION STARTED------------");
  
  System.out.println("Data Before Encryption :" + data);
  byte[] dataToEncrypt = data.getBytes();
  byte[] encryptedData = null;
  email1 = name;
  
  try {
   PublicKey pubKey = readPublicKeyFromFile();
   Cipher cipher = Cipher.getInstance("RSA");
   cipher.init(Cipher.ENCRYPT_MODE, pubKey);
   encryptedData = cipher.doFinal(dataToEncrypt);
   System.out.println("Encryted Data: " + encryptedData);
   
  } catch (Exception e) {
   e.printStackTrace();
  } 
  
  System.out.println("----------------ENCRYPTION COMPLETED------------");  
  return encryptedData;
 }
   
  public PublicKey readPublicKeyFromFile() throws IOException{
      
      
      ///////////////////
     try{
       Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/cloud_deduplication", "root", "");
      String sql = "SELECT public_key FROM registration where username='"+email1+"'";
      System.out.println(sql);
      
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet resultSet = stmt.executeQuery();
    while (resultSet.next()) {
//      String name = resultSet.getString(1);
//      String description = resultSet.getString(2);
         String path=System.getProperty("user.dir");
      File pub = new File("D://serverkey");
      
       
      if(!pub.isDirectory())
      {
           System.out.println("folder created");
          pub.mkdir();
      } 
      FileOutputStream fos = new FileOutputStream(pub+"//aa_public_key.key");
      System.out.println(pub+"//aa_public_key.key");

      byte[] buffer = new byte[1];
      InputStream is = resultSet.getBinaryStream("public_key");
      while (is.read(buffer) > 0) {
        fos.write(buffer);
      }
      fos.close();
    }
    conn.close();
     }catch(Exception e){}
      ///////////////////
  FileInputStream fis = null;
  ObjectInputStream ois = null;
  try {
      String path=System.getProperty("user.dir");
      File f=new File("D://serverkey");
         
   fis = new FileInputStream(f+"//aa_public_key.key");
   ois = new ObjectInputStream(fis);
   
   BigInteger modulus = (BigInteger) ois.readObject();
      BigInteger exponent = (BigInteger) ois.readObject();
   
      //Get Public Key
      RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);
      KeyFactory fact = KeyFactory.getInstance("RSA");
      PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);
            
      return publicKey;
      
  } catch (Exception e) {
   e.printStackTrace();
  }
  finally{
   if(ois != null){
    ois.close();
    if(fis != null){
     fis.close();
    }
   }
  }
  return null;
 }
}
