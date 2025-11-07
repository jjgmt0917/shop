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
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
	<style>
		.main-container {
			max-width: 1000px;
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
		
		.form-textarea {
			width: 100%;
			min-height: 400px;
			padding: 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
			line-height: 1.8;
			resize: vertical;
		}
		
		.form-textarea:focus {
			outline: none;
			border-color: #999;
		}
		
		.form-textarea::placeholder {
			color: #aaa;
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
		
		.char-count {
			text-align: right;
			font-size: 12px;
			color: #666;
			margin-top: 6px;
		}
	</style>
</head>
<body>
	<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import>
	<div class="main-container">
		<h1 class="page-title">공지사항 추가</h1>
		
		<div class="form-card">
			<form action="${pageContext.request.contextPath}/emp/addNotice" method="post" id="noticeForm">
				<!-- 제목 -->
				<div class="form-group">
					<label class="form-label" for="title">
						제목<span class="required">*</span>
					</label>
					<input type="text" class="form-input" name="title" id="title" placeholder="제목을 입력하세요" required maxlength="100">
					<div class="char-count">
						<span id="titleCount">0</span> / 100
					</div>
				</div>
					
				<!-- 내용 -->
				<div class="form-group">
					<label class="form-label" for="content">
						내용<span class="required">*</span>
					</label>
					<textarea class="form-textarea" name="content" id="content" placeholder="내용을 입력하세요" required></textarea>
					<div class="char-count">
						<span id="contentCount">0</span>자
					</div>
				</div>
				
				<!-- 버튼 -->
				<div class="button-group">
					<a href="${pageContext.request.contextPath}/emp/noticeList" class="btn-cancel">취소</a>
					<button type="button" class="btn-submit">등록</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	// 제목 글자수 카운트
	$('#title').on('input', function() {
		let length = $(this).val().length;
		$('#titleCount').text(length);
	});
	
	// 내용 글자수 카운트
	$('#content').on('input', function() {
		let length = $(this).val().length;
		$('#contentCount').text(length.toLocaleString());
	});
	
	$('.btn-submit').click(() => {
		if($('#title').val() == "") {
			alert('제목을 입력하세요');
			return;
		}
		if($('#content').val().length < 10) {
			alert('글의 내용은 최소 10글자 입니다.');
			return;
		}
		
		
		if(confirm('공지사항을 등록하시겠습니까?')) {
			$('#noticeForm').submit();
		} else {
			return;
		}
		
	});
</script>
</html>