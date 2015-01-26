package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Game;
import com.movement.bussiness.GameAttachment;

public interface GameAttachmentDao extends GenericDao<GameAttachment, Integer> {

	public List<GameAttachment> findByGame(Game game);
	
}
