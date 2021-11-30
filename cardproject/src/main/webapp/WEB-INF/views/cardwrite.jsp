<%@page import="com.spring.card.vo.UserInfoEdu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 이력 작성</title>
<link rel="stylesheet" type="text/css" href="./resources/css/cardwrite.css">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function rewrite() {
	if(confirm("다시 쓰기겠습니까?")) {
		user.reset();
	} return;
}

function kakaopost() {
    new daum.Postcode({
        oncomplete: function(data) {
           document.querySelector("#userZipcode").value = data.zonecode;
           document.querySelector("#userAddress").value =  data.address
        }
    }).open();
}

window.addEventListener('DOMContentLoaded', function() {
	const userschoolTable = document.getElementById("userschool");
	const userschoolrows = userschoolTable.rows.length;
	var userschool;
	if(userschoolrows == 1) {
		userschool = userschoolTable.insertRow();
		
		var userschoolName = userschool.insertCell();
		userschoolName.innerHTML = "<input type=\"text\" name=\"eduSchoolName\">";
		
		var userschoolStatus = userschool.insertCell();
		userschoolStatus.innerHTML = "<select name=\"eduStatus\">"
								 + "<option value=\"\">선택없음</option>"
								 + "<option value=\"입학\">입학</option>"
								 + "<option value=\"재학\">재학</option>"
								 + "<option value=\"졸업\">졸업</option>"
								 + "<option value=\"졸업예정\">졸업예정</option>"
								 + "</select>";
		var userschoolDate = userschool.insertCell();
		userschoolDate.innerHTML = "<input type=\"date\" name=\"eduDate\">";
	}
	
	const userQualifiTable = document.getElementById("userqualifi");
	const userQualifirows = userQualifiTable.rows.length;
	
	var userQualifi;
	if(userQualifirows == 1) {
		userQualifi = userQualifiTable.insertRow();
		
		var userQualifiName = userQualifi.insertCell();
		userQualifiName.innerHTML = "<input type=\"text\" name=\"qualifiName\">";
		
		var userQualifiGetdate = userQualifi.insertCell();
		userQualifiGetdate.innerHTML = "<input type=\"date\" name=\"qualifiGetdate\">";
	}
	
	const userCareerTable = document.getElementById("userCareer");
	const userCareerrows = userCareerTable.rows.length;
	
	var userCareer;
	if(userCareerrows == 2) {
		userCareer = userCareerTable.insertRow();
		
		var userCareerCompName = userCareer.insertCell();
		userCareerCompName.innerHTML = "<input type=\"text\" name=\"careerCompName\">";
		
		var userCareerEnterdate = userCareer.insertCell();
		userCareerEnterdate.innerHTML = "<input type=\"date\" name=\"careerEnterdate\">";
		
		var userCareerLeavedate = userCareer.insertCell();
		userCareerLeavedate.innerHTML = "<input type=\"date\" name=\"careerLeavedate\">";
		
		var userCareerSpot = userCareer.insertCell();
		userCareerSpot.innerHTML = "<input type=\"text\" name=\"careerSpot\">";
		
		var userCareerResponsib = userCareer.insertCell();
		userCareerResponsib.innerHTML = "<input type=\"text\" name=\"careerResponsib\">";
	}
	
	const userTrainingTable = document.getElementById("usertraining");
	const userTrainingrows = userTrainingTable.rows.length;
	
	var userTraining;
	if(userTrainingrows == 1) {
		userTraining = userTrainingTable.insertRow();
		
		var userTrainingName = userTraining.insertCell();
		userTrainingName.innerHTML = "<input type=\"text\" name=\"trainingName\">";
		
		var userTrainingStartdate = userTraining.insertCell();
		userTrainingStartdate.innerHTML = "<input type=\"date\" name=\"trainingStartdate\">";
		
		var userTrainingEnddate = userTraining.insertCell();
		userTrainingEnddate.innerHTML = "<input type=\"date\" name=\"trainingEnddate\">";
		
		var userTrainingAgency = userTraining.insertCell();
		userTrainingAgency.innerHTML = "<input type=\"text\" name=\"trainingAgency\">";
	}
	
	const userLicenTable = document.getElementById("userlicen");
	const userLicenrows = userLicenTable.rows.length;
	
	var userLicen;
	if(userLicenrows == 1) {
		userLicen = userLicenTable.insertRow();
		
		var userLicenName = userLicen.insertCell();
		userLicenName.innerHTML = "<input type=\"text\" name=\"licenName\">";
		
		var userLicenSkillLevel = userLicen.insertCell();
		userLicenSkillLevel.innerHTML = "<input type=\"text\" name=\"licenSkillLevel\">";
	}
	
	const userProjectTable = document.getElementById("userproject");
	const userProjectrows = userProjectTable.rows.length;
	
	var userProject;
	if(userProjectrows == 2) {
		userProject = userProjectTable.insertRow();
		
		var userProjectName = userProject.insertCell();
		userProjectName.innerHTML = "<input type=\"text\" name=\"skillProjectName\">";
		
		var userProjectStartdate = userProject.insertCell();
		userProjectStartdate.innerHTML = "<input type=\"date\" name=\"skillStartdate\">";
		
		var userProjectEnddate = userProject.insertCell();
		userProjectEnddate.innerHTML = "<input type=\"date\" name=\"skillEnddate\">";
		
		var userProjectCustomerComp = userProject.insertCell();
		userProjectCustomerComp.innerHTML = "<input type=\"text\" name=\"skillCustomerComp\">";
		
		var userProjectWorkComp = userProject.insertCell();
		userProjectWorkComp.innerHTML = "<input type=\"text\" name=\"skillWorkComp\">";
		
		var userProjectApplied = userProject.insertCell();
		userProjectApplied.innerHTML = "<input type=\"text\" name=\"skillApplied\">";
		
		var userProjectIndustry = userProject.insertCell();
		userProjectIndustry.innerHTML = "<input type=\"text\" name=\"skillIndustry\">";
		
		var userProjectRole = userProject.insertCell();
		userProjectRole.innerHTML = "<input type=\"text\" name=\"skillRole\">";
		
		var userProjectModel = userProject.insertCell();
		userProjectModel.innerHTML = "<input type=\"text\" name=\"skillModel\">";
		
		var userProjectOs = userProject.insertCell();
		userProjectOs.innerHTML = "<input type=\"text\" name=\"skillOs\">";
		
		var userProjectLang = userProject.insertCell();
		userProjectLang.innerHTML = "<input type=\"text\" name=\"skillLang\">";
		
		var userProjectDbms = userProject.insertCell();
		userProjectDbms.innerHTML = "<input type=\"text\" name=\"skillDbms\">";
		
		var userProjectComm = userProject.insertCell();
		userProjectComm.innerHTML = "<input type=\"text\" name=\"skillComm\">";
		
		var userProjectTool = userProject.insertCell();
		userProjectTool.innerHTML = "<input type=\"text\" name=\"skillTool\">";
		
		var userProjectEtc = userProject.insertCell();
		userProjectEtc.innerHTML = "<input type=\"text\" name=\"skillEtc\">";
		
	}
});



