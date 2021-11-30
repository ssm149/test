package com.spring.card.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.card.dao.CardDao;
import com.spring.card.service.CardService;
import com.spring.card.vo.UserInfo;
import com.spring.card.vo.UserInfoCareer;
import com.spring.card.vo.UserInfoEdu;
import com.spring.card.vo.UserInfoLicen;
import com.spring.card.vo.UserInfoProject;
import com.spring.card.vo.UserInfoQualifi;
import com.spring.card.vo.UserInfoTraining;

@Service
public class CardServiceimpl implements CardService {

	@Autowired
	CardDao cardDao;
	
	@Override
	public List<UserInfo> userList() {
		return cardDao.userList();
	}

	@Override
	public UserInfo userInfoDetail(int userIdx) {
		return cardDao.userInfoDetail(userIdx);
	}

	@Override
	public List<UserInfoCareer> userInfoCareer(int userIdx) {
		return cardDao.userInfoCareer(userIdx);
	}

	@Override
	public List<UserInfoEdu> userInfoEdu(int userIdx) {
		return cardDao.userInfoEdu(userIdx);
	}

	@Override
	public List<UserInfoQualifi> userInfoQualifi(int userIdx) {
		return cardDao.userInfoQualifi(userIdx);
	}

	@Override
	public List<UserInfoTraining> userInfoTraining(int userIdx) {
		return cardDao.userInfoTraining(userIdx);
	}

	@Override
	public List<UserInfoLicen> userInfoLicen(int userIdx) {
		return cardDao.userInfoLicen(userIdx);
	}

	@Override
	public List<UserInfoProject> userInfoProject(int userIdx) {
		return cardDao.userInfoProject(userIdx);
	}

	@Override
	public UserInfo userInfoResult(String userSocialSecunum) {
		return cardDao.userInfoResult(userSocialSecunum);
	}
	
	
	//글쓰기 저장
	@Override
	public int userInfoInsert(UserInfo userInfo) {
		return cardDao.userInfoInsert(userInfo);
	}
	
	@Override
	public int userInfoEduInsert(UserInfoEdu userInfoEdu) {
		return cardDao.userInfoEduInsert(userInfoEdu);
	}
	
	@Override
	public int userInfoQualifiInsert(UserInfoQualifi userInfoQualifi) {
		return cardDao.userInfoQualifiInsert(userInfoQualifi);
	}
	
	@Override
	public int userInfoCareerInsert(UserInfoCareer userInfoCareer) {
		return cardDao.userInfoCareerInsert(userInfoCareer);
	}
	
	@Override
	public int userInfoTrainingInsert(UserInfoTraining userInfoTraining) {
		return cardDao.userInfoTrainingInsert(userInfoTraining);
	}
	
	@Override
	public int userInfoLicenInsert(UserInfoLicen userInfoLicen) {
		return cardDao.userInfoLicenInsert(userInfoLicen);
	}
	
	@Override
	public int userInfoProjectInsert(UserInfoProject userInfoProject) {
		return cardDao.userInfoProjectInsert(userInfoProject);
	}
	
	
	
	
	@Override
	public int userInfoUpdate(UserInfo userInfo) {
		return cardDao.userInfoUpdate(userInfo);
	}
	
	@Override
	public int userInfoEduUpdate(UserInfoEdu userInfoEdu) {
		return cardDao.userInfoEduUpdate(userInfoEdu);
	}

	@Override
	public int userInfoQualifiUpdate(UserInfoQualifi userInfoQualifi) {
		return cardDao.userInfoQualifiUpdate(userInfoQualifi);
	}
	
	@Override
	public int userInfoCareerUpdate(UserInfoCareer userInfoCareer) {
		return cardDao.userInfoCareerUpdate(userInfoCareer);
	}
	
	@Override
	public int userInfoTrainingUpdate(UserInfoTraining userInfoTraining) {
		return cardDao.userInfoTrainingUpdate(userInfoTraining);
	}
	
	@Override
	public int userInfoLicenUpdate(UserInfoLicen userInfoLicen) {
		return cardDao.userInfoLicenUpdate(userInfoLicen);
	}
	
	@Override
	public int userInfoProjectUpdate(UserInfoProject userInfoProject) {
		return cardDao.userInfoProjectUpdate(userInfoProject);
	}

	
}
