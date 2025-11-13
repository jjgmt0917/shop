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
	<c:import url="/WEB-INF/view/inc/customerMenu.jsp"></c:import>
	
	<div>
		<form method="post" action="${pageContext.request.contextPath}/customer/addOrders">
			<table border = "1">
				<tr>
					<th>filename</th>
					<th>goodsName</th>
					<th>goodsPrice</th>
					<th>pointRate</th>
					<th>goodsQuantity</th>
				</tr>
				<c:forEach var="m" items="${list}">
					<input type="hidden" name="goodsCode" value="${m.goodsCode}">
					<input type="hidden" name="orderQuantity" value="${m.goodsQuantity}">
					<input type="hidden" name="goodsPrice" value="${m.goodsPrice}">
					<tr>
						<td><img src="${pageContext.request.contextPath}/upload/${m.filename}"></td>
						<td>${m.goodsName}</td>
						<td>${m.goodsPrice}</td>
						<td>${m.pointRate}</td>
						<td>${m.goodsQuantity}</td>
					</tr>
				</c:forEach>
			</table>
			
			<div>
				<div>
					배송지 선택 : 
					<select id="addressList" size="5">
						<c:forEach var="addr" items="${addressList}">
							<option value="${addr.addressCode}">${addr.address}</option>
						</c:forEach>
					</select>
					<input type="text" id="addressCode" name="addressCode" readonly>
					<input type="text" id="address" readonly>
				</div>
				<div>
					결제금액 : 
					<input type="number" name="orderPrice" value="${orderPrice}" readonly>
				</div>
			</div>
			<div>
				<!-- 3) -->
				<button type="submit">결제하기(주문완료)</button>
			</div>
		</form>
	</div>
	<script>
		$('#addressList').dblclick(function() {
			$('#addressCode').val($('#addressList').val());
			$('#address').val($('select option:selected').text());
		});
	</script>
</body>
</html>