package thibaud.compiler.shape;

import thibaud.compiler.maths.Vector2f;

public class Point extends Shape{
	private Vector2f pos;
	
	public Point(){
		this.pos = new Vector2f();
	}
	
	public Point(Vector2f pos){
		this.pos = pos;
	}
}
