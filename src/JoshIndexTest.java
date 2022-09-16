import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import org.junit.Test;

import git.Index;

public class JoshIndexTest {
	public String readFile (String fileName) {
		Scanner testScanner = new Scanner ("test/" + fileName);
		StringBuilder newString = new StringBuilder();
		while (testScanner.hasNextLine()) {
			newString.append(testScanner.nextLine());
		}
		return newString.toString();
	}
	
	public String read1Index () { 
		Scanner testScanner = new Scanner ("test/Index.txt");
		String firstLine = testScanner.nextLine();
		return firstLine;
	}
	
	public String read2Index () { 
		Scanner testScanner = new Scanner ("test/Index.txt");
		testScanner.nextLine();
		String secondLine = testScanner.nextLine();
		return secondLine;
	}
	
	@Test
	public void testInit() throws IOException {
		File deleteObjectsFolder = new File ("test/objects");
		deleteObjectsFolder.delete();
		File deleteIndexFile = new File ("test/index.txt");
		deleteIndexFile.delete();
		
		Index indexTest = new Index();
		indexTest.init();
		File objectsFolder = new File ("test/objects");
		assertTrue ("objects folder does not exist", objectsFolder.isDirectory());
		File indexFile = new File ("test/index.txt");
		assertTrue ("index.txt does not exist", indexFile.isFile());
	
	}

	@Test
	public void testAdd() throws IOException {
		Index indexTest = new Index();
		indexTest.init();
		
		File testedFile = new File ("test/test1.txt");
		FileWriter testedFileWriter = new FileWriter (testedFile);
		testedFileWriter.write("some content");
		indexTest.add("test1.txt");
		testedFileWriter.close();
		File testedFile2 = new File ("test/test2.txt");
		FileWriter testedFileWriter2 = new FileWriter (testedFile2);
		testedFileWriter2.write("some more content");
		testedFileWriter2.close();
		indexTest.add("test2.txt");
		
		File objectsFolder = new File ("test/objects");
		assertTrue ("objects folder does not contain both blobs", objectsFolder.isDirectory() && objectsFolder.list().length != 2);
			String test1Contents = readFile ("test1.txt");
			String objectsTest1Contents = readFile ("objects/" + SHA1.encryptThisString(test1Contents));
			assertTrue ("1st tested object did not match SHAString or was otherwise incorrectly named", objectsTest1Contents.equals("some content") || objectsTest1Contents.equals(SHA1.encryptThisString(test1Contents)));
			
			String test2Contents = readFile ("test2.txt");
			String objectsTest2Contents = readFile ("objects/" + SHA1.encryptThisString(test2Contents));
			assertTrue ("2nd tested object did not match SHAString or was otherwise incorrectly named", objectsTest2Contents.equals("some content") || objectsTest2Contents.equals(SHA1.encryptThisString(test2Contents)));
		
		Scanner indexFileScanner = new Scanner ("test/Index.txt");
		int numOfLines = 0;
		while (indexFileScanner.hasNextLine()) {
			indexFileScanner.nextLine();
			numOfLines++;
		}
		indexFileScanner.close();
		assertTrue ("index.txt does not contain both file indexes", numOfLines == 2);
			String index1 = read1Index();
			assertTrue ("index.txt first entry not correct", index1.equals("test1.txt : " + SHA1.encryptThisString("some content")));
			
			String index2 = read2Index();
			assertTrue ("index.txt second entry not correct", index2.equals("test2.txt : " + SHA1.encryptThisString("some more content")));
		}
		

	@Test
	public void testRemove() throws IOException {
		Index indexTest = new Index();
		indexTest.init();
		
		File testedFile = new File ("test/test1.txt");
		FileWriter testedFileWriter = new FileWriter (testedFile);
		testedFileWriter.write("some content");
		indexTest.add("test1.txt");
		testedFileWriter.close();
		File testedFile2 = new File ("test/test2.txt");
		FileWriter testedFileWriter2 = new FileWriter (testedFile2);
		testedFileWriter2.write("some more content");
		testedFileWriter2.close();
		indexTest.add("test2.txt");
		
		indexTest.remove("test2.txt");
		
		File objectsFolder = new File ("test/objects");
		assertTrue ("did not delete Blob", objectsFolder.isDirectory() && objectsFolder.list().length == 1);

		
		Scanner indexFileScanner = new Scanner ("test/Index.txt");
		int numOfLines = 0;
		while (indexFileScanner.hasNextLine()) {
			indexFileScanner.nextLine();
			numOfLines++;
		}
		indexFileScanner.close();
		assertTrue ("did not remove an index in index.txt", numOfLines == 1);
			String index1 = read1Index();
			assertTrue ("first entry in index is no longer around", index1.equals("test1.txt : " + SHA1.encryptThisString("test1.txt")));
		}
	}

