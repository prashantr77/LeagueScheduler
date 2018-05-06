package com.robinscheduler.service;

import java.time.LocalDate;
import java.util.List;

import com.robinscheduler.model.MatchSchedule;
import com.robinscheduler.model.Team;

public interface RobinSchedulerService {
	
//	public List<MatchSchedule> getMatchSchedule(List<Team> teamList, String startDate);
	public List<MatchSchedule> getMatchSchedule(List<Team> teamList, LocalDate startDate);
	
}
