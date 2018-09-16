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
	
/* .tooltip {
     position: relative;
    display: inline-block; 
    border-bottom: 1px dotted black;
}  */


.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: white;
    color: black;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;

    /* Position the tooltip */    
    position: absolute;
    z-index: 1;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
}
	
</style>
</head>
<body>

	<jsp:include page="../page/menu.jsp" />	
	<div class="container">
	<div class="row">
		<h1 class="text-muted text-center" style="color:black;">적 금/대 출 관 리 <div class="text-right"><button type="button" class="btn btn-default" onclick="modDelPage();">적금 수정/삭제페이지</button></div></h1>		
	</div>
	<div class="row">
		<div class="col-xs-4">
			<h2 class="text-muted text-center">적금/대출 등록</h2>
		</div>
		<div class="col-xs-4">
			<h2 class="text-muted text-center">적금/대출 목록</h2>
		</div>
		<div class="col-xs-4">
			<h2 class="text-muted text-center">납입 목록</h2>
			<!-- <span class="tooltiptext">아래 항목을 클릭하면 수정, 삭제를 할 수 있습니다.</span> -->
		</div>
	</div>
	
	
	<div class="row">
		<div class="col-xs-4">
			<div class="button-group text-center">
				<button id="saving_formBtn" class="btn btn-default btn-primary" type="button">적금</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="loan_formBtn"  class="btn btn-default btn-danger" type="button">대출</button>
			</div>	    		    
		</div>
		
		
		<div class="col-xs-4">
			<ul class="nav nav-pills">
			  <li><a href="#">적금보기</a></li>
			  <li><a href="#">대출보기</a></li>
			</ul>
		</div>
		
		<div class="col-xs-4">
			<!-- <span class="tooltiptext">아래 항목을 클릭하면 수정, 삭제를 할 수 있습니다.</span> -->
		</div>
	</div>
	
		<div class="row">
			<div class="col-xs-4">
				<form:form id="saving_form" action="/saving/add" class="form-horizontal" method="post"
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
				
				<form:form id="loan_form" action="/loan/add" class="form-horizontal" method="post"
					modelAttribute="loan" style="display:none;">
					<div class="form-group">
						<form:label path="loan_place" class="control-label col-xs-3">대출처</form:label>
						<div class="col-xs-6">
							<form:input path="loan_place" type="text" class="form-control"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="loan_place" class="error"/>
						</div>
					</div>
					
					<div class="form-group">
						<form:label path="loan_purpose" class="control-label col-xs-3">대출목적</form:label>
						<div class="col-xs-6">
							<form:input path="loan_purpose" type="text" class="form-control"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="loan_purpose" class="error"/>
						</div>
					</div>
					<div class="form-group">
						<form:label path="loan_date" class="control-label col-xs-3">대출날짜</form:label>
						<div class="col-xs-6">
							<form:input path="loan_date" type="date" class="form-control"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="loan_date" class="error"/>
						</div>
					</div>
					<div class="form-group">						
						<form:label path="loan_price" class="control-label col-xs-3">대출금액</form:label>
						<div class="col-xs-6">
							<form:input path="loan_price" type="text" class="form-control" onkeyup="inputNumberFormat(this);"/>
						</div>
						<div class="col-xs-6">
							<form:errors path="loan_price" class="error"/>
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
		 			
		 			<c:forEach var="loan" items="${loanList }">
		 				<div class="row">
		 					<div class="col-xs-12">
			 					<div class="list-group">
								    <a href="#" class="list-group-item" onclick="selectLoanList(${loan.idx});">
								    <input type="hidden" id="num_${loan.idx }" value="${loan.idx }" />
								    	<span id="lpan_${loan.idx }">대출처 : ${loan.loan_place }</span>&nbsp;&nbsp;&nbsp;<span id="purpose_${loan.idx }">대출목적 :${loan.loan_purpose }</span>
			 						<br /><span id="loanStart">대출날짜 : ${loan.loan_date }</span>
			 						<br /><span id="loanSum">누적금액 : ${loan.loan_price }</span>
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
			 						<input type="text" name="price" class="form-control" onkeyup="inputNumberFormat(this);"/>
			 					</div>
			 				</div>
			 				
			 				<div class="button-group text-right">
			 					
			 					<button type="button" class="btn btn-info" onclick="payReg(this.form);">등록</button>
			 				</div>
			 			</form>
			 		<div id="savingList" class="list-group">
			 			
			 		</div>
			 		<div class="modal fade" id="payModal" role="dialog">
					    <div class="modal-dialog modal-sm">
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">적금금액 수정/삭제</h4>
					        </div>
					        <div class="modal-body">
					        	<input type="hidden" id="tempPrice" />
					        	<input type="hidden" id="payIdxModal" />
					        	<input type="hidden" id="payNumModal" />
					          <p>가입은행 : <span id='payBankModal'></span></p>
					          <p>적금이름 : <span id='payNameModal'></span></p>
					          <p>입력날짜 : <span id='payRegModal'></span></p>
					          <input type="number" class="form-control" id="payPriceModal" />
					        </div>
					        <div class="modal-footer">
					          <button type="button" class="btn btn-primary" onclick="savingPayMod();">수정</button>
					          <button type="button" class="btn btn-danger" onclick="savingPayDel();">삭제</button>
					          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					        </div>
					      </div>
					    </div>
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
	
	$("#saving_formBtn").on("click",function(){
		$("#saving_form").show();
		$("#loan_form").hide();
	});
	
	$("#loan_formBtn").on("click",function(){
		$("#saving_form").hide();
		$("#loan_form").show();
	});
	
	
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
					var count = 0;
					for(var saving of data){
						count++;
						var $row = $("<div id='list_"+count+"' class='row list-group-item' data-toggle='modal' data-target='#payModal' data-backdrop='false' onclick='listClick("+count+");'>");
						var $input = $("<input type='hidden' id='payInput_"+count+"' value='"+saving.num+"'>");
						var $p1 = $("<p class='control-label'>");
						var $p2 = $("<p id='payPrice_"+count+"' class='control-label'>");
						var price=comma(saving.price);
						$p1.text("적금이름 : "+saving.payname+" 날짜 : "+saving.regdate);
						$p2.text(" 금액 : "+price);
						$row.append($p1);
						$row.append($p2);
						$row.append($input);
						var $savingList = $("#savingList").append($row);	
					}
					$("#listbox").append($savingList);
				}
			});
		}
		
		function payReg(form){
			var tempPrice = form.price.value;
			var idx = form.idx.value;
			var paybank = form.paybank.value;
			var payname = form.payname.value;
			if(tempPrice <= 0){
				alert("금액을 확인해주세요.");
				return;
			}
			var price = uncomma(tempPrice);
			$.ajax({
				url:"/savingpay/add",
				data:{price:price,
					  idx:idx,
					  paybank:paybank,
					  payname:payname},
				type:"post",
				success:function(data){
					if(data == 'y'){
						alert("금액이 정상적으로 입력되었습니다.");
						location.reload();
					}
				}
			});
		}
				
		function listClick(count){
			console.log("넘기는 num : "+count);
			$("#list_"+count).find("#payInput_"+count).each(function(e){
				var num = $(this).val();
				
				$.ajax({
					url:"/savingpay/selectOne",
					type:"post",
					data:{num:num},
					success:function(data){
						$("#tempPrice").val(data.price); //수정, 삭제시 비교할 가격
						$("#payIdxModal").val(data.idx);
						$("#payNumModal").val(data.num);
						$("#payBankModal").text(data.paybank);
						$("#payNameModal").text(data.payname);
						$("#payPriceModal").val(data.price);
						$("#payRegModal").text(data.regdate);
					}
				});
			});	
		}
				
		function savingPayMod(){
			var tempPrice = eval($("#tempPrice").val());
			var price = eval($("#payPriceModal").val());			
			var num = eval($("#payNumModal").val());
			var idx = eval($("#payIdxModal").val());
			console.log(typeof tempPrice);
			console.log(typeof price);
			console.log(typeof num);
			console.log(typeof idx);
			if(price <= 0){
				alert("금액을 확인해주세요.");
				return;
			}
						
			$.ajax({
				url:"/savingpay/mod",
				type:"post",
				data:{num:num,
					  price:price,
					  tempPrice:tempPrice,
					  idx:idx},
				success:function(data){
					if(data == 'y'){
						alert("수정이 완료되었습니다.");
						location.href="/saving";
					}
				}
			});
		}
		
		function savingPayDel(){
			var num = $("#payNumModal").val();
			var idx = $("#payIdxModal").val();
			var price = eval($("#tempPrice").val());
			$.ajax({
				url:"/savingpay/del",
				type:"post",
				data:{num:num,
					 price:price,
					 idx:idx},
				success:function(data){
					if(data == 'y'){
						alert("삭제가 완료되었습니다.");
						location.href="/saving";
					}
				}
			});
		}
		
		function modDelPage(){
			location.href="/saving/moddelpage";
		}
		    //콤마찍기
		    function comma(str) {
		    str = String(str);
		    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		}
		
		function uncomma(str) {
		    str = String(str);
		    return str.replace(/[^\d]+/g, '');
		}
		
		function inputNumberFormat(obj) {
		    obj.value = comma(uncomma(obj.value));
		}
	</script>
</body>
</html>