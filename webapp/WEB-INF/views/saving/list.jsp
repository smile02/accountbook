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
				<form:form action="/saving/add" class="form-horizontal" method="post"
					modelAttribute="saving">
					<div class="form-group">
						<form:label path="regbank" class="control-label col-xs-3">가입은행</form:label>
						<div class="col-xs-6">
							<form:input path="regbank" type="text" class="form-control"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="regbank" class="error"/>
						</div>
					</div>
					<div class="form-group">
						<form:label path="regname" class="control-label col-xs-3">적금이름</form:label>
						<div class="col-xs-6">
							<form:input path="regname" type="text" class="form-control"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="regname" class="error"/>
						</div>
					</div>
					<div class="form-group">
						<form:label path="startreg" class="control-label col-xs-3">가입날짜</form:label>
						<div class="col-xs-6">
							<form:input path="startreg" type="date" class="form-control"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="startreg" class="error"/>
						</div>
					</div>
					<div class="form-group">						
						<form:label path="endreg" class="control-label col-xs-3">만기날짜</form:label>
						<div class="col-xs-6">
							<form:input path="endreg" type="date" class="form-control"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="endreg" class="error"/>
						</div>
					</div>					
					<div class="button-group text-center">
						<button class="btn btn-success">등록</button>
						<button type="reset" class="btn btn-warning">취소</button>
					</div>				
				</form:form>				
			</div>			
			
			<div class="col-xs-4">
				<div id="savingbox" class="panel panel-default"
		 	style="height:500px; padding-left:20px; overflow:auto;">
		 			<c:forEach var="saving" items="${savingList }">
		 				<div class="row">
		 					<div class="col-xs-12">
			 					<div class="list-group">
								    <a href="#" class="list-group-item" onclick="selectList(${saving.idx});">
								    <input type="hidden" id="num_${saving.idx }" value="${saving.idx }" />
								    	<span id="bank_${saving.idx }">은행 : ${saving.regbank }</span>&nbsp;&nbsp;&nbsp;<span id="name_${saving.idx }">적금이름:${saving.regname }</span>
			 						<br /><span id="start">가입날짜 : ${saving.startreg }</span> / <span id="end">만기날짜 : ${saving.endreg }</span>
			 						<br /><span id="sum">누적금액 : <f:formatNumber value="${saving.price }" pattern="#,###"/></span>
								    </a>
								</div>
		 					</div>
		 				</div>
		 			</c:forEach>
		 		</div>
				</div>
				<div class="col-xs-4">
					<div id="listbox" class="panel panel-default"
			 	style="height:500px; padding-left:20px; overflow:auto;">
			 		<form id="form" action="/savingpay/add" class="form-horizontal" method="post"
			 			style="display:none;">
			 				<input id="payIdx" type="hidden" name="idx" />
			 				<input id="payBank" type="hidden" name="paybank" />
			 				<input id="payName" type="hidden" name="payname" />
			 				<div class="form-group">
			 				<div class="row">
			 					<div class="col-xs-3">
			 						<span class="text-center">은행이름</span>
			 					</div>
			 					<div class="col-xs-4">
			 						<span class="text-center">적금이름</span>
			 					</div>
			 					<div class="col-xs-5">
			 						<span class="text-center">금액</span>
			 					</div>
			 				</div>
			 					<span class="col-xs-3" id="spanbank"></span>
			 					<span class="col-xs-4" id="spanname"></span>
			 					<div class="col-xs-5">
			 						<input type="number" name="price" class="form-control"/>
			 					</div>
			 				</div>
			 				<div class="button-group text-right">
			 					<button class="btn btn-info">등록</button>
			 				</div>
			 			</form>
			 		<div id="savingList" class="list-group">
			 			
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
	var spanbank = "";
	var spanname = "";	
		function selectList(idx){
			var idx = $("#num_"+idx).val();
			var bankidx = $("#bank_"+idx).text().indexOf(":");
			var nameidx = $("#name_"+idx).text().indexOf(":");
			spanbank = $("#bank_"+idx).text().substring(bankidx+1,$("#bank_"+idx).text().length);
			spanname = $("#name_"+idx).text().substring(nameidx+1,$("#name_"+idx).text().length);
			$("#payIdx").val(idx);
			$("#payBank").val(spanbank);
			$("#payName").val(spanname);
			$.ajax({
				url:"/saving/selectList",
				type:"post",
				data:{idx:idx},
				success:function(data){
					$("#savingList").empty();
					$("#form").css("display","block");
					$("#spanbank").text(spanbank);
					$("#spanname").text(spanname);
					for(var saving of data){
						var $row = $("<div class='row list-group-item'>");
						var $p1 = $("<p class='control-label'>");
						var $p2 = $("<p class='control-label'>");
						var price=comma(saving.price);
						$p1.text("적금이름 : "+saving.payname+" 날짜 : "+saving.regdate);
						$p2.text(" 금액 : "+price);
						$row.append($p1);
						$row.append($p2);
						var $savingList = $("#savingList").append($row);	
					}
					$("#listbox").append($savingList);
				}
			});
		}
		    //콤마찍기
		    function comma(str) {
		        str = String(str);
		        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		    }
	</script>
</body>
</html>