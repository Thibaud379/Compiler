package thibaud.compiler.shape;

import thibaud.compiler.maths.Vector2f;

public class Line extends Shape {
	private Vector2f pos1, pos2;

	public Line(float x1, float y1, float x2, float y2, Shape s) {
		super();
		pos1 = new Vector2f(x1, y1);
		pos2 = new Vector2f(x2, y2);
		construct(s, 0);
	}

	public Line(float x, float y, double radius, float angle, Shape s) {
		super();
		pos1 = new Vector2f((float) (x - radius), y);
		pos2 = new Vector2f((float) (x + radius), y);
		construct(s, angle);
	}

	public Line(float x, float y, float size, double angle, Shape s) {
		super();
		pos1 = new Vector2f((float) (x - size / 2), y);
		pos2 = new Vector2f((float) (x + size / 2), y);

		construct(s, (float) angle);
	}

	private void construct(Shape s, float angle) {
		int sizeV = s.verts(), sizeE = s.edges(), sizeT = s.verts();
		if (s.getClass().getName() == Edge.class.getName()) {
			vertices.add(pos1);
			vertices.add(pos2);
			edges.add(new Vector2f(0, 1));
		} else if (s.getClass().getName() == Point.class.getName()) {
			vertices.add(pos1);
			vertices.add(pos2);
			edges.add(new Vector2f(0, 0));
			edges.add(new Vector2f(1, 1));
		} else {
			for (int i = 0; i < sizeV; i++) {
				vertices.add(s.vertices.get(i).add(pos1.x, pos1.y));
			}
			for (int i = 0; i < sizeE; i++) {
				edges.add(s.edges.get(i));
			}
			for (int i = 0; i < sizeV; i++) {
				vertices.add(s.vertices.get(i).add(pos2.x, pos2.y));
			}
			for (int i = 0; i < sizeE; i++) {
				edges.add(s.edges.get(i).add(sizeT));
			}
		}

		rotate(angle);
	}

}
