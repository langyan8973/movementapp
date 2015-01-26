package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Game;
import com.movement.bussiness.GameRecord;

public interface GameRecordDao extends GenericDao<GameRecord, Integer> {
	
	public List<GameRecord> findByGame(Game game);

}
