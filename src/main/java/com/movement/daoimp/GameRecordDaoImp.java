package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Game;
import com.movement.bussiness.GameAttachment;
import com.movement.bussiness.GameRecord;
import com.movement.dao.GameRecordDao;

@Repository
public class GameRecordDaoImp extends GenericDAOImp﻿<GameRecord, Integer> implements GameRecordDao {

	@Override
	public List<GameRecord> findByGame(Game game) {
		
		Criteria crit = getSession().createCriteria(GameRecord.class);
		crit = crit.createCriteria("game").add(Restrictions.eq("id", game.getId()));
		crit.setCacheable(true);

		return (List<GameRecord>)crit.list();
	}

}
