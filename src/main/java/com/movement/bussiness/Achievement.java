package com.movement.bussiness;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.movement.util.JaxbDateSerializer;


@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Achievement implements Serializable {

	private Integer id;
	
	private UserEvent userevent;
	
	private Date time;
	
	private String address;
	
	private String description;
	
	private Integer status;
	
	public Achievement(){
		
	}

	@XmlElement
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlTransient
	public UserEvent getUserevent() {
		return userevent;
	}

	public void setUserevent(UserEvent userevent) {
		this.userevent = userevent;
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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
