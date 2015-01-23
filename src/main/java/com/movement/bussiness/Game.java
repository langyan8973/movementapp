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
public class Game implements Serializable {

	@XmlElement
	private Integer id;
	
	@XmlTransient
	private Competition competition;
	
	@XmlElement
	private CompetitionLevel level;
	
	@XmlElement
	private List<GameRecord> records;
	
	@XmlElement
	private List<GameAttachment> attachments;
	
	@XmlTransient
	private Integer status;
	
	public Game(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public CompetitionLevel getLevel() {
		return level;
	}

	public void setLevel(CompetitionLevel level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<GameRecord> getRecords() {
		return records;
	}

	public void setRecords(List<GameRecord> records) {
		this.records = records;
	}

	public List<GameAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<GameAttachment> attachments) {
		this.attachments = attachments;
	}
	
	
}
