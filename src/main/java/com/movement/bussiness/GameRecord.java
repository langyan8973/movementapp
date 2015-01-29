package com.movement.bussiness;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GameRecord implements Serializable {

	private Integer id;
	
	private Game game;
	
	private Team team;
	
	private User player;
	
	private SimplifyUser contestant;
	
	private String score;
	
	private Integer win;
	
	private Integer status;
	
	public GameRecord(){
		
	}

	@XmlElement
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlTransient
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@XmlElement
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@XmlTransient
	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
		if(player!=null)
			this.contestant = new SimplifyUser(player);
	}

	@XmlElement
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@XmlTransient
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@XmlElement
	public Integer getWin() {
		return win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public SimplifyUser getContestant() {
		return contestant;
	}

	public void setContestant(SimplifyUser contestant) {
		this.contestant = contestant;
		if(contestant!=null)
			this.player = new User(contestant);
	}
	
}
