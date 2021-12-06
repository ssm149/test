package com.spring.card.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.card.HomeController;
import com.spring.card.service.CardService;
import com.spring.card.vo.UserInfo;
import com.spring.card.vo.UserInfoCareer;
import com.spring.card.vo.UserInfoEdu;
import com.spring.card.vo.UserInfoLicen;
import com.spring.card.vo.UserInfoProject;
import com.spring.card.vo.UserInfoQualifi;
import com.spring.card.vo.UserInfoTraining;

@Controller
public class CardController {
	
	@Autowired
	CardService cardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/card", method = {RequestMethod.GET, RequestMethod.POST})
	public String card(Locale locale, Model model) {
		logger.info("========== card 영역", locale);

		List<UserInfo> userList = cardService.userList();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
		
		if(!userList.isEmpty()) {
			
			for(UserInfo userlist : userList) {
				String userDate = userlist.getUserRegisterDate();
				try {
					Date dateUserList = simpleDateFormat.parse(userDate);
					String userDateList = simpleDateFormat.format(dateUserList);
					
					userlist.setUserRegisterDate(userDateList);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			model.addAttribute("userList", userList);
		}
		return "card";
	}
	
	@RequestMapping(value="/cardwrite", method = RequestMethod.GET)
	public String cardwrite(Locale locale, Model model, HttpServletRequest request) {
		logger.info("========== cardwrite 영역", locale);
		
		return "cardwrite";
	}
	
	@RequestMapping(value = "/cardwrite", method = RequestMethod.POST)
	public String userInfoCardwrite(Locale locale, Model model, HttpServletRequest request) {
		logger.info("========== userInfoCardwrite 영역", locale);
		
		String struserIdx = request.getParameter("userIdx");
		int userIdx = 0;
		
		if(struserIdx == null || struserIdx == "") {
			model.addAttribute("userIdx", userIdx);
		} else if(struserIdx != null || struserIdx != "") {
			userIdx = Integer.parseInt(request.getParameter("userIdx"));
		
			UserInfo userInfoDetail = cardService.userInfoDetail(userIdx);
			
			String email = userInfoDetail.getUserEmail();
			if(email != "" && email != null) {
				String f_email = "";
				String s_email = "";
				int f_e = email.indexOf("@");
				
				if(f_e != -1) {
					f_email = email.substring(0, f_e);
					s_email = email.substring(f_e,email.length());
					userInfoDetail.setUserEmail(f_email);
					userInfoDetail.setUserDomain(s_email);
				} else {
					userInfoDetail.setUserDomain("");
				}
				
			}
			List<UserInfoCareer> userInfoCareer = cardService.userInfoCareer(userIdx);
			List<UserInfoEdu> userInfoEdu = cardService.userInfoEdu(userIdx);
			List<UserInfoQualifi> userInfoQualifi = cardService.userInfoQualifi(userIdx);
			List<UserInfoTraining> userInfoTraining = cardService.userInfoTraining(userIdx);
			List<UserInfoLicen> userInfoLicen = cardService.userInfoLicen(userIdx);
			List<UserInfoProject> userInfoProject = cardService.userInfoProject(userIdx);
			
			model.addAttribute("userInfoProject", userInfoProject);
			model.addAttribute("userInfoLicen", userInfoLicen);
			model.addAttribute("userInfoTraining", userInfoTraining);
			model.addAttribute("userInfoQualifi", userInfoQualifi);
			model.addAttribute("userInfoEdu", userInfoEdu);
			model.addAttribute("userInfoCareer", userInfoCareer);
			model.addAttribute("userInfo",userInfoDetail);
			model.addAttribute("userIdx", userIdx);
		}
		
		return "cardwrite";
	}
	
	@RequestMapping(value = "/cardInsert", method = RequestMethod.POST)
	public String cardInsert(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//userinfo 테이블 정보
		String userIdx = request.getParameter("userIdx");
		
		//고정 테이블(vo)
		UserInfo userInfo = new UserInfo();
		
		//유동 테이블(vo)
		//유동 테이블 list으로 insert/delete / select 출력으로 수정
		//컬렉션 프레임 워크로 수정중
		ArrayList<UserInfoEdu> userInfoEduList = new ArrayList<UserInfoEdu>();
		ArrayList<UserInfoQualifi> userInfoQualifiList = new ArrayList<UserInfoQualifi>();
		ArrayList<UserInfoCareer> userInfoCareerList = new ArrayList<UserInfoCareer>();
		ArrayList<UserInfoTraining> userInfoTrainingList = new ArrayList<UserInfoTraining>();
		ArrayList<UserInfoLicen> userInfoLicenList = new ArrayList<UserInfoLicen>();
		ArrayList<UserInfoProject> userInfoProjectList = new ArrayList<UserInfoProject>();
		
		int IntuserIdx = 0;
		if(userIdx != "") {
			IntuserIdx = Integer.parseInt(userIdx);
			userInfo.setUserIdx(IntuserIdx);
		}
		
		/*고정 테이블*/
		/*개인정보*/
		userInfo.setUserName(request.getParameter("userName"));
		userInfo.setUserSocialSecunum(request.getParameter("userSocialSecunum"));
		userInfo.setUserGender(request.getParameter("userGender"));
		userInfo.setUserComp(request.getParameter("userComp"));
		userInfo.setUserCompEnterdate(request.getParameter("userCompEnterdate"));
		userInfo.setUserDept(request.getParameter("userDept"));
		userInfo.setUserSpot(request.getParameter("userSpot"));
		userInfo.setUserArmyServ(request.getParameter("userArmyServ"));
		userInfo.setUserMaritalStatus(request.getParameter("userMaritalStatus"));
		userInfo.setUserArmyServEnter(request.getParameter("userArmyServEnter"));
		userInfo.setUserArmyServLeave(request.getParameter("userArmyServLeave"));
		userInfo.setUserArmyServPeriod(request.getParameter("userArmyServPeriod")); 
		
		//userinfo2 테이블 정보
		userInfo.setUserTelnumWired(request.getParameter("userTelnumWired"));
		userInfo.setUserTelnumWireless(request.getParameter("userTelnumWireless"));
		
		String userEmail = request.getParameter("userEmail");
		String userDomain = request.getParameter("userDomain");
		String emailResult = "";
		
		emailResult = userEmail + userDomain;
		userInfo.setUserEmail(emailResult);
		userInfo.setUserZipcode(request.getParameter("userZipcode"));
		userInfo.setUserAddress(request.getParameter("userAddress"));
		
		int userInfoInsertCnt = 0;
		if(IntuserIdx == 0) {
			userInfoInsertCnt = cardService.userInfoInsert(userInfo);
			
			if(userInfoInsertCnt > 0) {
				System.out.println("기본 정보 입력 성공");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('기본정보 등록 실패!');");
				out.print("history.back();");
				out.print("</script>");
			}
		}
		
		/*유동 테이블*/
		
		/*학교*/
		/*
		 * userInfoEdu.setEduSchoolName(request.getParameter("eduSchoolName"));
		 * userInfoEdu.setEduStatus(request.getParameter("eduStatus"));
		 * userInfoEdu.setEduDate(request.getParameter("eduDate"));
		 */
		
		//주민번호로 전달 받은이유는 : 동일한 값이 없기떄문
		String userSocialSecunum = request.getParameter("userSocialSecunum");
		UserInfo userInfoOne = cardService.userInfoResult(userSocialSecunum);
		
		//유동 테이블 설정
		
		String schoolIdx[] = request.getParameterValues("eduIdx");
		String schoolName[] = request.getParameterValues("eduSchoolName");
		String schoolStatus[] = request.getParameterValues("eduStatus");
		String schoolDate[] = request.getParameterValues("eduDate");
		
		if(schoolName != null) {
			
			System.out.println("유저 인덱스 - school "+userInfoOne.getUserIdx());
			
			for(int i = 0; i<schoolName.length; i++) {
				UserInfoEdu userInfoEdu = new UserInfoEdu();
				if (schoolIdx[i] == "") {
					userInfoEdu.setEduIdx(-1);
				} else if (schoolIdx[i] != "") {
					userInfoEdu.setEduIdx(Integer.parseInt(schoolIdx[i]));
				}
				userInfoEdu.setUserIdx(userInfoOne.getUserIdx());
				userInfoEdu.setEduSchoolName(schoolName[i]);
				userInfoEdu.setEduStatus(schoolStatus[i]);
				userInfoEdu.setEduDate(schoolDate[i]);
				
				userInfoEduList.add(userInfoEdu);
			}
		}
		
		/*자격증*/
		/*
		 * userInfoQualifi.setQualifiName(request.getParameter("qualifiName"));
		 * userInfoQualifi.setQualifiGetdate(request.getParameter("qualifiGetdate"));
		 */
		
		String qualifiIdx[] = request.getParameterValues("qualifiIdx");
		String qualifiName[] = request.getParameterValues("qualifiName");
		String qualifiGetdate[] = request.getParameterValues("qualifiGetdate");
		
		if(qualifiName != null) {
			
			System.out.println("유저 인덱스 - qualifi "+userInfoOne.getUserIdx());
			
			for(int i = 0; i<qualifiName.length; i++) {
				UserInfoQualifi userInfoQualifi = new UserInfoQualifi();
				if (qualifiIdx[i] == "") {
					userInfoQualifi.setQualifiIdx(-1);
				} else if (qualifiIdx[i] != "") {
					userInfoQualifi.setQualifiIdx(Integer.parseInt(qualifiIdx[i]));
				}
				
				userInfoQualifi.setUserIdx(userInfoOne.getUserIdx());
				userInfoQualifi.setQualifiName(qualifiName[i]);
				userInfoQualifi.setQualifiGetdate(qualifiGetdate[i]);
				
				userInfoQualifiList.add(userInfoQualifi);
			}
		}
		
		/*회사*/
		/*
		 * userInfoCareer.setCareerCompName(request.getParameter("careerCompName"));
		 * userInfoCareer.setCareerEnterdate(request.getParameter("careerEnterdate"));
		 * userInfoCareer.setCareerLeavedate(request.getParameter("careerLeavedate"));
		 * userInfoCareer.setCareerSpot(request.getParameter("careerSpot"));
		 * userInfoCareer.setCareerResponsib(request.getParameter("careerResponsib"));
		 */
		
		String careerIdx[] = request.getParameterValues("careerIdx");
		String careerCompName[] = request.getParameterValues("careerCompName");
		String careerEnterdate[] = request.getParameterValues("careerEnterdate");
		String careerLeavedate[] = request.getParameterValues("careerLeavedate");
		String careerSpot[] = request.getParameterValues("careerSpot");
		String careerResponsib[] = request.getParameterValues("careerResponsib");
		
		if(careerCompName != null) {
			
			System.out.println("유저 인덱스 - career "+userInfoOne.getUserIdx());
			
			for(int i = 0; i<careerCompName.length; i++) {
				UserInfoCareer userInfoCareer = new UserInfoCareer();
				
				if (careerIdx[i] == "") {
					userInfoCareer.setCareerIdx(-1);
				} else if (careerIdx[i] != "") {
					userInfoCareer.setCareerIdx(Integer.parseInt(careerIdx[i]));
				}
				
				userInfoCareer.setUserIdx(userInfoOne.getUserIdx());
				userInfoCareer.setCareerCompName(careerCompName[i]);
				userInfoCareer.setCareerEnterdate(careerEnterdate[i]);
				userInfoCareer.setCareerLeavedate(careerLeavedate[i]);
				userInfoCareer.setCareerSpot(careerSpot[i]);
				userInfoCareer.setCareerResponsib(careerResponsib[i]);
				
				userInfoCareerList.add(userInfoCareer);
			}
		}
		
		/*교육*/
		/*
		 * userInfoTraining.setTrainingName(request.getParameter("trainingName"));
		 * userInfoTraining.setTrainingStartdate(request.getParameter(
		 * "trainingStartdate"));
		 * userInfoTraining.setTrainingEnddate(request.getParameter("trainingEnddate"));
		 * userInfoTraining.setTrainingAgency(request.getParameter("trainingAgency"));
		 */
		
		String trainingIdx[] = request.getParameterValues("trainingIdx");
		String trainingName[] = request.getParameterValues("trainingName");
		String trainingStartdate[] = request.getParameterValues("trainingStartdate");
		String trainingEnddate[] = request.getParameterValues("trainingEnddate");
		String trainingAgency[] = request.getParameterValues("trainingAgency");
		
		if(trainingName != null) {
			
			System.out.println("유저 인덱스 - training "+userInfoOne.getUserIdx());
			
			for(int i = 0; i<trainingName.length; i++) {
				UserInfoTraining userInfoTraining = new UserInfoTraining();
				
				if (trainingIdx[i] == "") {
					userInfoTraining.setTrainingIdx(-1);
				} else if (trainingIdx[i] != "") {
					userInfoTraining.setTrainingIdx(Integer.parseInt(trainingIdx[i]));
				}
				
				userInfoTraining.setUserIdx(userInfoOne.getUserIdx());
				userInfoTraining.setTrainingName(trainingName[i]);
				userInfoTraining.setTrainingStartdate(trainingStartdate[i]);
				userInfoTraining.setTrainingEnddate(trainingEnddate[i]);
				userInfoTraining.setTrainingAgency(trainingAgency[i]);
				
				userInfoTrainingList.add(userInfoTraining);
			}
		}
		
		/*보유 기술*/
		/*
		 * userInfoLicen.setLicenName(request.getParameter("licenName"));
		 * userInfoLicen.setLicenSkillLevel(request.getParameter("licenSkillLevel"));
		 */

		String licenIdx[] = request.getParameterValues("licenIdx");
		String licenName[] = request.getParameterValues("licenName");
		String licenSkillLevel[] = request.getParameterValues("licenSkillLevel");
		
		if(licenName != null) {
			
			System.out.println("유저 인덱스 - licen "+userInfoOne.getUserIdx());
			
			for(int i = 0; i<licenName.length; i++) {
				UserInfoLicen userInfoLicen = new UserInfoLicen();
				
				if (licenIdx[i] == "") {
					userInfoLicen.setLicenIdx(-1);
				} else if (licenIdx[i] != "") {
					userInfoLicen.setLicenIdx(Integer.parseInt(licenIdx[i]));
				}
				
				userInfoLicen.setUserIdx(userInfoOne.getUserIdx());
				userInfoLicen.setLicenName(licenName[i]);
				userInfoLicen.setLicenSkillLevel(licenSkillLevel[i]);
				
				userInfoLicenList.add(userInfoLicen);
			}
		}
		
		/*프로젝트*/
		/*
		 * userInfoProject.setSkillProjectName(request.getParameter("skillProjectName"))
		 * ; userInfoProject.setSkillStartdate(request.getParameter("skillStartdate"));
		 * userInfoProject.setSkillEnddate(request.getParameter("skillEnddate"));
		 * userInfoProject.setSkillCustomerComp(request.getParameter("skillCustomerComp"
		 * )); userInfoProject.setSkillWorkComp(request.getParameter("skillWorkComp"));
		 * userInfoProject.setSkillApplied(request.getParameter("skillApplied"));
		 * userInfoProject.setSkillIndustry(request.getParameter("skillIndustry"));
		 * userInfoProject.setSkillRole(request.getParameter("skillRole"));
		 * userInfoProject.setSkillModel(request.getParameter("skillModel"));
		 * userInfoProject.setSkillOs(request.getParameter("skillOs"));
		 * userInfoProject.setSkillLang(request.getParameter("skillLang"));
		 * userInfoProject.setSkillDbms(request.getParameter("skillDbms"));
		 * userInfoProject.setSkillComm(request.getParameter("skillComm"));
		 * userInfoProject.setSkillTool(request.getParameter("skillTool"));
		 * userInfoProject.setSkillEtc(request.getParameter("skillEtc"));
		 */
		
		String skillProjectIdx[] = request.getParameterValues("skillProjectIdx");
		String skillProjectName[] = request.getParameterValues("skillProjectName");
		String skillStartdate[] = request.getParameterValues("skillStartdate");
		String skillEnddate[] = request.getParameterValues("skillEnddate");
		String skillCustomerComp[] = request.getParameterValues("skillCustomerComp");
		String skillWorkComp[] = request.getParameterValues("skillWorkComp");
		String skillApplied[] = request.getParameterValues("skillApplied");
		String skillIndustry[] = request.getParameterValues("skillIndustry");
		String skillRole[] = request.getParameterValues("skillRole");
		String skillModel[] = request.getParameterValues("skillModel");
		String skillOs[] = request.getParameterValues("skillOs");
		String skillLang[] = request.getParameterValues("skillLang");
		String skillDbms[] = request.getParameterValues("skillDbms");
		String skillComm[] = request.getParameterValues("skillComm");
		String skillTool[] = request.getParameterValues("skillTool");
		String skillEtc[] = request.getParameterValues("skillEtc");
		
		if(skillProjectName != null) {
			
			System.out.println("유저 인덱스 - Project "+userInfoOne.getUserIdx());
			
			for(int i = 0; i<skillProjectName.length; i++) {
				UserInfoProject userInfoProject = new UserInfoProject();
				
				if (skillProjectIdx[i] == "") {
					userInfoProject.setSkillIdx(-1);
				} else if (skillProjectIdx[i] != "") {
					userInfoProject.setSkillIdx(Integer.parseInt(skillProjectIdx[i]));
				}
				
				userInfoProject.setUserIdx(userInfoOne.getUserIdx());
				userInfoProject.setSkillProjectName(skillProjectName[i]);
				userInfoProject.setSkillStartdate(skillStartdate[i]);
				userInfoProject.setSkillEnddate(skillEnddate[i]);
				userInfoProject.setSkillCustomerComp(skillCustomerComp[i]);
				userInfoProject.setSkillWorkComp(skillWorkComp[i]);
				userInfoProject.setSkillApplied(skillApplied[i]);
				userInfoProject.setSkillIndustry(skillIndustry[i]);
				userInfoProject.setSkillRole(skillRole[i]);
				userInfoProject.setSkillModel(skillModel[i]);
				userInfoProject.setSkillOs(skillOs[i]);
				userInfoProject.setSkillLang(skillLang[i]);
				userInfoProject.setSkillDbms(skillDbms[i]);
				userInfoProject.setSkillComm(skillComm[i]);
				userInfoProject.setSkillTool(skillTool[i]);
				userInfoProject.setSkillEtc(skillEtc[i]);
				
				userInfoProjectList.add(userInfoProject);
			}
		}
		
		//insert성공 카운터 돌려 받기 위한 변수명
				int userInfoEduInsertCnt = 0;
				int userInfoQualifiInsertCnt = 0;
				int userInfoCareerInsertCnt = 0;
				int userInfoTrainingInsertCnt = 0;
				int userInfoLicenInsertCnt = 0;
				int userInfoProjectInsertCnt = 0;
				
				//update성공 카운터 돌려 박기 위한 변수명
				int userInfoUpdateCnt = 0;
				int userInfoEduUpdateCnt = 0;
				int userInfoQualifiUpdateCnt = 0;
				int userInfoCareerUpdateCnt = 0;
				int userInfoTrainingUpdateCnt = 0;
				int userInfoLicenUpdateCnt = 0;
				int userInfoProjectUpdateCnt = 0;
				
				//update (-)버튼을 눌렀을시 위한 변수명
				int userInfoEduDeleteCnt = 0;
		
		//delete 삭제 값
				
		String deleteEduStr = request.getParameter("dle1");
		String deleteQualifiStr = request.getParameter("dle2");
		String deleteCareerStr = request.getParameter("dle3");
		String deleteTrainingStr = request.getParameter("dle4");
		String deleteLicenStr = request.getParameter("dle5");
		String deleteProjectStr = request.getParameter("dle6");
				
		String deleteEduArr[] = deleteEduStr.split(",");
		String deleteQualifiArr[] = deleteQualifiStr.split(",");
		String deleteCareerArr[] = deleteCareerStr.split(",");
		String deleteTrainingArr[] = deleteTrainingStr.split(",");
		String deleteLicenArr[] = deleteLicenStr.split(",");
		String deleteProjectArr[] = deleteProjectStr.split(",");
		
		int deleteEudIdx[] = new int [deleteEduArr.length];
		int deleteQualifiIdx[] = new int [deleteQualifiArr.length];
		int deleteCareerIdx[] = new int [deleteCareerArr.length];
		int deleteTrainingIdx[] = new int [deleteTrainingArr.length];
		int deleteLicenIdx[] = new int [deleteLicenArr.length];
		int deleteProjectIdx[] = new int [deleteProjectArr.length];
		
		int k;
		if(deleteEduArr != null) {
			for(k = 0; k<deleteEduArr.length; k++) {
				System.err.println("delete idx1 번호  나옴");
				if(deleteEduArr[k] != "") {
					deleteEudIdx[k] = Integer.parseInt(deleteEduArr[k]);
				} else if (deleteEduArr[k] == "") {
					deleteEudIdx[k] = -1;
				}
			}
		}
		
		if(deleteQualifiArr != null) {
			for(k = 0; k<deleteQualifiArr.length; k++) {
				System.err.println("delete idx2 번호  나옴");
				if(deleteQualifiArr[k] != "") {
					deleteQualifiIdx[k] = Integer.parseInt(deleteQualifiArr[k]);
				} else if (deleteQualifiArr[k] == "") {
					deleteQualifiIdx[k] = -1;
				}
			}
		}
		
		if(deleteCareerArr != null) {
			for(k = 0; k<deleteCareerArr.length; k++) {
				System.err.println("delete idx3 번호  나옴");
				if(deleteCareerArr[k] != "") {
					deleteCareerIdx[k] = Integer.parseInt(deleteCareerArr[k]);
				} else if (deleteCareerArr[k] == "") {
					deleteCareerIdx[k] = -1;
				}
			}
		}
		
		if(deleteTrainingArr != null) {
			for(k = 0; k<deleteTrainingArr.length; k++) {
				System.err.println("delete idx4 번호  나옴");
				if(deleteTrainingArr[k] != "") {
					deleteTrainingIdx[k] = Integer.parseInt(deleteTrainingArr[k]);
				} else if (deleteTrainingArr[k] == "") {
					deleteTrainingIdx[k] = -1;
				}
			}
		}
		
		if(deleteLicenArr != null) {
			for(k = 0; k<deleteLicenArr.length; k++) {
				System.err.println("delete idx5 번호  나옴");
				if(deleteLicenArr[k] != "") {
					deleteLicenIdx[k] = Integer.parseInt(deleteLicenArr[k]);
				} else if (deleteLicenArr[k] == "") {
					deleteLicenIdx[k] = -1;
				}
			}
		}
		
		if(deleteProjectArr != null) {
			for(k = 0; k<deleteProjectArr.length; k++) {
				System.err.println("delete idx6 번호  나옴");
				if(deleteProjectArr[k] != "") {
					deleteProjectIdx[k] = Integer.parseInt(deleteProjectArr[k]);
				} else if (deleteProjectArr[k] == "") {
					deleteProjectIdx[k] = -1;
				}
			}
		}
		
		if(IntuserIdx == 0) {
			for(UserInfoEdu userInfoEduCnt  : userInfoEduList) {
				userInfoEduInsertCnt = cardService.userInfoEduInsert(userInfoEduCnt);
			}
			
			for(UserInfoQualifi userInfoQualifiCnt : userInfoQualifiList) {
				userInfoQualifiInsertCnt = cardService.userInfoQualifiInsert(userInfoQualifiCnt);
			}
			
			for(UserInfoCareer userInfoCareerCnt : userInfoCareerList) {
				userInfoCareerInsertCnt = cardService.userInfoCareerInsert(userInfoCareerCnt);
			}
			
			for(UserInfoTraining userInfoTrainingCnt : userInfoTrainingList) {	
				userInfoTrainingInsertCnt = cardService.userInfoTrainingInsert(userInfoTrainingCnt);
			}
			
			for(UserInfoLicen userInfoLicenCnt : userInfoLicenList) {	
				userInfoLicenInsertCnt = cardService.userInfoLicenInsert(userInfoLicenCnt);
			}
			
			for(UserInfoProject userInfoProjectCnt : userInfoProjectList) {	
				userInfoProjectInsertCnt = cardService.userInfoProjectInsert(userInfoProjectCnt);
			}
			
			if(userInfoInsertCnt > 0) {
				System.out.println("성공11");
				System.out.println("유저 인덱스 : "+IntuserIdx);
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('등록 실패!');");
				out.print("history.back();");
				out.print("</script>");
			}
		} else if (IntuserIdx != 0) {
			
			userInfoUpdateCnt = cardService.userInfoUpdate(userInfo);
			//여기 수정 해야함 (유동 테이블)
			//유동 테이블 조회후 
			List<UserInfoEdu> reUserInfoEdu = cardService.userInfoEdu(IntuserIdx);
			List<UserInfoQualifi> reUserInfoQualifi = cardService.userInfoQualifi(IntuserIdx);
			List<UserInfoCareer> reUserInfoCareer = cardService.userInfoCareer(IntuserIdx);
			List<UserInfoTraining> reUserInfoTraining = cardService.userInfoTraining(IntuserIdx);
			List<UserInfoLicen> reUserInfoLicen = cardService.userInfoLicen(IntuserIdx);
			List<UserInfoProject> reUserInfoProject = cardService.userInfoProject(IntuserIdx);
			
			int i;
			int j;
			//조회시 비어있으면 insert
			if(reUserInfoEdu.isEmpty()) {
				for(UserInfoEdu userInfoEduCnt : userInfoEduList) {
					userInfoEduInsertCnt = cardService.userInfoEduInsert(userInfoEduCnt);
				}
			}
			
			/*
			 * for(UserInfoEdu userInfoEduCnt : userInfoEduList) { userInfoEduUpdateCnt =
			 * cardService.userInfoEduUpdate(userInfoEduCnt); }
			 */
			
			//조회시 비어있지 않으면 update
			if(!reUserInfoEdu.isEmpty()) {
				
				//정보 수정 파트(update)
				for(i=0; i<userInfoEduList.size(); i++) {
					for(j=0; j<reUserInfoEdu.size(); j++) {
						if(userInfoEduList.get(i).getEduIdx().equals(reUserInfoEdu.get(j).getEduIdx())) {
							System.err.println("정보 수정 파트1-1");
							userInfoEduUpdateCnt = cardService.userInfoEduUpdate(userInfoEduList.get(i));
						} 
					}
				}
				
				//정보 수정 파트(update) (+) 버튼 기능
				for(i=0; i<userInfoEduList.size(); i++) {
					String ListEduCnt = cardService.userInfoEduList(userInfoEduList.get(i).getEduIdx());
					
					if(ListEduCnt == null) {
						cardService.userInfoEduInsert(userInfoEduList.get(i));
					}
					ListEduCnt = "1";
				}
				
				//정보 수정 파트(update) (-) 버튼 기능
				 for(i=0; i<deleteEudIdx.length; i++) { 
					 System.err.println("삭제 버튼 기능 활성화2-1 ");
					 cardService.userInfoEduDel(deleteEudIdx[i]);
				}
			}
			
			/*
			 * for(UserInfoQualifi userInfoQualifiCnt : userInfoQualifiList) {
			 * userInfoQualifiUpdateCnt =
			 * cardService.userInfoQualifiUpdate(userInfoQualifiCnt); }
			 */
			
			//조회시 비어있으면 insert
			if(reUserInfoQualifi.isEmpty()) {
				for(UserInfoQualifi userInfoQualifiCnt : userInfoQualifiList) {
					userInfoQualifiInsertCnt = cardService.userInfoQualifiInsert(userInfoQualifiCnt);
				}
			}
			
			//조회시 비어있지 않으면 update
			if(!reUserInfoQualifi.isEmpty()) {
			
				//정보 수정 파트(update)
				for(i=0; i<userInfoQualifiList.size(); i++) {
					for(j=0; j<reUserInfoQualifi.size(); j++) {
						if(userInfoQualifiList.get(i).getQualifiIdx().equals(reUserInfoQualifi.get(j).getQualifiIdx())) {
							System.err.println("정보 수정 파트1-2");
							userInfoQualifiUpdateCnt = cardService.userInfoQualifiUpdate(userInfoQualifiList.get(i));
						} 
					}
				}
				
				//정보 수정 파트(update) (+) 버튼 기능
				for(i=0; i<userInfoQualifiList.size(); i++) {
					String ListQualifiCnt = cardService.userInfoQualifiList(userInfoQualifiList.get(i).getQualifiIdx());
					
					if(ListQualifiCnt == null) {
						cardService.userInfoQualifiInsert(userInfoQualifiList.get(i));
					}
					ListQualifiCnt = "1";
				}
				
				//정보 수정 파트(update) (-) 버튼 기능
				 for(i=0; i<deleteQualifiIdx.length; i++) { 
					 System.err.println("삭제 버튼 기능 활성화2-2 ");
					 cardService.userInfoQualifiDel(deleteQualifiIdx[i]);
				} 
			}
			
			/*
			 * for(UserInfoCareer userInfoCareerCnt : userInfoCareerList) {
			 * userInfoCareerUpdateCnt =
			 * cardService.userInfoCareerUpdate(userInfoCareerCnt); }
			 */
			
			//조회시 비어있으면 insert
			if(reUserInfoCareer.isEmpty()) {
				for(UserInfoCareer userInfoCareerCnt : userInfoCareerList) {
					userInfoCareerInsertCnt = cardService.userInfoCareerInsert(userInfoCareerCnt);
				}
			}
			
			//조회시 비어있지 않으면 update
			if(!reUserInfoCareer.isEmpty()) {
			
				//정보 수정 파트(update)
				for(i=0; i<userInfoCareerList.size(); i++) {
					for(j=0; j<reUserInfoCareer.size(); j++) {
						if(userInfoCareerList.get(i).getCareerIdx().equals(reUserInfoCareer.get(j).getCareerIdx())) {
							System.err.println("정보 수정 파트1-3");
							userInfoCareerUpdateCnt = cardService.userInfoCareerUpdate(userInfoCareerList.get(i));
						} 
					}
				}
				
				//정보 수정 파트(update) (+) 버튼 기능
				for(i=0; i<userInfoCareerList.size(); i++) {
					String ListCareerCnt = cardService.userInfoCareerList(userInfoCareerList.get(i).getCareerIdx());
					
					if(ListCareerCnt == null) {
						cardService.userInfoCareerInsert(userInfoCareerList.get(i));
					}
					ListCareerCnt = "1";
				}
				
				//정보 수정 파트(update) (-) 버튼 기능
				 for(i=0; i<deleteCareerIdx.length; i++) { 
					 System.err.println("삭제 버튼 기능 활성화2-3 ");
					 cardService.userInfoCareerDel(deleteCareerIdx[i]);
				} 
			}
			
			/*
			 * for(UserInfoTraining userInfoTrainingCnt : userInfoTrainingList) {
			 * userInfoTrainingUpdateCnt =
			 * cardService.userInfoTrainingUpdate(userInfoTrainingCnt); }
			 */
			
			//조회시 비어있으면 insert
			if(reUserInfoTraining.isEmpty()) {
				for(UserInfoTraining userInfoTrainingCnt : userInfoTrainingList) {
					userInfoTrainingInsertCnt = cardService.userInfoTrainingInsert(userInfoTrainingCnt);
				}
			}
			
			//조회시 비어있지 않으면 update
			if(!reUserInfoTraining.isEmpty()) {
			
				//정보 수정 파트(update)
				for(i=0; i<userInfoTrainingList.size(); i++) {
					for(j=0; j<reUserInfoTraining.size(); j++) {
						if(userInfoTrainingList.get(i).getTrainingIdx().equals(reUserInfoTraining.get(j).getTrainingIdx())) {
							System.err.println("정보 수정 파트1-4");
							userInfoTrainingUpdateCnt = cardService.userInfoTrainingUpdate(userInfoTrainingList.get(i));
						} 
					}
				}
				
				//정보 수정 파트(update) (+) 버튼 기능
				for(i=0; i<userInfoTrainingList.size(); i++) {
					String ListTrainingCnt = cardService.userInfoTrainingList(userInfoTrainingList.get(i).getTrainingIdx());
					
					if(ListTrainingCnt == null) {
						cardService.userInfoTrainingInsert(userInfoTrainingList.get(i));
					}
					ListTrainingCnt = "1";
				}
				
				//정보 수정 파트(update) (-) 버튼 기능
				 for(i=0; i<deleteTrainingIdx.length; i++) { 
					 System.err.println("삭제 버튼 기능 활성화2-4 ");
					 cardService.userInfoTrainingDel(deleteTrainingIdx[i]);
				} 
			}
			
			/*
			 * for(UserInfoLicen userInfoLicenCnt : userInfoLicenList) {
			 * userInfoLicenUpdateCnt = cardService.userInfoLicenUpdate(userInfoLicenCnt); }
			 */
			
			//조회시 비어있으면 insert
			if(reUserInfoLicen.isEmpty()) {
				for(UserInfoLicen userInfoLicenCnt : userInfoLicenList) {
					userInfoLicenInsertCnt = cardService.userInfoLicenInsert(userInfoLicenCnt);
				}
			}
			
			//조회시 비어있지 않으면 update
			if(!reUserInfoLicen.isEmpty()) {
			
				//정보 수정 파트(update)
				for(i=0; i<userInfoLicenList.size(); i++) {
					for(j=0; j<reUserInfoLicen.size(); j++) {
						if(userInfoLicenList.get(i).getLicenIdx().equals(reUserInfoLicen.get(j).getLicenIdx())) {
							System.err.println("정보 수정 파트1-5");
							userInfoLicenUpdateCnt = cardService.userInfoLicenUpdate(userInfoLicenList.get(i));
						} 
					}
				}
				
				//정보 수정 파트(update) (+) 버튼 기능
				for(i=0; i<userInfoLicenList.size(); i++) {
					String ListLicenCnt = cardService.userInfoLicenList(userInfoLicenList.get(i).getLicenIdx());
					
					if(ListLicenCnt == null) {
						cardService.userInfoLicenInsert(userInfoLicenList.get(i));
					}
					ListLicenCnt = "1";
				}
				
				//정보 수정 파트(update) (-) 버튼 기능
				 for(i=0; i<deleteLicenIdx.length; i++) { 
					 System.err.println("삭제 버튼 기능 활성화2-5 ");
					 cardService.userInfoLicenDel(deleteLicenIdx[i]);
				} 
			}
			
			/*
			 * for(UserInfoProject userInfoProjectCnt : userInfoProjectList) {
			 * userInfoProjectUpdateCnt =
			 * cardService.userInfoProjectUpdate(userInfoProjectCnt); }
			 */
			
			//조회시 비어있으면 insert
			if(reUserInfoProject.isEmpty()) {
				for(UserInfoProject userInfoProjectCnt : userInfoProjectList) {
					userInfoProjectInsertCnt = cardService.userInfoProjectInsert(userInfoProjectCnt);
				}
			}
			
			//조회시 비어있지 않으면 update
			if(!reUserInfoProject.isEmpty()) {
			
				//정보 수정 파트(update)
				for(i=0; i<userInfoProjectList.size(); i++) {
					for(j=0; j<reUserInfoProject.size(); j++) {
						if(userInfoProjectList.get(i).getSkillIdx().equals(reUserInfoProject.get(j).getSkillIdx())) {
							System.err.println("정보 수정 파트1-6");
							userInfoProjectUpdateCnt = cardService.userInfoProjectUpdate(userInfoProjectList.get(i));
						} 
					}
				}
				
				//정보 수정 파트(update) (+) 버튼 기능
				for(i=0; i<userInfoProjectList.size(); i++) {
					String ListProjectCnt = cardService.userInfoProjectList(userInfoProjectList.get(i).getSkillIdx());
					
					if(ListProjectCnt == null) {
						cardService.userInfoProjectInsert(userInfoProjectList.get(i));
					}
					ListProjectCnt = "1";
				}
				
				//정보 수정 파트(update) (-) 버튼 기능
				 for(i=0; i<deleteProjectIdx.length; i++) { 
					 System.err.println("삭제 버튼 기능 활성화2-6 ");
					 cardService.userInfoProjectDel(deleteProjectIdx[i]);
				} 
			}
			
			if(userInfoUpdateCnt > 0) {
				System.out.println("성공22");
				System.out.println("유저 인덱스 : "+IntuserIdx);
				model.addAttribute("userIdx", userIdx);
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('정보 수정 실패!');");
				out.print("history.back();");
				out.print("</script>");
			}
		}
		return "redirect:card";
	}
	
	@RequestMapping(value = "/cardUserDelete", method = RequestMethod.POST)
	public String delete(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userListIdxArr[] = request.getParameterValues("UserCHK");
		
		if(userListIdxArr == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원이 선택되지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		int userIdx[] = new int [userListIdxArr.length];
		//체크된 회원만큼 삭제
		for(int i =0; i<userListIdxArr.length; i++) {
			userIdx[i] = Integer.parseInt(userListIdxArr[i]);
			
			int userDeleteCnt = cardService.userInfoDelete(userIdx[i]);
			
			if(userDeleteCnt == 0) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('삭제한 회원이 없습니다');");
				out.print("</script>");
			} else if (userDeleteCnt > 0) {
				
				List<UserInfoEdu> userEdu = cardService.userInfoEdu(userIdx[i]);
				List<UserInfoQualifi> userQualifi = cardService.userInfoQualifi(userIdx[i]);
				List<UserInfoCareer> userCareer = cardService.userInfoCareer(userIdx[i]);
				List<UserInfoTraining> userTraining = cardService.userInfoTraining(userIdx[i]);
				List<UserInfoLicen> userLicen = cardService.userInfoLicen(userIdx[i]);
				List<UserInfoProject> userProject = cardService.userInfoProject(userIdx[i]);
				
				//List가 비어있지않으면 회원 idx으로 삭제
				if(!userEdu.isEmpty()) {
					for(int j =0; j<userEdu.size(); j++) {
						cardService.userInfoEduDelete(userIdx[i]);
					}
				}
				
				if(!userQualifi.isEmpty()) {
					for(int j =0; j<userQualifi.size(); j++) {
						cardService.userInfoQualifiDelete(userIdx[i]);
					}
				}
				
				if(!userCareer.isEmpty()) {
					for(int j =0; j<userCareer.size(); j++) {
						cardService.userInfoCareerDelete(userIdx[i]);
					}
				}
				
				if(!userTraining.isEmpty()) {
					for(int j =0; j<userTraining.size(); j++) {
						cardService.userInfoTrainingDelete(userIdx[i]);
					}
				}
				
				if(!userCareer.isEmpty()) {
					for(int j =0; j<userCareer.size(); j++) {
						cardService.userInfoCareerDelete(userIdx[i]);
					}
				}
				
				if(!userLicen.isEmpty()) {
					for(int j =0; j<userLicen.size(); j++) {
						cardService.userInfoLicenDelete(userIdx[i]);
					}
				}
				
				if(!userProject.isEmpty()) {
					for(int j =0; j<userProject.size(); j++) {
						cardService.userInfoProjectDelete(userIdx[i]);
					}
				}
			}
		}
		
		
		//redirect 주소 변경
		return "redirect:card";
	}
	
	//만들지 아직 모름
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model) {
		logger.info("========== login 영역", locale);
		
		return "login";
	}
	
}
