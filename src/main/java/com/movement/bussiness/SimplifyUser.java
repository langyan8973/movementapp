package com.movement.bussiness;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimplifyUser implements Serializable {

	@XmlElement
	private Integer id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String alias;
	
	@XmlElement
	private String sex;
	
	@XmlElement
	private Integer age;
	
	@XmlElement
	private String phone_number;
	
	@XmlElement
	private String address;
	
	@XmlElement
	private String thumbnail;
	
	public SimplifyUser(){
		
	}
	
	public SimplifyUser(User user){
		
		id = user.getId();
		name = user.getName();
		alias = user.getAlias();
		sex = user.getSex();
		age = user.getAge();
		phone_number = user.getPhone_number();
		address = user.getAddress();
		thumbnail = user.getThumbnail();
	}

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
