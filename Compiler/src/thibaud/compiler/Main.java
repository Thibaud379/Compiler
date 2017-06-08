package thibaud.compiler;

import static thibaud.compiler.Parser.parse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import thibaud.compiler.shape.Shape;
import thibaud.compiler.utils.FileUtil;
import thibaud.compiler.utils.LogUtil;

public class Main {
	public static HashMap<String, Float> sf = new HashMap<String, Float>();
	public static HashMap<String, Shape> ss = new HashMap<String, Shape>();
	public static String[] args = {"",""};

	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++) {
			Main.args[i] = args[i]; 
		}
		try {
			PrintWriter writer = new PrintWriter(Main.args[0] + ".s2d", "UTF-8");
			String line = null;
			int index = 0;
			Shape s = null;

			while ((line = FileUtil.readLine(args[0], index)) != null) {
				if ((s = parse(line)) != null) {
					if(args.length > 1 && args[1].equals("debug"))
						System.out.print(s.draw());
					writer.print(s.draw());
					
				}

				index++;
			}
    
			writer.close();
		} catch (IOException e) {

		}

	}

}
