package thibaud.compiler.maths;

public class Vector2f {
	public float x, y;

	public Vector2f() {
		x = 0;
		y = 0;
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f add(float xa, float ya) {
		return new Vector2f(x+xa, y+ya);
	}
	
	public Vector2f add(float a) {
		return new Vector2f(x+a, y+a);
	}
}
