/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;



import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.security.MessageDigest;

public class AESFile 
{
public static String algorithm = "AES";
//private static byte[] keyValue=new byte[] {'0','2','3','4','5','6','7','8','9','1','2','3','4','5','6','7'};// your key
   static Key key;
// Performs Encryption
    public static String encrypt(String plainText) throws Exception 
    {
          System.out.println("ke:"+key);
          //  Key key = generateKey();
            Cipher chiper = Cipher.getInstance(algorithm);
            chiper.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = chiper.doFinal(plainText.getBytes());
            String encryptedValue = new BASE64Encoder().encode(encVal);
            return encryptedValue;
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception 
    {
          System.out.println("ke:"+key);
            // generate key 
           // Key key = generateKey();
            Cipher chiper = Cipher.getInstance(algorithm);
            chiper.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
            byte[] decValue = chiper.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
    }

//generateKey() is used to generate a secret key for AES algorithm
    public static Key generateKey(byte[] kval,String algo) throws Exception 
    {
            key = new SecretKeySpec(kval, algo);
            return key;
    }

    // performs encryption & decryption 
    public static void main(String[] args) throws Exception 
    {
        FileReader file = new FileReader("E:\\demo.txt");
        
         String path = "E:\\demo.txt";
        BufferedReader reader = new BufferedReader(file);
        String text = "";
        String line = reader.readLine();
            while(line!= null)
                {
                    text += line;
            line = reader.readLine();
                }
                reader.close();
            System.out.println(text);

            
            byte[] b1 = createChecksum(path, "MD5");  
            System.out.println("b1 lenght"+b1.length);
             System.out.println("b length"+b1.length);
      System.out.println("b::"+b1);
      
      String instr=new String(b1,"UTF-8");
      System.out.println("instr::"+instr);
      
      byte[] bg=instr.getBytes();
      System.out.println("bg length"+bg.length);
      System.out.println("bg::"+bg);
      
      
          generateKey(b1,"AES");
             
            System.out.println("ke:"+key);
            String plainText = text;
            String encryptedText = AESFile.encrypt(plainText);
            String decryptedText = AESFile.decrypt(encryptedText);

            System.out.println("Plain Text : " + plainText);
            System.out.println("Encrypted Text : " + encryptedText);
            System.out.println("Decrypted Text : " + decryptedText);
    }
    
    
    public static byte[] createChecksum(String filename, String algo) throws Exception{  
        System.out.println("in createChecksum");
        System.out.println("filename"+filename);
        System.out.println("algo"+algo);
      FileInputStream fis = new FileInputStream(filename);  
       System.out.println("fis"+fis);
      byte[] buffer = new byte[1024];  
       System.out.println("buffer"+buffer);
      MessageDigest complete = MessageDigest.getInstance(algo); //One of the following "SHA-1", "SHA-256", "SHA-384", and "SHA-512"  
       System.out.println("complete"+complete);
      int numRead;  
      do {  
           numRead = fis.read(buffer);  
           if (numRead > 0) {  
                complete.update(buffer, 0, numRead);  
                 System.out.println("numRead"+numRead);
           }  
      } while (numRead != -1);  
      fis.close();  
      return complete.digest();  
 } 
}

