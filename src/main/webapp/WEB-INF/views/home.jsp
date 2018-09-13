<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- ì£¼ìì¶ê° -->
<P>  The time on the server is ${serverTime}. </P>

<form action="signout" method="post">
<input type="button" onclick="submit();" value="로그아웃"> 
</form>
</body>
</html>
