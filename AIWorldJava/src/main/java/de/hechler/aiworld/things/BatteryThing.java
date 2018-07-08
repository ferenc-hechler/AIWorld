package de.hechler.aiworld.things;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWColor;
import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.AIWShape;
import de.hechler.aiworld.core.AIWShape.ShapeType;
import de.hechler.aiworld.core.InteractionInterface;
import de.hechler.aiworld.core.VisibleObject;

public class BatteryThing extends BaseThing {

	private final static String LOAD_TOPIC = "LOAD";
	private final static Set<String> TOPICS = new HashSet<>(Arrays.asList(LOAD_TOPIC));

	private double maxDistForLoading;

	public class BatteryInteraction implements InteractionInterface {
		private Thing interactingThing;
		public BatteryInteraction(Thing interactingThing) {
			this.interactingThing = interactingThing;
		}
		@Override
		public Set<String> queryTopics() {
			return TOPICS;
		}
		@Override
		public String ask(String topic, String question) {
			if (interactingThing == null) {
				return InteractionInterface.E_CLOSED;
			}
			if (Objects.equals(topic, LOAD_TOPIC)) {
				try {
					double amount = Double.parseDouble(question);
					interactingThing.transferEnergy(BatteryThing.this, amount);
				}
				catch (NullPointerException | NumberFormatException e) {
					return InteractionInterface.E_FORMAT_QUESTION;
				}
			}
			return InteractionInterface.E_UNKNOWN_TOPIC;
		}

		@Override
		public String endConversation() {
			if (interactingThing == null) {
				return InteractionInterface.E_CLOSED;
			}
			interactingThing = null;
			return InteractionInterface.S_OK;
		}
		
	}
	
	public BatteryThing(AIWorld world, AIWPosition pos, double maxDistForLoading) {
		super(world, pos, ThingType.ACCU, 0);
		this.maxDistForLoading = maxDistForLoading;
	}
	
	@Override
	public void addVisibleState(List<VisibleObject> out) {
		out.add(new VisibleObject(getPosition(), new AIWShape(ShapeType.SQUARE, maxDistForLoading), new AIWColor(0,255,0)));
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
		return false;
	}

	@Override
	public InteractionInterface interact(Thing interactingThing) {
		if (getPosition().qDist(interactingThing.getPosition()) > maxDistForLoading) {
			return null;
		}
		return new BatteryInteraction(interactingThing);
	}

	/**
	 * our accu currently has an infinite amount of energy   :-)
	 */
	@Override
	public double transferEnergy(Thing fromThing, double amount) {
		return Math.abs(amount);
	}
	
	@Override
	public String toString() {
		return "BatteryThing[pos=" + getPosition() + "], maxDistForLoading=" + maxDistForLoading + "]";
	}


	
}
