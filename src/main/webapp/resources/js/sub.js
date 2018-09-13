var sub = (function($){
	return {
		lnbAction : function(lnbIdx){
			$("#lnb").find("li").eq(lnbIdx).addClass("on").siblings("li").removeClass("on");
		},
		profileLoad : function(){
			$("#profileLoad").change(function(){
				if($(this).is(":checked")){
					var managerName=  $("#memberName").text();
					var managerPhone1=  $("#phoneNum1").text();
					var managerPhone2=  $("#phoneNum2").text();
					var managerPhone3=  $("#phoneNum3").text();
					var email1=  $("#email1").text();
					var email2=  $("#email2").text();
					var managerPhoneOption = $("#userTelM1").find("option");
					var managerPhoneOptionLenght = $("#userTelM1").find("option").length;
					$("#managerName").val(managerName);
					$("#userTelM2").val(managerPhone2);
					$("#userTelM3").val(managerPhone3);
					$("#userEmail1").val(email1);
					$("#userEmail2").val(email2);
					$("#userTelM1").val(managerPhone1);
				}else{
					$("#managerName").val("");
					$("#userTelM2").val("");
					$("#userTelM3").val("");
					$("#userEmail1").val("");
					$("#userEmail2").val("");
					$("#userTelM1").val("010");
				}
			});
		},
		dataPicker : function(){
			$( "#datepicker1" ).datepicker({
			dateFormat: 'yy.mm.dd',
			prevText: '이전 달',
			nextText: '다음 달',
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNames: ['일','월','화','수','목','금','토'],
			dayNamesShort: ['일','월','화','수','목','금','토'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			showMonthAfterYear: true,
			changeMonth: true,
			changeYear: true,
			yearSuffix: '년'
		  });
		}
	}
})(jQuery);
$(function(){
	// 휴대전화 번호 체크 공통함수
	$(".num_check").on("keyup", function(e){
		var regNumber = /^[0-9]*$/;
		if(!regNumber.test($(this).val())) {
			alert("휴대번호를 정확히 기재해주세요");
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
			return;
		}
	});
	
	// input box 숫자만 입력
	$(".num").on("keyup", function(e){
		var regNumber = /^[0-9]*$/;
		if(!regNumber.test($(this).val())) {
			//alert("숫자만 입력가능합니다.");
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
			return;
		}
	});

	////파일 변경 스크립트
	var uploadFile = $(".upload_file");
		uploadFile.on("change", function(){
			if(window.FileReader){
				var filename = $(this)[0].files[0].name;
				} else {
				var filename = $(this).val().split('/').pop().split('\\').pop();
			}
			$(this).siblings(".bs_file").text(filename);
		});
		$("#upload").click(function(e){
			e.preventDefault();             
			$("input:file").click();
			$("input:file").val().toLowerCase();
		});
	
	// 이메일 선택박스 공통함수
	// select box(이메일 선택), prev(주소 입력란)
	$('.Select_mail').change(function () {
		if ($(this).val() != '') {
			$(this).prev().val($(this).val());
			$(this).prev().attr('readonly', true);
		}
		else {
			$(this).prev().val('');
			$(this).prev().attr('readonly', false); 
		}
	});

	$('#userEmail2Select').change(function () {
		if ($(this).val() != '') {
			$('#email_2, #email2, #userEmail2').val($(this).val());
			$('#email_2, #email2, #userEmail2').attr('readonly', true);
		}
		else {
			$('#email_2, #email2, #userEmail2').val('');
			$('#email_2, #email2, #userEmail2').attr('readonly', false);
		}
	});
	var swiper = new Swiper('.swiper-container', {
		slidesPerView: 1,
		loop: true,
		pagination : '.swiper-pagination',
		paginationClickable: true,
		nextButton: '.swiper-button-next',
    	prevButton: '.swiper-button-prev'
    });
	//기술지원 방문요청 요청날짜 최소값 설정
	$('input[name="requestDate"]').datepicker('option', 'minDate', new Date());
	//장비 반입반출 예정일 최소값 설정
	$('input[name="scheduleDate"]').datepicker('option', 'minDate', new Date());
});
//기술지원 신청정보 체크
function isValidationRequiredValue(){
	var maxLength = $('#listArea tr').eq(0).find('input,select').length;
	var result = true;
	$('#listArea tr').each(function(idx, item){
		var valueCount = 0;
		
		$(item).find('input,select').each(function(i, sItem){
			if($(sItem).val() != ''){
				valueCount++;
			}
		});
		if(valueCount > 0 && valueCount < maxLength){
			alert('해당 줄의 값을 전부 입력 바랍니다.');
			$(item).find('input,select').eq(0).focus();
			result = false;
			return false;
		}
	});
	return result;
};
//기술지원 필수값 체크
function isValidationRequiredNoInputIP(text){
	var result = true;
	var valueCount = 0;
	$('#listArea tr').each(function(idx, item){
		if($(item).find('.reqIp').val() != ''){
			valueCount++;
		}
	});
	if(valueCount == 0){
		alert(text+'를 입력하세요.');
		$('#listArea tr').find('.reqIp').eq(0).focus();
		result = false;
	}
	return result;
};
//우편번호 검색
function openzip(){
	var pop = window.open("/api/jusoPopup.do","pop","width=570,height=420, scrollbars=yes, resizable=yes");
};
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	$('#address').val(roadAddrPart1);
	$('#address-detail').val(addrDetail);
	$('#zipcode').val(zipNo);
};
