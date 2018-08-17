<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>환영 환영~</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
	.error{
		color:red;
	}
</style>
</head>
<body>
	<jsp:include page="../page/menu.jsp" />
	<div class="container">	
		<div class="col-sm-4 col-sm-offset-4">
			<div id="login">
				<form:form action="/account/member/login" modelAttribute="member" class="form-horizontal" type="post">
					<h2 class="text-muted text-center">로 그 인</h2>
					<div class="form-group">
						<form:label path="nickname" class="control-label col-xs-4">닉네임 :</form:label>
						<div class="col-xs-8">
							<form:input path="nickname" type="text" class="form-control" />
						</div>
					</div>
					<form:errors path="nickname" class="error"/>
					<br />
					<div class="form-group">					
						<form:label path="password" class="control-label col-xs-4">비밀번호 :</form:label>
						<div class="col-xs-8">
							<form:input path="password" type="password" class="form-control"/>
						</div>
					</div>
					<form:errors path="password" class="error"/>
					<br />
					<div class="button-group text-right">
							<button class="btn btn-warning">로그인</button>
							<button type="button" class="btn btn-default" onclick="closeBtn();">취소</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>