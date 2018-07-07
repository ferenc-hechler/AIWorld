package de.hechler.aiworld.util;

import java.util.Random;

import de.hechler.aiworld.core.AIWPosition;

public class RandomUtil {

	private static Random random = new Random();   // use new Random(seed) to reproduce results

	public static int getInt(int max) {
		return random.nextInt(max);
	}

	public static int getInt(int min, int max) {
		return min + random.nextInt(max - min);
	}

	public static double getDouble(double max) {
		return random.nextDouble()*max;
	}

	public static double getDouble(double min, double max) {
		return min + random.nextDouble()*(max - min);
	}

	public static float getFloat(float max) {
		return random.nextFloat()*max;
	}

	public static float getFloat(float min, float max) {
		return min + random.nextFloat()*(max - min);
	}
	
	public static boolean checkProbability(double probability) {
		return random.nextFloat() < probability;
	}

	
	public static AIWPosition getPosition(double worldSize) {
		AIWPosition result = new AIWPosition(getDouble(0, worldSize), getDouble(0, worldSize), getDouble(-AIWPosition.RAD_180, AIWPosition.RAD_180));
		result.normalizePos(worldSize);
		return result;
	}

	public static double getDir() {
		return getDouble(-Math.PI, Math.PI);
	}
	
}
