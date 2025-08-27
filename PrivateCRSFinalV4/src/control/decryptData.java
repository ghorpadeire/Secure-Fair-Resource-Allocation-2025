package control;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
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
public class decryptData {
    String nn;
    
    public String decData(byte[] data,String name) throws IOException {
        
        System.out.println("name"+name);
  System.out.println("\n----------------DECRYPTION STARTED------------");
  byte[] descryptedData = null;
  String dec=null;
  nn = name;
  try {
   PrivateKey privateKey = readPrivateKeyFromFile();
   Cipher cipher = Cipher.getInstance("RSA");
   cipher.init(Cipher.DECRYPT_MODE, privateKey);
   System.out.println("data::"+data);
   descryptedData = cipher.doFinal(data);
   dec=new String(descryptedData);
   System.out.println("Decrypted Data: " + new String(descryptedData));
   
  } catch (Exception e) {
   e.printStackTrace();
  } 
  
  System.out.println("----------------DECRYPTION COMPLETED------------"); 
  return dec;
 }
    
    public PrivateKey readPrivateKeyFromFile() throws IOException{
        
        ///////////////////
        
         String path=System.getProperty("user.dir");
      File pub = new File("D://serverkey");
     try{
       Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/cloud_deduplication", "root", "");
//          System.out.println(MainFrame.uu);
     String sql = "SELECT private_key FROM registration where username='"+nn+"'";
       System.out.println(sql);
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet resultSet = stmt.executeQuery();
    while (resultSet.next()) {
//      String name = resultSet.getString(1);
//      String description = resultSet.getString(2);
//         String path=System.getProperty("user.dir");
//      File pub = new File(path+"//serverkey");
      
       
      if(!pub.isDirectory())
      {
          pub.mkdir();
      } 
      FileOutputStream fos = new FileOutputStream(pub+"\\"+nn+"private_key.key");
      System.out.println(pub+"\\"+nn+"private_key.key");

      byte[] buffer = new byte[1];
      InputStream is = resultSet.getBinaryStream("private_key");
      while (is.read(buffer) > 0) {
        fos.write(buffer);
        System.out.println();
      }
      fos.close();
    }
    conn.close();
     }catch(Exception e){}
      ///////////////////
        
        
  FileInputStream fis = null;
  ObjectInputStream ois = null;
  try {
     
   //fis = new FileInputStream(new File(pub+"\\"+nn+"private_key.key"));  D:\serverkey
      fis = new FileInputStream(new File(pub+"\\"+nn+"private_key.key"));
   ois = new ObjectInputStream(fis);
   
   BigInteger modulus = (BigInteger) ois.readObject();
      BigInteger exponent = (BigInteger) ois.readObject();
   //System.out.println("rtfjmnfgxmfdhg");
      //Get Private Key
      RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
      KeyFactory fact = KeyFactory.getInstance("RSA");
      PrivateKey privateKey = fact.generatePrivate(rsaPrivateKeySpec);
            System.out.println("privateKey ::"+privateKey.toString());
      return privateKey;
      
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
