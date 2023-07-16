<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cài đặt mật khẩu</title>
<!--  * Project:Cinema WebApp- Form Reset Form-->

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css"/>"
	rel="stylesheet">


<style>
html, body {
	height: 110%;
}

label {
	color: black;
}

.form-signin {
	max-width: 360px;
	padding: 15px;
}

.form-signin .form-floating:focus-within {
	z-index: 2;
}

.form-floating {
	margin-top: 30px;
	margin-bottom: 20px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

img {
	border-radius: 8px;
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 100%;
}

h1 {
	text-align: center;
}

button {
	margin-top: 10px;
	margin-bottom: 20px;
}

.right {
	text-align: right;
	float: right;
}

.center {
	text-align: center;
	float: center;
}
</style>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/headerkhach.jsp"%>
	<div class="main-body container mt-3">
		<main class="form-signin w-100 m-auto">
		<form id="loginForm" action="resetpass" method="post"
			modelAttribute="Resetpass">
			<img class="mb-4"
				src="https://i.pinimg.com/564x/c4/c8/08/c4c8085cd050efafd96b021591e6f75c.jpg"
				alt="" width="250" height="220">
			<h1 class="h3 mb-3 fw-normal">Reset Password</h1>
			<div class="form-floating">
				<input type="text" class="form-control" id="account"
					placeholder="Password" path="account" value="${account.account}"
					name="account" readonly> <label path="account">UserName</label>
				<div id="usernameErrow" style="color: red;"></div>
				<p style="color: red">${messageAccount}</p>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="oldpassword"
					path="oldpassword" placeholder="Password" name="oldpassword">
				<label path="oldpassword">Nhập Mật Khẩu Hiện Tại</label>
				<div id="oldpasswordErrow" style="color: red;"></div>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="password"
					path="password" placeholder="Password" name="password"> <label
					path="password">Nhập Mật Khẩu Mới</label>
				<div id="passwordErrow" style="color: red;"></div>
				<p style="color: red">${messagePassword}</p>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="repassword"
					path="repassword" placeholder="Password" name="repassword">
				<label path="repassword">Nhập Lại Mật Khẩu Mới</label>
				<div id="repasswordErrow" style="color: red;"></div>
			</div>
			<button class="w-100 btn btn-lg btn-warning" style="color: white"
				id="myBtn" type="submit">Cập Nhật Mật Khẩu</button>

		</form>
		</main>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script>
		document.getElementById("loginForm").onsubmit = function(e) {
			let username = document.getElementById("account").value;
			let password = document.getElementById("password").value;
			let repassword = document.getElementById("repassword").value;
			if (username == "") {
				username = "";
				document.getElementById("account").style.borderColor = "red"
				document.getElementById("usernameErrow").innerHTML = "Vui Lòng Nhập UserName ";
				e.preventDefault();
			} else {
				document.getElementById("account").style.borderColor = ""
				document.getElementById("usernameErrow").innerHTML = "";
			}

			if (repassword == "") {
				repassword = "";
				document.getElementById("repassword").style.borderColor = "red"
				document.getElementById("repasswordErrow").innerHTML = "Vui Lòng Nhập Lại Mật Khẩu ! ";
				e.preventDefault();
			} else if (repassword != password) {
				repassword = "";
				document.getElementById("repassword").style.borderColor = "red"
				document.getElementById("repasswordErrow").innerHTML = "Mật Khẩu Phải Trùng Với Mật Khẩu Bạn Đã Nhập !";
				e.preventDefault();
			} else {
				document.getElementById("repassword").style.borderColor = ""
				document.getElementById("repasswordErrow").innerHTML = "";
			}

			if (password == "") {
				password = "";
				document.getElementById("password").style.borderColor = "red"
				document.getElementById("passwordErrow").innerHTML = "Vui Lòng Nhập Password ! ";
				e.preventDefault();
			} else if ((/^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$/
					.test(password))) {
				password = "";
				document.getElementById("password").style.borderColor = "red"
				document.getElementById("passwordErrow").innerHTML = "Mật Khẩu Phải Bao Gồm 1 Kí Tự Viết Hoa, Viết Thường Và 1 Ký Tự Đặc Biệt !";
				e.preventDefault();
			} else {
				document.getElementById("password").style.borderColor = ""
				document.getElementById("passwordErrow").innerHTML = "";
				return true;
			}
		}
	</script>
</body>
</html>