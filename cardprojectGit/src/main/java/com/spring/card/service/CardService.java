package com.spring.card.service;

import java.util.List;

import com.spring.card.vo.UserInfo;
import com.spring.card.vo.UserInfoCareer;
import com.spring.card.vo.UserInfoEdu;
import com.spring.card.vo.UserInfoLicen;
import com.spring.card.vo.UserInfoProject;
import com.spring.card.vo.UserInfoQualifi;
import com.spring.card.vo.UserInfoTraining;

public interface CardService {

	//페이지 리스트에서 조회
	public List<UserInfo> userList();
	
	//글쓰기 페이지에서 불러오기
	public UserInfo userInfoDetail(int userIdx);
	public List<UserInfoCareer> userInfoCareer(int userIdx);
	public List<UserInfoEdu> userInfoEdu(int userIdx);
	public List<UserInfoQualifi> userInfoQualifi(int userIdx);
	public List<UserInfoTraining> userInfoTraining(int userIdx);
	public List<UserInfoLicen> userInfoLicen(int userIdx);
	public List<UserInfoProject> userInfoProject(int userIdx);
	
	//신규정보 입력을 위해 검증 테스트
	public UserInfo userInfoResult(String userSocialSecunum);

	//글쓴 정보 저장
	public int userInfoInsert(UserInfo userInfo);
	public int userInfoEduInsert(UserInfoEdu userInfoEdu);
	public int userInfoQualifiInsert(UserInfoQualifi userInfoQualifi);
	public int userInfoCareerInsert(UserInfoCareer userInfoCareer);
	public int userInfoTrainingInsert(UserInfoTraining userInfoTraining);
	public int userInfoLicenInsert(UserInfoLicen userInfoLicen);
	public int userInfoProjectInsert(UserInfoProject userInfoProject);
	
	//글쓴 정보 수정(저장)
	public int userInfoUpdate(UserInfo userInfo);
	public int userInfoEduUpdate(UserInfoEdu userInfoEdu);
	public int userInfoQualifiUpdate(UserInfoQualifi userInfoQualifi);
	public int userInfoCareerUpdate(UserInfoCareer userInfoCareer);
	public int userInfoTrainingUpdate(UserInfoTraining userInfoTraining);
	public int userInfoLicenUpdate(UserInfoLicen userInfoLicen);
	public int userInfoProjectUpdate(UserInfoProject userInfoProject);
	
	//글쓴 정보 (+)버튼(구현전 조회)
	public String userInfoEduList(Integer eduIdx);
	public String userInfoQualifiList(Integer eduIdx);
	public String userInfoCareerList(Integer eduIdx);
	public String userInfoTrainingList(Integer eduIdx);
	public String userInfoLicenList(Integer eduIdx);
	public String userInfoProjectList(Integer eduIdx);
		
	//글쓴 정보 수정(-)버튼
	public int userInfoEduDel(int deleteIdx);
	public int userInfoQualifiDel(int deleteIdx);
	public int userInfoCareerDel(int deleteIdx);
	public int userInfoTrainingDel(int deleteIdx);
	public int userInfoLicenDel(int deleteIdx);
	public int userInfoProjectDel(int deleteIdx);
	
	//삭제 요청
	//고정 테이블
	public int userInfoDelete(int userIdx);
		
	//유동 테이블
	public int userInfoEduDelete(int userIdx);
	public int userInfoQualifiDelete(int userIdx);
	public int userInfoCareerDelete(int userIdx);
	public int userInfoTrainingDelete(int userIdx);
	public int userInfoLicenDelete(int userIdx);
	public int userInfoProjectDelete(int userIdx);
	
}
