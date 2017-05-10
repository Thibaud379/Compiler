package thibaud.compiler;

import static thibaud.compiler.utils.MatcherUtils.getArgs;
import static thibaud.compiler.utils.MatcherUtils.getShape;
import static thibaud.compiler.utils.MatcherUtils.getType;

import java.util.ArrayList;

import thibaud.compiler.maths.Vector2f;
import thibaud.compiler.shape.Edge;
import thibaud.compiler.shape.Line;
import thibaud.compiler.shape.Point;
import thibaud.compiler.shape.Polygon;
import thibaud.compiler.shape.Shape;
import thibaud.compiler.utils.LogUtil;

public class Parser {

	public Parser() {
	}

	public static Shape parse(String str) {

		String type = getType(str);
		Float[] cArgs = getArgs(str);
		String shape = getShape(str);
		Shape output = null;

		if (type.equals("Point")) {
			output = new Point(new Vector2f(cArgs[0], Float.valueOf(shape)));
		} else if (type.startsWith("Line")) {
			if (shape.equals("Edge") || shape.equals("Point")) {
				Shape s = (shape.equals("Edge")) ? new Edge() : new Point();
				if (type.endsWith("R")) {
					output = new Line(cArgs[0], cArgs[1], (double) cArgs[2], cArgs[3], s);
				} else if (type.endsWith("S")) {
					output = new Line(cArgs[0], cArgs[1], cArgs[2], (double) cArgs[3], s);
				} else {
					output = new Line(cArgs[0], cArgs[1], cArgs[2], cArgs[3], s);
				}
			}else{
				Shape s = parse(shape);
				if (type.endsWith("R")) {
					output = new Line(cArgs[0], cArgs[1], (double) cArgs[2], cArgs[3], s);
				} else if (type.endsWith("S")) {
					output = new Line(cArgs[0], cArgs[1], cArgs[2], (double) cArgs[3], s);
				} else {
					output = new Line(cArgs[0], cArgs[1], cArgs[2], cArgs[3], s);
				}
			}
		} else if (type.startsWith("Polygon")) {
			if (shape.equals("Edge") || shape.equals("Point")) {
				Shape s = (shape.equals("Edge")) ? new Edge() : new Point();
				if (type.startsWith("PolygonS")) {
					output = new Polygon(cArgs[0], cArgs[1], (double) cArgs[2], cArgs[3], s,
							type.charAt(type.length() - 1));
				} else {
					output = new Polygon(cArgs[0], cArgs[1], cArgs[2], cArgs[3], s, Integer.valueOf(type.substring(7, type.length())));
				}
			}else{
				Shape s = parse(shape);
				if (type.startsWith("PolygonS")) {
					output = new Polygon(cArgs[0], cArgs[1], (double) cArgs[2], cArgs[3], s,
							type.charAt(type.length() - 1));
				} else {
					output = new Polygon(cArgs[0], cArgs[1], cArgs[2], cArgs[3], s, Integer.valueOf(type.substring(7, type.length())));
				}
			}
		}
		if(output == null)
		LogUtil.log("Could not parse \""+str+"\"", 2);
		return output;
	}
}
