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
		<div class="row">
			<h2 class="text-center text-muted">수입 목록</h2>
		</div>
		<br/>
		<c:forEach var="income" items="${incomeList }">
			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-heading">수입내역 : ${income.comments }</div>
					<div class="panel-body">수입한 사람 : ${income.nickname }</div>
					<div class="panel-body">수입방법 : ${income.ways}</div>					
					<div class="panel-body">금액 : ${income.price }</div>
					<div class="panel-body">메모 : <textarea rows="5" class="form-control" 
					readonly="readonly" style="resize:none;">${income.memo }</textarea></div>
					<div class="panel-footer">수입일 : ${income.regdate } <br />
						<button type="button" class="btn btn-secandary"
						data-toggle="modal" data-target="#myModal" data-backdrop="false"
						onclick="modView(${income.idx});">수정</button> 
						<button type="button" class="btn btn-danger" onclick="incomeDel(${income.idx})">삭제</button>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title">수정화면!</h4>
        </div>
        <div class="modal-body">
	        <form class="form-horizontal">
	        	<input type="hidden" id="idx"/>
	        	<div class="form-group">
	        		<label for="comments" class="control-label col-xs-4">수입내역 :</label>
	        		<div class="col-xs-8">
	        			<input id="comments" type="text" class="form-control"/>
	        		</div>
	        	</div>
	        	<span id="comments-error" class="col-xs-8 col-xs-offset-4 error"></span> 	        	
	        	<div class="form-group">
	        		<label for="nickname" class="control-label col-xs-4">수입한 사람 :</label>
	        		<div class="col-xs-8">
	        			<p id="nickname" class="form-control"></p>
	        		</div>
	        	</div>
	        	
	        	<div class="form-group">
	        		<label for="regdate" class="control-label col-xs-4">수입날짜 :</label>
	        		<div class="col-xs-8">
	        			<input id="regdate" type="date" class="form-control"/>
	        		</div>
	        	</div>
	        	<span id="regdate-error" class="col-xs-8 col-xs-offset-4 error"></span>
	        	<div class="form-group">
	        		<label for="ways" class="control-label col-xs-4">수입방법 :</label>
	        		<div class="col-xs-4">
	        			<select id="ways" class="form-control"></select>
	        		</div>
	        	</div>
	        	        	
	        	<div class="form-group">
	        		<label for="price" class="control-label col-xs-4">금액 :</label>
	        		<div class="col-xs-8">
	        			<input id="price" type="text" class="form-control"/>
	        		</div>
	        	</div>
	        	<span id="price-error" class="col-xs-8 col-xs-offset-4 error"></span>
	        	<div class="form-group">
	        		<label for="memo" class="control-label col-xs-4">메모 :</label>
	        		<div class="col-xs-8">
	        			<textarea id="memo" rows="5" class="form-control" style="resize:none;"></textarea>
	        		</div>
	        	</div>
	        	<span id="memo-error" class="col-xs-8 col-xs-offset-4 error"></span>
        	</form>
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-info" onclick="incomeModBtn();">수정 완료</button>
          <button id="closeBtn" type="button" class="btn btn-default" onclick="closeBtn();">수정 취소</button>          
        </div>
      </div>      
    </div>
  </div>
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
	
		function closeBtn(){
			$("#closeBtn").attr("data-dismiss","modal");
			location.reload();
		}
		
		function incomeDel(idx){
			var check = confirm("정말로 삭제하시겠습니까?");
			if(!check){
				return;
			}				
			$.ajax({
				url:"/income/del",
				type:"post",
				data:{idx:idx},
				success:function(data){
					if(data=='y'){
						location.reload();
					}else{
						alert("서버에 오류 발생")
					}
				}
			});
		}
		
		function modView(idx){
			$.ajax({
				url:"/income/mod",
				type:"get",
				data:{idx:idx},
				success:function(data){
					$("#idx").val(data.idx);
					$("#comments").val(data.comments);
					$("#nickname").text(data.nickname);
					$("#regdate").val(data.regdate);
					$("#price").val(data.price);
					$("#memo").val(data.memo);
					$("#ways").append("<option val='"+data.ways+"'>"+data.ways+"</option>");
					if(data.ways == '카드'){
						$("#ways").append("<option val='현금'>현금</option>");
					}else{
						$("#ways").append("<option val='카드'>카드</option>");
					}
				}
			});
		}
		
		function incomeModBtn(){
			$.ajax({
				url:"/income/mod",
				type:"post",
				data:{idx:$("#idx").val(),
					  comments:$("#comments").val(),
					  regdate:$("#regdate").val(),
					  price:$("#price").val(),
					  memo:$("#memo").val(),
					  ways:$("#ways").val()
				},
				success:function(data){
					if(data.success != 'success'){
						$("#comments-error").text(data.수입내역);
						$("#regdate-error").text(data.날짜);
						$("#price-error").text(data.금액);
						$("#memo-error").text(data.메모);
					}else{
						location.reload();
					}
				}
			});
		}
	</script>
</body>
</html>