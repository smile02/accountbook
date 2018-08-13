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
<style>
.paging{
	display:inline-block;
}
.pagination{
	margin-top:15px;
}
</style>
<body>
	<jsp:include page="../page/menu.jsp"/>
	<div class="container">
		<div class="row">
			<h2 class="text-center text-muted">지출 목록</h2>
		</div>
		<div class="row">
			<label class="control-label col-xs-1">일자검색</label>
			
			<div class="col-xs-1">
				<input type="text" class="form-control" id="year" placeholder="년"
				onkeypress="return onlyMyNumber(event);" />
			</div>
			<div class="col-xs-1">
				<input type="text" class="form-control" id="month" placeholder="월"
				onkeypress="return onlyMyNumber(event);" />
			</div>
			<div class="button-group">
				<div class="col-xs-1">
					<button type="button" class="form-control btn btn-info"
					onclick="selectSearch();">검색</button>
				</div>
			</div>
		</div>
		
		<div class="row">
			<span class="small text-muted">년,월로 검색이 가능하고, 둘 중 하나만 입력해도 됩니다.
			아무것도 입력하지 않고 검색할 시 전체검색이 됩니다. </span>
		</div>
			<div class="row col-xs-12 col-xs-offset-6">
				<span class="text-muted col-xs-1">전체합계</span>
				<label class="col-xs-1 control-label">${priceAllSum} 원</label>
				
				<span class="text-muted col-xs-1">년별합계</span>
				<label class="col-xs-1 control-label">${priceYearSum} 원</label>
				
				<span class="text-muted col-xs-1">월별합계</span>
				<label class="col-xs-1 control-label">${priceMonthSum} 원</label>
			</div>
		<br/>
		<c:forEach var="expand" items="${expandList }">
			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-heading">지출내역 : ${expand.comments }</div>
					<div class="panel-body">지출한 사람 : ${expand.nickname }</div>
					<div class="panel-body">지출방법 : ${expand.big_ways}-${expand.small_ways}</div>
					<div class="panel-body">지출목적 : ${expand.big_purpose}-${expand.small_purpose}</div>					
					<div class="panel-body">금액 : ${expand.price }</div>
					
					<div class="panel-body">메모 : <textarea rows="5" class="form-control" 
					readonly="readonly" style="resize:none;">${expand.memo }</textarea></div>
					<div class="panel-footer">지출일 : ${expand.regdate } <br /><br />
						<button type="button" class="btn btn-secandary"
						data-toggle="modal" data-target="#myModal2" data-backdrop="false"
						onclick="modView(${expand.idx});">수정하기</button> 
						<button type="button" class="btn btn-danger" onclick="expandDel(${expand.idx})">삭제하기</button>						
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="row">
			<div class="col-sm-12 text-center">
				<div class="paging">
					<ul class="pagination pagination-sm">
						${paging }
					</ul>
				</div>
			</div>
		</div>	
	</div>
	<!-- Modal -->
  <div class="modal fade" id="myModal2" role="dialog">
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
        		<label for="comments" class="control-label col-xs-4">지출내역 :</label>
        		<div class="col-xs-8">
        			<input id="comments" type="text" class="form-control"/>
        		</div>
        	</div>
        	<span id="comments-error" class="col-xs-8 col-xs-offset-4 error"></span> 	        	
        	<div class="form-group">
        		<label for="nickname" class="control-label col-xs-4">지출한 사람 :</label>
        		<div class="col-xs-8">
        			<p id="nickname" class="form-control"></p>
        		</div>
        	</div>
        	
        	<div class="form-group">
        		<label for="regdate" class="control-label col-xs-4">지출날짜 :</label>
        		<div class="col-xs-8">
        			<input id="regdate" type="date" class="form-control"/>
        		</div>
        	</div>
        	<span id="regdate-error" class="col-xs-8 col-xs-offset-4 error"></span>
        	<div class="form-group">
        		<label for="big_ways" class="control-label col-xs-4">지출방법 :</label>
        		<div class="col-xs-4">
        			<select id="big_ways" class="form-control" onchange="waysChange();">
        			</select>
        		</div>
        		<div class="col-xs-4">
        			<select id="small_ways" class="form-control">
        			</select>
        		</div>
        	</div>
        	
        	<div class="form-group">
        		<label for="big_purpose" class="control-label col-xs-4">지출목적 :</label>
        		<div class="col-xs-4">
        			<select id="big_purpose" class="form-control" onchange="purposeChange();">
        			</select>
        		</div>
        		<div class="col-xs-4">
        			<select id="small_purpose" class="form-control">
        			</select>
        		</div>
        	</div>
        	
        	<div class="form-group">
        		<label for="price" class="control-label col-xs-4">가격 :</label>
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
          <button type="button" class="btn btn-info" onclick="expandModBtn(this.form);">수정 완료</button>
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
	function selectSearch(){
		var year = $("#year").val();
		var month = $("#month").val();
		if(year == '' && month == ''){
			location.href="/expand";
		}else if(year == ''){
			location.href="/expand?month="+month;
		}else if(month == ''){
			location.href="/expand?year="+year;
		}else{
			location.href="/expand?year="+year+"&month="+month;	
		}
	}
		function closeBtn(){
			$("#closeBtn").attr("data-dismiss","modal");
			location.reload();
		}
		function expandModBtn(form){
			if($("#price").val().length == 0){
				$("#price").val(0);
			}
			$.ajax({
				url:"/expand/mod",
				type:"post",
				data:{	  
				  idx:$("#idx").val(),
				  comments:$("#comments").val(),
				  nickname:$("#nickname").text(),
				  big_ways:$("#big_ways").val(),
				  small_ways:$("#small_ways").val(),
				  big_purpose:$("#big_purpose").val(),
				  small_purpose:$("#small_purpose").val(),
				  price:$("#price").val(),
				  memo:$("#memo").val(),
				  regdate:$("#regdate").val()
				},
				success:function(data){
					if(data.success != null ){
						alert("수정이 완료되었습니다.");
						location.reload();
						return;
					}
					if(data.날짜 != null){
						$("#regdate-error").text(data.날짜);	
					}else{
						$("#regdate-error").text("");
					}
					
					if(data.지출내역 != null){
						$("#comments-error").text(data.지출내역);	
					}else{
						$("#comments-error").text("");	
					}
					
					if(data.메모 != null){
						$("#memo-error").text(data.메모);	
					}else{
						$("#memo-error").text("");
					}
					
					if(data.가격 != null){
						$("#price-error").text(data.가격);	
					}else{
						$("#price-error").text("");
					}
					
				}
			});
		}	
		function modView(idx){
			$.ajax({
				url:"/expand/mod",
				type:"get",
				data:{idx:idx},
				success:function(data){
					$("#idx").val(data.expandView.idx);
					$("#big_ways").empty();
					$("#small_ways").empty();
					$("#big_purpose").empty();
					$("#small_purpose").empty();
					
					$("#comments").val(data.expandView.comments);
					$("#nickname").text(data.expandView.nickname);
					$("#regdate").val(data.expandView.regdate);
					
					$("#big_ways").append("<option val='"+data.expandView.big_ways+"'>"+data.expandView.big_ways+"</option>");
					$("#small_ways").append("<option val='"+data.expandView.small_ways+"'>"+data.expandView.small_ways+"</option>");
					for(var bways of data.bigWaysList){
						if(bways.big_name != data.expandView.big_ways){
							var $option = $("<option>").val(bways.big_name).text(bways.big_name);
							$("#big_ways").append($option);	
						}						
					}
					for(var sways of data.smallWaysList){
						if(sways.small_name != data.expandView.small_ways){
							var $option = $("<option>").val(sways.small_name).text(sways.small_name);
							$("#small_ways").append($option);	
						}						
					}
					
					$("#big_purpose").append("<option val='"+data.expandView.big_purpose+"'>"+data.expandView.big_purpose+"</option>");
					$("#small_purpose").append("<option val='"+data.expandView.small_purpose+"'>"+data.expandView.small_purpose+"</option>");
					for(var bpurpose of data.bigPurposeList){
						if(bpurpose.big_name != data.expandView.big_purpose){
							var $option = $("<option>").val(bpurpose.big_name).text(bpurpose.big_name);
							$("#big_purpose").append($option);	
						}						
					}
					for(var spurpose of data.smallPurposeList){
						if(spurpose.small_name != data.expandView.small_purpose){
							var $option = $("<option>").val(spurpose.small_name).text(spurpose.small_name);
							$("#small_purpose").append($option);	
						}						
					}
					
					$("#price").val(data.expandView.price);
					$("#memo").text(data.expandView.memo);
					
				}
			});
		}
		
		function expandDel(idx){
			var check = confirm("정말로 삭제하시겠습니까?");
			if(!check){
				return;
			}
			$.ajax({
				url:"/expand/del",
				type:"post",
				data:{idx:idx},
				success:function(data){
					if(data == 'y'){
						location.reload();
					}else{
						alert("서버에 문제 발생");
					}
				}
			});
		}
		function waysChange(){
			var big_name = $("#big_ways").val(); //select태그 안에 있는 선택되어진 option태그			
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
		
		function onlyMyNumber(event) {			
			var keyValue = event.keyCode; 
			if( ((keyValue >= 48) && (keyValue <= 57)) ) 
				return true; 
			else 
				return false; 
			}
	</script>
</body>
</html>