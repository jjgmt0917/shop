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
		<h1 class="page-title">직원목록</h1>
		
		<!-- 검색 섹션 -->
		<div class="search-section">
			<form method="get" action="${pageContext.request.contextPath}/emp/empList">
				<div class="search-row">
					<select class="search-select" name="searchType">
						<option value="">주문번호</option>
						<option value="">주문자</option>
					</select>
					<input type="text" class="search-input" name="searchKeyword" placeholder="검색어를 입력하세요">
					<button type="submit" class="search-btn">검색</button>
				</div>
			</form>
		</div>
		
		<!-- 테이블 섹션 -->
		<div class="table-section">
			<div class="table-header">
				<a href="${pageContext.request.contextPath}/emp/addEmp" class="add-btn">+ 직원 추가</a>
			</div>
			<table class="common-table">
				<thead>
					<tr>
						<th>주문번호</th>
						<th>제품명</th>
						<th>주문량</th>
						<th>주문자</th>
						<th>주소</th>
						<th>주문일자</th>
						<th>배송상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ㅇ" items="${list}">
						<tr>
							<td>${o.orderCode}</td>
							<td>${o.goodsCode}</td>
							<td>${o.orderQuantity}</td>
							<td>${o.customerName}</td>
							<td>${o.customerPhone}</td>
							<td>${o.address}</td>
							<td>${o.createdate}</td>
							<td>
							<!-- 배송완료후 구매확정시 포인트 제공 -->
								<a href="${pageContext.request.contextPath}/emp/modifyOrderState?orderCode=${o.orderCode}&currentState=${o.state}"
								   class="status-btn ${o.state == '주문완료' ? 'active' : ''}" 
								   data-order-code="${o.orderCode}">
									${o.state}
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 페이징 -->
			<div class="pagination">
				<c:if test="${currentPage > 10}">
					<a href="${pageContext.request.contextPath}/emp/ordersList?currentPage=${currentPage - 10}">&lt; 이전</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<a href="${pageContext.request.contextPath}/emp/ordersList?currentPage=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
				</c:forEach>
				<c:if test="${endPage < lastPage}">
					<c:choose>
						<c:when test="${currentPage + 10 > lastPage}">
							<a href="${pageContext.request.contextPath}/emp/ordersList?currentPage=${lastPage}">다음 &gt;</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/emp/ordersList?currentPage=${currentPage + 10}">다음 &gt;</a>
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
		
		let empCode = $(e.currentTarget).data('order-code');
		let btn = $(e.currentTarget);
		
		$.ajax({
			url: '/shop/restapi/modifyOrderState',
			type: 'post',
			data: {
				orderCode: orderCode,
			},
			dataType: 'json',
			success: function(response) {
				// 버튼 상태 변경
				if(response.newState == 1) {
					btn.text('활성화').removeClass('status-btn').addClass('status-btn active');
					alert('활성화되었습니다.');
				} else if(response.newState == 0) {
					btn.text('비활성화').removeClass('active');
                   	alert('비활성화되었습니다.');
				} else {
					alert('상태 변경에 실패했습니다.');
				}
			},
			error: function() {
				console.log('orderCode: ' + orderCode);
                alert('오류가 발생했습니다.');
            }
		});
	});
</script>
</html>