package de.hechler.aiworld.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VisibleObjectGroup extends VisibleObject {

	private List<VisibleObject> children;

	public VisibleObjectGroup() {
		children = new ArrayList<>();
	}

	public VisibleObjectGroup(Collection<VisibleObject> children) {
		children = new ArrayList<>(children);
	}

	public void addChild(VisibleObject gObj) {
		children.add(gObj);
	}

	public void addChildren(Collection<VisibleObject> gObjs) {
		children.addAll(gObjs);
	}

}
