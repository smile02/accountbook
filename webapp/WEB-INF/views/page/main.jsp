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
	<jsp:include page="menu.jsp" />
	<div class="container">	
		<div class="col-sm-4 col-sm-offset-4">
			<h3 class="text-center text-muted">가계부 등록</h3>
			<div class="buttons" style="margin:0 auto;">
				<button type="button" class="btn btn-info" onclick="expand_reg();">지출등록</button>&nbsp;
				<button type="button" class="btn btn-success" onclick="income_reg();">수입등록</button><br /><br />
			</div>	
				<div id="expandReg" class="row" style="display:none;">
					<form:form id="form" class="form-horizontal"
						action="/expand/add" modelAttribute="expand">
						<div class="form-group">
							<form:label path="regdate" class="control-label col-xs-3">날짜 :</form:label>
							<div class="col-xs-8">
								<form:input type="date" path="regdate" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<form:label path="comments" class="control-label col-xs-3">지출내역 :</form:label>
							<div class="col-xs-8">
								<form:input path="comments" class="form-control" placeholder="예)OOO마트에서 XXX를 샀다!"/>
							</div>
						</div>
						<div class="form-group">
							<form:label path="big_ways" class="control-label col-xs-3">지출방법 :</form:label>
							<div class="col-xs-4">
								<form:select path="big_ways" onchange="waysChange();" class="form-control">
									<option value="">::선택::</option>
									<c:forEach var="way" items="${big_waysList }">
										<option value="${way.big_name}">${way.big_name}</option>
									</c:forEach>
								</form:select>								
							</div>
							<div class="col-xs-4">
								<form:select path="small_ways" class="form-control"></form:select>
							</div>
						</div>
						<div class="form-group">
							<form:label path="big_purpose" class="control-label col-xs-3">지출목적 :</form:label>
							<div class="col-xs-4">
								<form:select path="big_purpose" onchange="purposeChange();" class="form-control">
									<option value="">::선택::</option>
									<c:forEach var="purpose" items="${big_purposeList }">
										<option value="${purpose.big_name}">${purpose.big_name}</option>
									</c:forEach>
								</form:select>								
							</div>
							<div class="col-xs-4">
								<form:select path="small_purpose" class="form-control"></form:select>
							</div>
						</div>
						<div class="form-group">
							<form:label path="price" class="control-label col-xs-3">금액 :</form:label>
							<div class="col-xs-8">
								<form:input path="price" class="form-control" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<form:label path="memo" class="control-label col-xs-3">메모 :</form:label>
							<div class="col-xs-8">
								<form:textarea path="memo" rows="5" class="form-control" style="resize:none;"
								placeholder="예)1.오늘은 과소비했네... 2.저렴하게 샀다!! 다행ㅎㅎ"/>
							</div>
						</div>
						<button class="btn btn-success text-right" style="display:block;">등록</button>
					</form:form>
					<br />
					<button type="button" class="btn btn-default" onclick="closeBtn();">창 닫기</button>							
				</div>
				<div id="incomeReg" class="row" style="display:none;">
					<form:form id="form" class="form-horizontal"
						action="/income/add" modelAttribute="income">
						<div class="form-group">
							<form:label path="regdate" class="control-label col-xs-3">날짜 :</form:label>
							<div class="col-xs-8">
								<form:input type="date" path="regdate" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<form:label path="comments" class="control-label col-xs-3">수입내역 :</form:label>
							<div class="col-xs-8">
								<form:input path="comments" class="form-control" placeholder="월급!!!들어왔따"/>
							</div>
							
						</div>
						<div class="form-group">
							<form:label path="ways" class="control-label col-xs-3">수입방법 :</form:label>
							<div class="col-xs-8">
								<form:select path="ways" class="form-control">
									<form:option value="a">카드</form:option>
									<form:option value="b">현금</form:option>
								</form:select>
							</div>
						</div>						
						<div class="form-group">
							<form:label path="price" class="control-label col-xs-3">금액 :</form:label>
							<div class="col-xs-8">
								<form:input path="price" class="form-control" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<form:label path="memo" class="control-label col-xs-3">메모 :</form:label>
							<div class="col-xs-8">
								<form:textarea path="memo" rows="5" class="form-control" style="resize:none;"
								placeholder="예)나이쓰!!! 월급 이제 부자다~ㅎㅎ 다시 돈 나가겠지만 ㅠ"/>
							</div>
						</div>
						<button class="btn btn-success text-right" style="display:block;">등록</button>
					</form:form>
					<br />
					<button type="button" class="btn btn-default" onclick="closeBtn();">창 닫기</button>
				</div>
			</div>
			
		</div>

	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
	
		function expand_reg(){
			$("#incomeReg").css("display","none");
			$("#expandReg").css("display","block");			
		}
		function income_reg(){
			$("#expandReg").css("display","none");
			$("#incomeReg").css("display","block");
		}
		
		function closeBtn(){
			$("#expandReg").css("display","none");
			$("#incomeReg").css("display","none");
			location.reload();
		}
		
		function waysChange(){
			var big_name = $("#big_ways").val(); //select태그 안에 있는 선택되어진 option태그
			console.log(big_name);
			$.ajax({
				url:"/ways/small",
				type:"post",
				data:{big_name:big_name},
				success:function(smallWaysList){
					$("#small_ways").empty();
					$("#small_ways").append("<option val=''>::선택::</option>");
					for(var sways of smallWaysList){
						var $option = $("<option>").val(sways.small_name).text(sways.small_name);				
						$("#small_ways").append($option);
					}
				}			
			});
		}
		
		function purposeChange(){
			var big_name = $("#big_purpose").val(); //select태그 안에 있는 선택되어진 option태그
			console.log(big_name);
			$.ajax({
				url:"/purpose/small",
				type:"post",
				data:{big_name:big_name},
				success:function(smallPurposeList){
					$("#small_purpose").empty();
					$("#small_purpose").append("<option val=''>::선택::</option>");
					for(var spurpose of smallPurposeList){
						var $option = $("<option>").val(spurpose.small_name).text(spurpose.small_name);				
						$("#small_purpose").append($option);
					}
				}			
			});
		}
	</script>
</body>
</html>