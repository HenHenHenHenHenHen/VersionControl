import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import git.Index;

public class JoshIndexTest {
	@Test
	public void testInit() throws IOException {
		Index indexTest = new Index();
		indexTest.init();
		File objectsFolder = new File ("test/objects");
		if (!objectsFolder.isDirectory()) {
			fail ("objects folder does not exist");
		} 
		
		
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
		
	
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

}
