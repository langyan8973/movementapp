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
public class Activity implements Serializable {

	private Integer id;
	
	private String title;
	
	private String content;
	
	private Date time;
	
	private String address;
	
	private SportsEvent event;
	
	private User initiator;
	
	private SimplifyUser constitutor;
	
	private List<ActivityAttachment> attachments;
	
	private Integer status;
	
	public Activity(){
		
	}
	
	@XmlElement
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public SportsEvent getEvent() {
		return event;
	}

	public void setEvent(SportsEvent event) {
		this.event = event;
	}

	@XmlTransient
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@XmlElement
	public List<ActivityAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ActivityAttachment> attachments) {
		this.attachments = attachments;
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
		this.initiator = new User(constitutor);
	}

}
