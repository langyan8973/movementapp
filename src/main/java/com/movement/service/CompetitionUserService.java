package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionUser;
import com.movement.dao.CompetitionUserDao;

@Service
@Transactional
public class CompetitionUserService {
	
	@Autowired
	private CompetitionUserDao dao;
	
	public List<CompetitionUser> getByCompetition(Competition competition){
		
		List<CompetitionUser> competitionUsers = dao.findByCompetition(competition);
		
		return competitionUsers;
		
	}

}
