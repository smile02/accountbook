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
	<h1 class="text-muted text-center" style="color:black;">적 금 관 리</h1>
	<div class="row">
		<div class="col-xs-4">
			<h2 class="text-muted text-center">적금 등록</h2>
		</div>
		<div class="col-xs-4">
			<h2 class="text-muted text-center">적금 목록</h2>
		</div>
		<div class="col-xs-4">
			<h2 class="text-muted text-center">납입 목록</h2>
		</div>
	</div>
		<div class="row">
			<div class="col-xs-4">
				<form action="/saving/add" class="form-horizontal" method="post">
					<div class="form-group">
						<label for="regbank" class="control-label col-xs-3">가입은행</label>
						<div class="col-xs-6">
							<input id="regbank" type="text" name="regbank" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="regname" class="control-label col-xs-3">적금이름</label>
						<div class="col-xs-6">
							<input id="regname" type="text" name="regname" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="startreg" class="control-label col-xs-3">가입날짜</label>
						<div class="col-xs-6">
							<input id="startreg" type="date" name="startreg" class="form-control"/>
						</div>
					</div>
					<div class="form-group">						
						<label for="endreg" class="control-label col-xs-3">만기날짜</label>
						<div class="col-xs-6">
							<input id="endreg" type="date" name="endreg" class="form-control"/>
						</div>
					</div>					
					<div class="button-group text-center">
						<button class="btn btn-success">등록</button>
						<button type="reset" class="btn btn-warning">취소</button>
					</div>				
				</form>
				
			</div>
			<div class="col-xs-4">
				<div id="savingbox" class="panel panel-default"
		 	style="height:500px; padding-left:20px; overflow:auto;">
		 			<c:forEach var="saving" items="${savingList }">
		 				${saving.regname }
		 				${saving.regbank }
		 				${saving.nickname }
		 			</c:forEach>
		 		</div>
				</div>
			<div class="col-xs-4">
				<div id="listbox" class="panel panel-default"
		 	style="height:500px; padding-left:20px; overflow:auto;"></div>
			</div>
		</div>
	</div>
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>