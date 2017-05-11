package thibaud.compiler.shape;

import thibaud.compiler.maths.Vector2f;

public class Point extends Shape{
	
	public Point(){
		super();
	}
	
	public Point(Vector2f pos){
		super();
		vertices.add(pos);
		edges.add(new Vector2f(0,0));
	}
}
