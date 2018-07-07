package de.hechler.aiworld.core;

public class VisibleObject {

	private AIWPosition pos;
	private AIWShape shape;
	private AIWColor col;
	
	public VisibleObject() {
		this(new AIWPosition(), new AIWShape(), new AIWColor(0));
	}
	public VisibleObject(AIWPosition pos, AIWShape shape, AIWColor col) {
		this.pos = pos;
		this.shape = shape;
		this.col = col;
	}
	public AIWPosition getPos() {
		return pos;
	}
	public void setPos(AIWPosition pos) {
		this.pos = pos;
	}
	public AIWShape getShape() {
		return shape;
	}
	public void setShape(AIWShape shape) {
		this.shape = shape;
	}
	public AIWColor getCol() {
		return col;
	}
	public void setCol(AIWColor col) {
		this.col = col;
	}
	@Override
	public String toString() {
		return "VisibleObject [pos=" + pos + ", shape=" + shape + ", col=" + col + "]";
	}
	
	
	
}
