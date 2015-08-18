// RacingGroup.java
// 
// ICS 45J : Lab Assignment 4
//
// Completed by	: Emily Puth
// UCINetID		: eputh
// StudentID	: 28239807
// Modified		: 05/27/2015

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

// RacingGroup stores a group of RacingSmileys

public class RacingGroup implements RacingGroupInterface {
	private static int RACERS = 4;
	private static String[] NAMES = {"Batman", "Superman", "Flash", "Aquaman"};
	
	private ArrayList<RacingSmiley> racers;
	private Random generator;
	private int height; // height of each smiley
	
	
	// The constructor creates a race with a random number of racers 
	// equal to or less than 4. Each of these racers/smileys are
	// assigned a random name and color.
	
	public RacingGroup (Color smileyColor) {
		generator = new Random();
		racers = new ArrayList<RacingSmiley>();
		// if there are more than 0 racers, the height of each smiley
		// is determined by the size of the display divided by the
		// number of racers
		if (RACERS > 0)
			height = ((RacingDisplay.BOTTOM_EDGE - RacingDisplay.TOP_EDGE) - 100) / RACERS - 3;
		for (int i = 0; i < RACERS; i++) {
			AnimatedSmiley smiley = new AnimatedSmiley (5,0);
			smiley.getFace().setAttributes(Color.YELLOW, height/2, height * (i+1) - height/2, height, height);
			smiley.getLeftEye().setAttributes(Color.YELLOW, (int)((height/2) * 0.7), (int)((height * (i+1) - height/2) - height * 0.2), 20, 40);
			smiley.getRightEye().setAttributes(smileyColor, (int)((height/2) * 1.3), (int)((height * (i+1) - height/2) - height * 0.2), 20, 40);
			smiley.getSmile().setAttributes(smileyColor, height, height * (i+1) - height/2 + (int)(height * 0.3), height-2, height * 0.2);
			racers.add(new RacingSmiley(smiley, NAMES[i], new Color (generator.nextInt(255), generator.nextInt(255), generator.nextInt(255))));
		}
	}
	
	
	// Accessor
	// Returns the list of racers in the race.
	public ArrayList<RacingSmiley> getRacers() {
		return racers;
	}
	
	
}