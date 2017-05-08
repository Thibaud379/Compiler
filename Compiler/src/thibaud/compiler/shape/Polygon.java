package thibaud.compiler.shape;

import thibaud.compiler.maths.Vector2f;

public class Polygon extends Shape {
	private Vector2f pos;

	public Polygon(float x, float y, float radius, float angle, Shape s, int faces) {
		super();
		pos = new Vector2f(x, y);

	}

	public Polygon(float x, float y, double size, float angle, Shape s, int faces) {
		super();
		pos = new Vector2f(x, y);
	}
}
