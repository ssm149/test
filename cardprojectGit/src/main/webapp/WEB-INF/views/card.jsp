<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 리스트</title>
<link rel="stylesheet" type="text/css" href="./resources/css/card.css">
</head>
<script type="text/javascript">

function link(idx) {
	
	document.getElementById("userIdx").value = idx;
	
	alert(document.getElementById("userIdx").value);
	
	document.getElementById("s").action="/cardwrite";
	document.getElementById("s").submit();
}

function userDelete() {/* 
	var userArrList = document.getElementsByName("UserCHK");
	var userChk = "";
	for(var i =0; i<userArrList.length; i++) {
		if(userArrList[i].checked == true) {
			-alert(userArrList[i].value);-
			userChk[i] = userArrList[i].value;
		}
		alert(userChk[i].value);
	}
	if(userChk == null || userChk == "") {
		return;
	} */
	if(confirm("회원을 삭제하시겠습니까?")){
		d.action="/cardUserDelete";
		d.submit();
	} else return false;
}

function memberCheckAll() {
	var userArrList = document.getElementsByName("UserCHK");
	for(var i =0; i<userArrList.length; i++) {
		userArrList[i].checked = true;
	}
}

function unMemberCheckAll() {
	var userArrList = document.getElementsByName("UserCHK");
	for(var i =0; i<userArrList.length; i++) {
		userArrList[i].checked = false;
	}
}

</script>
<body>
	<section id="f_sec">
		
		<h2>회원 리스트</h2>
		
		<form id="s" method="post">
			<input type="hidden" name="userIdx" id="userIdx">
		</form>
		<form name="d" method="post">
		<table border="1">
			<tr>
				<th>번호</th>
				<th width="100px;">작성 날짜</th>
				<th>이름</th>
				<th>성별</th>
				<th>선택</th>
			</tr>
			<c:choose>
				<c:when test="${userList != null }">
					<c:forEach var="UserInfo" items="${userList }" varStatus="status">
						<tr>
							<td>${UserInfo.userIdx }</td>
							<td>${UserInfo.userRegisterDate }</td>
							<%-- "cardwrite?userIdx=${UserInfo.userIdx }" --%>
							<td><a href="#" onclick="link(${UserInfo.userIdx })">${UserInfo.userName }</a></td>
							<td>${UserInfo.userGender }</td>
							<td align="center">
								<input type="checkbox" name="UserCHK" value="${UserInfo.userIdx }">
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:when test="${userList == null }">
					<tr>
						<td colspan="5">회원 정보가 없습니다.</td>
					</tr>	
				</c:when>
			</c:choose>
		</table>
		<div class="inputBox">
			<input type="submit" value="삭제" onclick="userDelete(); return false;">
			<input type="button" value="전체 선택" onclick="memberCheckAll();">
			<input type="button" value="전체 선택 헤재" onclick="unMemberCheckAll();">
		</div>
		</form>
		<div class="butBox">
			<button onclick="javascript:location='cardwrite'">이력서 작성하기</button>
		</div>
	</section>
</body>
</html>