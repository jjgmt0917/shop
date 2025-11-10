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
	<h2>베스트 상품목록</h2>
	<!-- 베스트 : 가장 많이 주문완료된 상품 5개 -->
	<div>
		
	</div>
	<h2>상품목록</h2>
	<div>
		<table width="80%">
			<tr>
				<!-- c:forEach varStatus : index(0~), count(1~), first(true/false), last(true/false) -->
				<c:forEach var="m" items="${goodsList}" varState="state">
					<td>
						<!-- image -->
						<div>
							<img src="${pageContext.request.contextPath}/upload/${m.filename}">
						</div>
						<!-- 이름, 가격 -->
						<div>
							${m.goodsName}<br>
							${m.goodsPrice}
						</div>
					</td>
					<c:if test="${state.last == false && state.count % 5 == 0 }">
						</tr><tr>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>