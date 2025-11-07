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
	<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet">
	<style>
		.main-container {
			max-width: 1400px;
			margin: 0 auto;
			padding: 40px 20px;
		}
		
		.page-title {
			font-size: 28px;
			font-weight: 600;
			color: #333;
		}
		
		.search-section {
			background-color: white;
			border-radius: 8px;
			padding: 20px;
			margin-bottom: 20px;
			box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
		}
		
		.search-row {
			display: flex;
			gap: 10px;
			align-items: center;
		}
		
		.search-select {
			padding: 8px 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
			min-width: 120px;
		}
		
		.search-input {
			flex: 1;
			padding: 8px 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
		}
		
		.search-btn {
			padding: 8px 30px;
			background-color: #000;
			color: white;
			border: none;
			border-radius: 4px;
			font-size: 14px;
			font-weight: 600;
			cursor: pointer;
		}
		
		.search-btn:hover {
			background-color: #333;
		}
		.table-header {
		    display: flex;
		    justify-content: flex-end;
		    margin-bottom: 15px;
		}
		.add-btn {
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
		
		.add-btn:hover {
			background-color: #333;
			color: white;
		}
	</style>
</head>
<body>
	<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import>
	<div class="main-container">
		<h1 class="page-title">공지사항</h1>
		
		<!-- 검색 섹션 -->
		<div class="search-section">
			<form method="get" action="${pageContext.request.contextPath}/emp/empList">
				<div class="search-row">
					<select class="search-select" name="searchType">
						<option value="">직원</option>
						<option value="empId">아이디</option>
						<option value="empName">이름</option>
						<option value="empCode">코드</option>
					</select>
					<input type="text" class="search-input" name="searchKeyword" placeholder="검색어를 입력하세요">
					<button type="submit" class="search-btn">검색</button>
				</div>
			</form>
		</div>
		
		<!-- 테이블 섹션 -->
		<div class="table-section">
			<div class="table-header">
				<a href="${pageContext.request.contextPath}/emp/addNotice" class="add-btn">+ 공지 추가</a>
			</div>
			<table class="common-table">
				<thead>
					<tr>
						<th>공지사항 번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="n" items="${list}">
						<tr>
							<td>${n.noticeCode}</td>
							<td><a href="${pageContext.request.contextPath}/emp/noticeOne?noticeCode=${n.noticeCode}">${n.noticeTitle}</a></td>
							<td>${n.empCode}</td>
							<td>${n.createdate}</td>
							<td>
								<a href="${pageContext.request.contextPath}/emp/modifyNotice?noticeCode=${n.noticeCode}"
								   class="list-btn-edit">
									수정
								</a>
								<a href="${pageContext.request.contextPath}/emp/removeNotice?noticeCode=${n.noticeCode}" 
					               class="list-btn-delete"
					               onclick="return confirm('정말 삭제하시겠습니까?');">
					               삭제
					            </a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- 페이징 -->
			<div class="pagination">
				<c:if test="${currentPage > 10}">
					<a href="${pageContext.request.contextPath}/emp/noticeList?currentPage=${currentPage - 10}">&lt; 이전</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<a href="${pageContext.request.contextPath}/emp/noticeList?currentPage=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
				</c:forEach>
				<c:if test="${endPage < lastPage}">
					<c:choose>
						<c:when test="${currentPage + 10 > lastPage}">
							<a href="${pageContext.request.contextPath}/emp/noticeList?currentPage=${lastPage}">다음 &gt;</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/emp/noticeList?currentPage=${currentPage + 10}">다음 &gt;</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script>
</script>
</html>