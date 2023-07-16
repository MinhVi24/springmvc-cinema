<!-- * Project: Cinema WebApp
	 * thêm danh sách khuyến mãi -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYpE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm khuyến mãi</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
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
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/header.jsp"%>
	<div class=" main-body d-flex justify-content-center">

		<form:form class="form d-flex justify-content-center"
			action="${pageContext.request.contextPath}/admin/khuyenmai/luu"
			method="post" modelAttribute="khuyenmai" id="form1">
			<div class="row" style="margin-left: 21%">
				<h2 class="section-heading text-uppercase fw-bold">Thêm khuyến
					mãi</h2>
				<div class="col mt-4">
					<div class="form-group  ">
						<form:label path="maKhuyenMai" for="maKhuyenMai ">Mã khuyến mãi</form:label>
						<form:input path="maKhuyenMai" type="text" class="form-control"
							id="maKhuyenMai" placeholder="Mã khuyến mãi" style="width:500px"></form:input>
						<small style="color: red !important;" id="ErrorMaKM"><form:errors
								path="maKhuyenMai" /> ${maKhuyenMaiError}</small> <small
							id="messageMaKM"></small> <small id="messageMaKMBlank"></small>
					</div>
					<br>
					<div class="form-group">
						<form:label path="tenKhuyenMai" for="tenKhuyenMai">Tên khuyến mãi</form:label>
						<form:input path="tenKhuyenMai" type="text" class="form-control"
							id="tenKhuyenMai" placeholder="Tên khuyến mãi"
							style="width: 500px;"></form:input>
						<small id="ErrorTenKM"><form:errors path="tenKhuyenMai" /></small>
						<small id="messageTenKM"></small> <small id="messageTenKMBlank"></small>
					</div>
					<br>
					<div class="form-group">
						<form:label path="moTaKhuyenMai" for="moTaKhuyenMai">Mô tả khuyến mãi</form:label>
						<form:input path="moTaKhuyenMai" type="text" class="form-control"
							id="moTaKhuyenMai" placeholder="Mô tả khuyến mãi"
							style="width: 500px;"></form:input>
						<div class="error">
							<form:errors path="moTaKhuyenMai" />
						</div>
					</div>
					<br>
					<div class="form-group">
						<form:label path="ngayBatDau" for="ngayBatDau">Ngày bắt đầu khuyến mãi</form:label>
						<form:input path="ngayBatDau" type="date" class="form-control"
							id="ngayBatDau" style="width: 500px;"></form:input>
						<small> <form:errors path="ngayBatDau" id="ErrorNgayBD" />${ngayBatDauError}</small>
						<small id="messageNgayBDKM"></small> <small
							id="messageNgayBDKMBlank"></small>
					</div>

					<br>
					<div class="form-group">
						<form:label path="ngayKetThuc" for="ngayKetThuc">Ngày kết thúc khuyến mãi</form:label>
						<form:input path="ngayKetThuc" type="date" class="form-control"
							id="ngayKetThuc" style="width: 500px;"></form:input>
						<small> <form:errors path="ngayKetThuc" id="ErrorNgayKT" />${ngayKetThucError}</small>
						<small id="messageNgayKTKM"></small> <small
							id="messageNgayKTKMBlank"></small>
					</div>

					<br>
					<div class="form-group">
						<form:label path="tiLeKhuyenMai" for="tiLeKhuyenMai">Tỉ lệ khuyến mãi</form:label>
						<form:input path="tiLeKhuyenMai" type="number"
							class="form-control" id="tiLeKhuyenMai" style="width: 500px;"></form:input>
						<small> <form:errors path="tiLeKhuyenMai" />
						</small> <small id="messageTiLeKM"></small> <small id="messageTiLeKMBlank"></small>
					</div>

					<br>
					<form:button value="list" type="submit"
						class="btn btn-warning d-flex justify-content-center mt-3"
						id="submit" style="font-size: 25px">Thêm khuyến mãi</form:button>
				</div>
			</div>
			<p></p>
		</form:form>
		<br />
	</div>
	<script>
		/* Check mã dịch vụ */
		$('#maKhuyenMai').change(checkMaKM);
		function checkMaKM() {
			var maKhuyenMai = document.getElementById('maKhuyenMai').value;
			console.log(maKhuyenMai);
			var format = "^KM[0-9]{5}$";
			if (maKhuyenMai === "") {
				$("#ErrorMaKM").hide();
				$("#messageMaKM").hide();
				$('#messageMaKMBlank').show();
				$('#messageMaKMBlank').html("Vui lòng nhập mã khuyến mãi!");
			} else if (maKhuyenMai.match(format)) {
				$("#messageMaKM").hide();
				$('#messageMaKMBlank').hide();
				return 1;
			} else {
				$('#messageMaKMBlank').hide();
				$("#messageMaKM").show();
				$("#messageMaKM")
						.html(
								"Mã khuyến mãi không đúng định dạng: KMxxxxx(x là 1 chữ số)");
				return 0;
			}
		}

		$("#form1").submit(function(e) {
			var val1 = checkMaKM();
			if ((val1 == 1)) {
				return true;
			} else {
				return false;
			}
		});
		/* Check tên khuyến mãi*/
		$('#tenKhuyenMai').change(checkTenKM);
		function checkTenKM() {
			var tenKhuyenMai = document.getElementById('tenKhuyenMai').value;
			console.log(tenKhuyenMai);
			var format = "^.{3,50}$";
			if (tenKhuyenMai === "") {
				$("#ErrorTenKM").hide();
				$("#messageTenKM").hide();
				$('#messageTenKMBlank').show();
				$('#messageTenKMBlank').html("Vui lòng nhập tên khuyến mãi!");
			} else if (tenKhuyenMai.match(format)) {
				$("#messageTenKM").hide();
				$('#messageTenKMBlank').hide();
				return 1;
			} else {
				$('#messageTenKMBlank').hide();
				$("#messageTenKM").show();
				$("#messageTenKM").html(
						"Nhập tên khuyến mãi khoảng từ 3 đến 50 kí tự!");
				return 0;
			}
		}

		$("#form1").submit(function(e) {
			var val1 = checkTenKM();
			if ((val1 == 1)) {
				return true;
			} else {
				return false;
			}
		});

		/* Check ngày bắt đầu khuyến mãi*/
		$('#ngayBatDau').change(checkNgayBDKM);
		function checkNgayBDKM() {
			var ngayBatDau = document.getElementById('ngayBatDau').value;
			console.log(ngayBatDau);
			if (ngayBatDau === "") {
				$("#ErrorNgayBD").hide();
				$("#messageNgayBDKM").hide();
				$('#messageNgayBDKMBlank').show();
				$('#messageNgayBDKMBlank').html(
						"Vui lòng nhập ngày bắt đầu khuyến mãi!");
				return 0;
			} else {
				$("#messageNgayBDKM").hide();
				$('#messageNgayBDKMBlank').hide();
				return 1;
			}
		}

		$("#form1").submit(function(e) {
			var val1 = checkNgayBDKM();
			if ((val1 == 1)) {
				return true;
			} else {
				return false;
			}
		});
		/* Check ngày kết thúc khuyến mãi*/
		$('#ngayKetThuc').change(checkNgayKTKM);
		function checkNgayKTKM() {
			var ngayKetThuc = document.getElementById('ngayKetThuc').value;
			console.log(ngayBatDau);
			if (ngayKetThuc === "") {
				$("#ErrorNgayBD").hide();
				$("#messageNgayKTKM").hide();
				$('#messageNgayKTKMBlank').show();
				$('#messageNgayKTKMBlank').html(
						"Vui lòng nhập ngày kết thúc khuyến mãi!");
				return 0;
			} else {
				$("#messageNgayKTKM").hide();
				$('#messageNgayKTKMBlank').hide();
				return 1;
			}
		}
		$("#form1").submit(function(e) {
			var val1 = checkNgayKTKM();
			if ((val1 == 1)) {
				return true;
			} else {
				return false;
			}
		});
		/* Nhập tỉ lệ khuyến mãi */
		$('#tiLeKhuyenMai').change(checkTiLeKM);
		function checkTiLeKM() {
			var tiLeKM = document.getElementById('tiLeKhuyenMai').value;
			console.log(tiLeKM);
			var pattern = "^[0-9]+\d*$"
			if (tiLeKM === "") {
				$('#messageTiLeKMBlank').show();
				$('#messageTiLeKMBlank')
						.html("Vui lòng nhập tỉ lệ khuyến mãi!");
				$("#messageTiLeKM").hide();
			} else if (tiLeKM.match(pattern)) {

				$("#messageTiLeKM").hide();
				$('#messageTiLeKMBlank').hide();
				return 1;
			} else {
				$('#ErrorDOnGia').hide();
				$('#messageTiLeKMBlank').hide();
				$("#messageTiLeKM").show();
				$("#messageTiLeKM").html("Đơn giá nhập vào phải lớn hơn 0!")
				return 0;
			}
		}

		$("#form1").submit(function(e) {
			var val1 = checkTiLeKM();
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