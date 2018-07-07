package de.hechler.aiworld.core;

public class AIWPosition {

	private double x;
	private double y;
	private double dir;
	
	public AIWPosition() {
		this(0,0,0);
	}

	public AIWPosition(double x, double y, double dir) {
		this.x = x;
		this.y = y;
		this.dir = normalizeDir(dir);
	}

	public AIWPosition(AIWPosition pos) {
		x = pos.x;
		y = pos.y;
		dir = pos.dir;
	}

	public static double normalizeDir(double dir) {
		if (dir > Math.PI) {
			return dir - 2*Math.PI;
		}
		else if (dir < -Math.PI) {
			return dir + 2*Math.PI;
		}
		return dir;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDir() {
		return dir;
	}

	public void setDir(double dir) {
		this.dir = dir;
	}

	public void forward(double dist) {
		x = x + dist*Math.sin(dir);
		y = y + dist*Math.cos(dir);
	}
	
	public void turn(double angle) {
		dir = dir + angle;
		if (dir > Math.PI) {
			dir = dir - 2*Math.PI;
		}
		else if (dir < -Math.PI) {
			dir = dir + 2*Math.PI;
		}
	}

	public AIWPosition transform(AIWPosition pos) {
		double xRot =   pos.getX()*Math.cos(dir) + pos.getY()*Math.sin(dir);
		double yRot = - pos.getX()*Math.sin(dir) + pos.getY()*Math.cos(dir);
		double dirRot = normalizeDir(pos.getDir()+dir);
		AIWPosition result = new AIWPosition(x+xRot, y+yRot, dirRot);
		return result;
	}

	public void normalizePos(double worldSize) {
		double halfWorldSize = 0.5*worldSize;
		if (x < -halfWorldSize) {
			x = x + worldSize;
		}
		else if (x >= halfWorldSize) {
			x = x - worldSize;
		}
		if (y < -halfWorldSize) {
			y = y + worldSize;
		}
		else if (y >= halfWorldSize) {
			y = y - worldSize;
		}
	}

	@Override
	public String toString() {
		return "("+x+","+y+":"+dir*180/Math.PI+")";
	}

	public double dist(AIWPosition position) {
		double dx = position.getX() - getX();
		double dy = position.getY() - getY();
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public double dir(AIWPosition position) {
		double dx = position.getX() - getX();
		double dy = position.getY() - getY();
		return Math.atan2(dx, dy);
	}
	
}
