import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import git.Blob;

public class JoshBlobTest {

	@Test
	public void testBlob() throws IOException {
		File testedFile = new File ("joshtest.txt");
		FileWriter testedFileWriter = new FileWriter (testedFile);
		testedFileWriter.write("some content");
		
		Blob testBlob = new Blob ("joshtest.txt");
		assertNotNull ("Blob was null", testBlob);
	}
}
