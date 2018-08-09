<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span class="text-muted text-right">${sessionScope.loginMember.nickname}님 환영합니다.</span>
	<a class="btn btn-default">마이페이지</a>
	<a class="btn btn-danger" href="/member/logout">로그아웃</a>	
</body>
</html>