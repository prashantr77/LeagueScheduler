package com.robinscheduler.model;

import java.time.LocalDate;

// Class which saves details of Match like Team Names, match Location, Date of match.
public class MatchSchedule {
	
	Team team1= null;
	Team team2 = null;
	String date = null;
	String matchLocation = null;
	
	
	public String getMatchLocation() {
		return matchLocation;
	}
	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}
	public Team getTeam1() {
		return team1;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public Team getTeam2() {
		return team2;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchSchedule [team1=");
		builder.append(team1);
		builder.append(", team2=");
		builder.append(team2);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
