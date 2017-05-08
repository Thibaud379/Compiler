package thibaud.compiler.shape;

import thibaud.compiler.maths.Vector2f;
import static java.lang.Math.*;

public class Polygon extends Shape {

	public Polygon(float x, float y, float radius, float angle, Shape s, int sides) {
		super();
		Vector2f pos = new Vector2f(x, y);
		construct(s, angle, radius, pos, sides);

	}

	public Polygon(float x, float y, double size, float angle, Shape s, int sides) {
		super();
		Vector2f pos = new Vector2f(x, y);
		float radius = (float) (size / (2 * sin(Math.PI / sides)));
		construct(s, angle, radius, pos, sides);
	}

	private void construct(Shape s, float angle, float radius, Vector2f pos, int sides) {
		Vector2f pos1, pos2;
		float rot = (float) ((2 * PI) / sides);
		pos1 = rotate(new Vector2f(0, radius), angle, pos);

		if (s.getClass().getName() == Edge.class.getName()) {
			vertices.add(pos1);
			for (int i = 0; i < sides - 1; i++) {
				pos2 = rotate(new Vector2f(0, radius), rot * (i + 1) + angle, pos);
				vertices.add(pos2);
				edges.add(new Vector2f(i, i + 1));
				pos1 = pos2;
			}
			edges.add(new Vector2f(sides - 1, 0));
		} else if (s.getClass().getName() == Point.class.getName()) {
			vertices.add(pos1);
			for (int i = 0; i < sides - 1; i++) {
				pos2 = rotate(new Vector2f(0, radius), rot * (i + 1) + angle, pos);
				vertices.add(pos1);
				edges.add(new Vector2f(i, i));
				pos1 = pos2;
			}
		} else {
			for (int i = 0; i < sides; i++) {
				pos2 = rotate(new Vector2f(0, radius), rot * (i + 1) + angle, pos);
				int sizeV = s.verts(), sizeE = s.edges(), sizeT = verts();
				for (int j = 0; j < sizeV; j++) {
					vertices.add(s.vertices.get(j).add(pos1.x, pos1.y));
				}
				for (int j = 0; j < sizeE; j++) {
					edges.add(s.edges.get(j).add(sizeT));
				}
				pos1 = pos2;
			}
		}
	}
}
