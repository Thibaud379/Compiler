package thibaud.compiler;

import static thibaud.compiler.utils.MatcherUtils.*;

import java.util.HashMap;

import thibaud.compiler.maths.Vector2f;
import thibaud.compiler.shape.Edge;
import thibaud.compiler.shape.Line;
import thibaud.compiler.shape.Point;
import thibaud.compiler.shape.Polygon;
import thibaud.compiler.shape.Shape;
import thibaud.compiler.utils.LogUtil;
import thibaud.compiler.utils.MatcherUtils;

public class Parser {

	public Parser() {
	}

	public static Shape parse(String str) {
		if (getMatcher(str, MatcherUtils.keyAll).find()) {
			return parseShape(str);
		} else if (getMatcher(str, MatcherUtils.keyVariableDeclaration).find()) {
			parseVar(str);
			return null;
		} else if(str.startsWith("%")){
			LogUtil.log("Comment: "+str, 1);
		}else {
			LogUtil.log("Line \"" + str + "\" couldn't be parsed", 2);
		}
		return null;

	}

	private static void parseVar(String str) {
		if (getVarType(str).equals("float")) {
			Main.sf.put(getVarName(str), getVarValue(str));
		} else if (getVarType(str).equals("shape")) {
			Main.ss.put(getVarName(str), parseShape(getVarArgs(str)));
		}

	}

	private static Shape parseShape(String str) {
		String type = getType(str);
		Float[] cArgs = getArgs(str);
		String shape = getShape(str);
		Shape output = null;
		Shape s = null;

		if (type == null || cArgs == null || shape == null) {
			LogUtil.log("String: \""+str+"\" couldn't be parsed", 3);
		}

			if (type.equals("Point")) {
				output = new Point(new Vector2f(cArgs[0], Float.valueOf(shape)));
			} else if (type.startsWith("Line")) {
				if (shape.equals("Edge") || shape.equals("Point")) {
					s = (shape.equals("Edge")) ? new Edge() : new Point();
					if (type.endsWith("R")) {
						output = new Line(cArgs[0], cArgs[1], (double) cArgs[2], cArgs[3], s);
					} else if (type.endsWith("S")) {
						output = new Line(cArgs[0], cArgs[1], cArgs[2], (double) cArgs[3], s);
					} else {
						output = new Line(cArgs[0], cArgs[1], cArgs[2], cArgs[3], s);
					}
				} else {
					s = shape.startsWith("#") ? Main.ss.get(shape.substring(1)): parseShape(shape);

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
					s = (shape.equals("Edge")) ? new Edge() : new Point();
					if (type.startsWith("PolygonS")) {
						System.out.println(type.charAt(type.length() - 1));
						output = new Polygon(cArgs[0], cArgs[1], (double) cArgs[2], cArgs[3], s,
								Integer.valueOf(type.substring(8, type.length())));
					} else {
						output = new Polygon(cArgs[0], cArgs[1], cArgs[2], cArgs[3], s,
								Integer.valueOf(type.substring(7, type.length())));
					}
				} else {
					s = shape.startsWith("#") ? Main.ss.get(shape.substring(1)) : parseShape(shape);
					if (type.startsWith("PolygonS")) {
						output = new Polygon(cArgs[0], cArgs[1], (double) cArgs[2], cArgs[3], s,
								Integer.valueOf(type.substring(8, type.length())));
					} else {
						output = new Polygon(cArgs[0], cArgs[1], cArgs[2], cArgs[3], s,
								Integer.valueOf(type.substring(7, type.length())));
					}
				}
			}
		if (output == null)
			LogUtil.log("Could not parse \"" + str + "\"", 2);
		return output;
	}
}
