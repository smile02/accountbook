<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var check = confirm('${msg}');
	if(check){
		location.href="${url}";	
	}
</script>