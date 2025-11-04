<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		* {
			margin: 0;
			padding: 0;
			box-sizing: border-box;
		}
		
		body {
			font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Apple SD Gothic Neo', 'Malgun Gothic', sans-serif;
			background-color: #f5f5f5;
			min-height: 100vh;
			display: flex;
			align-items: center;
			justify-content: center;
			padding: 20px;
		}
		
		.signup-wrapper {
			width: 100%;
			max-width: 600px;
		}
		
		.signup-title {
			font-size: 28px;
			font-weight: 600;
			margin-bottom: 25px;
			color: #333;
		}
		
		.signup-box {
			background-color: white;
			border: 1px solid #e0e0e0;
			border-radius: 8px;
			padding: 40px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			margin-bottom: 20px;
		}
		
		.form-row {
			display: flex;
			align-items: center;
			margin-bottom: 20px;
		}
		
		.form-row:last-child {
			margin-bottom: 0;
		}
		
		.form-label {
			width: 120px;
			font-weight: 500;
			color: #333;
			font-size: 14px;
			flex-shrink: 0;
		}
		
		.form-input-wrapper {
			flex: 1;
			display: flex;
			gap: 10px;
		}
		
		.form-input {
			width: 100%;
			padding: 10px 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
		}
		
		.form-input:focus {
			outline: none;
			border-color: #999;
		}
		
		.form-input::placeholder {
			color: #999;
			font-size: 13px;
		}
		
		.id-check-btn {
			padding: 10px 20px;
			background-color: #000;
			color: white;
			border: none;
			border-radius: 4px;
			font-size: 13px;
			font-weight: 600;
			cursor: pointer;
			white-space: nowrap;
		}
		
		.id-check-btn:hover {
			background-color: #333;
		}
		
		.submit-btn {
			width: 100%;
			padding: 16px;
			background-color: #000;
			color: white;
			border: none;
			border-radius: 8px;
			font-size: 16px;
			font-weight: 600;
			cursor: pointer;
		}
		
		.submit-btn:hover {
			background-color: #333;
		}
		
		@media (max-width: 576px) {
			.signup-box {
				padding: 30px 20px;
			}
			
			.form-row {
				flex-direction: column;
				align-items: flex-start;
			}
			
			.form-label {
				width: 100%;
				margin-bottom: 8px;
			}
			
			.form-input-wrapper {
				width: 100%;
			}
		}
	</style>
</head>
<body>
	<div class="signup-wrapper">
		<h1 class="signup-title">회원가입</h1>
		
		<form id="signupForm" method="post" action="${pageContext.request.contextPath}/public/addCustomer">
			<div class="signup-box">
				<!-- 아이디 -->
				<div class="form-row">
					<label class="form-label">아이디</label>
					<div class="form-input-wrapper">
						<input type="text" class="form-input" id="id" name="id" placeholder="아이디">
						<button type="button" class="id-check-btn" id="idCheckBtn">아이디확인</button>
						<input type="checkbox" id="idCheck" style="display: none;">
					</div>
				</div>
				
				<!-- 비밀번호 -->
				<div class="form-row">
					<label class="form-label">비밀번호</label>
					<div class="form-input-wrapper">
						<input type="password" class="form-input" id="pw" name="pw" placeholder="비밀번호">
					</div>
				</div>
				
				<!-- 비밀번호 확인 -->
				<div class="form-row">
					<label class="form-label">비밀번호 확인</label>
					<div class="form-input-wrapper">
						<input type="password" class="form-input" id="passwordConfirm" name="passwordConfirm" placeholder="비밀번호 확인">
					</div>
				</div>
				
				<!-- 이름 -->
				<div class="form-row">
					<label class="form-label">이름</label>
					<div class="form-input-wrapper">
						<input type="text" class="form-input" id="name" name="name" placeholder="2자리 이상">
					</div>
				</div>
				
				<!-- 휴대폰 번호 -->
				<div class="form-row">
					<label class="form-label">휴대폰 번호</label>
					<div class="form-input-wrapper">
						<input type="text" class="form-input" id="phone" name="phone" placeholder="010 - 0000 - 0000">
					</div>
				</div>
			</div>
			
			<button type="button" class="submit-btn" id="addBtn">회원가입</button>
		</form>
	</div>
</body>
<script>
	$('#idCheckBtn').on('click', () => {
		if($('#id').val() == null || $('#id').val() == "") {
			alert('아이디를 입력해주세요.');
			return;
		}
		
		let idPattern = /^[a-z0-9]+$/;
		if(!idPattern.test($('#id').val())){
			alert('아이디는 영어소문자와 숫자로만 입력해주세요');
			return;
		}
		
		$.ajax({
			url: '/shop/restapi/searchId',
			type: 'post',
			data: {searchId : $('#id').val()},
			success: function(response) {
				console.log('성공');
				if(response == '0') {
					alert('사용 가능한 아이디입니다.');
					$('#idCheck').prop("checked", true);
				} else {
					alert('이미 사용중인 아이디입니다.');
					$('#id').focus();
				}
			},
			error: function(xhr, status, error) {  // fail -> error로 변경
				console.log('실패:', error);
				alert('비동기 요청 실패');
			}
		});

	});
	$('#id').keydown(() => {
		$('#idCheck').prop("checked", false);
	});
	$('#addBtn').click(() => {
		if(!$('#idCheck').is(":checked")) {
			alert('아이디를 확인해주세요');
			return;
		}
		
		if($('#pw').val() == "") {
			alert('비밀번호를 입력해주세요');
			return;
		}
		
		let pwPattern = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,15}$/;
		if(!pwPattern.test($('#pw').val())){
			alert('영어, 숫자조합으로만 이루어진 4자 이상의 비밀번호여야 합니다.');
			return;
		}
		
		if($('#pw').val() != $('#passwordConfirm').val()) {
			alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
			return;
		}
		
		if($('#name').val().length < 2) {
			alert('이름은 2자 이상으로 해주세요');
			return;
		}
		
		let phonePattern = /^0\d{1,2}-\d{3,4}-\d{4}$/;
		if(!phonePattern.test($('#phone').val())){
			alert('휴대폰번호가 적절하지 않습니다.');
			return;
		}
		
		$('#signupForm').submit();
	});
</script>
</html>