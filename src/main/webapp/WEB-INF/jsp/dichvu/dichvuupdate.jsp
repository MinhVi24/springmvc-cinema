<!-- Project: Cinema WebApp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật dịch vụ</title>
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
	padding-left: 30px;
	font-size: 40px;
}

input {
	width: 500px;
	height: 50px;
}
</style>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="row  d-flex justify-content-center">
		<div class="col-2  d-flex justify-content-center"
			style="margin-top: 5%;">
			<div class="col">
				<h2 class="section-heading text-uppercase fw-bold">Chỉnh sửa
					dịch vụ</h2>
				<form:form
					action="${pageContext.request.contextPath}/admin/dichvu/luudichvucapnhat"
					method="post" modelAttribute="DichVuForm" id="form1" class="mt-3">
					<table>
						<tr class="form-group flex-column">
							<form:label path="maDichVu">Mã dịch vụ</form:label>
							<form:input path="maDichVu" id="maDichVu" readonly="true"
								class="form-control" style="font-size: 20px;" />
							<form:errors path="maDichVu" />
							<br>
						</tr>
						<tr>
							<form:label path="tenDichVu">Tên dịch vụ</form:label>
							<form:input path="tenDichVu" id="tenDichVu" readonly="false"
								class="form-control" style="font-size: 20px;" />
							<small><form:errors path="tenDichVu" /></small>
							<small id="messageTenDV" style="color: red" class="mt-2"></small>
							<small id="messageBlankTenDV" style="color: red" class="mt-2"></small>
							<br>
						</tr>
						<tr>
							<form:label path="moTaDichVu">Mô tả dịch vụ</form:label>
							<form:input path="moTaDichVu" type="text" readonly="false"
								class="form-control" style="font-size: 20px;" />
							<small><form:errors path="moTaDichVu" /></small>
							<br>
						</tr>
						<tr>
							<form:label path="donGia">Đơn giá</form:label>
							<form:input path="donGia" type="number" id="donGia"
								readonly="false" class="form-control" style="font-size: 20px;" />
							<small><form:errors path="donGia" /> </small>
							<small id="messageDonGia" style="color: red" class="mt-2"></small>
							<small id="messageBlankDonGia" style="color: red" class="mt-2"></small>
							<br>
						</tr>
						<tr>
							<form:button value="them"
								class="btn btn-warning d-flex justify-content-center mt-3"
								style="font-size: 30px">Cập nhật</form:button>
						</tr>
					</table>
					<p style="color: red">${message1}</p>
					<p id=messageMaDV style="color: red"></p>
					<p id="messageDonGia" style="color: red"></p>
				</form:form>
			</div>
		</div>
	</div>
	<script>
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
						"Tên dịch vụ nằm trong khoảng từ 3-50 kí tự;")
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
</body>
</html>