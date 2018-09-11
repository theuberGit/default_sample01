<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="No-Cache">
<jsp:useBean id="toDay" class="java.util.Date" />
<link rel="stylesheet" href="/resources/css/common.css">
<link rel="shortcut icon" type="image/x-icon" href="/resources/images/common/favicon_16.ico">
<link rel="shortcut icon" type="image/x-icon" href="/resources/images/common/favicon_32.ico">
<sp:eval expression="@applicationProperties['daou.url.http.admin']" var="httpAdminUrl"/>