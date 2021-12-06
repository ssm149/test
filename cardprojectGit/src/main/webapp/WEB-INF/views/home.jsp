<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<!-- home을 거쳐 바로 card 페이지로 이동 -->
<script type="text/javascript">
location = "card";
</script>
<!-- <a href="card">유저 리스트</a> -->
</body>
</html>
