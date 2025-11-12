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
		.product-container {
			max-width: 1200px;
			margin: 40px auto;
			padding: 0 20px;
		}
		
		.product-detail {
			background-color: white;
			border-radius: 8px;
			padding: 40px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			display: grid;
			grid-template-columns: 1fr 1fr;
			gap: 60px;
		}
		
		.product-image-section {
			display: flex;
			justify-content: center;
			align-items: flex-start;
		}
		
		.product-image {
			width: 100%;
			max-width: 500px;
			border-radius: 8px;
			border: 1px solid #e0e0e0;
		}
		
		.product-info-section {
			display: flex;
			flex-direction: column;
		}
		
		.product-name {
			font-size: 28px;
			font-weight: 600;
			color: #333;
			margin-bottom: 15px;
			line-height: 1.4;
		}
		
		.product-price {
			font-size: 32px;
			font-weight: 700;
			color: #000;
			margin-bottom: 20px;
		}
		
		.product-price-won {
			font-size: 20px;
			font-weight: 400;
			margin-left: 5px;
		}
		
		.product-info-list {
			border-top: 1px solid #e0e0e0;
			padding-top: 20px;
			margin-bottom: 30px;
		}
		
		.info-item {
			display: flex;
			padding: 12px 0;
			border-bottom: 1px solid #f5f5f5;
		}
		
		.info-label {
			width: 120px;
			font-weight: 500;
			color: #666;
			font-size: 14px;
		}
		
		.info-value {
			flex: 1;
			color: #333;
			font-size: 14px;
		}
		
		.stock-status {
			display: inline-block;
			padding: 4px 12px;
			border-radius: 4px;
			font-size: 13px;
			font-weight: 600;
		}
		
		.stock-available {
			background-color: #e8f5e9;
			color: #2e7d32;
		}
		
		.stock-soldout {
			background-color: #ffebee;
			color: #c62828;
		}
		
		.rating {
			color: #ffa500;
			font-size: 16px;
		}
		
		.quantity-section {
			margin: 30px 0;
			padding: 20px;
			background-color: #f8f9fa;
			border-radius: 6px;
		}
		
		.quantity-label {
			font-weight: 500;
			color: #333;
			font-size: 14px;
			margin-bottom: 10px;
		}
		
		.quantity-select {
			width: 100%;
			padding: 12px 15px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 15px;
			cursor: pointer;
		}
		
		.quantity-select:focus {
			outline: none;
			border-color: #999;
		}
		
		.total-price-section {
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding: 20px;
			background-color: #f8f9fa;
			border-radius: 6px;
			margin-bottom: 20px;
		}
		
		.total-label {
			font-size: 16px;
			font-weight: 500;
			color: #333;
		}
		
		.total-price {
			font-size: 28px;
			font-weight: 700;
			color: #000;
		}
		
		.button-group {
			display: flex;
			gap: 10px;
		}
		
		.btn-cart {
			flex: 1;
			padding: 16px;
			background-color: white;
			color: #333;
			border: 2px solid #000;
			border-radius: 4px;
			font-size: 16px;
			font-weight: 600;
			cursor: pointer;
		}
		
		.btn-cart:hover {
			background-color: #f8f9fa;
		}
		
		.btn-order {
			flex: 1;
			padding: 16px;
			background-color: #000;
			color: white;
			border: none;
			border-radius: 4px;
			font-size: 16px;
			font-weight: 600;
			cursor: pointer;
		}
		
		.btn-order:hover {
			background-color: #333;
		}
		
		@media (max-width: 768px) {
			.product-detail {
				grid-template-columns: 1fr;
				gap: 30px;
				padding: 20px;
			}
		}
	</style>
</head>
<body>
	<c:import url="/WEB-INF/view/inc/customerMenu.jsp"></c:import>
	<div class="product-container">
		<div class="product-detail">
			<!-- 상품 이미지 -->
			<div class="product-image-section">
				<img src="${pageContext.request.contextPath}/upload/${goods.filename}" 
				     alt="${goods.goodsName}" 
				     class="product-image">
			</div>
			
			<!-- 상품 정보 -->
			<div class="product-info-section">
				<h1 class="product-name">${goods.goodsName}</h1>
				
				<div class="product-price">
					${goods.goodsPrice}<span class="product-price-won">원</span>
				</div>
				
				<div class="product-info-list">
					<div class="info-item">
						<div class="info-label">적립 포인트</div>
						<div class="info-value">
							<span class="rating">★</span> ${goods.pointRate * 100}% 적립
						</div>
					</div>
					
					<div class="info-item">
						<div class="info-label">배송비</div>
						<div class="info-value">무료배송</div>
					</div>
					
					<div class="info-item">
						<div class="info-label">판매상태</div>
						<div class="info-value">
							<c:choose>
								<c:when test="${goods.soldout == 'Y'}">
									<span class="stock-status stock-soldout">품절</span>
								</c:when>
								<c:otherwise>
									<span class="stock-status stock-available">판매중</span>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				
				<c:if test="${goods.soldout != 'soldout'}">
					<form id="orderForm">
						<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath">
						<input type="hidden" name="goodsCode" value="${goods.goodsCode}">
						<!-- 수량 선택 -->
						<div class="quantity-section">
							<div class="quantity-label">수량</div>
							<select class="quantity-select" name="quantity" id="quantity">
								<c:forEach var="n" begin="1" end="10">
									<option value="${n}">${n}개</option>
								</c:forEach>
							</select>
						</div>
						
						<!-- 총 가격 -->
						<div class="total-price-section">
							<span class="total-label">총 상품금액</span>
							<span class="total-price" id="totalPrice">${goods.goodsPrice}원</span>
						</div>
						
						<!-- 버튼 -->
						<div class="button-group">
							<button type="button" id="cartBtn" class="btn-cart">장바구니</button>
							<button type="button" id="orderBtn" class="btn-order">바로구매</button>
						</div>
					</form>
				</c:if>
				
				<c:if test="${goods.soldout == 'soldout'}">
					<div class="total-price-section">
						<span style="color: #c62828; font-weight: 600;">현재 품절된 상품입니다.</span>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script>
	const basePrice = ${goods.goodsPrice};
	
	// 수량 변경 시 총 가격 업데이트
	$('#quantity').on('change', function() {
		let quantity = $(this).val();
		let totalPrice = basePrice * quantity;
		$('#totalPrice').text(totalPrice.toLocaleString() + '원');
	});
	
	// 장바구니
	$('#cartBtn').click(function(){
		if(confirm('장바구니에 담으시겟습니까?')){
			$('#orderForm').attr('method', 'post');
			$('#orderForm').attr('action', $('#contextPath').val() + '/customer/addCart');
		}
		
	});
	
	// 바로 구매
	$('#orderBtn').click(function(){
		if(confirm('바로 구매하시겠습니까?')){
			$('#orderForm').attr('method', 'get');
			$('#orderForm').attr('action', $('#contextPath').val() + '/customer/addOrders');
		}
		
	});
/*
*/
</script>
</html>