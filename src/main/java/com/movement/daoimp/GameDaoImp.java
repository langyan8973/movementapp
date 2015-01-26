package com.movement.daoimp;

import org.springframework.stereotype.Repository;

import com.movement.bussiness.Game;
import com.movement.dao.GameDao;

@Repository
public class GameDaoImp extends GenericDAOImp﻿<Game, Integer> implements GameDao {

}
