package com.spring.card.vo;

import lombok.Data;

@Data
public class UserInfo {
	private Integer userIdx;
	private String userRegisterDate;
	private String userName;
	private String userSocialSecunum;
	private String userGender;
	private String userComp;
	private String userCompEnterdate;
	private String userDept;
	private String userSpot;
	private String userArmyServ;
	private String userMaritalStatus;
	private String userArmyServEnter;
	private String userArmyServLeave;
	private String userArmyServPeriod;
	private String userTelnumWired;
	private String userTelnumWireless;
	private String userEmail;
	
	//분활 작업을위해 추가
	private String userDomain;
	
	private String userZipcode;
	private String userAddress;
	
}
