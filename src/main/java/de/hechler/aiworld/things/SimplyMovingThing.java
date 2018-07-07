package de.hechler.aiworld.things;

import java.util.ArrayList;
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
		super(world, pos, ThingType.ANYTHING);
	}
	
	@Override
	public void tick() {
		moveForward(1);
		rotateRandom();		
	}

	
	private void rotateRandom() {
		pos.turn(RandomUtil.getDouble(-0.1, 0.1));
	}

	private void moveForward(double dist) {
		pos.forward(dist);
		pos.normalizePos(world.getWorldSize());
	}
	
	@Override
	public void addVisibleState(List<VisibleObject> out) {
		out.add(new VisibleObject(getPosition(), new AIWShape(ShapeType.DOT), new AIWColor(255,0,0)));
	}

}
