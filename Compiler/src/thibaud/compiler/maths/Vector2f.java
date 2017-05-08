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
		return new Vector2f(x + xa, y + ya);
	}

	public Vector2f add(float a) {
		return new Vector2f(x + a, y + a);
	}

	public static Vector2f center(Vector2f a, Vector2f b) {
		return new Vector2f((a.x + b.x) / 2, (a.y + b.y) / 2);
	}

	public void draw(String str) {
		System.out.println(str + " " + x + "x, " + y + "y.");
	}
}
