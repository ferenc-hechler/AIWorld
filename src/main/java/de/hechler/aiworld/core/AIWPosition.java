package de.hechler.aiworld.core;

public class AIWPosition {

	public final static double RAD_0 = 0.0;
	public final static double RAD_90 = 0.5*Math.PI;
	public final static double RAD_180 = Math.PI;
	public final static double RAD_270 = 1.5*Math.PI;
	public final static double RAD_360 = 2.0*Math.PI;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double dir;
	
	public AIWPosition() {
		this(0,0,0);
	}

	public AIWPosition(double x, double y, double dir) {
		this.x = x;
		this.y = y;
		setDir(normalizeDir(dir));
	}

	public AIWPosition(AIWPosition pos) {
		x = pos.x;
		y = pos.y;
		setDir(pos.dir);
	}

	private void resetDirCache() {
		if (dir == RAD_0) {
			dx = 0.0;
			dy = 1.0;
		}
		else if (dir == RAD_90) {
			dx = 1.0;
			dy = 0.0;
		}
		else if (dir == RAD_180) {
			dx = 0.0;
			dy = -1.0;
		}
		else if (dir == RAD_270) {
			dx = -1.0;
			dy = 0.0;
		}
		else {
			dx = Double.NaN;
			dy = Double.NaN;
		}
			
	}
	
	public static double normalizeDir(double dir) {
		if (dir > RAD_180) {
			return dir - RAD_360;
		}
		else if (dir < -RAD_180) {
			return dir + RAD_360;
		}
		return dir;
	}

	public double getX() {
		return x;
	}

	public double getDX() {
		if (Double.isNaN(dx)) {
			dx = Math.sin(dir);
		}
		return dx;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public double getDY() {
		if (Double.isNaN(dy)) {
			dy = Math.cos(dir);
		}
		return dy;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDir() {
		return dir;
	}

	public AIWPosition setDir(double dir) {
		this.dir = dir;
		resetDirCache();
		return this;
	}

	public AIWPosition forward(double dist) {
		x = x + dist*getDX();
		y = y + dist*getDY();
		return this;
	}
	
	public AIWPosition turn(double angle) {
		setDir(dir + angle);
		if (dir > RAD_180) {
			setDir(dir - RAD_360);
		}
		else if (dir < -RAD_180) {
			setDir(dir + RAD_360);
		}
		return this;
	}

	public AIWPosition normalizePos(double worldSize) {
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
		return this;
	}

	public double dist(AIWPosition position) {
		double deltaX = position.getX() - getX();
		double deltaY = position.getY() - getY();
		return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}
	
	public double sqDist(AIWPosition position) {
		double deltaX = position.getX() - getX();
		double deltaY = position.getY() - getY();
		return deltaX*deltaX + deltaY*deltaY;
	}
	
	public double qDist(AIWPosition position) {
		double deltaX = position.getX() - getX();
		double deltaY = position.getY() - getY();
		return Math.max(deltaX, deltaY);
	}
	
	public double dirTo(AIWPosition position) {
		double deltaX = position.getX() - getX();
		double deltaY = position.getY() - getY();
		return Math.atan2(deltaX, deltaY);
	}

	public AIWPosition set(AIWPosition newPos) {
		x = newPos.x;
		y = newPos.y;
		setDir(newPos.dir);
		return this;
	}


	public boolean checkInside(double minX, double minY, double maxX, double maxY) {
		return (minX<=x) && (x<=maxX) && (minY<=y) && (y<=maxY);
	}

	
	public AIWPosition createTransformed(AIWPosition pos) {
		double xRot =   pos.getX()*getDY() + pos.getY()*getDX();
		double yRot = - pos.getX()*getDX() + pos.getY()*getDY();
		double dirRot = normalizeDir(pos.getDir()+dir);
		AIWPosition result = new AIWPosition(x+xRot, y+yRot, dirRot);
		return result;
	}

	public AIWPosition createCopy() {
		return new AIWPosition(this);
	}

	@Override
	public String toString() {
		return "("+x+","+y+":"+dir*360/RAD_360+")";
	}

}
