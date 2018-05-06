package com.robinscheduler.model;

// Class saves information about each team like team name, team location. It can be further extended to add more details 
//like No of matches played, no of matches won, points to name a few
public class Team {

	private String name = null;
	private String teamLocation= null;
	
	public Team(){
		super();
	}

	public Team(String name, String teamLocation) {
		super();
		this.name = name;
		this.teamLocation = teamLocation;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeamLocation() {
		return teamLocation;
	}
	public void setTeamLocation(String teamLocation) {
		this.teamLocation = teamLocation;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Team name=");
		builder.append(name);
		builder.append(", teamLocation=");
		builder.append(teamLocation);
		builder.append("]");
		return builder.toString();
	}
	
	//This properties can be used later
	/*private int points = 0;
	private int matchPlayed = 0;
	private int lostMatches= 0;
	private int wonMatches=0;*/

	
	
}
