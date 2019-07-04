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
	
	// requestDataResponseBody 클릭시 이벤트 핸들러
	$("#requestDataResponseBody").on("click", function(){
		
		$.ajax({
			url : "/ajax/requestDataResponseBody",
			method : "post",
			success : function(data){
				// data : {page : 5, pageSize : 10} 가져온 데이터는 이런 형태. 아래와 같은 기존 형태가 아니다.
				// data.pageVo : {pageVo : {page : 5, pageSize : 10}}
				$("#pageResponseBody").text(data.page);
				$("#pageSizeResponseBody").text(data.pageSize);
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
	
	// 전송할 json 객체를 준비
	/*
	public class UserVo(){
		private String userId;
		private String pass;
		public String userId(){....};
	}
	UserVo user = new UserVo();
	user.setUserId("brown");
	user.setPass("brown1234");				이 과정을 js로 바꾸면 아래와 같다
	*/
	
	//var user = {}; // 새 객체 만들기.			{} : js에서 객체를 나타내는 리터럴
	var user = {userId : "brown", pass : "brown1234"};
	//JSON.stringify() : js객체를 json문자열로 생성
	//JSON.parse("json문자열") : json문자열을 js객체로 변경(jQuery라이브러리에서 자동변환되므로 가끔 쓰인다)
	$("#userFormString").text("userId=brown&pass=brown1234");
	$("#userJsonString").text(JSON.stringify(user));
	
	//@ResponseBody 데이터 전송
	$("#userJsonStringBtn").on("click", function(){
		$.ajax({
			url : "/ajax/requestBody",
			method : "post", // get방식일 때는 body가 없으므로 post
			contentType : "application/json", // ajax를 통해 보내는 데이터 형식이 json임을 알려준다
			//dataType : "xml", // 설정하지 않으면 jQuery가 알아서 판단한다
			dataType : "json", // server측으로부터 받고자 하는 데이터타입 (accpet 헤더)
			data : JSON.stringify(user),
			success : function(data){
				console.log(data);
				
				//xml		손이 많이 간다
				//$("#userJsonResult .userId").text(data.getElementsByTagName("userId")[0].childNodes[0].textContent);
				//$("#userJsonResult .pass").text(data.getElementsByTagName("pass")[0].childNodes[0].textContent);
				
				//json
				$("#userJsonResult .userId").text(data.userId);
				$("#userJsonResult .pass").text(data.pass);
			}
		})
	})
	
});
</script>
<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터가져오기</a><br>
page : <span id="page"></span> <br>
pageSize : <span id="pageSize"></span>

<h2>ajax json 데이터 요청(@ResponseBody)</h2>
<a id="requestDataResponseBody">데이터가져오기</a><br>
page : <span id="pageResponseBody"></span> <br>
pageSize : <span id="pageSizeResponseBody"></span>

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

<hr>

<h2>ajax json 데이터 보내기</h2>
<a id="userJsonStringBtn">데이터 보내기</a><br>
요청 보내는 데이터(기존): <div id="userFormString"></div>
요청 보내는 데이터 : <div id="userJsonString"></div>
받는 데이터 : 
<div id="userJsonResult">
	userId : <span class="userId"></span><br>
	pass : <span class="pass"></span>
</div>



