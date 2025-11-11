<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/nav.css" rel="stylesheet">
<nav class="top-nav">
    <div class="nav-container">
        <ul class="nav-menu">
        	<!-- 상품목록 -> 상세보기 -> 주문 -->
        	<li><a href="${pageContext.request.contextPath}/customer/customerIndex">상품목록</a></li>
        	<!-- 배송지목록 / 배송지추가 (5개까지만 6번째 입력시 가장 먼저 입력된 주소지 삭제) / 삭제 -->
            <li><a href="${pageContext.request.contextPath}/customer/addressList">배송지관리</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/cartList">장바구니</a></li>
            <li><a href="${pageContext.request.contextPath}/public/noticeList">공지사항</a></li>
            <!-- 개인정보열람 
            	/ 폰번호수정
            	/ 비밀번호 수정() - 트랜잭션 costomer 비밀번호 수정 + pw_history에 비밀번호 입력(6개가 되었다. 가장 빠른 데이터는 삭제)
            	/ 회원탈퇴() - 트랜잭션 : outid 입력 + customer 삭제
            -->
            <li><a href="${pageContext.request.contextPath}/customer/customerInfo?customerId=${loginCustomer.customerId}">내정보</a></li>
        </ul>
        <div class="nav-right">
            <span class="user-info">${loginCustomer.customerName}님</span>
            <button class="logout-btn" onclick="location.href='${pageContext.request.contextPath}/customer/customerLogout'">로그아웃</button>
        </div>
    </div>
</nav>