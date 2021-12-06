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

	//정보 등록하기전 검색 파트
	@Override
	public UserInfo userInfoResult(String userSocialSecunum) {
		return sqlSession.selectOne("card.userInfoResult",userSocialSecunum);
	}
	
	//정보 등록 파트
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
	
	//정보 수정 파트
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
	
	//(+)버튼 활성화 구현전 조회
	@Override
	public String userInfoEduList(Integer eduIdx) {
		return sqlSession.selectOne("card.userInfoEduListCtn", eduIdx);
	}
	
	@Override
	public String userInfoQualifiList(Integer eduIdx) {
		return sqlSession.selectOne("card.userInfoQualifiListCtn", eduIdx);
	}
	
	@Override
	public String userInfoCareerList(Integer eduIdx) {
		return sqlSession.selectOne("card.userInfoCareerListCtn", eduIdx);
	}
	
	@Override
	public String userInfoTrainingList(Integer eduIdx) {
		return sqlSession.selectOne("card.userInfoTrainingListCtn", eduIdx);
	}
	
	@Override
	public String userInfoLicenList(Integer eduIdx) {
		return sqlSession.selectOne("card.userInfoLicenListCtn", eduIdx);
	}
	
	@Override
	public String userInfoProjectList(Integer eduIdx) {
		return sqlSession.selectOne("card.userInfoProjectListCtn", eduIdx);
	}
	
	
	//(-)버튼 활성화
	@Override
	public int userInfoEduDel(int deleteIdx) {
		return sqlSession.delete("card.userInfoMinSchoolDelete", deleteIdx);
	}
	
	@Override
	public int userInfoQualifiDel(int deleteIdx) {
		return sqlSession.delete("card.userInfoMinQualifiDelete", deleteIdx);
	}
	
	@Override
	public int userInfoCareerDel(int deleteIdx) {
		return sqlSession.delete("card.userInfoMinCareerDelete", deleteIdx);
	}
	
	@Override
	public int userInfoTrainingDel(int deleteIdx) {
		return sqlSession.delete("card.userInfoMinTrainingDelete", deleteIdx);
	}
	
	@Override
	public int userInfoLicenDel(int deleteIdx) {
		return sqlSession.delete("card.userInfoMinLicenDelete", deleteIdx);
	}
	
	@Override
	public int userInfoProjectDel(int deleteIdx) {
		return sqlSession.delete("card.userInfoMinProjectDelete", deleteIdx);
	}
	
	//회원 정보 삭제
	@Override
	public int userInfoDelete(int userIdx) {
		return sqlSession.delete("card.userInfoDelete", userIdx);
	}
	
	@Override
	public int userInfoEduDelete(int userIdx) {
		return sqlSession.delete("card.userInfoSchoolDelete", userIdx);
	}
	
	@Override
	public int userInfoQualifiDelete(int userIdx) {
		return sqlSession.delete("card.userInfoQualifiDelete", userIdx);
	}
	
	@Override
	public int userInfoCareerDelete(int userIdx) {
		return sqlSession.delete("card.userInfoCareerDelete", userIdx);
	}
	
	@Override
	public int userInfoTrainingDelete(int userIdx) {
		return sqlSession.delete("card.userInfoTrainingDelete", userIdx);
	}
	
	@Override
	public int userInfoLicenDelete(int userIdx) {
		return sqlSession.delete("card.userInfoLicenDelete", userIdx);
	}
	
	@Override
	public int userInfoProjectDelete(int userIdx) {
		return sqlSession.delete("card.userInfoProjectDelete", userIdx);
	}
	
}
