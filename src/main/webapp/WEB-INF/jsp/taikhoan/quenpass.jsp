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
<!-- Quên Pass Form-->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css"/>"
	rel="stylesheet">

<style>
html, body {
	height: 100%;
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
			<form id="loginForm"
				action="${pageContext.request.contextPath}/taikhoan/otp"
				method="post" modelAttribute="emailtk">
				<img class="mb-4"
					src="https://i.pinimg.com/564x/c4/c8/08/c4c8085cd050efafd96b021591e6f75c.jpg"
					alt="" width="250" height="220">
				<h1 class="h3 mb-3 fw-normal">Quên Mật Khẩu</h1>
				<p>Nhập email đăng kí</p>
				<div class="form-floating">
					<input type="text" class="form-control" id="emailtk"
						placeholder="Password" path="emailtk" name="emailtk"> <label
						path="emailtk">Email</label>
					<p style="color: red">${messageEmail}</p>
					<p style="color: red">${messageOtp}</p>
					<p style="color: #000d33; font-weight: bold;">${messageEmailcorrect}</p>
					<div id="emailErrow" style="color: red;"></div>
				</div>
				<button class="w-100 btn btn-lg btn-warning" style="color: white"
					id="myBtn" type="submit">Đặt Lại Mật khẩu</button>
			</form>
		</main>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script>
		document.getElementById("loginForm").onsubmit = function(e) {
			let emailtk = document.getElementById("emailtk").value;
			if (emailtk == "") {
				emailtk = "";
				document.getElementById("emailtk").style.borderColor = "red"
				document.getElementById("emailErrow").innerHTML = "Vui Lòng Nhập Email !";
				e.preventDefault();
			} else if (/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/
					.test(emailtk)) {
				emailtk = "";
				document.getElementById("emailtk").style.borderColor = ""
				document.getElementById("emailErrow").innerHTML = "";
				return true;
			} else {
				document.getElementById("emailtk").style.borderColor = "red"
				document.getElementById("emailErrow").innerHTML = "Email Sai Định Dạng !";
				return false;

			}
		}
	</script>
</body>
</html>