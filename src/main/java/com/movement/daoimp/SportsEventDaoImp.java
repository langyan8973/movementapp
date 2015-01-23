package com.movement.daoimp;

import org.springframework.stereotype.Repository;

import com.movement.bussiness.SportsEvent;
import com.movement.dao.SportsEventDao;

@Repository
public class SportsEventDaoImp extends GenericDAOImp﻿<SportsEvent, Integer> implements SportsEventDao {

}
