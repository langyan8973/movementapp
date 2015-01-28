package com.movement.bussiness;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventLevel implements Serializable {
	
	@XmlElement
	private Integer id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private Integer minvalue;
	
	@XmlTransient
	private Integer status;
	
	public EventLevel(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Integer getMinvalue() {
		return minvalue;
	}

	public void setMinvalue(Integer minvalue) {
		this.minvalue = minvalue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
