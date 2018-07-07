package de.hechler.aiworld.things;

import java.util.List;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWColor;
import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.AIWShape;
import de.hechler.aiworld.core.AIWShape.ShapeType;
import de.hechler.aiworld.core.VisibleObject;
import de.hechler.aiworld.util.RandomUtil;

public class SimplyMovingThing extends BaseThing {

	public SimplyMovingThing(AIWorld world, AIWPosition pos) {
		super(world, pos, ThingType.THING);
	}
	
	@Override
	public void tick() {
		boolean moveOK = moveForward(1);
		if (moveOK) {
			rotate(RandomUtil.getDouble(-0.1, 0.1));
		}
		else {
			rotate(RandomUtil.getDouble(-AIWPosition.RAD_90, AIWPosition.RAD_90));
		}
	}
	
	@Override
	public void addVisibleState(List<VisibleObject> out) {
		out.add(new VisibleObject(getPosition(), new AIWShape(ShapeType.DOT), new AIWColor(255,0,0)));
	}

	@Override
	public String toString() {
		return "SimplyMovingThing [pos=" + getPosition() + "]";
	}

}
