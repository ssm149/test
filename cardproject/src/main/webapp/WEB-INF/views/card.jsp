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
</script>
<body>
	<section id="f_sec">
		
		<h2>회원 리스트</h2>
		
		<div class="aBox">
			<a href="login">login</a>
		</div>
		<form id="s" method="post">
			<input type="hidden" name="userIdx" id="userIdx">
		</form>
		<table border="1">
			<tr>
				<th>번호</th>
				<th width="100px;">작성 날짜</th>
				<th>이름</th>
				<th>성별</th>
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
						</tr>
					</c:forEach>
				</c:when>
				<c:when test="${userList == null }">
					<tr>
						<td>회원 정보가 없습니다.</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
		<button onclick="javascript:location='cardwrite'">이력서 작성하기</button>
	</section>
</body>
</html>