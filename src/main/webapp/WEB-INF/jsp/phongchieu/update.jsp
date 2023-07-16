<!--  * Project: Cinema WebApp
      * JSP: Update Phong Chieu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật thông tin phòng chiếu</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>" rel="stylesheet">
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
	
	
		
		
		<div class="container p-5" style="margin-top: 100px; margin-bottom: 100px; width: 500px">
			
			<div>
				<a href="${pageContext.request.contextPath}/admin/phongchieu/list" class="btn btn-dark">Danh Sách Phòng Chiếu</a>
			</div>
			
			<form:form action="${pageContext.request.contextPath}/admin/phongchieu/update" method="post" modelAttribute="PhongchieuForm">
			
				<div class="form-group">
					<form:label path="maPhongChieu" class="col-form-label">Mã Phòng Chiếu</form:label>
					<form:input path="maPhongChieu" name="maPhongChieu" id="maPhongChieu" class="form-control" readonly="true"/>
					<div class="text-danger">
						<form:errors path="maPhongChieu" ></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<form:label path="tenPhong" class="col-form-label">Tên Phòng</form:label>
					<form:input path="tenPhong" name="tenPhong" id="tenPhong" class="form-control"/>
					<div class="text-danger">
						<form:errors path="tenPhong" ></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<form:label path="hangPhong" class="col-form-label">Hạng Phòng</form:label>
					<form:input path="hangPhong" name="hangPhong" id="hangPhong" class="form-control"/>
					<div class="text-danger">
						<form:errors path="hangPhong" ></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<form:label path="soLuongGhe" class="col-form-label">Số Lượng Ghế</form:label>
					<form:input type="number" path="soLuongGhe" name="soLuongGhe" id="soLuongGhe" class="form-control"/>
					<div class="text-danger">
						<form:errors path="soLuongGhe"></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<form:label path="donGia" class="col-form-label">Đơn Giá</form:label>
					<form:input path="donGia" name="donGia" id="donGia" class="form-control"/>
					<div class="text-danger">
						<form:errors path="donGia"></form:errors>
					</div>
				</div>
				
				
			
				
				
				<br>
				<div class="col-auto">
					<form:button name="add" id="add" class="btn btn-success">Update</form:button>
				</div>
			
			
			</form:form>
		</div>
	
	
	<%-- <%@ include file="/common/footer.jsp"%> --%>
	<script type="text/javascript">
		const maPCRegex = /^PC[0-9]{5}$/;
		
		$("#add").on("click", function(e) {
			if (!maPCRegex.test($("#maPhongChieu").val())){
				e.preventDefault();
				$("#maPhongChieu ~ .text-danger").html("Mã phòng chiếu chưa đúng format PCxxxxx")
			}
			
			if ($("#maPhongChieu").val() == ""){
				e.preventDefault();
				$("#maPhongChieu ~ .text-danger").html("Vui lòng nhập mã phòng chiếu!")
			}
			
			if ($("#hangPhong").val() > 10){
				e.preventDefault();
				$("#hangPhong ~ .text-danger").html("Vui lòng nhập hạng phòng tối đa 10 kí tự!")
			}
			
			if ($("#hangPhong").val() == ""){
				e.preventDefault();
				$("#hangPhong ~ .text-danger").html("Vui lòng nhập hạng phòng!")
			}
			
			if ($("#tenPhong").val() > 20){
				e.preventDefault();
				$("#tenPhong ~ .text-danger").html("Vui lòng nhập tên phòng tối đa 20 kí tự!")
			}
			
			if ($("#tenPhong").val() == ""){
				e.preventDefault();
				$("#tenPhong ~ .text-danger").html("Vui lòng nhập tên phòng!")
			}
			
			if ($("#soLuongGhe").val() < 0){
				e.preventDefault();
				$("#soLuongGhe ~ .text-danger").html("Vui lòng nhập số lượng ghế lớn hơn 0!")
			}
			
			if ($("#soLuongGhe").val() == ""){
				e.preventDefault();
				$("#soLuongGhe ~ .text-danger").html("Vui lòng nhập số lượng ghế!")
			}
			
			if ($("#donGia").val() < 0){
				e.preventDefault();
				$("#donGia ~ .text-danger").html("Vui lòng nhập đơn giá lớn hơn 0!")
			}
			
			if ($("#donGia").val() == ""){
				e.preventDefault();
				$("#donGia ~ .text-danger").html("Vui lòng nhập đơn giá!")
			}
		})
		
	</script>
</body>
</html>