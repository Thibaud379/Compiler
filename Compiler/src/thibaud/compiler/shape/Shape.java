package thibaud.compiler.shape;

import java.util.ArrayList;

import thibaud.compiler.maths.Vector2f;

public class Shape {
	protected ArrayList<Vector2f> vertices, edges;

	public Shape() {
		vertices = new ArrayList<Vector2f>();
		edges = new ArrayList<Vector2f>();
	}

	public String draw() {

		String shape = "";
		Vector2f vert1 = new Vector2f(), last = new Vector2f();
		Vector2f vert2 = new Vector2f();
		for (Vector2f edge : edges) {
			vert1 = vertices.get((int) edge.x);
			vert2 = vertices.get((int) edge.y);

			if (vert1 == vert2) {
				shape += round(vert1.x) + "," + round(vert1.y) + "\n";
				shape += "down\n";
				shape += "up\n";
			} else if (last == vert1) {
				shape += "down\n";
				shape += round(vert2.x) + "," + round(vert2.y) + "\n";
				shape += "up\n";
			} else {
				shape += round(vert1.x) + "," + round(vert1.y) + "\n";
				shape += "down\n";
				shape += round(vert2.x) + "," + round(vert2.y) + "\n";
				shape += "up\n";
			}
			last = vert2;
		}
		return shape;
	}

	public int verts() {
		return vertices.size();
	}

	public int edges() {
		return edges.size();
	}

	protected void rotate(float angle, Vector2f center) {

		if (angle != 0) {
			angle = -angle;
			for (int i = 0; i < vertices.size(); i++) {
				float curX = vertices.get(i).add(-center.x).x;
				float curY = vertices.get(i).add(-center.y).y;
				float r = (float) Math.sqrt(curX * curX + curY * curY);
				float a = (float) Math.atan2(curY, curX) + angle;
				vertices.set(i, new Vector2f(
						(float) (r * Math.cos(a)) + center.x, 
						(float) (r * Math.sin(a)) + center.y));
			}
		}
	}

	protected Vector2f rotate(Vector2f vec, float angle, Vector2f center) {

		if (angle != 0) {
			angle = -angle;
			float curX = vec.add(-center.x).x;
			float curY = vec.add(-center.y).y;
			float r = (float) Math.sqrt(curX * curX + curY * curY);
			float a = (float) Math.atan2(curY, curX) + angle;
			return new Vector2f((float) (r * Math.cos(a)) + center.x, 
								(float) (r * Math.sin(a)) + center.y);
		} else
			return vec;
	}

	private float round(float a) {
		return (float) Math.round(a * 100) / 100f;
	}
}
