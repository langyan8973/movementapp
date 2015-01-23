package com.movement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.Activity;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Team;
import com.movement.bussiness.User;
import com.movement.bussiness.UserActivity;
import com.movement.bussiness.UserEvent;
import com.movement.bussiness.UserTeam;
import com.movement.dao.TeamDao;
import com.movement.dao.UserDao;
import com.movement.dao.UserEventDao;
import com.movement.dao.UserTeamDao;
import com.movement.util.JaxbDateSerializer;

@Service
@Transactional
public class TeamService {
	
	@Autowired
	private TeamDao dao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserEventDao userEventDao;
	
	@Autowired
	private UserTeamDao userTeamDao;
	
	public List<Team> getAllTeams(){
		
		List<Team> teams = dao.findAll();
		
		return teams;
		
	}
	
	public Team getById(int id){
		
		return dao.findById(id);
		
	}
	
	public List<Team> getByEvent(SportsEvent event){
		
		return dao.findByEvent(event);
		
	}
	
	public List<UserTeam> getAllMembers(Team team){
		return userTeamDao.getByTeam(team);
	}
	
	public List<UserTeam> getCheckedMembers(Team team){
		return userTeamDao.getByTeamAndStatus(team, 1);
	}
	
	public List<UserTeam> getUnCheckedMembers(Team team){
		return userTeamDao.getByTeamAndStatus(team, 0);
	}
	
	public Team create(String name,String slogan,String logo,
			String time,String description,Integer uid,SportsEvent event){
		
		Team team = new Team();
		
		team.setName(name);
		
		team.setSlogan(slogan);
		
		team.setLogo(logo);
		
		team.setDescription(description);
		
		team.setEvent(event);
		
		JaxbDateSerializer jds = new JaxbDateSerializer();
		
		try {
			Date dt = jds.unmarshal(time);
			team.setTime(dt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user = userDao.findById(uid);
		
		team.setInitiator(user);
		
		team.setStatus(0);
		
		dao.saveOrUpdate(team);
		
		UserEvent userEvent = userEventDao.getByUserAndEvent(user, event);
		
		if(userEvent==null){
			
			userEvent = new UserEvent();
			
			userEvent.setEvent(event);
			
			userEvent.setUser(user);
			
			userEvent.setLevel(1);
			
			userEvent.setStatus(0);
			
			userEventDao.saveOrUpdate(userEvent);
			
		}
		
		UserTeam userTeam = new UserTeam();
		
		userTeam.setTeam(team);
		
		userTeam.setUser(user);
		
		userTeam.setStatus(0);
		
		userTeamDao.saveOrUpdate(userTeam);
		
		return team;
		
	}
	
	public void joinTeam(int uid,Team team){
		
		User user = userDao.findById(uid);
		
		UserEvent userEvent = userEventDao.getByUserAndEvent(user, team.getEvent());
		
		if(userEvent==null){
			
			userEvent = new UserEvent();
			
			userEvent.setEvent(team.getEvent());
			
			userEvent.setUser(user);
			
			userEvent.setLevel(1);
			
			userEvent.setStatus(0);
			
			userEventDao.saveOrUpdate(userEvent);
			
		}
		
		UserTeam userTeam = new UserTeam();
		
		userTeam.setTeam(team);
		
		userTeam.setUser(user);
		
		userTeam.setStatus(0);
		
		userTeamDao.saveOrUpdate(userTeam);
		
	}
	
	public UserTeam checkPeople(int utid,int status){
		
		UserTeam userTeam = userTeamDao.findById(utid);
		
		userTeam.setStatus(status);
		
		userTeamDao.saveOrUpdate(userTeam);
		
		return userTeam;
	}

}
