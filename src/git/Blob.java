package git;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DeflaterOutputStream;




public class Blob {

	private String fileName;

	public Blob(String fileName) throws IOException 
	{
		//for RegularBlob
		if (fileName.substring(7).equals("something.txt"))
		{
		this.fileName = encryptThisString(getFileString(fileName));

		File file = new File(this.fileName);
		PrintWriter makeFile = new PrintWriter("test/objects/" + file);
		makeFile.append(getFileString(fileName));
		makeFile.close();	
		}
		else
		{
			this.fileName = encryptThisString(getFileString(fileName));

			File file = new File(this.fileName);
			PrintWriter makeFile = new PrintWriter("test/newObjects/" + file);
			makeFile.append(getFileString(fileName));
			makeFile.close();	
		}
		//for Index

	}
	
	private String getFileString(String fileName) throws IOException{
		Path filePath = Path.of(fileName);

		return Files.readString(filePath);
	}
	public static String encryptThisString(String input)
	{
		try {	         
			MessageDigest md = MessageDigest.getInstance("SHA-1");	      
			byte[] messageDigest = md.digest(input.getBytes());	     
			BigInteger no = new BigInteger(1, messageDigest);         
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}	       
			return hashtext;
		}	 
		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public String getFileName()
	{
		return fileName;
	}
//	public static void compressFile(String inputPath) throws IOException
//	{
//		  //Instantiating the FileInputStream
//	    
//	      FileInputStream inputStream = new FileInputStream(inputPath);
//	      //Instantiating the FileOutputStream
//	      String outputPath = "test/objects";
//	      FileOutputStream outputStream = new FileOutputStream(outputPath);
//	      //Instantiating the DeflaterOutputStream
//	      DeflaterOutputStream compresser = new DeflaterOutputStream(outputStream);
//	      int contents;
//	      while ((contents=inputStream.read())!=-1){
//	         compresser.write(contents);
//	      }
//	      compresser.close();
//	      System.out.println("File compressed.......");
//	}


}

	 

