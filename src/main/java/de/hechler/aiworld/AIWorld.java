package de.hechler.aiworld;

import java.util.ArrayList;
import java.util.List;

import de.hechler.aiworld.core.VisibleObject;
import de.hechler.aiworld.things.SimplyMovingThing;
import de.hechler.aiworld.things.Thing;

public class AIWorld {

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

	
}
