package com.movement.bussiness;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.movement.util.JaxbDateSerializer;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Team implements Serializable {
	
	private Integer id;
	
	private String name;
	
	private SportsEvent event;
	
	private String slogan;
	
	private String logo;
	
	private String description;
	
	private Date time;
	
	private User initiator;
	
	private SimplifyUser constitutor;
	
	private List<TeamAttachment> attachments;
	
	private Integer game_played;
	
	private Integer win_count;

	private Integer status;
	
	public Team(){}

	@XmlElement
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public SportsEvent getEvent() {
		return event;
	}

	public void setEvent(SportsEvent event) {
		this.event = event;
	}

	@XmlElement
	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	@XmlElement
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@XmlTransient
	public User getInitiator() {
		return initiator;
	}

	public void setInitiator(User initiator) {
		this.initiator = initiator;
		this.constitutor = new SimplifyUser(initiator);
	}

	@XmlElement
	public SimplifyUser getConstitutor() {
		return constitutor;
	}

	public void setConstitutor(SimplifyUser constitutor) {
		this.constitutor = constitutor;
	}

	@XmlTransient
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@XmlElement
	public List<TeamAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TeamAttachment> attachments) {
		this.attachments = attachments;
	}
	
	@XmlElement
	public Integer getGame_played() {
		return game_played;
	}

	public void setGame_played(Integer game_played) {
		this.game_played = game_played;
	}

	@XmlElement
	public Integer getWin_count() {
		return win_count;
	}

	public void setWin_count(Integer win_count) {
		this.win_count = win_count;
	}

}
