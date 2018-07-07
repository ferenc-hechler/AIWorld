package de.hechler.aiworld.core;

public class AIWShape {

	public enum ShapeType {
		GROUP, PIX, DOT, CROSS, CIRCLE, SQUARE, TRIANGLE
	}

	private ShapeType type;
	private boolean filled;
	
	public AIWShape() {
		this(ShapeType.DOT, false);
	}
	
	public AIWShape(ShapeType type) {
		this(type, false);
	}
	
	public AIWShape(ShapeType type, boolean filled) {
		this.type = type;
	}
	
	public ShapeType getType() {
		return type;
	}

	public boolean isFilled() {
		return filled;
	}
	
}
