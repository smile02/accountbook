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
		<h2>적금/대출 수정 ,삭제</h2>
  			<p><strong>적금을 삭제하게 되면 등록된 금액도 같이 삭제가 되므로 꼭!!! 확인하고 진행해주세요.</strong></p>    
			  <table class="table table-hover table-condensed table-bordered">
			    <thead>		    		    
			      <tr>
			        <th class="text-center">가입은행</th>
			        <th class="text-center">적금이름</th>
			        <th class="text-center">가입날짜</th>
			        <th class="text-center">만기날짜</th>
			        <th class="text-center">누적금액</th>
			        <th class="text-center">비고</th>
			      </tr>
			    </thead>	
			    	    		    		    
			    <tbody>			    
				    <c:forEach var="saving" items="${savingList}">				   
						<tr>
					        <td><input type="text" class="form-control" id="regbank_${saving.idx }" value="${saving.regbank }"/></td>
					        <td><input type="text" class="form-control" id="regname_${saving.idx }" value="${saving.regname }"/></td>
					        <td><input type="date" class="form-control" id="startreg_${saving.idx }" value="${saving.startreg }"/></td>
					        <td><input type="date" class="form-control" id="endreg_${saving.idx }" value="${saving.endreg }"/></td>
					        <td><f:formatNumber pattern="#,###">${saving.price }</f:formatNumber></td>
					        <td> 
					        	<div class="button-group">
						        	<div style="width:80px;" class="col-xs-3">
						        		<button type="button" class="form-control btn btn-primary" onclick="savingMod(${saving.idx});">수정</button>
						        	</div>
						        	<div style="width:80px;" class="col-xs-3">
						        		<button class="form-control btn btn-danger" onclick="savingDel(${saving.idx});">삭제</button>
						        	</div>
					        	</div>
					         </td>
			      		</tr>		    
				    </c:forEach>			  
			    </tbody>
			    
			    <thead>		    		    
			      <tr>
			        <th class="text-center">대출처</th>
			        <th class="text-center">대출목적</th>
			        <th class="text-center">대출날짜</th>
			        <th class="text-center">대출금액</th>
			        <th class="text-center">누적금액</th>
			        <th class="text-center">비고</th>
			      </tr>
			    </thead>	
			    	    		    		    
			    	    		    		    
			    <tbody>			    
				    <c:forEach var="loan" items="${loanList}">				   
						<tr>
					        <td><input type="text" class="form-control" id="loan_place_${loan.idx }" value="${loan.loan_place }"/></td>
					        <td><input type="text" class="form-control" id="loan_purpose_${loan.idx }" value="${loan.loan_purpose }"/></td>
					        <td><input type="date" class="form-control" id="loan_date_${loan.idx }" value="${loan.loan_date }"/></td>
					        <td><input type="text" class="form-control" id="loan_price_${loan.idx }" value="${loan.loan_price }"/></td>
					        <td>${loan.loan_price }</td>
					        <td> 
					        	<div class="button-group">
						        	<div style="width:80px;" class="col-xs-3">
						        		<button type="button" class="form-control btn btn-primary" onclick="loanMod(${loan.idx});">수정</button>
						        	</div>
						        	<div style="width:80px;" class="col-xs-3">
						        		<button class="form-control btn btn-danger" onclick="loanDel(${loan.idx});">삭제</button>
						        	</div>
					        	</div>
					         </td>
			      		</tr>		    
				    </c:forEach>			  
			    </tbody>
			    
			  </table>
		  <div class="row">
		  	<div class="button-group text-center">
		  		<button class="btn btn-default" type="button" onclick="location.href='/saving'">대출/적금목록으로</button>
		  	</div>
		  </div>
	</div>
			
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
	
	function savingMod(idx){
		var regbank = $("#regbank_"+idx).val();
		var regname = $("#regname_"+idx).val();
		var startreg = $("#startreg_"+idx).val();
		var endreg = $("#endreg_"+idx).val();
		
		$.ajax({
			url:"/saving/mod",
			type:"post",
			data:{idx:idx,
				  regbank:regbank,
				  regname:regname,
				  startreg:startreg,
				  endreg:endreg},
			success:function(data){
				if(data.success != "success"){
					alert(data.error);
					return;
				}else{
					alert("수정되었습니다.");
					location.href="/saving";
				}
			}
		});
	}
	
	function savingDel(idx){
		if(!confirm("정말로 삭제하시겠습니까?")){
			return;
		}
		$.ajax({
			url:"/saving/del",
			type:"post",
			data:{idx:idx},
			success:function(data){
				if(data == 'y'){
					alert("삭제가 완료되었습니다.");
					location.href="/saving";
				}
			}
		});
	}
	</script>
</body>
</html>