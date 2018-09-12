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
<div class="support contact">
	<div id="container">
		<div class="content">
			<form id="frm" action="/support/contactRequest.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="cphone" />
				<input type="hidden" name="email" title="이메일"/>
				<input type="hidden" name="mktYn" title="마케팅수신동의"/>
				<div class="sec section1">
					<!-- <div class="table_tit">
						<h3>상담내용</h3>
					</div> -->
					<table class="table_1">
						<tbody>
							<tr>
								<th scope="row" class="required">제목</th>
								<td><input type="text" placeholder="제목을 입력해 주세요." name="title" id="title" title="제목" class="w600"></td>
							</tr>
							<tr>
								<th class="required"><label for="workContent">내용</label></th>
								<td><textarea name="contents" id="contents" title="내용 " cols="30" rows="10" class="write_box"></textarea></td>
							</tr>
							<tr>
								<th scope="row" >파일첨부</th>
								<td>
									<span class="bs_file"></span>
									<a href="#" id="upload" class="commonBtn2 type3 etc2">찾아보기</a>
									<input type="file" id="upload_file" class="upload_file" name="file" accept=".jpg, .gif, .jpeg, .png, .doc, .docx, .xls, .xlsx, .ppt, .pptx, .hwp, .pdf">
									<p class="txt1">※ jpg,png,ppt,pptx,docx,doc,xlsx,xls,pdf,hwp 형식만 지원 <br> ※ 파일 사이즈 종합 10MB까지 업로드 가능합니다.</p>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_area">
						<a href="#" id="regiCont" class="commonBtn6 type4">등록</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>