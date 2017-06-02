package thibaud.compiler.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

	public static String readLine(String path, int lineN) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(path + ".txt")));
			String line = null;
			int index = 0;
			while ((line = br.readLine()) != null && index != lineN)
				index++;
			br.close();
			return line;
		} catch (IOException e) {
			LogUtil.log("File not found: " + path, 4);
		}
		
		return null;

	}
}
