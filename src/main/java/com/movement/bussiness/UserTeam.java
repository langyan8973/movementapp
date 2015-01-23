package com.movement.bussiness;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserTeam implements Serializable {
	
	@XmlElement
	private Integer id;
	
	@XmlTransient
	private User user;
	
	@XmlElement
	private SimplifyUser teamuser;
	
	@XmlElement
	private Team team;
	
	@XmlTransient
	private Integer status;
	
	public UserTeam(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.teamuser = new SimplifyUser(user);
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public SimplifyUser getTeamuser() {
		return teamuser;
	}

	public void setTeamuser(SimplifyUser teamuser) {
		this.teamuser = teamuser;
	}
	
	

}
