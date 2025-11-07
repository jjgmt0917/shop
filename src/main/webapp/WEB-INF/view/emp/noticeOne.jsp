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
		
		.notice-detail-card {
			background-color: white;
			border-radius: 8px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			overflow: hidden;
		}
		
		.notice-header {
			padding: 30px;
			border-bottom: 2px solid #f0f0f0;
		}
		
		.notice-title {
			font-size: 24px;
			font-weight: 600;
			color: #333;
			margin-bottom: 15px;
		}
		
		.notice-meta {
			display: flex;
			gap: 20px;
			font-size: 14px;
			color: #666;
		}
		
		.notice-meta-item {
			display: flex;
			align-items: center;
			gap: 8px;
		}
		
		.notice-meta-label {
			font-weight: 500;
			color: #333;
		}
		
		.notice-content {
			padding: 40px 30px;
			min-height: 300px;
			line-height: 1.8;
			font-size: 15px;
			color: #333;
		}
		
		.button-group {
			padding: 20px 30px;
			border-top: 1px solid #f0f0f0;
			display: flex;
			justify-content: space-between;
		}
		
		.btn-list {
			padding: 10px 24px;
			background-color: #000;
			color: white;
			border: none;
			border-radius: 4px;
			font-size: 14px;
			font-weight: 600;
			cursor: pointer;
			text-decoration: none;
			display: inline-block;
		}
		
		.btn-list:hover {
			background-color: #333;
			color: white;
		}
		
		.right-buttons {
			display: flex;
			gap: 10px;
		}
		
		.btn-edit {
			padding: 10px 20px;
			background-color: white;
			color: #333;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
			font-weight: 600;
			cursor: pointer;
			text-decoration: none;
			display: inline-block;
		}
		
		.btn-edit:hover {
			background-color: #f8f9fa;
		}
		
		.btn-delete {
			padding: 10px 20px;
			background-color: #dc3545;
			color: white;
			border: none;
			border-radius: 4px;
			font-size: 14px;
			font-weight: 600;
			cursor: pointer;
			text-decoration: none;
			margin-left: 10px;
		}
		
		.btn-delete:hover {
			background-color: #c82333;
		}
	</style>
</head>
<body>
	<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import>
	<div class="main-container">
		<h1 class="page-title">공지사항</h1>
		
		<div class="notice-detail-card">
			<!-- 공지사항 헤더 -->
			<div class="notice-header">
				<h2 class="notice-title">${notice.title}</h2>
				<div class="notice-meta">
					<div class="notice-meta-item">
						<span class="notice-meta-label">작성자:</span>
						<span>${notice.writer}</span>
					</div>
					<div class="notice-meta-item">
						<span class="notice-meta-label">등록일:</span>
						<span>${notice.createdate}</span>
					</div>
				</div>
			</div>
			
			<!-- 공지사항 내용 -->
			<div class="notice-content" id="noticeContent">
				${notice.content}
			</div>
			
			<!-- 버튼 그룹 -->
			<div class="button-group">
				<a href="${pageContext.request.contextPath}/emp/noticeList" class="btn-list">목록으로</a>
				<div class="right-buttons">
					<a href="${pageContext.request.contextPath}/emp/modifyNotice?noticeCode=${notice.code}" class="btn-edit">수정</a>
					<a href="${pageContext.request.contextPath}/emp/removeNotice?noticeCode=${notice.code}" class="btn-delete">삭제</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	let content = $('#noticeContent').text();
	content = content.replace(/\r\n/g, '<br>').replace(/\n/g, '<br>');
	$('#noticeContent').html(content);
</script>
</html>