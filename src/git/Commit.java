package git;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commit {
    String pTree;
	String summary;
	String author;
	String date;
	String prev = null;
	String next = null;
	public Commit(String pTree, String summary,String author, String parentPointer) throws FileNotFoundException
	{
		
		this.pTree = pTree;
		this.prev = parentPointer;
		
		this.summary = summary;
		this.author = author;
		this.date = getDate();
		writeCommitFile();
		
	}
	
	public String subsetFileContents()
	{
		return summary+ "\n" + date + "\n" + author + "\n" + prev;
	}
	
	public void writeCommitFile() throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter("test/objects/" + encryptThisString(subsetFileContents()));
		pw.append(pTree + "\n");
		pw.append(prev + "\n");
		pw.append(next + "\n");
		pw.append(author + "\n");
		pw.append(date + "\n");
		pw.append(summary);
		pw.close();
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
	
	public String getDate()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(new Date());
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		String data = df.format(new Date());
//		System.out.println(data);
		Commit theMit = new Commit(null,"yuh","Jbao","test/objects/1dc0eea45513abfb82439a406563352f1adc697e");
	}

}
