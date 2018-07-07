package de.hechler.aiworld.things;

import java.util.List;

import de.hechler.aiworld.AIWorld;
import de.hechler.aiworld.core.AIWPosition;
import de.hechler.aiworld.core.VisibleObject;

public interface Thing {

	AIWorld getWorld();
	
	ThingType getType();
	
	void tick();

	AIWPosition getPosition();

	void addVisibleState(List<VisibleObject> out);

}
