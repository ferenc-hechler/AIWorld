package de.hechler.aiworld.things;

import java.util.List;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWColor;
import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.AIWShape;
import de.hechler.aiworld.core.AIWShape.ShapeType;
import de.hechler.aiworld.core.VisibleObject;

public class WallThing extends BaseThing {

	private double length;
	private double fromX;
	private double toX;
	private double fromY;
	private double toY;
	
	public WallThing(AIWorld world, AIWPosition pos, double length) {
		super(world, pos, ThingType.WALL, 0);
		this.length = length;
		AIWPosition endPos = pos.createCopy();
		endPos.forward(length);
		fromX = Math.min(pos.getX(), endPos.getX());
		toX   = Math.max(pos.getX(), endPos.getX());
		fromY = Math.min(pos.getY(), endPos.getY());
		toY   = Math.max(pos.getY(), endPos.getY());
	}
	
	@Override
	public void addVisibleState(List<VisibleObject> out) {
		out.add(new VisibleObject(getPosition(), new AIWShape(ShapeType.LINE, length), new AIWColor(128,128,128)));
	}

	@Override
	public boolean isAlive() {
		return false;
	}
	
	@Override
	protected void tick_alive() {
		throw new UnsupportedOperationException(getClass().getName()+" can not live");
	}
	


	@Override
	public boolean checkCollision(double minX, double minY, double maxX, double maxY) {
		return (fromX<=maxX) && (toX>=minX) && (fromY<=maxY) && (toY>=minY);   // TODO: handle border 
	}

	@Override
	public String toString() {
		return "WallThing[length=" + length + ", fromX=" + fromX + ", toX=" + toX + ", fromY=" + fromY + ", toY=" + toY
				+ "]";
	}

}
