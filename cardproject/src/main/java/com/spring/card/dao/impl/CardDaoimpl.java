package com.spring.card.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.card.dao.CardDao;
import com.spring.card.vo.UserInfo;
import com.spring.card.vo.UserInfoCareer;
import com.spring.card.vo.UserInfoEdu;
import com.spring.card.vo.UserInfoLicen;
import com.spring.card.vo.UserInfoProject;
import com.spring.card.vo.UserInfoQualifi;
import com.spring.card.vo.UserInfoTraining;

@Repository
public class CardDaoimpl implements CardDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<UserInfo> userList() {
		return sqlSession.selectList("card.userList");
	}

	@Override
	public UserInfo userInfoDetail(int userIdx) {
		return sqlSession.selectOne("card.userInfoDetail",userIdx);
	}

	@Override
	public List<UserInfoCareer> userInfoCareer(int userIdx) {
		return sqlSession.selectList("card.userInfoCareer",userIdx);
	}

	@Override
	public List<UserInfoEdu> userInfoEdu(int userIdx) {
		return sqlSession.selectList("card.userInfoEdu",userIdx);
	}

	@Override
	public List<UserInfoQualifi> userInfoQualifi(int userIdx) {
		return sqlSession.selectList("card.userInfoQualifi",userIdx);
	}

	@Override
	public List<UserInfoTraining> userInfoTraining(int userIdx) {
		return sqlSession.selectList("card.userInfoTraining",userIdx);
	}

	@Override
	public List<UserInfoLicen> userInfoLicen(int userIdx) {
		return sqlSession.selectList("card.userInfoLicen",userIdx);
	}

	@Override
	public List<UserInfoProject> userInfoProject(int userIdx) {
		return sqlSession.selectList("card.userInfoProject",userIdx);
	}

	@Override
	public UserInfo userInfoResult(String userSocialSecunum) {
		return sqlSession.selectOne("card.userInfoResult",userSocialSecunum);
	}
	
	
	
	@Override
	public int userInfoInsert(UserInfo userInfo) {
		return sqlSession.insert("card.userInfoInsert", userInfo);
	}

	@Override
	public int userInfoEduInsert(UserInfoEdu userInfoEdu) {
		return sqlSession.insert("card.userInfoSchoolInsert", userInfoEdu);
	}
	
	@Override
	public int userInfoQualifiInsert(UserInfoQualifi userInfoQualifi) {
		return sqlSession.insert("card.userInfoQualifiInsert", userInfoQualifi);
	}
	
	@Override
	public int userInfoCareerInsert(UserInfoCareer userInfoCareer) {
		return sqlSession.insert("card.userInfoCareerInsert", userInfoCareer);
	}

	@Override
	public int userInfoTrainingInsert(UserInfoTraining userInfoTraining) {
		return sqlSession.insert("card.userInfoTrainingInsert", userInfoTraining);
	}
	
	@Override
	public int userInfoLicenInsert(UserInfoLicen userInfoLicen) {
		return sqlSession.insert("card.userInfoLicenInsert", userInfoLicen);
	}
	
	@Override
	public int userInfoProjectInsert(UserInfoProject userInfoProject) {
		return sqlSession.insert("card.userInfoProjectInsert", userInfoProject);
	}
	
	
	
	
	@Override
	public int userInfoUpdate(UserInfo userInfo) {
		return sqlSession.update("card.userInfoUpdate", userInfo);
	}

	@Override
	public int userInfoEduUpdate(UserInfoEdu userInfoEdu) {
		return sqlSession.update("card.userInfoSchoolUpdate", userInfoEdu);
	}
	
	@Override
	public int userInfoQualifiUpdate(UserInfoQualifi userInfoQualifi) {
		return sqlSession.update("card.userInfoQualifiUpdate", userInfoQualifi);
	}
	
	@Override
	public int userInfoCareerUpdate(UserInfoCareer userInfoCareer) {
		return sqlSession.update("card.userInfoCareerUpdate", userInfoCareer);
	}

	@Override
	public int userInfoTrainingUpdate(UserInfoTraining userInfoTraining) {
		return sqlSession.update("card.userInfoTrainingUpdate", userInfoTraining);
	}
	
	@Override
	public int userInfoLicenUpdate(UserInfoLicen userInfoLicen) {
		return sqlSession.update("card.userInfoLicenUpdate", userInfoLicen);
	}
	
	@Override
	public int userInfoProjectUpdate(UserInfoProject userInfoProject) {
		return sqlSession.update("card.userInfoProjectUpdate", userInfoProject);
	}
	
}
