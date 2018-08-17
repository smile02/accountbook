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
			<h2 class="text-center text-muted">수입 등록</h2><br />
				<div id="incomeReg" class="row">
					<form:form id="form" class="form-horizontal"
						action="/account/income/add" modelAttribute="income">
						<div class="form-group">
						
							<form:label path="nickname" class="control-label col-xs-3">작성자 :</form:label>
							<div class="col-xs-8">
								<p id="nickname" class="form-control">${sessionScope.loginMember.nickname }</p>
							</div>
						</div>
						<div class="form-group">
							<form:label path="regdate" class="control-label col-xs-3">날짜 :</form:label>
							<div class="col-xs-8">
								<form:input type="date" path="regdate" class="form-control"/>
							</div>
						</div>
						<div class="col-xs-8 col-xs-offset-3">
							<form:errors path="regdate" class="error"/>
						</div>
						<div class="form-group">
							<form:label path="comments" class="control-label col-xs-3">수입내역 :</form:label>
							<div class="col-xs-8">
								<form:input path="comments" class="form-control" placeholder="월급!!!들어왔따"/>
							</div>							
						</div>
						<div class="col-xs-8 col-xs-offset-3">
							<form:errors path="comments" class="error"/>
						</div>
						<div class="form-group">
							<form:label path="ways" class="control-label col-xs-3">수입방법 :</form:label>
							<div class="col-xs-8">
								<form:select path="ways" class="form-control">
									<form:option value="카드">카드</form:option>
									<form:option value="현금">현금</form:option>
								</form:select>
							</div>
						</div>
						<div class="col-xs-8 col-xs-offset-3">
							<form:errors path="ways" class="error"/>
						</div>				
						<div class="form-group">
							<form:label path="price" class="control-label col-xs-3">금액 :</form:label>
							<div class="col-xs-8">
								<form:input path="price" class="form-control" value="0"/>
							</div>
						</div>
						<div class="col-xs-8 col-xs-offset-3">
							<form:errors path="price" class="error"/>
						</div>
						<div class="form-group">
							<form:label path="memo" class="control-label col-xs-3">메모 :</form:label>
							<div class="col-xs-8">
								<form:textarea path="memo" rows="5" class="form-control" style="resize:none;"
								placeholder="예)나이쓰!!! 월급 이제 부자다~ㅎㅎ 다시 돈 나가겠지만 ㅠ"/>
							</div>
						</div>
						<div class="col-xs-8 col-xs-offset-3">
							<form:errors path="memo" class="error"/>
						</div>
						
						<br />
						<div class="row text-right">
							<button class="btn btn-success">등록</button>
							<button type="button" class="btn btn-default">다시 입력하기</button>
						</div>
					</form:form>
					<br />					
				</div>
			</div>
			
		</div>

	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
</body>
</html>