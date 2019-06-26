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

<title>사용자페이징리스트</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
<style>
	.userTr:hover{
		cursor : pointer;
	}
</style>

<script>
	$(document).ready(function(){
		// 사용자 tr 태그 이벤트 등록
		$(".userTr").on("click", function(){ // .userTr은 사용자리스트의 <tr>
			// 이벤트가 잘 등록되었는지 확인
			console.log("userTr click");	
			//userId를 획득하는 방법
			//$(this).find(".userId").text();
			//$(this).data("userid"); // data-userid="${vo.userId }"로 부여한 값을 찾는다
			
			// 사용자 아이디를 #userId 값으로 설정해주고
			var userId = $(this).find(".userId").text(); // .userId는 ${vo.userId}를 가진 <td>
			$("#userId").val(userId); // #userId는 아이디 전송을 위한 hidden input의 아이디
			
			// #frm을 이용하여 submit();
			$("#frm").submit(); // #frm은 #userId를 포함한 form. action = "${cp}/user"
			
		});
	});
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
						<h2 class="sub-header">사용자</h2>
						
						<!-- 사용자 상세조회 : userId가 필요하다 -->
						<form id="frm" action="${cp }/user/user"
						method="get">
							<input type="hidden" id="userId" name="userId" />
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								
								<!-- 향상된 for문 형태 -->
								<c:forEach items="${userList }" var="vo" varStatus="status">
									<tr class="userTr" data-userid="${vo.userId }" data-name="${vo.name }"> <!-- data-userid부분은 임의의 속성을 추가한 것 -->
<%-- 									<td>${status.index} / ${status.count} / ${vo.userId }</td> --%>
										<td class="userId">${vo.userId }</td>
										<td>${vo.name }</td>
										<td>${vo.alias }</td>
										<td></td>
									</tr>
								</c:forEach>
								
								
							</table>
						</div>
				
						<a href="${cp }/user/form" class="btn btn-default pull-right">사용자 등록</a>
					
						<!-- 
							사용자 수 : 105건
							페이지네이션 : 11건
						 -->
						<div class="text-center">
							<ul class="pagination">
								
<%-- 								<%PageVo pageVo = (PageVo)request.getAttribute("pageVo");%> --%>
<%-- 								<c:set var="pageVo" value="${pageVo.page }" scope="request"/> --%>
								
								<c:choose>
									<c:when test="${pageVo.page == 1 }">
										<li class="disabled"><span>«</span></li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="${cp}/user/pagingList?page=${pageVo.page - 1 }&pageSize=${pageVo.pageSize }">«</a>
										</li>
									</c:otherwise>
								</c:choose>
									
<!-- 									// 가져온 paginationSize를 활용해 화면에 나오는 page값을 고쳐준다  -->
<!-- 									// + /userPagingList?page=1&pageSize=10 반영하기 -->
<%-- 									<%int paginationSize = (Integer)request.getAttribute("paginationSize");%> --%>
<%-- 									for(int i=1;i<=paginationSize;i++){ %> --%>
										
<%-- 										<% --%>
<%-- 										// 내가 현재 몇 번째 페이지에 있는가? pageVo에 저장되어 있다. 그걸 찾아서 li에 class="active"를 지정한다 --%>
<%-- 										if(pageVo.getPage() == i){ %> --%>
<!-- 											<li class="active"> -->
<%-- 												<span><%=i %></span> --%>
<!-- 											</li> -->
<%-- 											<%} else {%> --%>
<!-- 											<li> -->
<%-- 												<a href="${cp}/userPagingList?page=<%=i %>&pageSize=<%=pageVo.getPageSize()%>"><%=i %></a> --%>
<!-- 											</li> -->
<%-- 											<%} %> --%>
<%-- 									<%}%> --%>
									<c:forEach begin="1" end="${paginationSize}" var="i">
										<c:choose>
											<c:when test="${pageVo.page == i }">
												<li class="active">
													<span>${i }</span>
												</li>
											</c:when>
											<c:otherwise>
												<li>
												<a href="${cp}/user/pagingList?page=${i }&pageSize=${pageVo.pageSize }">${i }</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									
									<c:choose>
									<c:when test="${pageVo.page == paginationSize }">
										<li class="disabled"><span>»</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="${cp}/user/pagingList?page=${pageVo.page + 1 }&pageSize=${pageVo.pageSize }">»</a></li>
									</c:otherwise>
									</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
