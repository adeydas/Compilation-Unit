package ws.abhis.utils.compilationunit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;

import junit.framework.TestCase;

public class StartCompilationTest extends TestCase {
	private String code = "package pack;\n\n" 
					+ "import pack.DepClass;\n"
					+ "public class TestClass {\n"
					+ "public static void main(String[] args) {\n"
					+ "\tSystem.out.println(\"Hello World\");\n"
					+ "\t}\n}";
	
	private String depCode = "package pack; \n\n"
					+ "public class DepClass {\n"
					+ "//Nothing here\n"
					+ "}\n";
	
	
	
	public void testStartCompilation() throws IOException, InterruptedException {
		String startPath = "target" + File.separator + "testproj" + File.separator ;
		File strPath = new File(startPath);
		if (!strPath.exists()) {
			strPath.mkdir();
		}
		String packageDirStr = startPath + "pack";
		File packageDir = new File(packageDirStr);
		if (!packageDir.exists()) {
			packageDir.mkdir();
		}
		File classFile = new File(packageDirStr + File.separator + "TestClass.java");
		if (!classFile.exists()) {
			classFile.createNewFile();
		}
		PrintWriter out = new PrintWriter(classFile);
		out.println(code);
		out.close();
		
		File depClassFile = new File (packageDirStr + File.separator + "DepClass.java");
		if (!depClassFile.exists()) {
			depClassFile.createNewFile();
		}
		PrintWriter out2 = new PrintWriter(depClassFile);
		out2.println(depCode);
		out2.close();
		
		
		StartCompilation obj = new StartCompilation();
		obj.start(packageDirStr, packageDirStr, packageDirStr);
	}
}
