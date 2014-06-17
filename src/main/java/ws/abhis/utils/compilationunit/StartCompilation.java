package ws.abhis.utils.compilationunit;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartCompilation {
	
	private String destinationPath;
	private String depPath;
	
	public void start(String sourcePath, String destinationPath, String depPath)
			throws IOException, InterruptedException {	
		this.destinationPath = destinationPath;
		this.depPath = depPath;
		
		File f = new File(sourcePath);
		listFile(f.listFiles());
		
	}
	
	private void compileCode(String fileName) throws IOException, InterruptedException {
		String cmd = "javac -cp " + this.depPath + " " + fileName + " -d " + this.destinationPath;
		System.out.println(cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
	}

	private void listFile(File[] files) throws IOException, InterruptedException {
		for (File file : files) {
			if (file.isDirectory()) {
				listFile(file.listFiles());
			} else {

				String fl = file.getAbsolutePath();
				if (fl.endsWith(".java")) {
					compileCode(file.getPath());
				}
			}
		}
	}

}
