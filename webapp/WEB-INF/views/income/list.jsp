<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>환영 환영~</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="../page/menu.jsp"/>
	<div class="container">
		<h2 class="text-center text-muted">수입 목록</h2>
		<br/>
		<c:forEach var="income" items="${incomeList }">
			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-heading">수입내역 : ${income.comments }</div>
					<div class="panel-body">수입한 사람 : ${income.nickname }</div>
					<div class="panel-body">수입방법 : ${income.ways}</div>					
					<div class="panel-body">금액 : ${income.price }</div>
					<div class="panel-body">메모 : ${income.memo }</div>
					<div class="panel-footer">수입일 : ${income.regdate } <br />
						<button type="button" class="btn btn-secandary">수정</button> 
						<button type="button" class="btn btn-danger">삭제</button>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>