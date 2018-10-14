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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<nav class="navbar navbar-default">
    <div class="navbar-header">    
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/">우리둘이~!!</a>
    </div>        
    
    
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">      
        <li id="lDrop" class="dropdown" id="collapseOne">
        <a id="aDrop" class="dropdown-toggle" data-toggle="dropdown" href="#" onclick="dropClick();"
        aria-controls="collapseOne">가계부 등록<span class="caret"></span></a>
          <ul  class="dropdown-menu" role="menu">
            <li role="presentation"><a role="menuitem" href="/expand/add">지출등록</a></li>
            <li role="presentation"><a role="menuitem" href="/income/add">수입등록</a></li>
          </ul>
         </li>
        <li><a href="/expand">지출목록</a></li>
        <li><a href="/income">수입목록</a></li>
        <li><a href="/saving">적금/대출 관리</a></li>
      </ul>      
      <ul class="nav navbar-nav navbar-right">
   		  <c:if test="${sessionScope.loginMember == null }">
   		  		<li><a href="/member/memberfind"><span class="glyphicon glyphicon-list-alt"></span>계정정보 찾기</a></li>
	    		<li><a href="/member/login"><span class="glyphicon glyphicon-log-in"></span>로그인</a></li>
  				<li><a href="/member/signup"><span class="glyphicon glyphicon-user"></span>회원가입</a> </li>
    		</c:if>
    		<c:if test="${sessionScope.loginMember != null }">
	    		<li><span class="text-muted text-right">${sessionScope.loginMember.nickname}님 환영합니다.</span><li>
				<li><a href="/member/info"><span class="glyphicon glyphicon-info-sign"></span>마이페이지</a></li>
				<li><a href="/member/logout"><span class="glyphicon glyphicon-log-out"></span>로그아웃</a></li>
    		</c:if>
      	</ul>
    </div>
</nav>
  </div>
  

<!--스크립트 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
var check = false;
	function dropClick(){
		if(!check){
			$("#lDrop").addClass("open");
			$("#aDrop").attr("aria-expanded","true");
			check = true;
		}else{
			$("#lDrop").removeClass("open");
			$("#aDrop").removeAttr("aria-expanded");
			check = false;
		}
		
		
	}
</script>
</body>
</html>