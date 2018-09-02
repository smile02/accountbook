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
			<h2 class="text-muted text-center">마 이 페 이 지</h2>
		</div><br />		
		<div class="row">
			<form class="form-horizontal">
						
				<input id="idx" type="hidden" value="${sessionScope.loginMember.idx }" name="idx" />
				<div class="form-group">
					<label for="nickname" class="control-label col-xs-3 col-xs-offset-1">닉네임</label>
					<div class="col-xs-3">
						<input id="nickname" type="text" class="form-control" value="${sessionScope.loginMember.nickname }" name="nickname" readonly="readonly"
						onkeydown="nickInput();" />
					</div>
					<div class="col-xs-2">
						<button disabled="disabled" id="nickDualCheck" class="btn btn-default" type="button" onclick="nickDualBtn();">중복확인</button>
					</div>
				</div>
				
				<div class="form-group">
					<label for="password" class="control-label col-xs-3 col-xs-offset-1">비밀번호</label>
					<div class="col-xs-3">
						<input id="password" type="password" class="form-control" name="password" readonly="readonly" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="passwordCheck" class="control-label col-xs-3 col-xs-offset-1">비밀번호 확인</label>
					<div class="col-xs-3">
						<input id="passwordCheck" type="password" class="form-control" readonly="readonly"/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="email" class="control-label col-xs-3 col-xs-offset-1">이메일</label>
					<div class="col-xs-3">
						<input id="email" type="text" class="form-control" value="${sessionScope.loginMember.email }" name="email" readonly="readonly" 
						onkeydown="emailInput();"/>
					</div>
					<div class="col-xs-2">
						<button disabled="disabled" id="emailDualCheck" class="btn btn-default" type="button" onclick="emailDualBtn();">이메일인증</button>
					</div>
				</div>
				<div class="form-group">
					<label for="emailCode" class="control-label col-xs-3 col-xs-offset-1">인증번호</label>
					<div class="col-xs-2">
						<input id="emailCode" type="text" class="form-control" name="emailCode" readonly="readonly"/>
					</div>
				</div>						
								
				<div class="button-group col-xs-4 col-xs-offset-5">
					<button id="change" class="btn btn-primary" type="button" onclick="infoChange();">수정</button>					
					<button id="main" class="btn btn-default" type="button" onclick="location.href='/'">메인으로</button>
					<button onclick="changeSuccess();" style="display:none;" id="success" class="btn btn-primary" type="button">수정완료</button>
					<button onclick="changeCancel();" style="display:none;" id="cancel" class="btn btn-default" type="button">수정취소</button>
				</div>				
			</form>			
		</div>
	</div>
		
	
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
		var nickChange = false;
		var emailChange = false;
		
		function nickInput(){
			nickChange = true;
			$("#nickDualCheck").removeAttr("disabled");
		}
		function emailInput(){
			emailChange = true;
			$("#emailDualCheck").removeAttr("disabled");
		}
		
		
		function nickDualBtn(){
			$.ajax({
				url:"/member/dualcheck",
				type:"post",
				data:{nickname:$("#nickname").val()},
				success:function(data){
					if(data == 'n'){
						alert("이미 존재하는 닉네임입니다.");
					}else{
						alert("사용 가능한 닉네임입니다.");
						nickChange = false;
						$("#nickDualCheck").attr("disabled","disabled");
					}					 
				}
			});			
		}
		
		
		function emailDualBtn(){
			$.ajax({
				url:"/member/emailcheck",
				type:"post",
				data:{email:$("#email").val()},
				success:function(data){
					console.log("data : "+data);
					$(".error").text("");
					if(data == 'null'){						
						alert("이메일을 입력해주세요.");
						emailChange = false;
						$("#emailDualCheck").attr("disabled","disabled");
					}else if(data == 'incorrect'){
						alert("이메일 형식이 올바르지 않습니다.");
						emailChange = false;
						$("#emailDualCheck").attr("disabled","disabled");
					}else if(data == 'duplicated'){
						alert("이미 가입되어 있는 이메일 주소입니다.");
						emailChange = false;
						$("#emailDualCheck").attr("disabled","disabled");
					}else if(data == 'error'){
						alert("서버 오류로 인해 이메일 발송을 실패했습니다.");
					}else{		
						$("#email").text("");
						alert("인증메일을 발송했습니다.\n코드를 입력해주세요.");
						emailChange = false;
						$("#emailCode").removeAttr("readonly");
					}
				}
			});			
		}
		
		
		function changeSuccess(){
			var idx = $("#idx").val();
			var nickname = $("#nickname").val();
			var password = $("#password").val();
			var email = $("#email").val();
			var emailCode = $("#emailCode").val();
			if(emailChange){
				emailCode = "false";
			}			
			
			if(nickChange){
				alert("닉네임 중복확인을 해주세요.");
				return;
			}
			if(emailChange){
				alert("이메일인증을 해주세요.");
				return;
			}
			
			$.ajax({
				url:"/member/info",
				type:"post",
				data:{idx:idx,
					  nickname:nickname,
					  password:password,
					  email:email,
					  emailCode:emailCode},					  
				success:function(data){
					if(data == 'y'){
						alert("회원정보를 수정했습니다.");
						changeCancel();
						nickChange = false;
						emailChange = false;
					}else if(data == "noEmail"){
						alert("이메일을 확인해주세요");
					}else if(data == "noNickname"){
						alert("닉네임을 확인해주세요");
					}else if(data == 'emailCheck'){
						alert("가입된 이메일인지 확인해주세요");
					}else if(data == 'emailCodeCheck'){
						alert("이메일 인증번호를 확인해주세요.");
					}else{
						alert("이메일 코드를 확인해주세요.");
					}
				}
			});
			
		}
		
		function infoChange(){
			$("#change").css("display","none");
			$("#main").css("display","none");
			$("#success").css("display","inline-block");
			$("#cancel").css("display","inline-block");
			
			$("#nickname").removeAttr("readonly");
			$("#email").removeAttr("readonly");
			$("#password").removeAttr("readonly");
			$("#passwordCheck").removeAttr("readonly");
		}
		
		
		function changeCancel(){
			$("#change").css("display","inline-block");
			$("#main").css("display","inline-block");
			$("#success").css("display","none");
			$("#cancel").css("display","none");
			
			
			$("#nickname").attr("readonly","readonly");
			$("#email").attr("readonly","readonly");
			$("#password").attr("readonly","readonly");
			$("#emailCode").attr("readonly","readonly");
			$("#passwordCheck").attr("readonly","readonly");
			$("#nickDualCheck").attr("disabled","disabled");
			$("#emailDualCheck").attr("disabled","disabled");
			
		}
		
	</script>
</body>
</html>