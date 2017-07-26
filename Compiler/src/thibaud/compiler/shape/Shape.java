package thibaud.compiler.shape;

import java.util.ArrayList;

import thibaud.compiler.maths.Vector2f;

public class Shape {
	protected ArrayList<Vector2f> vertices, edges;

	public Shape() {
		vertices = new ArrayList<Vector2f>();
		edges = new ArrayList<Vector2f>();
	}
	
	public Shape(Float[] points, Shape s) {
		vertices = new ArrayList<Vector2f>();
		edges = new ArrayList<Vector2f>();
		Vector2f pos1, pos2;
		pos1 = new Vector2f(points[0], points[1]);
		
		if (s.getClass().getName() == Edge.class.getName()) {
			vertices.add(pos1);
			for (int i = 1; i < points.length/2-1; i++) {
				System.out.println("1 :"+points.length/2+" :"+(i*2+2));
				pos2 = new Vector2f(points[i*2], points[i*2+1]);
				vertices.add(pos2);
				edges.add(new Vector2f(i-1, i ));
				pos1 = pos2;
			}
			edges.add(new Vector2f(points.length/2 -2, 0));
		} else if (s.getClass().getName() == Point.class.getName()) {
			for (int i = 0; i < points.length/2-1; i++) {
				pos1 = new Vector2f(points[i*2], points[i*2+1]);
				vertices.add(pos1);
				edges.add(new Vector2f(i, i));
			}
		} else {
			for (int i = 0; i < points.length; i+=2) {
				pos2 = new Vector2f(points[i+2], points[i+3]);
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

	public String draw() {
		for(Vector2f test: edges) {
			test.draw("test");  
			vertices.get((int) test.x).draw("1:");
			vertices.get((int) test.y).draw("2:");
		}
		String shape = "";
		Vector2f vert1 = new Vector2f(), last = new Vector2f();
		Vector2f vert2 = new Vector2f();
		for (Vector2f edge : edges) {
			vert1 = vertices.get((int) edge.x);
			vert2 = vertices.get((int) edge.y);

			if (vert1 == vert2) {
				shape += "up\n";
				shape += round(vert1.x) + "," + round(vert1.y) + "\n";
				shape += "down\n";
			} else if (last == vert1) {
				shape += round(vert2.x) + "," + round(vert2.y) + "\n";
			} else {
				shape += "up\n";
				shape += round(vert1.x) + "," + round(vert1.y) + "\n";
				shape += "down\n";
				shape += round(vert2.x) + "," + round(vert2.y) + "\n";
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
				vertices.set(i,
						new Vector2f((float) (r * Math.cos(a)) + center.x, (float) (r * Math.sin(a)) + center.y));
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
			return new Vector2f((float) (r * Math.cos(a)) + center.x, (float) (r * Math.sin(a)) + center.y);
		} else
			return vec;
	}

	private float round(float a) {
		return (float) Math.round(a * 100) / 100f;
	}
}
