package de.hechler.aiworld.core;

public class AIWColor {

	private int argb;

	public AIWColor(int r, int g, int b) {
		this(0xFF000000 | ((r << 16) & 0x00FF0000) | ((g << 8) & 0x0000FF00) | (b & 0x000000FF));
	}
	
	public AIWColor(int argb) {
		this.argb = argb;
	}
	
	public int getArgb() {
		return argb;
	}

	public int getA() {
		return (argb>>24) & 0xFF;
	}
	
	public int getR() {
		return (argb>>16) & 0xFF;
	}
	
	public int getG() {
		return (argb>>8) & 0xFF;
	}
	
	public int getB() {
		return argb & 0xFF;
	}

	@Override
	public String toString() {
		return "AIWColor [argb=0x" + Integer.toHexString(argb) + "]";
	}

}
