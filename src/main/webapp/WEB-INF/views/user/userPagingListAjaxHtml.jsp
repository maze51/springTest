<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 향상된 for문 형태 -->
<c:forEach items="${data.userList }" var="vo" varStatus="status">
	<tr class="userTr" data-userid="${vo.userId }" data-name="${vo.name }">
		<td class="userId">${vo.userId }</td>
		<td>${vo.name }</td>
		<td>${vo.alias }</td>
		<td></td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR

<c:choose>
	<c:when test="${pageVo.page == 1 }">
		<li class="disabled"><span>«</span></li>
	</c:when>
	<c:otherwise>
		<li><a href="javascript:userPagingListAjaxHtml(${pageVo.page - 1 }, ${pageVo.pageSize });">«</a></li>
	</c:otherwise>
</c:choose>

<c:forEach begin="1" end="${data.paginationSize}" var="i">
	<c:choose>
		<c:when test="${pageVo.page == i }">
			<li class="active"><span>${i }</span></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="javascript:userPagingListAjaxHtml(${i }, ${pageVo.pageSize });">${i }</a>
			</li>
		</c:otherwise>
	</c:choose>
</c:forEach>


<c:choose>
	<c:when test="${pageVo.page == data.paginationSize }">
		<li class="disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li><a href="javascript:userPagingListAjaxHtml(${pageVo.page + 1 }, ${pageVo.pageSize });">»</a></li>
	</c:otherwise>
</c:choose>
