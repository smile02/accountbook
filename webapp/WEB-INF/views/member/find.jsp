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
		<div class="row">
			<div class="col-xs-4 col-xs-offset-2">
				<h2 class="text-center">닉네임 찾기</h2>
				  <div class="panel panel-default">
				    <div class="panel-heading">닉네임 찾기</div>
				    <div class="panel-body">
				    	<form id="nickForm" action="/member/memberfind" method="post">
				    		<div class="form-group">
				    			<label for="email" class="control-label">이메일</label>
				    			<input id="email" type="text" class="form-control" placeholder="이메일을 적어주세요." />
				    		</div>
				    		<br />
				    		<div class="button-group text-right">
				    			<button type="button" class="btn btn-success"
				    			onclick="nickSearch();"><span class="glyphicon glyphicon-search">찾기</span></button>
				    		</div>
				    	</form>
				    </div>
				 </div>
			</div>
			
			<div class="col-xs-4">
				<h2 class="text-center">비밀번호 찾기</h2>
				   <div class="panel panel-default">
				    <div class="panel-heading">비밀번호 찾기</div>
				    <div class="panel-body">
				    	<form id="pwForm" action="/member/memberfind" method="post" >
				    		<div class="form-group">
				    			<label for="nickname2" class="control-label">닉네임</label>
				    			<input id="nickname2" type="text" class="form-control" placeholder="닉네임을 적어주세요." />
				    		</div>
				    		<div class="form-group">
				    			<label for="email2" class="control-label">이메일</label>
				    			<input id="email2" type="text" class="form-control" placeholder="이메일을 적어주세요." />
				    		</div>
				    		<br />
				    		<div class="button-group text-right">
				    			<button type="button" class="btn btn-success"
				    				onclick="pwSearh(this.form);"><span class="glyphicon glyphicon-search">찾기</span></button>
				    		</div>
				    	</form>
				    </div>
				 </div>
			</div>
		</div>
	</div>
	
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
	
		function nickSearch(){
			var email = $("#email").val();
			$.ajax({
				url:"/member/memberfind",
				type:"post",
				data:{email:email},
				success:function(data){
					alert(data.nickname);
				}
			});
		}
	</script>
</body>
</html>