<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
$(document).ready(function(){
	console.log("ready");
	
	// requestData 클릭시 이벤트 핸들러
	$("#requestData").on("click", function(){
		$.ajax({
			url : "/ajax/requestData",
			method : "post",
			success : function(data){
				// pageVo.page, pageVo.pageSize 출력하기
				$("#page").text(data.pageVo.page);
				$("#pageSize").text(data.pageVo.pageSize);
			}
		});
	});
	
	// user 클릭시 이벤트 핸들러
	$("#user").on("click", function(){
		$.ajax({
			url : "/ajax/user",
			method : "post",
			data : "userId=" + $("#userId").val(),
			success : function(data){
				/*
					name : <input type="text" id="name" readonly/><br>
					alias : <input type="text" id="alias" readonly/><br>
					birth : <input type="text" id="birth" readonly/>
					
					$("#name").val(data.userVo.name);
					$("#alias").val(data.userVo.alias);
					$("#birth").val(data.userVo.birth);
				*/
				
				// 이 형태는 유지보수성이 낮다 -> 별도 jsp로 빼는 것을 생각
				var html = "";
				html += "name : <input type=\"text\" id=\"name\" readonly value=\"" + data.userVo.name + "\" /><br>";
				html += "alias : <input type=\"text\" id=\"alias\" readonly value=\"" + data.userVo.alias + "\" /><br>";
				html += "birth : <input type=\"text\" id=\"birth\" readonly value=\"" + data.userVo.birth + "\" /><br>";
				
				$("#userJsonInfo").html(html);
			}
		});
	});
	
	$("#userHtml").on("click", function(){
		// userHtml 클래스 이벤트 핸들러
		$.ajax({
			url : "/ajax/userHtml",
			method : "post",
			data : $("#frm").serialize(),
			success : function(data){
				//document.getElementById("userInfo").innerHTML(data); 와 같다
				$("#userInfo").html(data);
			}
		});
	});
});
</script>
<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터가져오기</a><br>
page : <span id="page"></span> <br>
pageSize : <span id="pageSize"></span>

<hr>

<h2>ajax json 데이터 요청(user)</h2>
<a id="user">데이터가져오기</a><br>
userId : <input type="text" id="userId" value="sally"/><br>
<div id="userJsonInfo"></div>

<h2>ajax html 데이터 요청(user)</h2>
<a id="userHtml">데이터가져오기</a><br>
<form id="frm">
	userId : <input type="text" id="userIdHtml" name="userId" value="brown"/><br>
</form>
<div id="userInfo"></div>






