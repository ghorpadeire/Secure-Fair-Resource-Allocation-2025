/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class MyClass {

    public static void main(String[] args) {
         String path = "D:\\DNA.txt";
        
         String path1 = "D:\\DNA.txt";
        try {
            String hash = FileUtil.getChecksum(path, "MD5"); //or "SHA-256", "SHA-384", "SHA-512"  
             String hash1 = FileUtil.getChecksum(path1, "MD5"); //or "SHA-256", "SHA-384", "SHA-512"  
            System.out.println(hash);
             System.out.println(hash1);
             
             if(hash.equals(hash1))
             {
                 System.out.println("equal hash content");
             }else
             {
                 System.out.println("not equal hash content");
             }   
             byte arr[]=hash.getBytes();
             
              byte[] encodedKey     = Base64.decode(hash);
             System.out.println("arr[]"+encodedKey.length); 
              SecretKey originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES"); //EDIT: missing 'new'
              
              
               byte[] encodedKey1     = Base64.decode(hash1);
              SecretKey originalKey1 = new SecretKeySpec(encodedKey1, 0, encodedKey1.length, "DES"); //EDIT: missing 'new'
              
            
              System.out.println("originalKey"+originalKey);
              System.out.println("originalKey"+originalKey1);
        } catch (Exception e) {
            System.out.println("An error occured.");
        }
        
        
    }
}
