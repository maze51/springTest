<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.userTr:hover {
	cursor: pointer;
}
</style>

<script>
	$(document).ready(function() {
		// 사용자 tr 태그 이벤트 등록
		$("#userListTbody").on("click", ".userTr", function() { 
			console.log("userTr click");
			var userId = $(this).find(".userId").text();
			$("#userId").val(userId);
			$("#frm").submit();

		});
		
		// 첫번째 페이지의 사용자 정보를 요청
		//userPagingListAjax(1, 10); // 처음 요청하는 시점이므로 무조건 1	
		userPagingListAjaxHtml(1,10);
	});
	
	
	// 데이터 응답을 html로 받는 경우
	function userPagingListAjaxHtml(page, pageSize){
		$.ajax({
			url : "/user/pagingListAjaxHtml",
			method : "post",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(data){
				// html
				var html = data.split("SEPERATORSEPERATOR"); // 콜을 최소화하기 위한 방법. 두 번 요청하는 방법도 있다.
				$("#userListTbody").html(html[0]);
				$(".pagination").html(html[1]);
			}
		});
	}
	
	
	// 데이터 응답을 json으로 받는 경우
	function userPagingListAjax(page, pageSize){ // 여기서 ajax call
		$.ajax({
			url : "/user/pagingListAjax",
			method : "post",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(data){
				//console.log(data); 														// 모르면 반드시 찍어보고 처리하기!!!!
				
				var html = "";
				// 사용자 리스트
				data.data.userList.forEach(function(user){// data(데이터변수명).data(속성명).userList(속성 안의 배열).forEach(반복문)
					// @@@ html 생성
					// console.log(user); // 하나씩 꺼내서 확인
					html += "<tr class='userTr' data-userId='" + user.userId + "'>";
					html += " <td class='userId'>" + user.userId + "</td>";
					html += " <td>" + user.name + "</td>";
					html += " <td>" + user.alias + "</td>";
					html += " <td></td>";
					html += "</tr>";
				});
				
				// @@@ pagination 생성
				var pHtml = "";
				var pageVo = data.pageVo; // 많이 쓰이면 별도 변수로 빼면 좋다
				
				if(pageVo.page == 1)
					pHtml += "<li class='disabled'><span>«</span></li>";
				else
					pHtml += "<li><a href='javascript:userPagingListAjax(" + (pageVo.page - 1) + ", " + pageVo.pageSize + ");'>«</a></li>"; // javascript 함수를 이런 식으로 호출할 수 있다
				
				for( var i = 1; i <= data.data.paginationSize; i++){
					if(pageVo.page == i)
						pHtml += "<li class='active'><span>" + i + "</span></li>";
					else
						pHtml += "<li><a href='javascript:userPagingListAjax(" + i + ", " + pageVo.pageSize + ");'>" + i + "</a></li>";	
				}
				
				if(pageVo.size == data.data.paginationSize)
					pHtml += "<li class='disabled'><span>»</span></li>";
				else
					pHtml += "<li><a href='javascript:userPagingListAjax(" + (pageVo.page + 1) + ", " + pageVo.pageSize + ");'>»</a></li>";
				
				
				console.log("html : " , html);
				console.log("phtml : " , pHtml);
				$("#userListTbody").html(html);
				$(".pagination").html(pHtml);
			}
		});
	}
</script>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자 (tiles)</h2>

		<!-- 사용자 상세조회 : userId가 필요하다 -->
		<form id="frm" action="${cp }/user/user" method="get">
			<input type="hidden" id="userId" name="userId" />
		</form>

		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>등록일시</th>
				</tr>
				</thead>
				<tbody id="userListTbody">
				</tbody>
			</table>
		</div>

		<a href="${cp }/user/form" class="btn btn-default pull-right">사용자
			등록</a> <a href="${cp }/user/userListExcel?filename=userList"
			class="btn btn-default pull-right">엑셀다운</a>

		<div class="text-center">
			<ul class="pagination">

				
			</ul>
		</div>
	</div>
</div>
