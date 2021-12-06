package com.spring.card.vo;

import lombok.Data;

@Data
public class UserInfoCareer {
	private Integer careerIdx;
	private Integer userIdx;
	private String careerCompName;
	private String careerEnterdate;
	private String careerLeavedate;
	private String careerSpot;
	private String careerResponsib;
}
