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
			<div id="signUp">
				<form:form action="/account/member/signup" modelAttribute="member" class="form-horizontal">
					<h2 class="text-muted text-center">회 원 가 입</h2>
					
					<div class="form-group">
						<form:label path="nickname" class="control-label col-xs-4">닉네임 : </form:label>
						<div class="col-xs-6">
							<form:input path="nickname" class="form-control" onkeyup="nicknameCorrect();"/>
						</div>
						<div class="col-xs-2">
							<button id="nickDualBtn" type="button" class="btn btn-default" onclick="dualCheck();">중복확인</button>
						</div>
					</div>
					<form:errors path="nickname" class="error"/>
					<div class="form-group">
						<form:label path="password" class="control-label col-xs-4">비밀번호 : </form:label>
						<div class="col-xs-7">
							<form:input type="password" path="password" class="form-control" onkeyup="passwordCorrect();"/>
						</div>
					</div>
					<form:errors path="password" class="error"/>
					<div class="form-group">
						<form:label path="passwordCheck" class="control-label col-xs-4">비밀번호확인 : </form:label>
						<div class="col-xs-7">
							<form:input path="passwordCheck" type="password" class="form-control" onkeyup="passwordCorrect();"/>
						</div>						
						<div class="col-xs-1">
							<span id="passwordCorrect" class="error"></span>
						</div>
					</div>
					
					<div class="form-group">
						<form:label path="email" class="control-label col-xs-4">이메일 : </form:label>
						<div class="col-xs-7">
							<form:input path="email" class="form-control" onkeyup="emailCorrect();"/>
						</div>						
						<div class="col-xs-1">
							<button id="emailAuth" type="button" class="btn btn-default" onclick="emailCheck();">이메일 인증</button>
						</div>						
					</div>
					<div class="form-group">
						<form:label path="emailCode" class="control-label col-xs-4">인증코드 : </form:label>
						<div class="col-xs-7">
							<form:input path="emailCode" class="form-control" placeholder="인증코드 입력"/>
						</div>
					</div>
					<form:errors path="emailCode" class="error"/>
					<form:errors path="email" class="error"/>
					<span id="email-error" class="error"></span>
					<div class="button-group text-right">
						<button type="button" class="btn btn-warning" onclick="signUp(this.form);">회원가입</button>
						<button type="button" class="btn btn-default" onclick="idpwFind();">아이디/비밀번호 찾기</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
	var pwdCheck = false;
	var nickCheck = false;
		function signUp(form){
			if(!nickCheck && !pwdCheck){
				alert("닉네임중복확인, 비밀번호를 확인해주세요.");
				return;
			}else if(!nickCheck){
				alert("닉네임 중복확인을 해주세요.");
				return;
			}else if(!pwdCheck){
				alert("비밀번호를 확인해주세요.");
				return;
			}
			
			form.submit();
		}
	
		function idpwFind(){
			
		}
		function dualCheck(){
			$.ajax({
				url:"/account/member/dualcheck",
				type:"post",
				data:{nickname:$("#nickname").val()},
				success:function(data){
					if(data == 'n'){
						alert("이미 존재하는 닉네임입니다.");
					}else{
						alert("사용 가능한 닉네임입니다.");
						nickCheck = true;
						$("#nickDualBtn").attr("disabled","disabled");
					}					 
				}
			});
		}
		
		function nicknameCorrect(){
			nickCheck = false;
			$("#nickDualBtn").removeAttr("disabled");
		}
		function passwordCorrect(){			
			var	password = $("#password").val();
			var passwordCheck = $("#passwordCheck").val();
			pwdCheck = false;
			console.log("password : "+password+", passwordCheck : "+passwordCheck);
			console.log(password == passwordCheck);
			if(password != passwordCheck){
				$("#passwordCorrect").removeClass("glyphicon glyphicon-ok");
				$("#passwordCorrect").addClass("glyphicon glyphicon-remove").css("color","red");
			}else{
				$("#passwordCorrect").removeClass("glyphicon glyphicon-remove");
				$("#passwordCorrect").addClass("glyphicon glyphicon-ok").css("color","green");
				pwdCheck = true;
			}
			
		}
		
		function emailCorrect(){
			$("#emailAuth").removeAttr("disabled");
		}
		
		function emailCheck(){
			
			$("#emailAuth").attr("disabled","disabled");
			$(".error").text("");
			$.ajax({
				url:"/account/member/emailcheck",
				type:"post",
				data:{email:$("#email").val()},
				success:function(data){
					console.log("data : "+data);
					$(".error").text("");
					if(data == 'null'){						
						$("#email-error").text("이메일을 입력해주세요.");	
					}else if(data == 'incorrect'){
						$("#email-error").text("이메일 형식이 올바르지 않습니다.");
					}else if(data == 'duplicated'){
						$("#email-error").text("이미 가입되어 있는 이메일 주소입니다.");
					}else if(data == 'error'){
						$("#email-error").text("서버 오류로 인해 이메일 발송을 실패했습니다.");
					}else{		
						$("#email").text("");
						alert("인증메일을 발송했습니다.\n코드를 입력해주세요.");
					}
				}
			});
		}
		
	</script>
</body>
</html>