<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>

	$(document).ready(function(){
		
		// selectBox 옵션 설정
		<%-- $("#lang").val("${param.lang}"); --%>
		$("#lang").val("${lang}");
		
		$("#lang").on("change", function(){
			$("select[name=lang]").parent().submit(); // select의 부모 form을 지정해 submit(화면에 다른 form이 있고, 나중에 바뀔 수 있으므로 명확하게 지정)
			//$("#frm").submit(); // 다른 방법
		})
	})
</script>

<form id="frm" action="/locale/view" method="post">
	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="en">English</option>
		<option value="ja">日本語</option>
	</select>
</form>

GREETING : <spring:message code="GREETING"/> <br>
VISITOR : <spring:message code="VISITOR">
			<spring:argument value="brown"/>
		  </spring:message>