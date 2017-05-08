package thibaud.compiler;

import java.util.HashMap;

import thibaud.compiler.shape.Edge;
import thibaud.compiler.shape.Line;
import thibaud.compiler.shape.Shape;
import thibaud.compiler.utils.FileUtil;

public class Main {

	public static void main(String[] args) {
		HashMap<Float, String> varFloats;
		HashMap<Shape, String> varShapes;

		String line = null;
		int index = 0;
		while ((line = FileUtil.readLine(args[0], index)) != null) {
			System.out.println(line);
			index++;
		}
		Line l2 = new Line(0,0,10d,(float)Math.PI/2,new Edge());
		Line l1 = new Line(0,0,10,0d,l2);
		System.out.println(l1.draw()+"\n");
		
	}

}
