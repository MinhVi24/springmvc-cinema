<!-- Project: Cinema WebApp-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới dịch vụ</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
label {
	font-weight: bold;
	font-size: 25px;
}

small {
	color: red;
	font-size: 20px;
}

h2 {
	padding-left: 150px;
	font-size: 50px;
}

input {
	width: 500px;
	height: 50px;
}
</style>
</head>
<body class="d-flex flex-row justify-content-center text-white"
	style="background-color: #181616;">
	<%@ include file="/common/header.jsp"%>
	<div class="row">
		<div class="main-body col-10 d-flex flex-row justify-content-center">
			<div class="" style="margin-left: 500px;">
				<form:form class="form"
					action="${pageContext.request.contextPath}/admin/dichvu/luu"
					method="post" modelAttribute="dichvu" id="form1">
					<div class="row mt-5">
						<h2 class="section-heading text-uppercase fw-bold"
							style="margin-left: 10%;">Thêm dịch vụ</h2>
						<div class="col-12 ">
							<div class="form-group ">
								<form:label path="maDichVu" for="maDichVu">Mã dịch vụ</form:label>
								<form:input path="maDichVu" type="text" class="form-control"
									id="maDichVu" placeholder="Mã dịch vụ" style="width:500px" />
								<small style="color: red !important;"> <form:errors
										path="maDichVu" id="" />${maDichVuError}</small> <small
									id="messageMaDV" style="color: red" class="mt-2"></small> <small
									id="messageBlankMaDV" style="color: red" class="mt-2"></small>
							</div>
							<br>
							<div class="form-group">
								<form:label path="tenDichVu" for="tenDichVu">Tên dịch vụ</form:label>
								<form:input path="tenDichVu" type="text" class="form-control"
									id="tenDichVu" placeholder="Tên dịch vụ" style="width: 500px;"></form:input>
								<small id="ErrorTenDV"><form:errors path="tenDichVu" /></small>
								<small id="messageTenDV" style="color: red" class="mt-2"></small>
								<small id="messageBlankTenDV" style="color: red" class="mt-2"></small>
							</div>
							<br>
							<div class="form-group">
								<form:label path="moTaDichVu" for="moTaDichVu">Mô tả dịch vụ</form:label>
								<form:input path="moTaDichVu" type="text" class="form-control"
									id="moTaDichVu" placeholder="Mô tả dịch vụ"
									style="width: 500px;"></form:input>
								<small><form:errors path="moTaDichVu" /></small>
							</div>
							<br>
							<div class="form-group">
								<form:label path="donGia" for="donGia">Đơn giá</form:label>
								<form:input path="donGia" type="number" class="form-control"
									id="donGia" placeholder="Đơn giá" style="width: 500px;"></form:input>
								<small id="ErrorDOnGia"><form:errors path="donGia" /></small> <small
									id="messageDonGia" style="color: red" class="mt-2"></small> <small
									id="messageBlankDonGia" style="color: red" class="mt-2"></small>
							</div>

							<br>
							<form:button value="list" type="submit"
								class="btn btn-danger d-flex justify-content-center"
								style="font-size: 25px" id="submit">Thêm dịch vụ</form:button>
						</div>
					</div>


				</form:form>
				<br />
			</div>
		</div>
	</div>
	<script>
		/* popup */
		var urlParams = new URLSearchParams(window.location.search);
		var success = urlParams.get('success');

		if (success === 'true') {
			alert('Lưu dữ liệu thành công!');
		}
		/* Check mã dịch vụ */
		$('#maDichVu').change(checkMaDV);
		function checkMaDV() {
			var maDichVu = document.getElementById('maDichVu').value;
			console.log(maDichVu);
			var format = "^DV[0-9]{5}$";
			if (maDichVu === "") {
				$("#ErrorTenDV").hide();
				$("#messageMaDV").hide();
				$('#messageBlankMaDV').show();
				$('#messageBlankMaDV').html("Vui lòng nhập mã dịch vụ!");
			} else if (maDichVu.match(format)) {
				$("#messageMaDV").hide();
				$('#messageBlankMaDV').hide();
				return 1;
			} else {
				$('#messageBlankMaDV').hide();
				$("#messageMaDV").show();
				$("#messageMaDV")
						.html(
								"Mã dịch vụ không đúng định dạng: DVxxxxx(x là 1 chữ số)");
				return 0;
			}
		}

		$("#form1").submit(function(e) {
			var val1 = checkMaDV();
			if ((val1 == 1)) {
				return true;
			} else {
				return false;
			}
		});
		/* Check tên dịch vụ */

		$('#tenDichVu').change(checkTenDV);
		function checkTenDV() {
			console.log("vao ham check");
			var tenDichVu = document.getElementById('tenDichVu').value;
			console.log(tenDichVu);
			var pattern = "^.{3,50}$"
			if (tenDichVu === "") {
				console.log("check ten null");
				$('#messageBlankTenDV').show();
				$('#messageBlankTenDV').html("Vui lòng nhập tên dịch vụ!");
				$("#messageTenDV").hide();
			} else if (tenDichVu.match(pattern)) {
				console.log("check ten dung");
				$("#ErrorTenDV").hide();
				$("#messageTenDV").hide();
				$('#messageBlankTenDV').hide();
				return 1;
			} else {
				console.log("chek ten sai");
				$('#messageBlankTenDV').hide();
				$("#messageTenDV").show();
				$("#messageTenDV").html(
						"Tên dịch vụ nằm trong khoảng từ 3-50 kí tự")
				return 0;
			}
		}

		$("#form1").submit(function(e) {
			var val1 = checkTenDV();
			if ((val1 == 1)) {
				return true;
			} else {
				return false;
			}
		});

		/* check đơn giá */
		$('#donGia').change(checkDonGia);
		function checkDonGia() {
			var donGia = document.getElementById('donGia').value;
			console.log(donGia);
			var pattern = "^[0-9]+\d*$"
			if (donGia === "") {
				$('#messageBlankDonGia').show();
				$('#messageBlankDonGia').html("Vui lòng nhập tên đơn giá!");
				$("#messageDonGia").hide();
			} else if (donGia.match(pattern)) {
				$("#messageDonGia").hide();
				$('#messageBlankDonGia').hide();
				return 1;
			} else {
				$('#ErrorDOnGia').hide();
				$('#messageBlankDonGia').hide();
				$("#messageDonGia").show();
				$("#messageDonGia").html("Đơn giá nhập vào phải lớn hơn 0!")
				return 0;
			}
		}

		$("#form1").submit(function(e) {
			var val1 = checkDonGia();
			if ((val1 == 1)) {
				return true;
			} else {
				return false;
			}
		});
	</script>
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>
</body>
</html>