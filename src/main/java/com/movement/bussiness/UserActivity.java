package com.movement.bussiness;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserActivity implements Serializable {

	@XmlElement
	private Integer id;
	
	@XmlTransient
	private User user;
	
	@XmlElement
	private SimplifyUser people;
	
	@XmlElement
	private Activity activity;
	
	@XmlTransient
	private Integer status;
	
	public UserActivity(){
		
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
		this.people = new SimplifyUser(user);
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public SimplifyUser getPeople() {
		return people;
	}

	public void setPeople(SimplifyUser people) {
		this.people = people;
	}
	
}
