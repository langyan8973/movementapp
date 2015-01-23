package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Team;
import com.movement.bussiness.UserTeam;

public interface UserTeamDao extends GenericDao<UserTeam, Integer> {

	public List<UserTeam> getByTeam(Team team);
	
	public List<UserTeam> getByTeamAndStatus(Team team,int status);
	
}
