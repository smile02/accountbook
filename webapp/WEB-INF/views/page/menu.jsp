<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">메인으로!</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/expand">지출목록</a></li>
      <li><a href="/income">수입목록</a></li>
      <li><a href="#">Page 3</a></li>
    </ul> 
    <div class="text-right">
    	<c:if test="${sessionScope.loginMember == null }">
    		<jsp:include page="./member.jsp"/>
    	</c:if>
    	<c:if test="${sessionScope.loginMember != null }">
    		<jsp:include page="./mypage.jsp"/>
    	</c:if>
    </div>
  </div>
</nav>

<!--스크립트 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>