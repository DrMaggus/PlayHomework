package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {

	public static final List<String> allowedContentTypes = 
			Collections.unmodifiableList(Arrays.asList
					("application/java-vm", 
							"application/x-java",
							"text/plain",
							"text/html"));

	//Takes a File-object and saves it to 'path'
	//path should contain the filename !
	public static void saveFile(File file,String path) throws IOException {
		FileWriter fstream = new FileWriter(path);
		BufferedWriter out = new BufferedWriter(fstream);
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String line = "", content = "";
		while ((line = bReader.readLine()) != null) 
			content += line+"\n";
		out.write(content);
		out.close();
		bReader.close();
		fstream.close();
	}

	//Lists all files contained in a folder
	//Not used yet...
	public static ArrayList<String> listFilesofDirectory(String path) {
		ArrayList<String> files = new ArrayList<String>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 

		for (int i = 0; i < listOfFiles.length; i++) 
			if (listOfFiles[i].isFile()) 
				files.add(listOfFiles[i].getName());
		
		return files;
	}
	
	public static boolean containsSpecialChar(String str, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		return m.find();
	}
	public static boolean containsSpecialChar(String str) {
		Pattern p = Pattern.compile("[^A-Za-z0-9.()_\\S]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		return m.find();
	}
}