//+ 버튼 클릭시 추가 이벤트
function addBtn(idx) {
	if(idx == 1) {
		const userschoolTable = document.getElementById("userschool");
			
		var userschool;
		userschool = userschoolTable.insertRow();
			
		var userschoolName = userschool.insertCell();
		userschoolName.innerHTML = "<input type=\"text\" name=\"eduSchoolName\">";
			
		var userschoolStatus = userschool.insertCell();
		userschoolStatus.innerHTML = "<select name=\"eduStatus\">"
								 + "<option value=\"\">선택없음</option>"
								 + "<option value=\"입학\">입학</option>"
								 + "<option value=\"재학\">재학</option>"
								 + "<option value=\"졸업\">졸업</option>"
								 + "<option value=\"졸업예정\">졸업예정</option>"
								 + "</select>";
		var userschoolDate = userschool.insertCell();
		userschoolDate.innerHTML = "<input type=\"date\" name=\"eduDate\">";
	}
	
	if(idx == 2) {
		const userQualifiTable = document.getElementById("userqualifi");
		
		var userQualifi;
		userQualifi = userQualifiTable.insertRow();
			
		var userQualifiName = userQualifi.insertCell();
		userQualifiName.innerHTML = "<input type=\"text\" name=\"qualifiName\">";
			
		var userQualifiGetdate = userQualifi.insertCell();
		userQualifiGetdate.innerHTML = "<input type=\"date\" name=\"qualifiGetdate\">";
	}
	
	if(idx == 3) {
		const userCareerTable = document.getElementById("userCareer");
		
		var userCareer;
		userCareer = userCareerTable.insertRow();
			
		var userCareerCompName = userCareer.insertCell();
		userCareerCompName.innerHTML = "<input type=\"text\" name=\"careerCompName\">";
		
		var userCareerEnterdate = userCareer.insertCell();
		userCareerEnterdate.innerHTML = "<input type=\"date\" name=\"careerEnterdate\">";
			
		var userCareerLeavedate = userCareer.insertCell();
		userCareerLeavedate.innerHTML = "<input type=\"date\" name=\"careerLeavedate\">";
			
		var userCareerSpot = userCareer.insertCell();
		userCareerSpot.innerHTML = "<input type=\"text\" name=\"careerSpot\">";
			
		var userCareerResponsib = userCareer.insertCell();
		userCareerResponsib.innerHTML = "<input type=\"text\" name=\"careerResponsib\">";
		
	}
	
	if(idx == 4) {
		const userTrainingTable = document.getElementById("usertraining");
		
		var userTraining;
		userTraining = userTrainingTable.insertRow();
			
		var userTrainingName = userTraining.insertCell();
		userTrainingName.innerHTML = "<input type=\"text\" name=\"trainingName\">";
			
		var userTrainingStartdate = userTraining.insertCell();
		userTrainingStartdate.innerHTML = "<input type=\"date\" name=\"trainingStartdate\">";
			
		var userTrainingEnddate = userTraining.insertCell();
		userTrainingEnddate.innerHTML = "<input type=\"date\" name=\"trainingEnddate\">";
			
		var userTrainingAgency = userTraining.insertCell();
		userTrainingAgency.innerHTML = "<input type=\"text\" name=\"trainingAgency\">";		
	}
	
	if(idx == 5) {
		const userLicenTable = document.getElementById("userlicen");
		
		var userLicen;
		userLicen = userLicenTable.insertRow();
			
		var userLicenName = userLicen.insertCell();
		userLicenName.innerHTML = "<input type=\"text\" name=\"licenName\">";
			
		var userLicenSkillLevel = userLicen.insertCell();
		userLicenSkillLevel.innerHTML = "<input type=\"text\" name=\"licenSkillLevel\">";		
	}
	
	if(idx == 6) {
		const userProjectTable = document.getElementById("userproject");
		
		var userProject;
		userProject = userProjectTable.insertRow();
			
		var userProjectName = userProject.insertCell();
		userProjectName.innerHTML = "<input type=\"text\" name=\"skillProjectName\">";
			
		var userProjectStartdate = userProject.insertCell();
		userProjectStartdate.innerHTML = "<input type=\"date\" name=\"skillStartdate\">";
			
		var userProjectEnddate = userProject.insertCell();
		userProjectEnddate.innerHTML = "<input type=\"date\" name=\"skillEnddate\">";
			
		var userProjectCustomerComp = userProject.insertCell();
		userProjectCustomerComp.innerHTML = "<input type=\"text\" name=\"skillCustomerComp\">";
			
		var userProjectWorkComp = userProject.insertCell();
		userProjectWorkComp.innerHTML = "<input type=\"text\" name=\"skillWorkComp\">";
			
		var userProjectApplied = userProject.insertCell();
		userProjectApplied.innerHTML = "<input type=\"text\" name=\"skillApplied\">";
			
		var userProjectIndustry = userProject.insertCell();
		userProjectIndustry.innerHTML = "<input type=\"text\" name=\"skillIndustry\">";
			
		var userProjectRole = userProject.insertCell();
		userProjectRole.innerHTML = "<input type=\"text\" name=\"skillRole\">";
			
		var userProjectModel = userProject.insertCell();
		userProjectModel.innerHTML = "<input type=\"text\" name=\"skillModel\">";
			
		var userProjectOs = userProject.insertCell();
		userProjectOs.innerHTML = "<input type=\"text\" name=\"skillOs\">";
			
		var userProjectLang = userProject.insertCell();
		userProjectLang.innerHTML = "<input type=\"text\" name=\"skillLang\">";
			
		var userProjectDbms = userProject.insertCell();
		userProjectDbms.innerHTML = "<input type=\"text\" name=\"skillDbms\">";
			
		var userProjectComm = userProject.insertCell();
		userProjectComm.innerHTML = "<input type=\"text\" name=\"skillComm\">";
			
		var userProjectTool = userProject.insertCell();
		userProjectTool.innerHTML = "<input type=\"text\" name=\"skillTool\">";
			
		var userProjectEtc = userProject.insertCell();
		userProjectEtc.innerHTML = "<input type=\"text\" name=\"skillEtc\">";
	}
}
function minBtn(idx) {
	if(idx == 1) {
		const userschoolTable = document.getElementById("userschool");
		const userschoolrows = userschoolTable.rows.length;
		
		if(userschoolrows > 2){
			userschoolTable.deleteRow(-1)
		};
	}
	
	if(idx == 2) {
		const userQualifiTable = document.getElementById("userqualifi");
		const userQualifirows = userQualifiTable.rows.length;
		
		if(userQualifirows > 2){
			userQualifiTable.deleteRow(-1);
		}
	}
	
	if(idx == 3) {
		const userCareerTable = document.getElementById("userCareer");
		const userCareerrows = userCareerTable.rows.length;
		
		if(userCareerrows > 3) {
			userCareerTable.deleteRow(-1);
		}
	}
	
	if(idx == 4) {
		const userTrainingTable = document.getElementById("usertraining");
		const userTrainingrows = userTrainingTable.rows.length;
		
		if(userTrainingrows > 2) {
			userTrainingTable.deleteRow(-1);
		}
	}
	
	if(idx == 5) {
		const userLicenTable = document.getElementById("userlicen");
		const userLicenrows = userLicenTable.rows.length;
		
		if(userLicenrows > 2) {
			userLicenTable.deleteRow(-1);
		}
	}
	
	if(idx == 6) {
		const userProjectTable = document.getElementById("userproject");
		const userProjectrows = userProjectTable.rows.length;
		
		if(userProjectrows > 3) {
			userProjectTable.deleteRow(-1);
		}
	}
	
}
</script>
<body>
	<section id="cardwirte">
	<h2>개 인 이 력 카 드</h2>
	<form action="/cardInsert" method="post">
	<div class="inputBox">
		<div class="leftbox">
			<p>
			<c:choose>
				<c:when test="${userIdx == 0 }">새로운 회원</c:when>
				<c:when test="${userIdx != null }">회원 번호 : ${userIdx }</c:when>
			</c:choose>
			※ 새 이력 작성</p>
		</div>
		<div class="rightbox">
			<input type="button" value="뒤로가기" onclick="location='card'">
			<input type="button" value="다시쓰기" onclick="rewrite(); false;">
			<input type="submit" value="저장">
			<input type="hidden" name="userIdx" value="${userIdx }">
		</div>
	</div>
		<table class="userinfo" border="1">
			<c:choose>
				<c:when test="${userInfo == null }">
				<tr>
					<th>*성명</th>
					<td>
						<input type="text" name="userName"> 
					</td>
					<th>*주민등록번호</th>
					<td colspan="3">
						<input type="text" name="userSocialSecunum" maxlength="13" placeholder='"-" 제외한 숫자만 입력'> 
					</td>
					<th>성별</th>
					<td>
						<select name="userGender">
							<option value="">선택</option>
							<option value="남자">남자</option>
							<option value="여자">여자</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>소속회사</th>
					<td colspan="5">
						<input type="text" name="userComp"> 
					</td>
					<th>입사일</th>
					<td>
						<input type="date" name="userCompEnterdate">
					</td>
				</tr>
				<tr>
					<th>부서</th>
					<td><input type="text" name="userDept"></td>
					<th>직위</th>
					<td><input type="text" name="userSpot"></td>
					<th width="100px">병역</th>
					<td><input type="text" name="userArmyServ"></td>
					<th>결혼</th>
					<td>
						<select name="userMaritalStatus">
							<option value="">선택</option>
							<option value="기혼">기혼</option>
							<option value="미혼">미혼</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<p>병역</p>
						<p>입대일 ~ 제대일</p>
					</th>
					<td colspan="2">
						<input type="date" name="userArmyServEnter" >
					</td>
					<th>~</th>
					<td colspan="2">
						<input type="date" name="userArmyServLeave">
					</td>
					<th>역종/병과</th>
					<td>
						<input type="text" name="userArmyServPeriod">
					</td>
				</tr>
				
				</c:when>
				<c:when test="${userInfo != null }">
				<tr>
					<th>*성명</th>
					<td>
						<input type="text" name="userName" value="${userInfo.userName }"> 
					</td>
					<th>*주민등록번호</th>
					<td colspan="3">
						<input type="text" name="userSocialSecunum" maxlength="13" value="${userInfo.userSocialSecunum }" placeholder='"-" 제외한 숫자만 입력'> 
					</td>
					<th>성별</th>
					<td>
						<select name="userGender">
							<option value="">선택</option>
							<option <c:if test="${userInfo.userGender eq '남성'}">selected</c:if> value="남성">남성</option>
							<option <c:if test="${userInfo.userGender eq '여성'}">selected</c:if> value="여성">여성</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>소속회사</th>
					<td colspan="5">
						<input type="text" name="userComp" value="${userInfo.userComp }"> 
					</td>
					<th>입사일</th>
					<td>
						<input type="date" name="userCompEnterdate" value="${userInfo.userCompEnterdate }">
					</td>
				</tr>
				<tr>
					<th>부서</th>
					<td><input type="text" name="userDept" value="${userInfo.userDept }"></td>
					<th>직위</th>
					<td><input type="text" name="userSpot" value="${userInfo.userSpot }"></td>
					<th width="100px">병역</th>
					<td><input type="text" name="userArmyServ" value="${userInfo.userArmyServ }"></td>
					<th>결혼</th>
					<td>
						<select name="userMaritalStatus">
							<option  value="">선택</option>
							<option <c:if test="${userInfo.userMaritalStatus eq '기혼'}">selected</c:if> value="기혼">기혼</option>
							<option <c:if test="${userInfo.userMaritalStatus eq '미혼'}">selected</c:if> value="미혼">미혼</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<p>병역</p>
						<p>입대일 ~ 제대일</p>
					</th>
					<td colspan="2">
						<input type="date" name="userArmyServEnter" value="${userInfo.userArmyServEnter }" >
					</td>
					<th>~</th>
					<td colspan="2">
						<input type="date" name="userArmyServLeave" value="${userInfo.userArmyServLeave }">
					</td>
					<th>역종/병과</th>
					<td>
						<input type="text" name="userArmyServPeriod" value="${userInfo.userArmyServPeriod }">
					</td>
				</tr>
				
				</c:when>
			</c:choose>
		</table>
		
		<table class="userinfo2" border="1">
			<c:choose>
				<c:when test="${userInfo == null }">
					<tr>
						<th>전화</th>
						<td colspan="2">
							<input type="text" placeholder='휴대전화 "-" 포함' name="userTelnumWired">
						</td>
						<td>
							<input type="text" placeholder='유선 "-" 포함' name="userTelnumWireless">
						</td>
					</tr>
					<tr>
						<th>E-Mail</th>
						<td colspan="2">
							<input type="text" name="userEmail">
						</td>
						<td>
							<select name="userDomain">
								<option value="">선택해주세요</option>
								<option value="@naver.com">@naver.com</option>
								<option value="@google.com">@google.com</option>
								<option value="@daum.net">@daum.net</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<input type="text" placeholder="우편번호" id="userZipcode" name="userZipcode">
						</td>
						<td>
							<input type="button" value="우편번호 찾기" onclick="kakaopost()">
						</td>
						<td>
							<input type="text" id="userAddress" name="userAddress" placeholder="주소">
						</td>
					</tr>
				</c:when>
				<c:when test="${userInfo != null }">
					<tr>
						<th>전화</th>
						<td colspan="2">
							<input type="text" placeholder='휴대전화 "-" 포함' value="${userInfo.userTelnumWired }" name="userTelnumWired">
						</td>
						<td>
							<input type="text" placeholder='유선 "-" 포함' value="${userInfo.userTelnumWireless }" name="userTelnumWireless">
						</td>
					</tr>
					<tr>
						<th>E-Mail</th>
						<td colspan="2">
							<input type="text" name="userEmail" value="${userInfo.userEmail }">
						</td>
						<td>
							<select name="userDomain">
								<option value="">선택해주세요</option>
								<option <c:if test="${userInfo.userDomain eq '@naver.com'}">selected</c:if> value="@naver.com">@naver.com</option>
								<option <c:if test="${userInfo.userDomain eq '@google.com'}">selected</c:if> value="@google.com">@google.com</option>
								<option <c:if test="${userInfo.userDomain eq '@daum.net'}">selected</c:if> value="@daum.net">@daum.net</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<input type="text" placeholder="우편번호" id="userZipcode" value="${userInfo.userZipcode }" name="userZipcode">
						</td>
						<td>
							<input type="button" value="우편번호 찾기" onclick="kakaopost()">
						</td>
						<td>
							<input type="text" id="userAddress" name="userAddress" value="${userInfo.userAddress }" placeholder="주소">
						</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
		
		<div class="table3">
		
			<table class="userschool" id="userschool" border="1">
			<c:choose>
				<c:when test="${userInfoEdu == null }">
					<tr>
						<th>학교명</th>
						<th>상태</th>
						<th>날짜</th>
					</tr>
					<tr>
						<td>
							<input type="text" name="eduSchoolName">
						</td>
						<td>
							<select name="eduStatus">
								<option value="">선택없음</option>
								<option value="입학">입학</option>
								<option value="재학">재학</option>
								<option value="졸업">졸업</option>
								<option value="졸업예정">졸업예정</option>
							</select>
						</td>
						<td>
							<input type="date" name="eduDate">
						</td>
					</tr>
				</c:when>
				<c:when test="${userInfoEdu != null }">
					<tr>
						<th>학교명</th>
						<th>상태</th>
						<th>날짜</th>
					</tr>
					<c:forEach var="userInfoEdu" items="${userInfoEdu }">
						<tr>
							<td>
								<input type="text" name="eduSchoolName" value="${userInfoEdu.eduSchoolName }">
							</td>
							<td>
								<select name="eduStatus">
									<option value="">선택없음</option>
									<option <c:if test="${userInfoEdu.eduStatus eq '입학'}">selected</c:if> value="입학">입학</option>
									<option <c:if test="${userInfoEdu.eduStatus eq '재학'}">selected</c:if> value="재학">재학</option>
									<option <c:if test="${userInfoEdu.eduStatus eq '졸업'}">selected</c:if> value="졸업">졸업</option>
									<option <c:if test="${userInfoEdu.eduStatus eq '졸업예정'}">selected</c:if> value="졸업예정">졸업예정</option>
								</select>
							</td>
							<td>
								<input type="date" name="eduDate" value=${userInfoEdu.eduDate }>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
				
			</table>
			<div class="btnBox">
				<div class="addBox">
					<input type="button" value="+" class="add" onclick="addBtn(1)">
				</div>
				<div class="minBox">
					<input type="button" value="-" class="min" onclick="minBtn(1)">
				</div>
			</div>
		</div>
		
		<div class="table4">
			<table class="userqualifi" id="userqualifi" border="1">
			<c:choose>
				<c:when test="${userInfoQualifi == null }">
					<tr>
						<th>자격증명</th>
						<th>취득일</th>
					</tr>
					<tr>
						<td>
							<input type="text" name="qualifiName">
						</td>
						<td>
							<input type="date" name="qualifiGetdate">
						</td>
					</tr>
				</c:when>
				<c:when test="${userInfoQualifi != null }">
					<tr>
						<th>자격증명</th>
						<th>취득일</th>
					</tr>
					<c:forEach var="userInfoQualifi" items="${userInfoQualifi }">
						<tr>
							<td>
								<input type="text" name="qualifiName" value="${userInfoQualifi.qualifiName }">
							</td>
							<td>
								<input type="date" name="qualifiGetdate" value="${userInfoQualifi.qualifiGetdate }">
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
				
			</table>
			<div class="btnBox">
				<div class="addBox">
					<input type="button" value="+" class="add" onclick="addBtn(2)">
				</div>
				<div class="minBox">
					<input type="button" value="-" class="min" onclick="minBtn(2)">
				</div>
			</div>
		</div>
		
		<table id="userCareer" border="1">
			<tr>
				<th rowspan="2">회사명</th>
				<th colspan="2">재직 기간</th>
				<th rowspan="2">직위</th>
				<th rowspan="2">담당업무</th>
			</tr>
			<tr>
				<th>시작일</th>
				<th>종료일</th>
			</tr>
			<c:choose>
				<c:when test="${userInfoCareer == null }">
					<tr>
						<td>
							<input type="text" name="careerCompName">
						</td>
						<td>
							<input type="date" name="careerEnterdate">
						</td>
						<td>
							<input type="date" name="careerLeavedate">
						</td>
						<td>
							<input type="text" name="careerSpot">
						</td>
						<td>
							<input type="text" name="careerResponsib">
						</td>
					</tr>
				</c:when>
				<c:when test="${userInfoCareer != null }">
					<c:forEach var="userCareer" items="${userInfoCareer}">
						<tr>
							<td>
								<input type="text" name="careerCompName" value="${userCareer.careerCompName }">
							</td>
							<td>
								<input type="date" name="careerEnterdate" value="${userCareer.careerEnterdate }">
							</td>
							<td>
								<input type="date" name="careerLeavedate" value="${userCareer.careerLeavedate }">
							</td>
							<td>
								<input type="text" name="careerSpot" value="${userCareer.careerSpot }">
							</td>
							<td>
								<input type="text" name="careerResponsib" value="${userCareer.careerResponsib }">
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
			
		</table>
		<div class="btnBox">
			<div class="addBox">
				<input type="button" value="+" class="add" onclick="addBtn(3)">
			</div>
			<div class="minBox">
				<input type="button" value="-" class="min" onclick="minBtn(3)">
			</div>
		</div>
		
		<div class="table5">
			<table class="usertraining" id="usertraining" border="1">
			<c:choose>
				<c:when test="${userInfoTraining == null}">
					<tr>
						<th>교육명</th>
						<th>시작일</th>
						<th>종료일</th>
						<th>기관</th>
					</tr>
					<tr>
						<td>
							<input type="text" name="trainingName">
						</td>
						<td>
							<input type="date" name="trainingStartdate">
						</td>
						<td>
							<input type="date" name="trainingEnddate">
						</td>
						<td>
							<input type="text" name="trainingAgency">
						</td>
					</tr>
				</c:when>
				<c:when test="${userInfoTraining != null}">
					<tr>
						<th>교육명</th>
						<th>시작일</th>
						<th>종료일</th>
						<th>기관</th>
					</tr>
					<c:forEach var="userInfoTraining" items="${userInfoTraining }">
						<tr>
							<td>
								<input type="text" name="trainingName" value="${userInfoTraining.trainingName }">
							</td>
							<td>
								<input type="date" name="trainingStartdate" value="${userInfoTraining.trainingStartdate }">
							</td>
							<td>
								<input type="date" name="trainingEnddate" value="${userInfoTraining.trainingEnddate }">
							</td>
							<td>
								<input type="text" name="trainingAgency" value="${userInfoTraining.trainingAgency }">
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
				
			</table>
			<div class="btnBox">
				<div class="addBox">
					<input type="button" value="+" class="add"  onclick="addBtn(4)">
				</div>
				<div class="minBox">
					<input type="button" value="-" class="min" onclick="minBtn(4)">
				</div>
			</div>
		</div>
		
		<div class="table6">
			<table class="userlicen" id="userlicen" border="1">
			<c:choose>
				<c:when test="${userInfoLicen == null }">
					<tr>
						<th>보유기술 및 외국어능력</th>
						<th>숙련도<br/>(A,B,C)</th>
					</tr>
					<tr>
						<td>
							<input type="text" name="licenName">
						</td>
						<td>
							<input type="text" name="licenSkillLevel">
						</td>
					</tr>
				</c:when>
				<c:when test="${userInfoLicen != null }">
					<tr>
						<th>보유기술 및 외국어능력</th>
						<th>숙련도<br/>(A,B,C)</th>
					</tr>
					<c:forEach var="userInfoLicen" items="${userInfoLicen }">
						<tr>
							<td>
								<input type="text" name="licenName" value="${userInfoLicen.licenName }">
							</td>
							<td>
								<input type="text" name="licenSkillLevel" value="${userInfoLicen.licenSkillLevel }">
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
				
			</table>
			<div class="btnBox">
				<div class="addBox">
					<input type="button" value="+" class="add"  onclick="addBtn(5)">
				</div>
				<div class="minBox">
					<input type="button" value="-" class="min" onclick="minBtn(5)">
				</div>
			</div>
		</div>
		
		<table class="userproject" id="userproject" border="1">
		<c:choose>
			<c:when test="${userInfoProject == null }">
				<tr>
					<th rowspan="2">프로젝트명<br/>업무명</th>
					<th colspan="2">참여기간</th>
					<th rowspan="2">고객사</th>
					<th rowspan="2">근무회사</th>
					<th colspan="2">개발분야</th>
					<th rowspan="2">역할</th>
					<th colspan="7">개발환경</th>
				</tr>
				<tr>
					<th>시작일</th>
					<th>종료일</th>
					<th>산업</th>
					<th>응용</th>
					<th>기종</th>
					<th>O.S</th>
					<th>언어</th>
					<th>DBMS</th>
					<th>TOOL</th>
					<th>통신</th>
					<th>기타</th>
				</tr>
				<tr>
					<td><input type="text" name="skillProjectName"></td>
					<td><input type="date" name="skillStartdate"></td>
					<td><input type="date" name="skillEnddate"></td>
					<td><input type="text" name="skillCustomerComp"></td>
					<td><input type="text" name="skillWorkComp"></td>
					<td><input type="text" name="skillApplied"></td>
					<td><input type="text" name="skillIndustry"></td>
					<td><input type="text" name="skillRole"></td>
					<td><input type="text" name="skillModel"></td>
					<td><input type="text" name="skillOs"></td>
					<td><input type="text" name="skillLang"></td>
					<td><input type="text" name="skillDbms"></td>
					<td><input type="text" name="skillComm"></td>
					<td><input type="text" name="skillTool"></td>
					<td><input type="text" name="skillEtc"></td>
				</tr>
			</c:when>
			<c:when test="${userInfoProject != null }">
				<tr>
					<th rowspan="2">프로젝트명<br/>업무명</th>
					<th colspan="2">참여기간</th>
					<th rowspan="2">고객사</th>
					<th rowspan="2">근무회사</th>
					<th colspan="2">개발분야</th>
					<th rowspan="2">역할</th>
					<th colspan="7">개발환경</th>
				</tr>
				<tr>
					<th>시작일</th>
					<th>종료일</th>
					<th>산업</th>
					<th>응용</th>
					<th>기종</th>
					<th>O.S</th>
					<th>언어</th>
					<th>DBMS</th>
					<th>TOOL</th>
					<th>통신</th>
					<th>기타</th>
				</tr>
				<c:forEach var="userInfoProject" items="${userInfoProject }">
					<tr>
						<td><input type="text" name="skillProjectName" value="${userInfoProject.skillProjectName }"></td>
						<td><input type="date" name="skillStartdate" value="${userInfoProject.skillStartdate }"></td>
						<td><input type="date" name="skillEnddate" value="${userInfoProject.skillEnddate }"></td>
						<td><input type="text" name="skillCustomerComp" value="${userInfoProject.skillCustomerComp }"></td>
						<td><input type="text" name="skillWorkComp" value="${userInfoProject.skillWorkComp }"></td>
						<td><input type="text" name="skillApplied" value="${userInfoProject.skillApplied }"></td>
						<td><input type="text" name="skillIndustry" value="${userInfoProject.skillIndustry }"></td>
						<td><input type="text" name="skillRole" value="${userInfoProject.skillRole }"></td>
						<td><input type="text" name="skillModel" value="${userInfoProject.skillModel }"></td>
						<td><input type="text" name="skillOs" value="${userInfoProject.skillOs }"></td>
						<td><input type="text" name="skillLang" value="${userInfoProject.skillLang }"></td>
						<td><input type="text" name="skillDbms" value="${userInfoProject.skillDbms }"></td>
						<td><input type="text" name="skillComm" value="${userInfoProject.skillComm }"></td>
						<td><input type="text" name="skillTool" value="${userInfoProject.skillTool }"></td>
						<td><input type="text" name="skillEtc" value="${userInfoProject.skillEtc }"></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
			
		</table>
		<div class="btnBox">
			<div class="addBox">
				<input type="button" value="+" class="add"  onclick="addBtn(6)">
			</div>
			<div class="minBox">
				<input type="button" value="-" class="min" onclick="minBtn(6)">
			</div>
		</div>
	</form>
	</section>
</body>
</html>