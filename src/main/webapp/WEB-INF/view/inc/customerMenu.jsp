<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/nav.css" rel="stylesheet">
<nav class="top-nav">
    <div class="nav-container">
        <ul class="nav-menu">
            <li><a href="${pageContext.request.contextPath}/customer/addressList">배송지관리</a></li>
            <li><a href="${pageContext.request.contextPath}/public/noticeList">공지사항</a></li>
            <li><a href="${pageContext.request.contextPath}/emp/myInfo">내정보</a></li>
        </ul>
        <div class="nav-right">
            <span class="user-info">${loginCustomer.customerName}님</span>
            <button class="logout-btn" onclick="location.href='${pageContext.request.contextPath}/customer/customerLogout'">로그아웃</button>
        </div>
    </div>
</nav>