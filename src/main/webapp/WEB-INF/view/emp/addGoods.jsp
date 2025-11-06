<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>shop</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
	<style>
		.main-container {
			max-width: 800px;
			margin: 0 auto;
			padding: 40px 20px;
		}
		
		.page-title {
			font-size: 28px;
			font-weight: 600;
			color: #333;
			margin-bottom: 30px;
		}
		
		.form-card {
			background-color: white;
			border-radius: 8px;
			padding: 40px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
		}
		
		.form-group {
			margin-bottom: 25px;
		}
		
		.form-label {
			display: block;
			font-weight: 500;
			color: #333;
			font-size: 14px;
			margin-bottom: 10px;
		}
		
		.form-label .required {
			color: #dc3545;
			margin-left: 4px;
		}
		
		.form-input {
			width: 100%;
			padding: 12px 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
		}
		
		.form-input:focus {
			outline: none;
			border-color: #999;
		}
		
		.form-input::placeholder {
			color: #aaa;
		}
		
		.file-input-wrapper {
			position: relative;
		}
		
		.file-input {
			width: 100%;
			padding: 12px 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
			cursor: pointer;
		}
		
		.file-input::-webkit-file-upload-button {
			padding: 8px 16px;
			background-color: #f8f9fa;
			border: 1px solid #ddd;
			border-radius: 4px;
			cursor: pointer;
			font-size: 13px;
			font-weight: 500;
			margin-right: 10px;
		}
		
		.file-input::-webkit-file-upload-button:hover {
			background-color: #e9ecef;
		}
		
		.file-note {
			font-size: 12px;
			color: #666;
			margin-top: 6px;
		}
		
		.button-group {
			display: flex;
			gap: 10px;
			justify-content: flex-end;
			margin-top: 30px;
		}
		
		.btn-submit {
			padding: 12px 30px;
			background-color: #000;
			color: white;
			border: none;
			border-radius: 4px;
			font-size: 15px;
			font-weight: 600;
			cursor: pointer;
		}
		
		.btn-submit:hover {
			background-color: #333;
		}
		
		.btn-cancel {
			padding: 12px 30px;
			background-color: white;
			color: #333;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 15px;
			font-weight: 600;
			cursor: pointer;
			text-decoration: none;
			display: inline-block;
		}
		
		.btn-cancel:hover {
			background-color: #f8f9fa;
		}
		
		.preview-area {
			margin-top: 15px;
			display: none;
		}
		
		.preview-img {
			max-width: 200px;
			max-height: 200px;
			border-radius: 4px;
			border: 1px solid #ddd;
		}
	</style>
</head>
<body>
	<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import>
	<div class="main-container">
		<h1 class="page-title">상품 추가</h1>
		
		<div class="form-card">
			<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/emp/addGoods" method="post" id="goodsForm">
				<!-- 상품 이미지 -->
				<div class="form-group">
					<label class="form-label">
						상품 이미지<span class="required">*</span>
					</label>
					<input type="file" class="file-input" name="goodsImg" id="goodsImg" accept="image/png, image/jpg, image/jpeg, image/gif">
					<div class="file-note">PNG, JPG, GIF 파일만 업로드 가능합니다.</div>
					<div class="preview-area" id="previewArea">
						<img id="previewImg" class="preview-img" src="" alt="미리보기">
					</div>
				</div>
				
				<!-- 상품명 -->
				<div class="form-group">
					<label class="form-label" for="goodsName">
						상품명<span class="required">*</span>
					</label>
					<input type="text" class="form-input" name="goodsName" id="goodsName" placeholder="상품명을 입력하세요" required>
				</div>
				
				<!-- 가격 -->
				<div class="form-group">
					<label class="form-label" for="goodsPrice">
						가격<span class="required">*</span>
					</label>
					<input type="number" class="form-input" name="goodsPrice" id="goodsPrice" placeholder="가격을 입력하세요 (숫자만)" min="0" required>
				</div>
				
				<!-- 포인트 적립률 -->
				<div class="form-group">
					<label class="form-label" for="pointRate">
						포인트 적립률<span class="required">*</span>
					</label>
					<input type="number" class="form-input" name="pointRate" id="pointRate" placeholder="포인트 적립률을 입력하세요 (예: 0.05)" step="0.01" min="0" max="1" required>
					<div class="file-note">0~1 사이의 소수점 숫자를 입력하세요. (예: 0.05 = 5%)</div>
				</div>
				
				<!-- 버튼 -->
				<div class="button-group">
					<a href="${pageContext.request.contextPath}/emp/goodsList" class="btn-cancel">취소</a>
					<button type="submit" class="btn-submit">상품 등록</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	// 이미지 미리보기
	$('#goodsImg').on('change', function(e) {
		const file = e.target.files[0];
		if(file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				$('#previewImg').attr('src', e.target.result);
				$('#previewArea').show();
			}
			reader.readAsDataURL(file);
		}
	});
	
	// 폼 유효성 검증
	$('#goodsForm').on('submit', function(e) {
		if(!$('#goodsImg').val()) {
			alert('상품 이미지를 선택해주세요.');
			e.preventDefault();
			return false;
		}
		
		if(!$('#goodsName').val().trim()) {
			alert('상품명을 입력해주세요.');
			$('#goodsName').focus();
			e.preventDefault();
			return false;
		}
		
		if(!$('#goodsPrice').val() || $('#goodsPrice').val() <= 0) {
			alert('올바른 가격을 입력해주세요.');
			$('#goodsPrice').focus();
			e.preventDefault();
			return false;
		}
		
		const pointRate = parseFloat($('#pointRate').val());
		if(isNaN(pointRate) || pointRate < 0 || pointRate > 1) {
			alert('포인트 적립률은 0~1 사이의 소수점 숫자여야 합니다. (예: 0.05)');
			$('#pointRate').focus();
			e.preventDefault();
			return false;
	});
</script>
</html>