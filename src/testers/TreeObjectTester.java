package testers;
import java.io.IOException;
import java.util.ArrayList;

import git.TreeObject;

public class TreeObjectTester {

	public static void main(String[] args) throws IOException {
		ArrayList<String> b = new ArrayList<String>();
		b.add("a");
		b.add("b");
		b.add("c");
		TreeObject a = new TreeObject(b);
		
	}

}
