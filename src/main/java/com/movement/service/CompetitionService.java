package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.bussiness.Site;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Team;
import com.movement.bussiness.Unit;
import com.movement.bussiness.User;
import com.movement.bussiness.UserActivity;
import com.movement.bussiness.UserEvent;
import com.movement.dao.CompetitionDao;
import com.movement.dao.CompetitionTeamDao;
import com.movement.dao.TeamDao;

@Service
@Transactional
public class CompetitionService {

	@Autowired
	private CompetitionDao dao;		
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private CompetitionTeamDao competitionTeamDao;
	
	public List<Competition> getAllCompetitions(){
		
		List<Competition> competitions = dao.findAll();
		
		return competitions;
		
	}
	
	public Competition getById(int id){
		
		return dao.findById(id);
	}
	
	public List<Competition> getByEvent(SportsEvent event){
		
		List<Competition> competitions = dao.findByEvent(event);
		
		return competitions;
		
	}
	
	public List<Competition> getByUnit(Unit unit){
		
		List<Competition> competitions = dao.findByUnit(unit);
		
		return competitions;
		
	}
	
	public List<Competition> getBySite(Site site){
		
		List<Competition> competitions = dao.findBySite(site);
		
		return competitions;
	}
	
	public void JoinCompetition(Integer tid,Competition competition){
		
		Team team = teamDao.findById(tid);
		
		CompetitionTeam competitionTeam = new CompetitionTeam();
		
		competitionTeam.setTeam(team);
		
		competitionTeam.setCompetition(competition);
		
		competitionTeamDao.saveOrUpdate(competitionTeam);
		
	}
}
