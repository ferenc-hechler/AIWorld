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
		super(world, pos, ThingType.THING, 100);
		this.speed = RandomUtil.getDouble(0.1, 1);
		this.turn = RandomUtil.getDouble(-0.05, 0.05);
	}
	
	@Override
	public void tick_alive() {
		// load battery
		super.tick_alive();
		// do move
		rotate(turn);		
		moveForward(1);
	}


	@Override
	public void addVisibleState(List<VisibleObject> out) {
		AIWColor col;
		if (getEnergy() > 0.5*getMaxEnergy()) {
			col = new AIWColor(0,0,255);
		}
		else {
			double starveFactor = Math.max(0, 2.0*getEnergy()/getMaxEnergy()); 
			col = new AIWColor(0,0,(int) (255.0*starveFactor));
		}
		out.add(new VisibleObject(getPosition(), new AIWShape(ShapeType.DOT), col));
	}

	@Override
	public String toString() {
		return "SpinningThing [pos=" + getPosition() + ", energy=" + getEnergy() + ", speed=" + speed + ", turn=" + turn + "]";
	}

	
	
}
