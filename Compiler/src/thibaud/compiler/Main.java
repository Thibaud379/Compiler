package thibaud.compiler;

import java.util.HashMap;

import thibaud.compiler.shape.Edge;
import thibaud.compiler.shape.Line;
import thibaud.compiler.shape.Polygon;
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

		Polygon p1 = new Polygon(0, 0, 2d, (float) (Math.PI / 4), new Edge(), 4);
		Polygon p = new Polygon(0, 0, 10d, (float) (Math.PI / 4), p1, 4);

		System.out.println(p.draw() + "\n");

	}

}
