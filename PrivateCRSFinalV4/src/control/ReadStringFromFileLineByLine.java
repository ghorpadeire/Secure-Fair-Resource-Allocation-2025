/*****************************************************************/
/* Copyright 2013 Code Strategies                                */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/

package control;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadStringFromFileLineByLine {

	public static void main(String[] args) {
		try {
			File dir = new File(".");
			 
			String source = "D:\\temp\\files\\swapnil.txt";
			String dest ="D:\\temp\\files\\swapnil1.txt";
	 
			File fin = new File(source);
			FileInputStream fis = new FileInputStream(fin);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
	 
			FileWriter fstream = new FileWriter(dest, true);
			BufferedWriter out = new BufferedWriter(fstream);
	 
			String aLine = null;
			while ((aLine = in.readLine()) != null) {
				//Process each line and add output to Dest.txt file
				out.write(aLine);
				out.newLine();
			}
	 
			// do not forget to close the buffer reader
			in.close();
	 
			// close buffer writer
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
