package de.hechler.aiworld.things;

import java.util.List;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWColor;
import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.AIWShape;
import de.hechler.aiworld.core.AIWShape.ShapeType;
import de.hechler.aiworld.core.VisibleObject;
import de.hechler.aiworld.util.RandomUtil;

public class SpinningThing extends BaseThing {

	private double speed;
	private double turn;
	
	public SpinningThing(AIWorld world, AIWPosition pos) {
		super(world, pos, ThingType.THING);
		this.speed = RandomUtil.getDouble(0.1, 1);
		this.turn = RandomUtil.getDouble(-0.05, 0.05);
	}
	
	@Override
	public void tick() {
		rotate(turn);		
		moveForward(1);
	}


	@Override
	public void addVisibleState(List<VisibleObject> out) {
		out.add(new VisibleObject(getPosition(), new AIWShape(ShapeType.DOT), new AIWColor(0,255,0)));
	}

	@Override
	public String toString() {
		return "SpinningThing [pos=" + getPosition() + ", speed=" + speed + ", turn=" + turn + "]";
	}

	
	
}
