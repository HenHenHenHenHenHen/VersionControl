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
	@Test
	public void testInit() throws IOException {
		File deleteObjectsFolder = new File ("test/objects");
		deleteObjectsFolder.delete();
		File deleteIndexFile = new File ("test/index.txt");
		deleteIndexFile.delete();
		
		Index indexTest = new Index();
		indexTest.init();
		File objectsFolder = new File ("test/objects");
		if (!objectsFolder.isDirectory()) {
			fail ("objects folder does not exist");
		} 
		File indexFile = new File ("test/index.txt");
		if (!indexFile.isFile()) {
			fail ("index.txt does not exist");
		}
		
		
		
		
	
	}

	@Test
	public void testAdd() throws IOException {
		Index indexTest = new Index();
		indexTest.init();
		
		File testedFile = new File ("test1.txt");
		FileWriter testedFileWriter = new FileWriter (testedFile);
		testedFileWriter.write("some content");
		indexTest.add("test1.txt");
		testedFileWriter.close();
		File testedFile2 = new File ("test2.txt");
		FileWriter testedFileWriter2 = new FileWriter (testedFile2);
		testedFileWriter2.write("some more content");
		testedFileWriter2.close();
		indexTest.add("test2.txt");
		
		File objectsFolder = new File ("test/objects");
		if (objectsFolder.isDirectory() && objectsFolder.list().length != 2) {
			fail ("objects folder not updated with Blobs");
		}
		
		Scanner indexFileScanner = new Scanner ("test/index.txt");
		int numOfLines = 0;
		while (indexFileScanner.hasNextLine()) {
			indexFileScanner.nextLine();
			numOfLines++;
		}
		indexFileScanner.close();
		if (numOfLines != 2) {
			fail ("index.txt does not contain both file indexes");
		}
	}

	@Test
	public void testRemove() throws IOException {
		Index indexTest = new Index();
		indexTest.init();
		
		File testedFile = new File ("test1.txt");
		FileWriter testedFileWriter = new FileWriter (testedFile);
		testedFileWriter.write("some content");
		indexTest.add("test1.txt");
		testedFileWriter.close();
		File testedFile2 = new File ("test2.txt");
		FileWriter testedFileWriter2 = new FileWriter (testedFile2);
		testedFileWriter2.write("some more content");
		testedFileWriter2.close();
		indexTest.add("test2.txt");
		
		indexTest.remove("test2.txt");
		
		File objectsFolder = new File ("test/objects");
		if (objectsFolder.isDirectory() && objectsFolder.list().length != 1) {
			fail ("did not delete Blob");
		}
		
		Scanner indexFileScanner = new Scanner ("test/index.txt");
		int numOfLines = 0;
		while (indexFileScanner.hasNextLine()) {
			indexFileScanner.nextLine();
			numOfLines++;
		}
		indexFileScanner.close();
		if (numOfLines != 1) {
			fail ("did not remove an index in index.txt");
		}
	}

}
