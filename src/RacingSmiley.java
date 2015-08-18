// RacingSmiley.java
// 
// ICS 45J : Lab Assignment 4
//
// Completed by	: Emily Puth
// UCINetID		: eputh
// StudentID	: 28239807
// Modified		: 05/27/2015

import java.awt.Color;
import java.util.Random;

// RacingSmiley is an extended AnimatedSmiley that is able to participate in
// the race. It must complete a number of tasks and move in left and right
// directions.

public class RacingSmiley extends AnimatedSmiley implements RacingSmileyInterface {
	private static int LAPS = 6; // number of laps each smiley must complete in the race
	private static int MAX_PIXELS = 10; // a smiley can move 1-10 pixels
	private static int STRATEGIES = 3; // (1) constant speed, (2) decrease speed, (3) increase speed
	private static final int RIGHT = 1; // smiley is moving to the right
	private static final int LEFT = -1; // smiley is moving to the left
	
	private static Random randSpeed;
	private static Random randStrategy;
	
	private String smileyName;
	private Color smileyColor;
	private int numLapsCompleted;
	private int currentDirection;
	private int ticks;
	private int speed;
	private int strategy;
	
	
	public RacingSmiley (AnimatedSmiley smiley, String name, Color color) {
		super(smiley);
		smileyName = name;
		smileyColor = color;
		numLapsCompleted = 0;
		currentDirection = RIGHT;
		randSpeed = new Random();
		randStrategy = new Random();
		speed = randSpeed.nextInt(MAX_PIXELS) + 1;
		strategy = randStrategy.nextInt(STRATEGIES);
		translateStrategy();
		setCurrentXMovement(speed);
		setCurrentYMovement(0);
	}
	
	
	// Determines whether a smiley is finished with the race.
	// A smiley is finished with the race if they have completed all laps.
	
	public boolean finishedRace() {
		return numLapsCompleted == LAPS;
	}
	

	// Handles each movement of a smiley.
	
	public void raceForOneTick() {
		moveIt();
		ticks++;
		// If a smiley hits a wall, its number of laps gets incremented,
		// its speed possibly changes, and its face, as well as its direction
		// is reversed
		if (hitRightWall() || hitLeftWall()) {
			numLapsCompleted++;
			changeSpeed();
			reverseSmiley();
		}
	}
	
	
	// Accessors
	
	public String getSmileyName() {
		return smileyName;
	}
	
	public Color getSmileyNameColor() {
		return smileyColor;
	}
	
	public int getLapsCompleted() {
		return numLapsCompleted;
	}
	
	public int getCurrentDirection() {
		return currentDirection;
	}
	
	public int getTicks() {
		return ticks;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getStrategy() {
		return strategy;
	}
	
	
	// Mutators
	public String setSmileyName(String name) {
		return smileyName = name;
	}
	
	public Color setSmileyColor(Color color) {
		return smileyColor = color;
	}
	
	
	// Useful private methods
	
	// Updates the speed of a smiley
	
	private void changeSpeed() {
		speed = speed * currentDirection;
		if (speed < 0 && currentDirection == RIGHT)
			speed *= -1;
		if (speed < 0 && currentDirection == LEFT)
			speed *= 1;
		setCurrentXMovement(speed);
	}
	
	
	// A smiley either stays at a constant speed, increases speed
	// or decreases speed.
	
	private void translateStrategy() {
		switch(strategy) 
		{
		case 1: 
			if (speed > 1) {
				speed--;
			}
		case 2:
			if (speed < (speed*2)) {
				speed++;
			}
		default:
		}
	}
	
	
	// Swap the colors of the left eye and the right eye.
	// This happens every time the smiley changes direction
	
	private void swapEyeColor() {
		Color origLeftColor = getLeftEye().getColor();
		getLeftEye().setColor(getRightEye().getColor());
		getRightEye().setColor(origLeftColor);
	}
	
	
	// Reverses the profile of the smiley when a wall is hit.
	// Also swaps the eye color for the situation.
	
	private void reverseFace() {
		int distance = 2 * Math.abs(getSmile().getCenterX() - getFace().getCenterX());
		swapEyeColor();
		if (hitRightWall()) {
			getSmile().translate(LEFT*distance, 0);
		}
		else if (hitLeftWall()) {
			getSmile().translate(RIGHT*distance, 0);
		}
	}
	
	
	// Reverses the direction of the smiley.
	
	private void reverseDirection() {
		if (hitRightWall()) {
			currentDirection = LEFT;
		}
		else if (hitLeftWall()) {
			currentDirection = RIGHT;
		}
	}
	
	
	// Handles the changes made when a smiley hits a wall.
	
	private void reverseSmiley() {
		reverseFace();
		reverseDirection();
		translateStrategy();
		strategy = randStrategy.nextInt(STRATEGIES);
	}
	
	
	private boolean hitLeftWall() {
		return getLeftEdge() <= RacingDisplay.LEFT_EDGE;
	}
	
	
	private boolean hitRightWall() {
		return getRightEdge() >= RacingDisplay.RIGHT_EDGE;
	}
	
}