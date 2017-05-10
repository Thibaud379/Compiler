package thibaud.compiler.shape;

import thibaud.compiler.maths.Vector2f;

public class Point extends Shape{
	private Vector2f pos;
	
	public Point(){
		super();
		this.pos = new Vector2f();
	}
	
	public Point(Vector2f pos){
		super();
		this.pos = pos;
		vertices.add(pos);
		edges.add(new Vector2f(0,0));
	}
}
