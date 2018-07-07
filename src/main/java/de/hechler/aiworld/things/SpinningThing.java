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
		super(world, pos, ThingType.ANYTHING);
		this.speed = RandomUtil.getDouble(0.1, 10);
		this.turn = RandomUtil.getDouble(-0.5, 0.5);
	}
	
	@Override
	public void tick() {
		rotate();		
		moveForward();
	}

	
	private void rotate() {
		pos.turn(turn);
	}

	private void moveForward() {
		pos.forward(speed);
		pos.normalizePos(world.getWorldSize());
	}
	
	@Override
	public void addVisibleState(List<VisibleObject> out) {
		out.add(new VisibleObject(getPosition(), new AIWShape(ShapeType.DOT), new AIWColor(0,255,0)));
	}

}
