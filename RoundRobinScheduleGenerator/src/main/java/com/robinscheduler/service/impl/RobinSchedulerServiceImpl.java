package com.robinscheduler.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.robinscheduler.model.MatchSchedule;
import com.robinscheduler.model.Team;
import com.robinscheduler.service.RobinSchedulerService;


// This class performs following activities :
// Create Schedule
// Set Match Location
// Set Match Date

@Service
public class RobinSchedulerServiceImpl implements RobinSchedulerService {

	private List<MatchSchedule> matchDetailsHome = new ArrayList<>();
	private List<MatchSchedule> matchDetailsAway = new ArrayList<>();
	private List<Team> group1 = new ArrayList<>();
	private List<Team> group2 = new ArrayList<>();
	private Date leagueStartDate;
	private LocalDate sourceDate = null;

	@Override
	//public List<MatchSchedule> getMatchSchedule (List<Team> teamList, String startDate) {
	public List<MatchSchedule> getMatchSchedule (List<Team> teamList, LocalDate startDate) {

		
		
		// this will add a dummy team to get the total no of teams equal to even count.
		// Team playing against a bye team will not have a match
		if (teamList.size() % 2 != 0) {
			teamList.add(new Team("Bye", "Bye"));
		}

		
		// Call to split teams into 2 groups 
		splitTeams(teamList);


		// Run this counter to generate schedule for Home Matches
		for (int count = 0; count < group1.size() * 2 - 1; count++) {

			for (int cnt = 0; cnt < group1.size(); cnt++) {
				// This will generate matches across elements in both groups
				roundrobingenerator(group1.get(cnt), group2.get(cnt));
			}

			// This will move elements keeping 1 constant
			changeteamposition(group1, group2);

		}

		// Inserting for away matches
		matchDetailsHome.addAll(matchDetailsAway);
		
		// This will set the date for the matches
		sourceDate = startDate;
		setMatchDate();

		return matchDetailsHome;
	}
	
	private void setMatchDate()
	{
		int iCnt = 0;
		LocalDate destDate = LocalDate.now();
		for(MatchSchedule match : matchDetailsHome)
		{
			String formattedDate = sourceDate.getYear()+"-"+sourceDate.getMonth()+"-"+sourceDate.getDayOfMonth();
			match.setDate(formattedDate);
			iCnt ++;
			
			if(iCnt % 2 == 0)
			{
				destDate = sourceDate.plusDays(1);
				sourceDate = destDate;
			}
		}
	}

	// Method to split the total no of teams into 2 groups
	private void splitTeams(List<Team> teamList)
	{
		// below will split the teams into 2 groups
		for (int cnt = 0; cnt < teamList.size() / 2; cnt++) {
			group1.add(teamList.get(cnt));
		}
		for (int cnt = teamList.size() / 2; cnt < teamList.size(); cnt++) {
			group2.add(teamList.get(cnt));
		}
	}

	public void roundrobingenerator(Team t1, Team t2) {
		// System.out.println(l1 +" plays with " + l2);
		// This will set the schedule and location for Home Matches irrespective of whether there is a BYE or not
		MatchSchedule mdHome = new MatchSchedule();
		mdHome.setTeam1(t1);
		mdHome.setTeam2(t2);
		mdHome.setMatchLocation(t1.getTeamLocation());
		matchDetailsHome.add(mdHome);
		
		// This will set the schedule and location for Away Matches
		
		//In round robin you will not add those teams in the schedule which has a BYE
		if (t2.getTeamLocation() != "Bye")
		{
			MatchSchedule mdAway = new MatchSchedule();
			mdAway.setTeam1(t1);
			mdAway.setTeam2(t2);
			mdAway.setMatchLocation(t2.getTeamLocation());
			matchDetailsAway.add(mdAway);
		}
	}

	// This method is used to move all elements around the 1st element across both the groups
	private void changeteamposition(List<Team> t1, List<Team> t2) {

		t2.add(0, t1.get(1));
		t1.remove(1);
		t1.add(t1.size(), t2.get(t2.size() - 1));
		t2.remove(t2.size() - 1);

	}


}
