<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자 수정</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
		
		// 주소찾기 버튼 클릭 이벤트 핸들러
		$("#addrSearchBtn").on("click", function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		        	$("#addr1").val(data.roadAddress);
		        	$("#zipcd").val(data.zonecode);
		        }
		    }).open();
		})
		
		// 사용자 수정 버튼 클릭 이벤트 핸들러
		$("#userUpdateBtn").on("click", function(){
			
			$("#frm").submit();
		})
	})
	
</script>
</head>

<body>
	
	<!-- header.jsp -->
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp" %>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자 수정</h2>
						
						<form id="frm" class="form-horizontal" role="form"
								action="${cp }/user/modify"
								method="post"
								enctype="multipart/form-data">
						
							<div class="form-group">
								<label for="file" class="col-sm-2 control-label">사용자 사진</label>
								<div class="col-sm-10">
									<input type="file" name="profile"/>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">사용자 아이디</label>
								<div class="col-sm-10">
									<input type="hidden" id="userId" name="userId" value="${userVo.userId }"/>
									<label>${userVo.userId }</label>
								</div>
							</div>
							
<!-- 							<div class="form-group"> -->
<!-- 								<label for="pass" class="col-sm-2 control-label">비밀번호</label> -->
<!-- 								<div class="col-sm-10"> -->
<!-- 									<input type="password" class="form-control" id="pass" name="pass" -->
<%-- 										placeholder="비밀번호" value="${userVo.pass }"> --%>
<!-- 								</div> -->
<!-- 							</div> -->
		
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">이름</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="name" name="name"
										placeholder="사용자 이름" value="${userVo.name }">
								</div>
							</div>
							
							<div class="form-group">
								<label for="alias" class="col-sm-2 control-label">별명</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="alias" name="alias"
										placeholder="별명" value="${userVo.alias }">
								</div>
							</div>
							
							<div class="form-group">
								<label for="addr1" class="col-sm-2 control-label">주소</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="addr1" name="addr1"
										placeholder="주소" readonly value="${userVo.addr1 }">
								</div>
								<div class="col-sm-2">
									<button type="button" id="addrSearchBtn" class="btn btn-default pull-right">주소검색</button>
								</div>
							</div>
							
							<div class="form-group">
								<label for="addr2" class="col-sm-2 control-label">상세주소</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="addr2" name="addr2"
										placeholder="상세주소" value="${userVo.addr2 }">
								</div>
							</div>
							
							<div class="form-group">
								<label for="zipcd" class="col-sm-2 control-label">우편번호</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="zipcd" name="zipcd"
										placeholder="우편번호" readonly value="${userVo.zipcd }">
								</div>
							</div>
							
							<div class="form-group">
								<label for="birth" class="col-sm-2 control-label">생일</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="birth" name="birth"
										placeholder="생일" value="${userVo.birth }">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button id="userUpdateBtn" type="button" class="btn btn-default">등록</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
