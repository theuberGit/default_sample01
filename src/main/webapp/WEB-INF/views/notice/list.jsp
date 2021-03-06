<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/js.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/sub.css">
<title>Spring Notice</title>
<style>
.list .btn_area_reg {text-align: right;}
</style>
</head>
<body>
<div class="list">
	<div id="container">
		<div class="content">
			<form method="get">
				<div class="sec1">
					<div class="search_box align_right">
						<select name="searchOption" id="searchOption">
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
						<input type="text" id="searchInput" name="searchInput" class="w200 search_text" title="검색어 입력" placeholder="검색어를 입력해 주세요." value="${news.searchInput }"><input type="text" style="display: none;" />
						<a href="javascript:void(0);" id="btnSearch" class="commonBtn3 type3 etc2" onclick="searchList();">검색</a>
					</div>
					<table class="table_list_type blue">
						<colgroup>
							<col style="width:10%">
							<col style="width:auto%">
							<col style="width:15%">
							<col style="width:20%">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>등록자</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="list" varStatus="count">
								<tr>
									<td><a href="/notice/view.do?idx=${list.idx }">${pagination.totalItemCount - (pagination.currentPage -1) * pagination.itemPerPage - count.count +1}</a></td>
									<td><a href="/notice/view.do?idx=${list.idx }">${list.title }</a></td>
									<td><a href="/notice/view.do?idx=${list.idx }">${list.regId }</a></td>
									<td><a href="/notice/view.do?idx=${list.idx }"><fmt:formatDate pattern="yyyy-MM-dd" value="${list.regDate }"/> </a></td>
								</tr>
							</c:forEach>
							<c:if test="${empty list }">
								<tr>
									<td colspan="4">게시글이 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<%@ include file="../include/pagination.jsp" %>
				</div>
			</form>
			<div class="btn_area_reg">
				<a href="/notice/register" id="regBtn" class="commonBtn3 type6">등록하기</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>