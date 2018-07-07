package de.hechler.aiworld.things;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWPosition;

public abstract class BaseThing implements Thing {

	protected AIWorld world;
	
	protected AIWPosition pos;

	protected ThingType type;
	
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

	@Override
	public String toString() {
		return "BaseThing [world=" + world + ", pos=" + pos + ", type=" + type + "]";
	}


	

}
