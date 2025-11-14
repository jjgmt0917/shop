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
</head>
<body>
	<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import>
	<div class="main-container">
		<h1 class="page-title">상품목록</h1>
		
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
				<a href="${pageContext.request.contextPath}/emp/addGoods" class="add-btn">+ 상품 추가</a>
			</div>
			<table class="common-table">
				<thead>
					<tr>
						<th>상품 코드</th>
						<th>상품이름</th>
						<th>가격</th>
						<th>판매상태</th>
						<th>등록일</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="m" items="${list}">
						<tr>
							<td>${m.goodsCode}</td>
							<td>${m.goodsName}</td>
							<td>${m.goodsPrice}</td>
							<td>
								<a href="${pageContext.request.contextPath}/emp/modifyGoodsSoldout?goodsCode=${m.goodsCode}"
								   class="status-btn ${m.soldout == 'soldout' ? '' : 'active'}" 
								   data-goods-code="${m.goodsCode}">
									${m.soldout == 'soldout' ? 'SOLD OUT' : '판매중'}
								</a>
							</td>
							<td>${m.createdate}</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 페이징 -->
			<div class="pagination">
				<c:if test="${currentPage > 10}">
					<a href="${pageContext.request.contextPath}/emp/goodsList?currentPage=${currentPage - 10}">&lt; 이전</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<a href="${pageContext.request.contextPath}/emp/goodsList?currentPage=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
				</c:forEach>
				<c:if test="${endPage < lastPage}">
					<c:choose>
						<c:when test="${currentPage + 10 > lastPage}">
							<a href="${pageContext.request.contextPath}/emp/goodsList?currentPage=${lastPage}">다음 &gt;</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/emp/goodsList?currentPage=${currentPage + 10}">다음 &gt;</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script>
	$('.status-btn').click((e) => {
		e.preventDefault();
		
		let goodsCode = $(e.currentTarget).data('goods-code');
		let btn = $(e.currentTarget);
		
		$.ajax({
			url: '/shop/restapi/modifyGoodsSoldout',
			type: 'post',
			data: {
				goodsCode: goodsCode,
			},
			dataType: 'json',
			success: function(response) {
				// 버튼 상태 변경
				if(response.newSoldout == null) {
					alert('판매시작합니다.');
					btn.text('판매중').removeClass('status-btn').addClass('status-btn active');
				} else if(response.newSoldOut == "soldout") {
                   	alert('재고가 소진되었습니다.');
					btn.text('SOLDOUT').removeClass('active');
				} else {
					alert('상태 변경에 실패했습니다.');
				}
			},
			error: function() {
				console.log('goodsCode: ' + goodsCode);
                alert('오류가 발생했습니다.');
            }
		});
	});
</script>
</html>