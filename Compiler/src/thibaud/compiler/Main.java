package thibaud.compiler;

import static thibaud.compiler.Parser.parse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import thibaud.compiler.shape.Shape;
import thibaud.compiler.utils.FileUtil;

public class Main {
	static HashMap<String, Float> sf = new HashMap<String, Float>();
	static HashMap<String, Shape> ss = new HashMap<String, Shape>();

	public static void main(String[] args) {
		try {
			PrintWriter writer = new PrintWriter(args[0] + ".s2d", "UTF-8");
			String line = null;
			int index = 0;
			Shape s = null;

			while ((line = FileUtil.readLine(args[0], index)) != null) {
				if ((s = parse(line, sf, ss)) != null) {
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
