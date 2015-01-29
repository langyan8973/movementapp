package com.movement.service;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.bussiness.CompetitionUser;
import com.movement.bussiness.Game;
import com.movement.bussiness.GameAttachment;
import com.movement.bussiness.GameRecord;
import com.movement.bussiness.Site;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Team;
import com.movement.bussiness.Unit;
import com.movement.bussiness.User;
import com.movement.bussiness.UserEvent;
import com.movement.bussiness.UserTeam;
import com.movement.dao.CompetitionDao;
import com.movement.dao.CompetitionTeamDao;
import com.movement.dao.CompetitionUserDao;
import com.movement.dao.GameAttachmentDao;
import com.movement.dao.GameDao;
import com.movement.dao.GameRecordDao;
import com.movement.dao.TeamDao;
import com.movement.dao.UserDao;
import com.movement.dao.UserEventDao;
import com.movement.dao.UserTeamDao;
import com.movement.util.CodeUpgradeType;

@Service
@Transactional
public class CompetitionService {

	@Autowired
	private CompetitionDao dao;		
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private UserEventDao userEventDao;
	
	@Autowired
	private UserTeamDao userTeamDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CompetitionTeamDao competitionTeamDao;
	
	@Autowired
	private CompetitionUserDao competitionUserDao;
	
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
	
	public void playerJoin(Integer uid,Competition competition){
		
		User user = userDao.findById(uid);
		
		CompetitionUser competitionUser = new CompetitionUser();
		
		competitionUser.setUser(user);
		
		competitionUser.setCompetition(competition);
		
		competitionUserDao.saveOrUpdate(competitionUser);
		
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
				
				Team team = gameRecord.getTeam();
				if(team!=null){
					
					if(team.getGame_played()>0){
						team.setGame_played(team.getGame_played()-1);
					}
					if(gameRecord.getWin()==1 && team.getWin_count()>0){
						
						team.setWin_count(team.getWin_count()-1);
						
					}
					
					teamDao.saveOrUpdate(team);
					
					List<UserTeam> userTeams = userTeamDao.getByTeam(team);
					
					if(userTeams!=null&&userTeams.size()>0){
						
						for (UserTeam userTeam : userTeams) {
							
							UserEvent userEvent = userEventDao.getByUserAndEvent(userTeam.getUser(), game.getCompetition().getEvent());
							
							if(userEvent.getExperiencer()>CodeUpgradeType.GAME_WIN_EXPERIENCER && gameRecord.getWin()==1){
								
								userEvent.setExperiencer(userEvent.getExperiencer()-CodeUpgradeType.GAME_WIN_EXPERIENCER);
								
							}
							else if(userEvent.getExperiencer()>CodeUpgradeType.GAME_LOSE_EXPERIENCER){
								
								userEvent.setExperiencer(userEvent.getExperiencer()-CodeUpgradeType.GAME_LOSE_EXPERIENCER);
								
							}
							
							userEvent.setGrade(userEvent.getExperiencer()/CodeUpgradeType.EVENT_UPGRADE_UNIT);
							
							userEventDao.saveOrUpdate(userEvent);
							
						}
						
						
					}
					
				}
				else if(gameRecord.getPlayer()!=null){
					
					User user = gameRecord.getPlayer();
					
					UserEvent userEvent = userEventDao.getByUserAndEvent(user, game.getCompetition().getEvent());
					
					if(userEvent.getExperiencer()>CodeUpgradeType.GAME_WIN_EXPERIENCER && gameRecord.getWin()==1){
						
						userEvent.setExperiencer(userEvent.getExperiencer()-CodeUpgradeType.GAME_WIN_EXPERIENCER);
						
					}
					else if(userEvent.getExperiencer()>CodeUpgradeType.GAME_LOSE_EXPERIENCER){
						
						userEvent.setExperiencer(userEvent.getExperiencer()-CodeUpgradeType.GAME_LOSE_EXPERIENCER);
						
					}
					userEvent.setGrade(userEvent.getExperiencer()/CodeUpgradeType.EVENT_UPGRADE_UNIT);
					userEventDao.saveOrUpdate(userEvent);
					
				}
				
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
				
				Team team = gameRecord.getTeam();
				
				if(team!=null){
					
					team.setGame_played(team.getGame_played()+1);
					
					if(gameRecord.getWin()==1){
						
						team.setWin_count(team.getWin_count()+1);
						
					}
					
					teamDao.saveOrUpdate(team);
					
					List<UserTeam> userTeams = userTeamDao.getByTeam(team);
					
					if(userTeams!=null&&userTeams.size()>0){
						
						for (UserTeam userTeam : userTeams) {
							
							UserEvent userEvent = userEventDao.getByUserAndEvent(userTeam.getUser(), game.getCompetition().getEvent());
							
							if(gameRecord.getWin()==1){
								
								userEvent.setExperiencer(userEvent.getExperiencer()+CodeUpgradeType.GAME_WIN_EXPERIENCER);
								
							}
							else{
								
								userEvent.setExperiencer(userEvent.getExperiencer()+CodeUpgradeType.GAME_LOSE_EXPERIENCER);
								
							}
							
							userEvent.setGrade(userEvent.getExperiencer()/CodeUpgradeType.EVENT_UPGRADE_UNIT);
							
							userEventDao.saveOrUpdate(userEvent);
							
						}
						
						
					}
					
				}
				else if(gameRecord.getPlayer()!=null){
					
					User user = gameRecord.getPlayer();
					
					UserEvent userEvent = userEventDao.getByUserAndEvent(user, game.getCompetition().getEvent());
					
					if(gameRecord.getWin()==1){
						
						userEvent.setExperiencer(userEvent.getExperiencer() + CodeUpgradeType.GAME_WIN_EXPERIENCER);
						
					}
					else{
						
						userEvent.setExperiencer(userEvent.getExperiencer() + CodeUpgradeType.GAME_LOSE_EXPERIENCER);
						
					}
					
					userEvent.setGrade(userEvent.getExperiencer()/CodeUpgradeType.EVENT_UPGRADE_UNIT);
					
					userEventDao.saveOrUpdate(userEvent);
					
				}
				
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
	
	public void closeCompetition(Competition competition){
		
		
		competition.setStatus(2);
		
		dao.saveOrUpdate(competition);
		
	}
}
