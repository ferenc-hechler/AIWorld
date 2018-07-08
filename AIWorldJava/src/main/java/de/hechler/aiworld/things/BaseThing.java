package de.hechler.aiworld.things;

import java.util.List;
import java.util.stream.Collectors;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.InteractionInterface;

public abstract class BaseThing implements Thing {

	private static final double DEFAULT_TICK_ENERGY = 0.1;

	private AIWorld world;
	
	private AIWPosition pos;

	private ThingType type;
	
	private double energy;
	private double maxEnergy;

	private boolean alive;
	
	public BaseThing(AIWorld world, AIWPosition pos, ThingType type, double maxEnergy) {
		this.world = world;
		this.pos = pos;
		this.type = type;
		this.energy = maxEnergy;
		this.maxEnergy = maxEnergy;
		this.alive = true;
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

	@Override
	public boolean checkCollision(double minX, double minY, double maxX, double maxY) {
 		return checkVisible(minX, minY, maxX, maxY);
	}
	
	@Override
	public boolean checkVisible(double minX, double minY, double maxX, double maxY) {
 		return pos.checkInside(minX, minY, maxX, maxY);
	}
	
	@Override
	public InteractionInterface interact(Thing interactingThing) {
		return null;
	}

	
	public double transferEnergy(Thing fromThing, double amount) {
		if (amount == 0.0) {
			return 0.0;
		}
		if (amount > 0.0) {
			double transferAmount = Math.max(maxEnergy-energy, Math.min(fromThing.getEnergy(), amount));
			transferAmount = fromThing.transferEnergy(this, -transferAmount);
			energy += transferAmount;
			return transferAmount;
		}
		double transferAmount = Math.min(energy, -amount);
		energy -= transferAmount;
		return transferAmount;
	}

	@Override
	public double getEnergy() {
		return energy;
	}

	protected double getMaxEnergy() {
		return maxEnergy;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public void tick() {
		if (isAlive()) {
			consumeEnergy(DEFAULT_TICK_ENERGY);
			tick_alive();
		}
	}
	
	/**
	 * default implementation loads from nearby battery. 
	 */
	protected void tick_alive() {
		if (energy < maxEnergy / 2) {
			List<Thing> nearbyThings = world.lookNearbyThings(this);
			nearbyThings.stream()
					.map(thing -> thing.interact(this))
					.filter(interact -> (interact != null) && interact.understandsTopic("LOAD"))
					.forEach(interact -> {
						double missingEnergy = maxEnergy - energy;
						if (missingEnergy > 0.0) {
							String question = Double.toString(missingEnergy);
							interact.ask("LOAD", question);
							interact.endConversation();
						}
					});
			
		}
	}
	
	private void consumeEnergy(double amount) {
		energy -= amount;
	}

	@Override
	public void die() {
		this.alive = false;
		this.energy = 0.0;
	}
	
	
	@Override
	public String toString() {
		return "THING[pos=" + pos + ", type=" + type + "]";
	}


}
