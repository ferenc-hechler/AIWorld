package de.hechler.aiworld.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AIWPositionTest {

	@Test
	@Disabled
	public void testNormalizeDir() {
		fail("Not yet implemented");
	}

	@Test
	public void testForward() {
		AIWPosition pos00 = new AIWPosition(0,0,0);
		AIWPosition pos10 = new AIWPosition(1,0,0);
		AIWPosition pos01 = new AIWPosition(0,1,0);
		AIWPosition pos11 = new AIWPosition(1,1,0);
		AIWPosition pos = new AIWPosition(pos00);
		pos.forward(1);
		assertEquals(pos01.getX(), pos.getX(), 0.001);
		assertEquals(pos01.getY(), pos.getY(), 0.001);
		pos.turn(Math.PI/2);
		pos.forward(1);
		assertEquals(pos11.getX(), pos.getX(), 0.001);
		assertEquals(pos11.getY(), pos.getY(), 0.001);
		pos.turn(Math.PI/2);
		pos.forward(1);
		assertEquals(pos10.getX(), pos.getX(), 0.001);
		assertEquals(pos10.getY(), pos.getY(), 0.001);
		pos.turn(Math.PI/2);
		pos.forward(1);
		assertEquals(pos00.getX(), pos.getX(), 0.001);
		assertEquals(pos00.getY(), pos.getY(), 0.001);
	}

	@Test
	@Disabled
	public void testTurn() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	public void testTransform() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	public void testNormalizePos() {
		fail("Not yet implemented");
	}

	@Test
	public void testDist() {
		AIWPosition pos00 = new AIWPosition(0,0,0);
		AIWPosition pos10 = new AIWPosition(1,0,0);
		AIWPosition pos01 = new AIWPosition(0,1,0);
		AIWPosition pos11 = new AIWPosition(1,1,0);
		assertEquals(1.0, pos00.dist(pos10), 0.001);
		assertEquals(1.0, pos00.dist(pos01), 0.001);
		assertEquals(Math.sqrt(2.0), pos00.dist(pos11), 0.001);
	}

	@Test
	public void testDir() {
		double dir;
		AIWPosition pos00 = new AIWPosition(0,0,0);
		AIWPosition pos10 = new AIWPosition(1,0,0);
		AIWPosition pos01 = new AIWPosition(0,1,0);
		AIWPosition pos11 = new AIWPosition(1,1,0);

		dir = pos00.dir(pos01);
		assertEquals(0.0, dir, 0.001);

		dir = pos01.dir(pos11);
		assertEquals(Math.PI/2, dir, 0.001);
		
		dir = pos11.dir(pos10);
		assertEquals(Math.PI, dir, 0.001);
		
		dir = pos10.dir(pos00);
		assertEquals(-Math.PI/2, dir, 0.001);
		
	}

}
