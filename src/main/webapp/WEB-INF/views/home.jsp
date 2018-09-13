<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
<a href="<%= request.getContextPath() %>/j_spring_security_logout">[로그아웃]</a>
</body>
</html>
