package com.spring.card.vo;

import lombok.Data;

@Data
public class UserInfoTraining {
	private Integer trainingIdx;
	private Integer userIdx;
	private String trainingName;
	private String trainingStartdate;
	private String trainingEnddate;
	private String trainingAgency;
}
