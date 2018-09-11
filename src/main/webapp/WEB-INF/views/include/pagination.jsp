<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function goPage(page){
		$("#page").val(page);
		$(".content form").submit();
	};
</script>
<input type="hidden" name="search" value="true" />
<input type="hidden" id="page" name="page" value="${pagination.currentPage}" />
<div class="pagination">
	<a href="#" onclick="goPage('1')"><img src="/resources/images/etc/arrow_ll.gif" alt=""></a>
	<a href="#" onclick="goPage('${pagination.jumpPrevPage}')"><img src="/resources/images/etc/arrow_lb.gif" alt=""></a>
	
	<c:forEach begin="${pagination.pageBegin }" end="${ pagination.pageEnd }" step="1" var="pageNumber">
			<c:choose>
				<c:when test="${pagination.currentPage == pageNumber }">
					<a class="active nm" href="javascript:void(0);">${pageNumber }</a>
				</c:when>
				<c:otherwise>
					<a class="nm" href="#" onclick="goPage('${pageNumber }')">${pageNumber }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	<a href="#" onclick="goPage('${pagination.jumpNextPage }')"><img src="/resources/images/etc/arrow_rb.gif" alt=""></a>
	<a href="#" onclick="goPage('${pagination.lastPage }')"><img src="/resources/images/etc/arrow_rr.gif" alt=""></a>
</div>