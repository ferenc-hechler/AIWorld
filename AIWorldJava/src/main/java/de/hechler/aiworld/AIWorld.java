package de.hechler.aiworld;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.VisibleObject;
import de.hechler.aiworld.things.BaseThing;
import de.hechler.aiworld.things.Thing;

public class AIWorld {

	private static final double VISIBLE_RANGE = 50.0;
	private int age;
	private double worldSize;
	
	private List<Thing> things;
	
	/**
	 * create a new world.
	 * @param worldSize 
	 */
	public AIWorld(double worldSize) {
		this.worldSize = worldSize;
		this.things = new ArrayList<>();
		this.age = 0;
	}


	/**
	 * time flies by and the world keeps turning.
	 */
	public void tick() {
		age = age + 1;
		for (Thing thing:things) {
			thing.tick();
		}
		for (int i=things.size()-1; i>=0; i--) {
			Thing thing = things.get(i);
			if (thing.isAlive() && thing.getEnergy() < 0.0) {
				thing.die();
				things.remove(i); 
			}
		}
	}


	/**
	 * 
	 * @param worldSize2 
	 * @param worldSize 
	 * @param j 
	 * @param fromX 
	 * @return
	 */
	public List<VisibleObject> getVisibleObjects(double minX, double minY, double maxX, double maxY) {
		List<VisibleObject> result = new ArrayList<>();
		things.stream().forEach(thing -> thing.addVisibleState(result));
		return result;
	}


	public void add(Thing thing) {
		things.add(thing);
	}


	public double getWorldSize() {
		return worldSize;
	}


	public boolean moveForward(Thing thing, double dist) {
		AIWPosition newPos = thing.getPosition().createCopy();
		newPos.forward(dist);
		newPos.normalizePos(worldSize);
		if (checkCollision(thing, newPos)) {
			return false;
		}
		thing.getPosition().set(newPos);
		return true;
	}


	private boolean checkCollision(Thing movingThing, AIWPosition newPos) {
		double minX = newPos.getX()-1.0;
		double minY = newPos.getY()-1.0;
		double maxX = minX + 2.0;
		double maxY = minY + 2.0;
		List<Thing> collidingThings = findThings(minX, minY, maxX, maxY);
		if (collidingThings.isEmpty()) {
			return false;
		}
		if (collidingThings.size() > 1) {
			return true;
		}
		return collidingThings.get(0) != movingThing;
	}


	public List<Thing> findThings(double minX, double minY, double maxX, double maxY) {
		return things.stream().filter(thing -> thing.checkCollision(minX, minY, maxX, maxY)).collect(Collectors.toList());
	}


	/**
	 * return list of things to be seen by lookingThing.
	 * @param thing
	 * @return
	 */
	public List<Thing> lookNearbyThings(Thing lookingThing) {
		List<Thing> result = new ArrayList<>();
		double x = lookingThing.getPosition().getX();
		double y = lookingThing.getPosition().getY();
		double visibleRange = VISIBLE_RANGE;
		double minX = x - visibleRange;
		double minY = y - visibleRange;
		double maxX = x + visibleRange;
		double maxY = y + visibleRange;
		return things.stream()
				.filter(thing -> (thing != lookingThing) && thing.checkVisible(minX, minY, maxX, maxY))
				.collect(Collectors.toList());
	}

	
}
