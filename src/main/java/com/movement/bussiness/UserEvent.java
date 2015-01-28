package com.movement.bussiness;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserEvent implements Serializable {

	@XmlElement
	private Integer id;
	
	@XmlTransient
	private User user;
	
	@XmlElement
	private SportsEvent event;
	
	@XmlElement
	private Integer experiencer;
	
	@XmlElement
	private Integer grade;
	
	@XmlElement
	private Integer reputably;
	
	@XmlElement
	private EventLevel level;
	
	@XmlElement
	private List<Achievement> achievements;
	
	@XmlTransient
	private Integer status;
	
	public UserEvent(){
		
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
	}

	public SportsEvent getEvent() {
		return event;
	}

	public void setEvent(SportsEvent event) {
		this.event = event;
	}

	public EventLevel getLevel() {
		return level;
	}

	public void setLevel(EventLevel level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Achievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}

	public Integer getExperiencer() {
		return experiencer;
	}

	public void setExperiencer(Integer experiencer) {
		this.experiencer = experiencer;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getReputably() {
		return reputably;
	}

	public void setReputably(Integer reputably) {
		this.reputably = reputably;
	}
	
}
