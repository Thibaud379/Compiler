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
		Vector2f vert1 = new Vector2f();
		Vector2f vert2 = new Vector2f();
		for (Vector2f edge : edges) {
			vert1 = vertices.get((int) edge.x);
			vert2 = vertices.get((int) edge.y);
			if (vert1 == vert2) {
				shape += round(vert1.x) + "," + round(vert1.y) + "\n";
				shape += "down\n";
				shape += "up\n";
			} else {
				shape += round(vert1.x) + "," + round(vert1.y) + "\n";
				shape += "down\n";
				shape += round(vert2.x) + "," + round(vert2.y) + "\n";
				shape += "up\n";
			}
		}
		return shape;
	}

	public int verts() {
		return vertices.size();
	}

	public int edges() {
		return edges.size();
	}

	protected void rotate(float angle) {
		if (angle != 0) {
			for (int i = 0; i < vertices.size(); i++) {
				float curX = vertices.get(i).x;
				float curY = vertices.get(i).y;
				float r = (float) Math.sqrt(curX * curX + curY * curY);
				float a = (float) Math.atan2(curY, curX) + angle;
				vertices.set(i, new Vector2f((float) (r * Math.cos(a)), (float) (r * Math.sin(a))));
			}
		}
	}
	
	private float round(float a){
		return (float)Math.round(a*100)/100f;
	}
}
