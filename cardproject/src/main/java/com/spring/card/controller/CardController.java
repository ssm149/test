package com.spring.card.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	@RequestMapping(value = "/card", method = RequestMethod.GET)
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
				int f_e = email.indexOf("@");
				
				String f_email = email.substring(0, f_e-1);
				String s_email = email.substring(f_e,email.length());
				
				userInfoDetail.setUserEmail(f_email);
				userInfoDetail.setUserDomain(s_email);
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
		UserInfoEdu userInfoEdu = new UserInfoEdu();
		UserInfoQualifi userInfoQualifi = new UserInfoQualifi();
		UserInfoCareer userInfoCareer = new UserInfoCareer();
		UserInfoTraining userInfoTraining = new UserInfoTraining(); 
		UserInfoLicen userInfoLicen = new UserInfoLicen();
		UserInfoProject userInfoProject = new UserInfoProject();
		
		int IntuserIdx = 0;
		if(userIdx != "") {
			IntuserIdx = Integer.parseInt(userIdx);
			userInfo.setUserIdx(IntuserIdx);
			userInfoQualifi.setUserIdx(IntuserIdx);
			userInfoEdu.setUserIdx(IntuserIdx);
			userInfoCareer.setUserIdx(IntuserIdx);
			userInfoTraining.setUserIdx(IntuserIdx);
			userInfoLicen.setUserIdx(IntuserIdx);
			userInfoProject.setUserIdx(IntuserIdx);
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
				System.out.println("유저 인덱스 : "+IntuserIdx);
				
				String userSocialSecunum = request.getParameter("userSocialSecunum");
				
				UserInfo userInfoOne = cardService.userInfoResult(userSocialSecunum);
				System.out.println("신규 가입된 유저 인덱스 "+userInfoOne.getUserIdx());
				
				userInfoQualifi.setUserIdx(userInfoOne.getUserIdx());
				userInfoEdu.setUserIdx(userInfoOne.getUserIdx());
				userInfoCareer.setUserIdx(userInfoOne.getUserIdx());
				userInfoTraining.setUserIdx(userInfoOne.getUserIdx());
				userInfoLicen.setUserIdx(userInfoOne.getUserIdx());
				userInfoProject.setUserIdx(userInfoOne.getUserIdx());
				
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
		/*자격증*/
		userInfoQualifi.setQualifiName(request.getParameter("qualifiName"));
		userInfoQualifi.setQualifiGetdate(request.getParameter("qualifiGetdate"));
		
		/*학교*/
		userInfoEdu.setEduSchoolName(request.getParameter("eduSchoolName"));
		userInfoEdu.setEduStatus(request.getParameter("eduStatus"));
		userInfoEdu.setEduDate(request.getParameter("eduDate"));
		
		/*회사*/
		userInfoCareer.setCareerCompName(request.getParameter("careerCompName"));
		userInfoCareer.setCareerEnterdate(request.getParameter("careerEnterdate"));
		userInfoCareer.setCareerLeavedate(request.getParameter("careerLeavedate"));
		userInfoCareer.setCareerSpot(request.getParameter("careerSpot"));
		userInfoCareer.setCareerResponsib(request.getParameter("careerResponsib"));
		
		/*교육*/
		userInfoTraining.setTrainingName(request.getParameter("trainingName"));
		userInfoTraining.setTrainingStartdate(request.getParameter("trainingStartdate"));
		userInfoTraining.setTrainingEnddate(request.getParameter("trainingEnddate"));
		userInfoTraining.setTrainingAgency(request.getParameter("trainingAgency"));
		
		/*보유 기술*/
		userInfoLicen.setLicenName(request.getParameter("licenName"));
		userInfoLicen.setLicenSkillLevel(request.getParameter("licenSkillLevel"));
		
		/*프로젝트*/
		userInfoProject.setSkillProjectName(request.getParameter("skillProjectName"));
		userInfoProject.setSkillStartdate(request.getParameter("skillStartdate"));
		userInfoProject.setSkillEnddate(request.getParameter("skillEnddate"));
		userInfoProject.setSkillCustomerComp(request.getParameter("skillCustomerComp"));
		userInfoProject.setSkillWorkComp(request.getParameter("skillWorkComp"));
		userInfoProject.setSkillApplied(request.getParameter("skillApplied"));
		userInfoProject.setSkillIndustry(request.getParameter("skillIndustry"));
		userInfoProject.setSkillRole(request.getParameter("skillRole"));
		userInfoProject.setSkillModel(request.getParameter("skillModel"));
		userInfoProject.setSkillOs(request.getParameter("skillOs"));
		userInfoProject.setSkillLang(request.getParameter("skillLang"));
		userInfoProject.setSkillDbms(request.getParameter("skillDbms"));
		userInfoProject.setSkillComm(request.getParameter("skillComm"));
		userInfoProject.setSkillTool(request.getParameter("skillTool"));
		userInfoProject.setSkillEtc(request.getParameter("skillEtc"));
		
		
		int userInfoUpdateCnt = 0;
		if(IntuserIdx != 0) {
			userInfoInsertCnt = cardService.userInfoQualifiInsert(userInfoQualifi);			
			userInfoInsertCnt = cardService.userInfoEduInsert(userInfoEdu);
			userInfoInsertCnt = cardService.userInfoCareerInsert(userInfoCareer);
			userInfoInsertCnt = cardService.userInfoTrainingInsert(userInfoTraining);
			userInfoInsertCnt = cardService.userInfoLicenInsert(userInfoLicen);
			userInfoInsertCnt = cardService.userInfoProjectInsert(userInfoProject);
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
			
		} else if (IntuserIdx > 0) {
			userInfoUpdateCnt = cardService.userInfoUpdate(userInfo);
			userInfoUpdateCnt = cardService.userInfoQualifiUpdate(userInfoQualifi);
			userInfoUpdateCnt = cardService.userInfoEduUpdate(userInfoEdu);
			userInfoUpdateCnt = cardService.userInfoCareerUpdate(userInfoCareer);
			userInfoUpdateCnt = cardService.userInfoTrainingUpdate(userInfoTraining);
			userInfoUpdateCnt = cardService.userInfoLicenUpdate(userInfoLicen);
			userInfoUpdateCnt = cardService.userInfoProjectUpdate(userInfoProject);
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
		
		return "cardwrite";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model) {
		logger.info("========== login 영역", locale);
		
		return "login";
	}
	
}
