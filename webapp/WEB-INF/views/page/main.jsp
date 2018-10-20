<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, user-scalable=no">
<title>환영 환영~</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
	.error{
		color:red;
	}
	
	
	@media all and (min-width:100px){
		.container{
			width:40%;
		}
		.row{
			width:80%;
		}
		table > caption{
			width:180%;
		}
	}
	
	@media all and (min-width:220px){
		.container{
			width:100%;
		}
		.row{
			width:100%;
		}
	}
	
	
	@media all and (min-width:768px){
		.container .row{
			width:30%;
		}
		table > caption{
			width:100%;
		}
	}
	
	
	@media all and (min-width:1024px){
		.container .row{
			width:100%;
		}
	}
	
	
</style>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="container">
		<div class="row">
			${calendar }
		</div>			
	</div>
	<!-- Modal -->
  <div class="modal fade" id="expandModal" role="dialog">
    <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 id="ex_curDate" class="modal-title">지출목록</h4>
        </div>
        <div class="modal-body">
       		<div id="ex_textbox" class="well">
       		</div>
        </div>        
        <div class="modal-footer">
          <button type="button" class="btn btn-info" onclick="expandList();">지출목록으로</button>
          <button id="closeBtn" type="button" class="btn btn-default" onclick="closeBtn();">닫기</button>          
        </div>
      </div>      
      
    </div>
  </div>
  
  <div class="modal fade" id="incomeModal" role="dialog">
    <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 id="in_curDate" class="modal-title">수입목록</h4>
        </div>
        <div class="modal-body">
       		<div id="in_textbox" class="well">
       		</div>
        </div>        
        <div class="modal-footer">
          <button type="button" class="btn btn-info" onclick="incomeList();">수입목록으로</button>
          <button id="closeBtn" type="button" class="btn btn-default" onclick="closeBtn();">닫기</button>          
        </div>
      </div>      
      
    </div>
  </div>
  
  <div class="modal fade" id="changeModal" role="dialog">
    <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title">년, 월입력</h4>
        </div>
        <div class="modal-body">
        	<form class="form-horizontal" method="get">
	        	<div class="row">
	        		<div class="form-group">
	        			<label for="yearInput" class="control-label col-xs-2">년</label>
	        			<div class="col-xs-4">
	        				<input id="yearInput" type="text" class="form-control" />
	        			</div>
	        		</div>
	        		<div class="form-group">
	        			<label for="monthInput" class="control-label col-xs-2">월</label>
	        			<div class="col-xs-4">
	        				<input id="monthInput" type="text" class="form-control" />
	        			</div>
	        		</div>
	        	</div>
        	</form>
        </div>        
        <div class="modal-footer">
          <button type="button" class="btn btn-info" onclick="regChange();">적용</button>
          <button id="closeBtn" type="button" class="btn btn-default" onclick="closeBtn();">닫기</button>          
        </div>
      </div>      
      
    </div>
  </div>
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
	function regChange(){
		var year = $("#yearInput").val();
		var month = $("#monthInput").val();
		if(month < 1){
			month = 1;
		}else if(month > 12){
			
		}
		
		location.href="/?year="+year+"&month="+month;		
	}
	$("#today").on("click",function(event){
		location.href="/";
		
	});
	$("#leftBtn").on("click",function(event){
		var month = $("#sendMonth").text();
		var year = $("#sendYear").text();
		console.log(month);
		month--;
		if(month <1){
			month = 12;
		}else if(month >12){
			month = 1;
		}
		console.log(month);
		$("#sendMonth").text(month);
		location.href="/?year="+year+"&month="+month;
		
	});
	
	$("#rightBtn").on("click",function(event){
		var month = $("#sendMonth").text();
		var year = $("#sendYear").text();
		console.log(month);
		month++;
		if(month <1){
			month = 12;
		}else if(month >12){
			month = 1;
		}
		console.log(month);
		$("#sendMonth").text(month);
		location.href="/?year="+year+"&month="+month;
		
	});
	
	$("#topBtn").on("click",function(event){
		var year = $("#sendYear").text();
		var month = $("#sendMonth").text();
		console.log(year);
		year++;		
		$("#sendYear").text(year);
		location.href="/?year="+year+"&month="+month;
		
	});
	
	$("#bottomBtn").on("click",function(event){
		var year = $("#sendYear").text();
		var month = $("#sendMonth").text();
		console.log(year);
		year--;		
		$("#sendYear").text(year);
		location.href="/?year="+year+"&month="+month;
		
	});
	
	var reg="";
		function expandList(){
			if(reg[1].length < 2){
				reg[1] = "0"+reg[1];
			}
			location.href="/expand?year="+reg[0]+"&month="+reg[1]+"&day="+reg[2];
		}
		
		function incomeList(){
			if(reg[1].length < 2){
				reg[1] = "0"+reg[1];
			}
			location.href="/income?year="+reg[0]+"&month="+reg[1]+"&day="+reg[2];
		}
		
		function expandBtn(btn){
			var regId = btn.id;
			var nickname = "${sessionScope.loginMember.nickname}";
			reg = regId.split("-");
			if(reg[1].length < 2){
				reg[1] = "0"+reg[1];
			}
			if(reg[2].length < 2){
				reg[2] = "0"+reg[2];
			}
			var regdate = reg[0]+"-"+reg[1]+"-"+reg[2];
			console.log(regdate);
			console.log(nickname);
			$.ajax({
				url:"/expand/selectExpand",
				type:"post",
				data:{nickname:nickname,
					  regdate:regdate},
				success:function(data){
					var cnt = 0;
					$("#ex_curDate").append("&nbsp;&nbsp;&nbsp;"+data[0].regdate+"일");
					for(expand of data){
						cnt++;
						var row = $("<div class='row'>");
						var p = $("<p class='control-label'>");
					 	var comments = $("<span id='ex_comments"+cnt+"' class='col-xs-5 control-label'>").text("제목:"+expand.comments);
					 	var com = comma(expand.price);
					 	var price = $("<span id='ex_price"+cnt+"' class='col-xs-3 control-label'>").text("가격:"+com);
						var purpose = $("<span id='ex_purpose"+cnt+"' class='col-xs-4 control-label'>").text("목적:"+expand.big_purpose+"-"+expand.small_purpose+"\n");
						p.append(comments);
						p.append(price);
						p.append(purpose);
						row.append(p);
						$("#ex_textbox").append(row);
					}
				}
			});
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
		
		function incomeBtn(btn){
			var regId = btn.id;
			var nickname = "${sessionScope.loginMember.nickname}";
			reg = regId.split("-");
			if(reg[1].length < 2){
				reg[1] = "0"+reg[1];
			}
			if(reg[2].length < 2){
				reg[2] = "0"+reg[2];
			}
			var regdate = reg[0]+"-"+reg[1]+"-"+reg[2];
			console.log(regdate);
			console.log(nickname);
			$.ajax({
				url:"/income/selectIncome",
				type:"post",
				data:{nickname:nickname,
					  regdate:regdate},
				success:function(data){
					console.log(data);
					var cnt = 0;
					$("#in_curDate").append("&nbsp;&nbsp;&nbsp;"+data[0].regdate+"일");
					for(income of data){
						cnt++;
						var row = $("<div class='row'>");
						var p = $("<p class='control-label'>");
					 	var comments = $("<span id='in_comments"+cnt+"' class='col-xs-5 control-label'>").text("제목:"+income.comments);
					 	var com = comma(income.price);
						var price = $("<span id='in_price"+cnt+"' class='col-xs-3 control-label'>").text("가격:"+com);
						var ways = $("<span id='in_ways"+cnt+"' class='col-xs-4 control-label'>").text("방법:"+income.ways+"\n");
						p.append(comments);
						p.append(price);
						p.append(ways);
						row.append(p);
						$("#in_textbox").append(row);
					}
				}
			});
		}
		
		function closeBtn(){
			$("#closeBtn").attr("data-dismiss","modal");
			location.reload();
		}
		
		$("#diffPrice").on("click",function(){
			var p
			if(!confirm("잔액을 이월하시겠습니까?")){
				return;
			}else{
				$.ajax({
					url:"/next",
					type:"post",
					data:{year:$("#sendYear").text(),
						  month:$("#sendMonth").text(),
						  price:$("#b_Price").text()},
					success:function(data){
						alert("이월 완료");
						location.href="/?year="+$("#sendYear").text()+"&month="+$("#sendMonth").text();
					}
				});
				
			//	alert("네 이월하겠습니다.");
				//지출목록에 현재 잔액으로 지출 추가 내용은 이월하는 거로 작성 (insert)
				//수입목록에 현재 잔액으로 수입 추가 내용은 이월한거 작성 (insert)
			}
		});
	</script>
</body>
</html>