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
	<style>
	body {
	    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Apple SD Gothic Neo', 'Malgun Gothic', sans-serif;
	    background-color: #f5f5f5;
	    min-height: 100vh;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    padding: 20px;
	}
	
	.signup-container {
	    width: 100%;
	    max-width: 600px;
	}
	
	.signup-title {
	    font-size: 28px;
	    font-weight: 600;
	    margin-bottom: 25px;
	    color: #333;
	}
	
	.signup-card {
	    background-color: white;
	    border: 1px solid #e0e0e0;
	    border-radius: 8px;
	    padding: 40px;
	    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	    margin-bottom: 20px;
	}
	
	.col-form-label {
	    font-weight: 500;
	    color: #333;
	    font-size: 14px;
	    padding-top: 10px;
	}
	
	.form-control {
	    padding: 10px 15px;
	    border-color: #ddd;
	    font-size: 14px;
	}
	
	.form-control:focus {
	    border-color: #999;
	    box-shadow: none;
	}
	
	.form-control::placeholder {
	    color: #999;
	    font-size: 13px;
	}
	
	.btn-check {
	    display: inline-block;
	    width: 100%;
	    padding: 10px 12px;
	    background-color: #000 !important;
	    border: none !important;
	    color: white !important;
	    font-weight: 600;
	    font-size: 13px;
	    transition: background-color 0.3s;
	    white-space: nowrap;
	    cursor: pointer;
	    border-radius: 4px;
	    text-align: center;
	}
	
	.btn-check:hover {
	    background-color: #333 !important;
	}
	
	.btn-signup-submit {
	    width: 100%;
	    padding: 16px;
	    background-color: #000;
	    border: none;
	    color: white;
	    font-weight: 600;
	    font-size: 16px;
	    transition: background-color 0.3s;
	    border-radius: 8px;
	    cursor: pointer;
	}
	
	.btn-signup-submit:hover {
	    background-color: #333;
	    color: white;
	}
	
	.btn-signup-submit:active {
	    background-color: #111 !important;
	}
	
	@media (max-width: 576px) {
	    .signup-card {
	        padding: 30px 20px;
	    }
	    
	    .col-form-label {
	        margin-bottom: 8px;
	    }
	}
	</style>
</head>
<body>
	    <div class="signup-container">
        <h1 class="signup-title">회원가입</h1>
        <form method="post" action="${pageContext.request.contextPath}/public/addMember">
            <div class="signup-card">
                <div class="mb-3 row align-items-center">
                    <label for="memberId" class="col-sm-3 col-form-label">아이디</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="id" name="memberId" placeholder="아이디">
                    </div>
                    <div class="col-sm-3">
                        <button id="idCheckBtn" type="button" class="btn-check">아이디확인</button>
                        <select id="idCheck" style="display:none;"></select>
                    </div>
                </div>

                <div class="mb-3 row align-items-center">
                    <label for="password" class="col-sm-3 col-form-label">비밀번호</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="pw" name="password" placeholder="비밀번호">
                    </div>
                </div>

                <div class="mb-3 row align-items-center">
                    <label for="passwordConfirm" class="col-sm-3 col-form-label">비밀번호 확인</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="비밀번호 확인">
                    </div>
                </div>

                <div class="mb-3 row align-items-center">
                    <label for="name" class="col-sm-3 col-form-label">이름</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="name" name="name" placeholder="2자리 이상">
                    </div>
                </div>

                <div class="mb-4 row align-items-center">
                    <label for="phone" class="col-sm-3 col-form-label">휴대폰 번호</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="010 - 0000 - 0000">
                    </div>
                </div>
            </div>

            <button type="button" id="addBtn" class="btn btn-signup-submit">회원가입</button>
        </form>
    </div>
</body>
</html>