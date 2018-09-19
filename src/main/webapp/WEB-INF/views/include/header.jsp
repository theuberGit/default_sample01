<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authorize access="isAuthenticated()">
	<%-- <span><sec:authentication property="principal.memberName"/>님</span> --%>
	<span><a href="javascript:void(0);" onclick="logout();">로그아웃</a></span>
</sec:authorize>
<form id="logout-form" action="/signout"></form>
<script type="text/javascript">
function logout(){
	$('#logout-form').submit();
};
</script>