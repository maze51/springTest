<%@page import="kr.or.ddit.prod.model.ProdVo"%>
<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<title>PROD페이징리스트</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
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
						<h2 class="sub-header">PROD</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>prod_id</th>
									<th>prod_name</th>
									<th>prod_lgu</th>
									<th>prod_buyer</th>
									<th>prod_cost</th>
									<th>prod_price</th>
									<th>prod_sale</th>
									<th>prod_outline</th>
								</tr>
								
								<%
									List<ProdVo> prodList = (List<ProdVo>)request.getAttribute("prodList"); // controller에서 가져온 userList
								%>
								
								<% for(int i=0;i<prodList.size();i++){%>
								<tr>
									<td><%=prodList.get(i).getProd_id() %></td>
									<td><%=prodList.get(i).getProd_name() %></td>
									<td><%=prodList.get(i).getProd_lgu() %></td>
									<td><%=prodList.get(i).getProd_buyer() %></td>
									<td><%=prodList.get(i).getProd_cost() %></td>
									<td><%=prodList.get(i).getProd_price() %></td>
									<td><%=prodList.get(i).getProd_sale() %></td>
									<td><%=prodList.get(i).getProd_outline() %></td>
								</tr>
								<%} %>
								
								
							</table>
						</div>
				
						<a class="btn btn-default pull-right">새 항목 등록</a>
					
						<div class="text-center">
							<ul class="pagination">
								
								<%PageVo pageVo = (PageVo)request.getAttribute("pageVo");%>
								
								<%if(pageVo.getPage() == 1){ %>
									<li class="disabled"><span>«</span></li>
								<%} else {%>
									<li><a href="${cp}/prod/pagingList?page=<%=pageVo.getPage()-1 %>&pageSize=<%=pageVo.getPageSize()%>">«</a></li>
								<%} %>
								
								<%	
									// 가져온 paginationSize를 활용해 화면에 나오는 page값을 고쳐준다 
									// + /userPagingList?page=1&pageSize=10 반영하기
									int paginationSize = (Integer)request.getAttribute("paginationSize");
									for(int i=1;i<=paginationSize;i++){ %>
										
										<%
										// 내가 현재 몇 번째 페이지에 있는가? pageVo에 저장되어 있다. 그걸 찾아서 li에 class="active"를 지정한다
										if(pageVo.getPage() == i){ %>
											<li class="active">
												<span><%=i %></span>
											</li>
											<%} else {%>
											<li>
												<a href="${cp}/prod/pagingList?page=<%=i %>&pageSize=<%=pageVo.getPageSize()%>"><%=i %></a>
											</li>
											<%} %>
									<%}%>
									<%if(pageVo.getPage() == paginationSize){ %>
										<li class="disabled"><span>»</span></li>
									<%} else {%>
										<li><a href="${cp}/prod/pagingList?page=<%=pageVo.getPage()+1 %>&pageSize=<%=pageVo.getPageSize()%>">»</a></li>
									<%} %>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
