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
public class User implements Serializable {

	private Integer id;
	
	private String name;
	
	private String password;
	
	private String access_token;
	
	private Date expires_in;
	
	private Integer sns_type;
	
	private String alias;
	
	private String sex;
	
	private Integer age;
	
	private String phone_number;
	
	private String address;
	
	private String thumbnail;
	
	private List<UserActivity> activities;
	
	private List<UserTeam> teams;
	
	private Integer status;
	
	private String openid;
	
	private String refreshtoken;
	
	public User(){
		
	}
	
	public User(SimplifyUser simplifyUser){
		
		this.name = simplifyUser.getName();
		
		this.address = simplifyUser.getAddress();
		
		this.alias = simplifyUser.getAlias();
		
		this.id = simplifyUser.getId();
		
		this.sex = simplifyUser.getSex();
		
		this.age = simplifyUser.getAge();
		
		this.phone_number = simplifyUser.getPhone_number();
		
		this.thumbnail = simplifyUser.getThumbnail();
		
	}

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
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	public Date getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Date expires_in) {
		this.expires_in = expires_in;
	}

	@XmlElement
	public Integer getSns_type() {
		return sns_type;
	}

	public void setSns_type(Integer sns_type) {
		this.sns_type = sns_type;
	}

	@XmlElement
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@XmlElement
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@XmlElement
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@XmlElement
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@XmlTransient
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@XmlElement
	public List<UserActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<UserActivity> activities) {
		this.activities = activities;
	}

	@XmlElement
	public List<UserTeam> getTeams() {
		return teams;
	}

	public void setTeams(List<UserTeam> teams) {
		this.teams = teams;
	}

	@XmlElement
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@XmlElement
	public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}
	
}
