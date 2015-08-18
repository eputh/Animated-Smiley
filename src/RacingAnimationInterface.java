// SmileyAnimationInterface.java
// 
// ICS 45J : Lab Assignment 4
//
// Completed by	: Emily Puth
// UCINetID		: eputh
// StudentID	: 28239807
// Modified		: 05/27/2015
// 
// Written for ICS45J Fall 2012, by Norman Jacobson, August 2012.

import java.util.ArrayList;

// A SmileyAnimation represents an animation in which a collection of
// smiley faces that race one another, each completing a set number of laps.
public interface RacingAnimationInterface
{
	// The program works for any number of smileys.

	
	// This constructor implements this interface (changing its name to match
	// the name of the class, if necessary). It creates an animation that races 
	// the smileys in the given group, showing the animation of the race on the
	// given display.
	
	// RacingAnimation(RacingGroup g, RacingDisplay d)	
	
	
	// animate() is called once, from RacerFrame, to show the running race when
	// the GO! button is pressed; should not be called elsewhere.
	// 
	// For each tick until all theRacers have completed all of their laps, animate()
	// moves the theRacers forward the distance they should go in that tick,
	// based on their current speeds. In more detail, animate() follows this logic:
	// 
	//     Until all theRacers have finished the race...
	//     Each time through the loop is one 'tick' of the race clock
	//     {   For each racer in the list of theRacers...
	//            If the racer has not yet finished the race...
	//                Move the racer forward one clock tick
	//         Repaint the screen to show the movement made this tick
	//         Pause to slow the animation to a visible speed
	//     }
	//     Race done!  Compute the statistics
	
	void animate();
	
	
	
	// Accessors -- used by the graphics routine to obtain
	// the information that it needs to display
	
	// getRacers() returns all the theRacers (with their information).
	public ArrayList<RacingSmiley> getRacers();
	
	// getStatisticsTitle() returns the title that should be shown in the
	// statistics area of the window.
	public String getStatisticsTitle();
	
	// getAverageTicks() returns the average time, in ticks, that each
	// smiley spent completing the race.
	public double getAverageTicks();
	
	// getFewestTicks() returns the number of ticks spent by the fastest
	// smiley in completing the race.
	public int getFewestTicks();
	
	// getMostTicks() returns the number of ticks spent by the slowest
	// smiley in completing the race.
	public int getMostTicks();
	
	// getFastestSmileyName() returns the name of the fastest smiley.
	public String getFastestSmileyName();
	
	// getSlowestSmileyName() returns the name of the slowest smiley.
	public String getSlowestSmileyName();
	
}
