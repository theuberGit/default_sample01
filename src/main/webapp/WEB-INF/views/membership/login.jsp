<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="../include/top.jsp" %> --%>
<meta property="og:title" content="">
<meta property="og:description" content="">
<meta name="description" content="">
<meta name="keywords " content="">
<link rel="stylesheet" href="/resources/css/sub.css">
<title>DAOU IDC</title>
</head>

<body> 
<!-- wrap -->
<div id="wrap" class="membership login">
	<!-- gnb -->
<%-- 	<%@ include file="../include/header.jsp" %> --%>
	<!-- //gnb -->
	<div id="container">
		<div class="visual_area">
			<div class="tit_area">
				<p class="page_location">
					<a href="/index.do">홈</a>
					<span>로그인</span>
				</p>
				<h2>로그인</h2>
			</div>
			<video autoplay loop id="video" class="video" poster="/resources/images/mypage/mypage_list_visual.jpg">
				<source src="/resources/file/mypage.mp4" type="video/mp4" />
			</video>
		</div>
<%-- 		<%@ include file="../include/quick_menu.jsp" %> --%>
		<div class="content">
			<div class="membership_cont">
				<div class="sec1">
					<h3>로그인</h3>
				</div>
				<form id="loginForm" action="/j_spring_security_check.do" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="sec2 last">
						<input type="text" id="username" name="username" class="w468" title="아이디" maxlength="20" placeholder="아이디를 입력해 주세요.">
						<input type="password" id="password" name="password" class="w468" title="비밀번호" maxlength="20" placeholder="비밀번호를 입력해 주세요.">
						<div class="security">
							<div class="left">
								<div id="captcha"><img alt="보안문자"/></div>
							</div>
							<div class="right">
								<p>프로그램을 이용한 자동입력 방지를 위해 보안절차를 거치고 있습니다.<br>왼쪽에 글자를 보이는 대로 입력해주세요.</p>
								<button type="button" class="refresh_btn" id="refreshBtn">새로고침</button>
							</div>
						</div>
						<input type="text" id="captchaInput" name="captchaInput" class="w468 mgb" title="자동입력방지 문자" placeholder="자동입력방지 문자를 입력해 주세요.">
						<div class="btn_area">
							<a href="javascript:void(0);" onclick="login();" class="commonBtn4 type2">로그인</a>
						</div>
						<ul class="join_list">
							<li><a href="/membership/find-id.do">아이디찾기</a></li>
							<li><a href="/membership/find-password.do">비밀번호 찾기</a></li>
							<li><a href="/membership/join/type-selection.do">회원가입</a></li>
						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- footer -->
<%-- 	<%@ include file="../include/footer.jsp" %> --%>
	<!-- //footer -->
</div>
<!-- //wrap -->
<%-- <%@ include file="../include/js.jsp" %> --%>
<script src="/resources/js/sub.js"></script>
</body>
<script>
create();
$("#refreshBtn").click(function(e){
    e.preventDefault();
    create();
});
$("#confirmBtn").click(function(e){
    e.preventDefault();
    $("#frm").submit();
});
function create(){
    $("#captcha img").attr("src", "/captcha?"+Math.random());
}
function login(){
	//if(!$('#username').required()) return false;
	//if(!$('#password').required()) return false;
	//if(!$('#captchaInput').required()) return false;
	
	$('#loginForm').submit();	
}
$('#password').keyup(function(e){
	if(e.keyCode == 13){
		$('#password').focus();
	}
});
$('#captchaInput').keyup(function(e){
	if(e.keyCode == 13){
		login();
	}
});
</script>
</html>