package com.robinscheduler.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinscheduler.model.MatchSchedule;
import com.robinscheduler.model.Team;
import com.robinscheduler.service.RobinSchedulerService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class RoundRobinSchedulerController {
	
	@Autowired
	RobinSchedulerService robinSchedulerService;

	@RequestMapping("/")
	public String helloWorld() throws JsonProcessingException{
		return "index";
	}
	
	
	@RequestMapping(value = "/scheduleGenerator",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//public List<MatchSchedule> scheduleGenerator(@RequestBody List<Team> teamList, @RequestParam("date") String date){
	public List<MatchSchedule> scheduleGenerator(@RequestBody List<Team> teamList,@RequestParam("date")String date){
		
		
		List<MatchSchedule> mathcList = new ArrayList<>();
		MatchSchedule matchSchedule = new MatchSchedule();

		try{
			
			DateTimeFormatter formatter_1=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate startDate= LocalDate.parse(date,formatter_1);

		// condition to check if there are 9 or more than 9 teams to meet the business requirement
				if (teamList.size() < 9) {
					matchSchedule.setMatchLocation("Error: Team count should be atleast 9");
					mathcList.add(matchSchedule);
					return mathcList;

				}else{
					
					return robinSchedulerService.getMatchSchedule(teamList, startDate);
				}
		}catch(Exception e){
			e.printStackTrace();
			matchSchedule.setMatchLocation("Error: "+e.getMessage());
			mathcList.add(matchSchedule);
			return mathcList;
			
		}
	}
	
}
