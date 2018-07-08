package de.hechler.aiworld.things;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWPosition;

public abstract class BaseThing implements Thing {

	private AIWorld world;
	
	private AIWPosition pos;

	private ThingType type;
	
	public BaseThing(AIWorld world, AIWPosition pos, ThingType type) {
		this.world = world;
		this.pos = pos;
		this.type = type;
	}
	
	@Override
	public AIWorld getWorld() {
		return world;
	}

	@Override
	public ThingType getType() {
		return type;
	}

	@Override
	public AIWPosition getPosition() {
		return pos;
	}

	protected boolean moveForward(double dist) {
		return world.moveForward(this, dist);
	}
	
	protected void rotate(double rad) {
		pos.turn(rad);
	}

	public boolean checkCollision(double minX, double minY, double maxX, double maxY) {
		// TODO: handle borders (min<0, max>worldsize)
 		return pos.checkInside(minX, minY, maxX, maxY);
	}
	
	
	@Override
	public String toString() {
		return "THING[pos=" + pos + ", type=" + type + "]";
	}


	

}
