<!--  * Project: Cinema WebApp
      * JSP: Form update thông tin khách hàng  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta charset="UTF-8">

<style>
table, td {
	border: 1px solid white;
}

table {
	border-collapse: collapse;
	width: 50%;
}

th, td {
	text-align: center;
}

span {
	color: red;
}
</style>

<title>Cập nhật thông tin khách hàng</title>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/headerkhach.jsp"%>
	<div
		class="main-body row col-10 d-flex flex-row justify-content-center">
		<div class="container" style="max-width: 600px">

			<form:form class="form"
				action="${pageContext.request.contextPath}/khachhang/control/update"
				method="post" modelAttribute="khachHang">

				<h1 align="center">Cập Nhật Thông Tin</h1>



				<div class="form-group input ">
					<form:label path="tenKhachHang" for="tenKhachHang">Tên Khách Hàng</form:label>
					<form:input path="tenKhachHang" type="text" class="form-control"
						id="tenKhachHang" placeholder="Tên Khách Hàng"></form:input>
					<small><form:errors path="tenKhachHang" /></small>
				</div>
				<br>


				<div class="form-group input col-12">
					<form:label path="gioiTinh" for="gioiTinh">Giới Tính</form:label>
					<div class="row ">
						<div class="col-2">
							<form:radiobutton path="gioiTinh" value="Nam" id="gioiTinhNam"
								label="Nam" />
						</div>
						<div class="col-2">
							<form:radiobutton path="gioiTinh" value="Nu" id="gioiTinhNu"
								label="Nữ" />
						</div>
					</div>
					<small><form:errors path="gioiTinh" id="gioiTinhE"
							class="error" /></small>
				</div>

				<br>
				<div class="form-group input">
					<form:label path="sdt" for="sdt">Số Điện Thoại</form:label>
					<form:input path="sdt" type="text" class="form-control" id="sdt"
						placeholder="Số Điện Thoại"></form:input>
					<small><form:errors path="sdt" /></small>
				</div>

				<br>



				<div class="form-group input=">
					<form:label path="ngaySinh" for="ngaySinh">Ngày Sinh</form:label>
					<form:input path="ngaySinh" type="date" class="form-control"
						id="ngaySinh"></form:input>
					<small><form:errors path="ngaySinh" />${msg}</small>
				</div>
				<br>
				<div class="form-group input">
					<form:label path="email" for="email">email</form:label>
					<form:input path="email" type="text" class="form-control"
						id="email" name="email" placeholder="Email" readonly="true"></form:input>
					<small><form:errors path="email" id="emailE" class="error" /></small>
					<!-- readonly="true" -->
				</div>

				<br>


				<div class="form-group input">
					<form:label path="diaChi" for="diaChi">Dịa Chỉ</form:label>
					<form:input path="diaChi" type="text" class="form-control"
						id="diaChi" placeholder="Địa chỉ"></form:input>
					<small><form:errors path="diaChi" /></small>
				</div>


				<br>
				<div class="form-group input " id="maKhachHang">
					<form:label path="maKhachHang" for="maKhachHang">Mã khách hàng</form:label>
					<form:input path="maKhachHang" type="text" class="form-control"
						id="maKhachHang" readonly="true"></form:input>
					<small><form:errors path="maKhachHang" /></small>
				</div>

				<div class="form-group input " style="text-align: center;">
					<form:button value="list" type="submit" class="btn btn-primary"
						id="submit" style=" width: 250px; margin-top: 40px ">Cập Nhật</form:button>

				</div>
				<br>

			</form:form>

			<p style="color: red; margin-top: -27px;">${update}</p>
			<p style="color: red">${thatbai}</p>
<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript">
		document.getElementById("maKhachHang").style.display = "none";
	</script>



</body>
</html>