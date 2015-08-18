// RacingAnimation.java
// 
// ICS 45J : Lab Assignment 4
//
// Completed by	: Emily Puth
// UCINetID		: eputh
// StudentID	: 28239807
// Modified		: 05/27/2015

import java.util.ArrayList;


// Display that shows the animation of the race
public class RacingAnimation implements RacingAnimationInterface {
	private String title;
	private RacingDisplay currentDisplay;
	private RacingGroup smileyGroup;
	private String fastestSmileyName;
	private String slowestSmileyName;
	private int mostTicks;
	private int fewestTicks;
	private float averageTicks;
	private ArrayList<RacingSmiley> racers;
	
	
	public RacingAnimation (RacingGroup racingGroup, RacingDisplay racingDisplay) {
		title = "Race Statistics";
		smileyGroup = racingGroup;
		currentDisplay = racingDisplay;
		racers = smileyGroup.getRacers();
		currentDisplay.repaint();
	}
	
	
	public void animate() {
		if(racers.size() > 0)
		{
			// animates while the race is incomplete -- some smileys have
			// yet to finish all laps
			while(!raceComplete())
			{
				for(RacingSmiley racer : racers)
				{
					// continue moving ticks for smileys that have not 
					// finished the race
					if(!racer.finishedRace())
					{
						racer.raceForOneTick();
					}
				}
				currentDisplay.repaint();
				pause(10);
			}
			// once the race is complete, calculate statistics
			calculateAverageTicks();
			calculateFewestTicks();
			calculateMostTicks();
			findFastestSmiley();
			findSlowestSmiley();
		}
	}
	
	
	private void pause(int millisecs)
	{
		try
		{
			Thread.sleep(millisecs);
		}
		catch (InterruptedException e)
		{
		}
	}


	// Accessors
	
	public ArrayList<RacingSmiley> getRacers() {
		return racers;
	}

	public String getStatisticsTitle() {
		return title;
	}
	
	public String getFastestSmileyName() {
		return fastestSmileyName;
	}

	public String getSlowestSmileyName() {
		return slowestSmileyName;
	}
	
	public int getMostTicks() {
		return mostTicks;
	}
	
	public int getFewestTicks() {
		return fewestTicks;
	}
	
	public double getAverageTicks() {
		return averageTicks;
	}

	

	
	// Private methods
	
	// Determines whether the race is complete.
	// The race is complete when all smileys have finished all of the laps.
	
	private boolean raceComplete()
	{
		int count = 0;
		for(RacingSmiley racer: racers)
			if(racer.finishedRace())
				count++;
		return count == racers.size();
	}
	
	
	private void calculateMostTicks() {
		int max = racers.get(0).getTicks();
		for (RacingSmiley racer: racers)
			if (racer.getTicks() > max)
				max = racer.getTicks();
		mostTicks = max;
	}
	
	
	private void calculateFewestTicks() {
		int min = racers.get(0).getTicks();
		for (RacingSmiley racer: racers)
			if (racer.getTicks() < min)
				min = racer.getTicks();
		fewestTicks = min;
	}
	
	
	private void calculateAverageTicks() {
		int total = 0;
		for(RacingSmiley racer: racers)
			total += racer.getTicks();
		averageTicks = total/racers.size();
	}
		
		
	private void findFastestSmiley() {
		String name = racers.get(0).getSmileyName();
		for(RacingSmiley racer: racers)
			if(racer.getTicks() == getFewestTicks())
				name = racer.getSmileyName();
		fastestSmileyName = name;
	}
	
	
	private void findSlowestSmiley() {
		String name = racers.get(0).getSmileyName();
		for(RacingSmiley racer: racers)
			if(racer.getTicks() == getMostTicks())
				name = racer.getSmileyName();
		slowestSmileyName = name;
	}
}
