package com.movement.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.ActivityAttachment;
import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.bussiness.Game;
import com.movement.bussiness.GameAttachment;
import com.movement.bussiness.GameRecord;
import com.movement.bussiness.Site;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Team;
import com.movement.bussiness.Unit;
import com.movement.bussiness.User;
import com.movement.bussiness.UserActivity;
import com.movement.bussiness.UserEvent;
import com.movement.dao.CompetitionDao;
import com.movement.dao.CompetitionTeamDao;
import com.movement.dao.GameAttachmentDao;
import com.movement.dao.GameDao;
import com.movement.dao.GameRecordDao;
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
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private GameRecordDao gameRecordDao;
	
	@Autowired
	private GameAttachmentDao gameAttachmentDao;
	
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
	
	public Competition saveOrUpdate(Competition competition){
		
		dao.saveOrUpdate(competition);
		
		return competition;
		
	}
	
	public Game saveOrUpdateGame(Game game){
		
		gameDao.saveOrUpdate(game);
		
		List<GameRecord> gameRecords = gameRecordDao.findByGame(game);
		
		if(gameRecords!=null && gameRecords.size()>0){
			
			Iterator<GameRecord> iterator;
			for (iterator = gameRecords.iterator(); iterator
					.hasNext();){
				
				GameRecord gameRecord = iterator.next();
				gameRecordDao.delete(gameRecord);
				
			}
		}
		
		if(game.getRecords()!=null && game.getRecords().size()>0){
			
			Iterator<GameRecord> iterator;
			for (iterator = game.getRecords().iterator(); iterator
					.hasNext();) {
				GameRecord gameRecord = iterator.next();
				
				gameRecord.setGame(game);
				
				gameRecord.setId(null);
				
				gameRecord.setStatus(0);
				
				gameRecordDao.saveOrUpdate(gameRecord);
				
			}
			
		}
		
		
		List<GameAttachment> gameAttachments = gameAttachmentDao.findByGame(game);
		
		if(gameAttachments!=null && gameAttachments.size()>0){
			
			Iterator<GameAttachment> iterator;
			for (iterator = gameAttachments.iterator(); iterator
					.hasNext();){
				
				GameAttachment gameAttachment = iterator.next();
				gameAttachmentDao.delete(gameAttachment);
				
			}
		}
		
		if(game.getAttachments()!=null && game.getAttachments().size()>0){
			
			Iterator<GameAttachment> iterator;
			for (iterator = game.getAttachments().iterator(); iterator
					.hasNext();) {
				GameAttachment gameAttachment = iterator.next();
				
				gameAttachment.setGame(game);
				
				gameAttachment.setId(null);
				
				gameAttachment.setStatus(0);
				
				gameAttachmentDao.saveOrUpdate(gameAttachment);
				
			}
			
		}
		
		return game;
		
	}
}
