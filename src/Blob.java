import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Blob {
	
	String fileName;
	
	public Blob(String fileName) throws IOException 
	{
		this.fileName = encryptThisString(getFileString(fileName));
		
		File file = new File(this.fileName);
		PrintWriter makeFile = new PrintWriter("test/objects/" + file);
		makeFile.append(getFileString(fileName));
		makeFile.close();	
		
	
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
	 

}
	 
	 

