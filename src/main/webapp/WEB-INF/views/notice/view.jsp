<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/sub.css">
<title>Spring Notice</title>
</head>
<body>
<div class="list-view">
	<div id="container">
		<div class="content">
			<div class="sec1">
				<table class="table_list_type blue">
					<colgroup>
						<col style="width:auto%">
						<col style="width:20%">
					</colgroup>
					<tbody>
						<tr class="notice">
							<td><a href="#">${item.title }</a></td>
							<td><a href="#">작성일 <span class="date"><fmt:formatDate pattern="yyyy.MM.dd" value="${item.distributeDate }"/> </span></a></td>
						</tr>
						<!-- view 영역 -->
						<tr class="cont">
							<td colspan="3">
								<p class="down_area">
									<span>첨부파일 다운로드</span>
								</p>
								<div class="text_box">${cfn:XSSReplaceEscape(fn:replace(item.content, httpAdminUrl, ''))}</div>
							</td>
						</tr>
						<!-- //view 영역 -->
						<c:if test="${!empty next }">
						<tr>
							<td><a href="news-view.do?idx=${next.idx }">${next.categoryStr }</a></td>
							<td><a href="news-view.do?idx=${next.idx }">${next.title }</a></td>
							<td><a href="news-view.do?idx=${next.idx }" class="view_next">다음</a></td>
						</tr>
						</c:if>
						<c:if test="${empty next }">
						<tr>
							<td></td>
							<td>다음글이 없습니다.</td>
							<td><a href="javascript:void(0);" class="view_next">다음</a></td>
						</tr>
						</c:if>
						<c:if test="${!empty prev }">
						<tr>
							<td><a href="news-view.do?idx=${prev.idx }">${prev.categoryStr }</a></td>
							<td><a href="news-view.do?idx=${prev.idx }">${prev.title }</a></td>
							<td><a href="news-view.do?idx=${prev.idx }"class="view_prev">이전</a></td>
						</tr>
						</c:if>
						<c:if test="${empty prev }">
						<tr>
							<td></td>
							<td>이전글이 없습니다.</td>
							<td><a href="javascript:void(0);" class="view_prev">이전</a></td>
						</tr>
						</c:if>
					</tbody>
				</table>
				<div class="btn_area">
					<a href="/notice/list${sessionScope.searchString }" class="commonBtn2 type3">목록</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>