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
			<h2 class="text-muted text-center">음 악 관 리</h2>
		</div><br />		
		
		<div class="row">
			<div class="panel" style="border:1px solid black">
				 <div class="panel-heading">노래목록</div>
    			 <div class="panel-body">
    			 	<table>
    			 			<tr>
    			 				<th>순번</th>
    			 				<th class="text-center">제목</th>
    			 				<th>비고</th>
    			 			</tr>
    			 			
    			 		<c:forEach var="music" items="${musicList }">
    			 			<tr>
 								<td>${music.rnum }</td>
 								<td>${music.music}</td>
 								<td><button type="button" class="btn btn-default">삭제</button></td>   			 		
    			 			</tr>
    			 		</c:forEach>
    			 	</table>
    			 </div>
			</div>
			
			<div class="row">
				<div class="col-sm-12 text-center">
						<div class="paging">
							<ul class="pagination pagination-sm">
								${paging }
							</ul>
						</div>
					</div>
				</div>
			
			<div class="panel">
				<form action="/file/music" method="post" enctype="multipart/form-data">
					<div class="panel-heading">노래등록 </div>
					<div class="panel-body">
						<div class="button-group">
							<input type="file" id="music_file" name="music_file"
								class="custom-file-input" onchange="fileCheck(this);"
								accept="audio/*"
								aria-describedby="music_file" style="display:inline-block;"/>
							<button id="upload" class="btn btn-primary">업로드</button>
						</div>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	
	<!--스크립트 라이브러리 -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
	
	$(function(){
		$("#upload").attr("disabled",true);
	});
	
	
	 function fileCheck(file){
			var point = file.value.lastIndexOf("."); //뒤에있는 .의 위치
			var extension = file.value.substring(point+1, file.value.length); //.다음부터 끝까지의 확장자
			
			if(extension != "mp3" && extension != "MP3"
				&& extension != "wav" && extension != "WAV"){
				alert("오디오 이외의 파일은 업로드 할 수 없습니다.");
				
				var parent = file.parentNode;			
				var next = file.nextSibling;
				var tmp = document.createElement("form");
				//tmp에 appendChild하고 reset한 다음 parent에 insertBefore
				tmp.appendChild(file);
				tmp.reset();
				parent.insertBefore(file, next);
				
				return;
			}
			$("#upload").removeAttr("disabled");
			
		}
	</script>
</body>
</html>