package de.hechler.aiworld.core;

public class AIWShape {

	public enum ShapeType {
		GROUP, PIX, DOT, CROSS, CIRCLE, SQUARE, TRIANGLE, LINE
	}

	private ShapeType type;
	private boolean filled;
	private double size;
	
	public AIWShape() {
		this(ShapeType.DOT, 1.0, false);
	}
	
	public AIWShape(ShapeType type) {
		this(type, 1.0, false);
	}
	
	public AIWShape(ShapeType type, double size) {
		this(type, size, false);
	}
	
	public AIWShape(ShapeType type, double size, boolean filled) {
		this.type = type;
		this.size = size;
		this.filled = filled;
	}
	
	public ShapeType getType() {
		return type;
	}

	public double getSize() {
		return size;
	}
	
	public boolean isFilled() {
		return filled;
	}

	@Override
	public String toString() {
		return "AIWShape [type=" + type + ", filled=" + filled + ", size=" + size + "]";
	}

	
	
}
