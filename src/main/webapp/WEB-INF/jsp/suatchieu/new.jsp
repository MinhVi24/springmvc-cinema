<!--  * Project: Cinema WebApp
      * JSP: Them Moi Suat Chieu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới suất chiếu</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
* {
	font-family: "Cairo", sans-serif;
	color: lightgray;
}

body{
	width : 100%;
	background-color: #181616;
}

.main-body {
	margin-top: 100px;
	width: 80%;
	color: lightgray;
}

.btn {
	background-color: #c0392b;
	border: 2px solid #c0392b;
	border-radius: 5px;
	color: lightgray;
}

.btn:hover {
	/* background-color: white; */
	border: 2px solid white;
	border-radius: 5px;
}

.header {
	width: 100%;
	height: 100px:
}
table {
	border: 1px solid white;
}
td {
	border: 1px solid white;
}
thead {
	background-color: #181616;
	border: 2px solid #c0392b;
	
}
th {
	color: #c0392b;
	text-align: center;
	border: 2px solid #c0392b;
}
.active-red {
	background-color: #c0392b !important;
	border: 2px solid #c0392b !important ;
}

.col-form-label {
	margin-top: 20px;
}
</style>
</head>
<body>
	<%@ include file="/common/header.jsp"%>




	<div class="container p-5"
		style="margin-top: 100px; margin-bottom: 100px; width: 500px">

		<div>
			<a href="${pageContext.request.contextPath}/admin/suatchieu/list"
				class="btn btn-dark">Danh Sách Suất Chiếu</a>
		</div>

		<form:form action="${pageContext.request.contextPath}/admin/suatchieu/save"
			method="post" modelAttribute="suatChieuForm">

			<%-- <div class="form-group">
					<form:label path="maSuatChieu" class="col-form-label">Mã Suất Chiếu</form:label>
					<form:input path="maSuatChieu" name="maSuatChieu" id="maSuatChieu" class="form-control"/>
					<div class="text-danger">
						<form:errors path="maSuatChieu" ></form:errors>
					</div>
				</div> --%>

			<div class="form-group">
				<form:label path="phim.maPhim" class="col-form-label">Phim</form:label>
				<form:select path="phim.maPhim" items="${phims}" itemValue="maPhim"
					itemLabel="tenPhim" class="form-control"></form:select>
				<div class="text-danger">
					<form:errors path="phim.maPhim"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<form:label path="phongChieu.maPhongChieu" class="col-form-label">Phòng Chiếu</form:label>
				<form:select path="phongChieu.maPhongChieu" items="${phongchieus}"
					itemValue="maPhongChieu" itemLabel="maPhongChieu"
					class="form-control"></form:select>
				<div class="text-danger">
					<form:errors path="phongChieu.maPhongChieu"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<form:label path="ngayChieu" class="col-form-label">Ngày Chiếu</form:label>
				<form:input type="date" path="ngayChieu" name="ngayChieu"
					id="ngayChieu" class="form-control" />
				<div class="text-danger">
					<form:errors path="ngayChieu"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<form:label path="gioBatDau" class="col-form-label">Giờ Bắt Đầu</form:label>
				<form:input type="time" path="gioBatDau" name="gioBatDau"
					id="gioBatDau" class="form-control" />
				<div class="text-danger">
					<form:errors path="gioBatDau"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<form:label path="gioKetThuc" class="col-form-label">Giờ Kết Thúc</form:label>
				<form:input type="time" path="gioKetThuc" name="gioKetThuc"
					id="gioKetThuc" class="form-control" />
				<div class="text-danger">
					<form:errors path="gioKetThuc"></form:errors>
				</div>
			</div>




			<br>
			<div class="col-auto">
				<form:button name="add" id="add" class="btn btn-success">Add</form:button>
			</div>
			<form:hidden path="maSuatChieu" />

		</form:form>
	</div>


<%-- 	<%@ include file="/common/footer.jsp"%> --%>
	<script type="text/javascript">
		function isTimeBeginLowerEnd(beginTime, endTime) {
			return new Date("1970/01/01 " + beginTime) < new Date("1970/01/01 "
					+ endTime);
		}

		$("#add").on("click", function(e) {
			  if ($("#ngayChieu").val() == "") {
			    e.preventDefault();
			    $("#ngayChieu ~ .text-danger").html("Vui lòng nhập ngày chiếu!");
			  }

			  if (!isTimeBeginLowerEnd($("#gioBatDau").val(), $("#gioKetThuc").val())) {
			    e.preventDefault();
			    $("#gioKetThuc ~ .text-danger").html("Giờ kết thúc phải lớn hơn giờ bắt đầu!");
			  }

			  if ($("#gioBatDau").val() == "") {
			    e.preventDefault();
			    $("#gioBatDau ~ .text-danger").html("Vui lòng nhập giờ bắt đầu!");
			  }

			  if ($("#gioKetThuc").val() == "") {
			    e.preventDefault();
			    $("#gioKetThuc ~ .text-danger").html("Vui lòng nhập giờ kết thúc!");
			  }
			});

	</script>
</body>
</html>