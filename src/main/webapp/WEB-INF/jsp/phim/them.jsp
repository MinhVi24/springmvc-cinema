<!--* Project: Cinema WebApp
	* Trang thêm một bộ phim mới-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Phim Mới</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style type="text/css">
#active:hover {
	background-color: #ccc;
}

.errors {
	color: red;
}

.rq {
	color: red;
}
</style>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/header.jsp"%>
	<div class="main-body"
		style="margin-top: 100px; margin-bottom: 100px; width: 500px">
		<!-- Form add -->
		<h1 align="center">Thêm Phim Mới</h1>
		<form:form
			action="${pageContext.request.contextPath}/admin/phim/themphim"
			method="post" enctype="multipart/form-data" modelAttribute="themphim">
			<div class="form-group">
				<form:label path="maPhim">Mã Phim<span class="rq">*</span> :</form:label>
				<form:input class="form-control" path="maPhim" />
				<form:errors path="maPhim" cssClass="errors" />
				<div class="errors">${msg3}</div>
			</div>
			<div class="form-group">
				<form:label path="tenPhim">Tên Phim<span class="rq">*</span> :</form:label>
				<form:input class="form-control" path="tenPhim" />
				<form:errors path="tenPhim" cssClass="errors" />
			</div>
			<div class="form-group">
				<form:label path="moTaPhim">Mô Tả Phim<span class="rq">*</span> :</form:label>
				<form:input class="form-control" path="moTaPhim" />
				<form:errors path="moTaPhim" cssClass="errors" />
			</div>
			<div class="form-group">
				<form:label path="noiDungPhim">Nội Dung Phim<span
						class="rq">*</span> :</form:label>
				<form:textarea rows="9" cols="70" wrap="true" class="form-control"
					path="noiDungPhim" id="txt2" />
				<form:errors path="noiDungPhim" cssClass="errors" />
			</div>
			<div class="form-group">
				<form:label path="daoDien">Đạo Diễn<span class="rq">*</span> :</form:label>
				<form:input class="form-control" path="daoDien" />
				<form:errors path="daoDien" cssClass="errors" />
			</div>
			<div class="form-group">
				<form:label path="ngayKhoiChieu">Ngày Khởi Chiếu<span
						class="rq">*</span> :</form:label>
				<form:input type="date" class="form-control" path="ngayKhoiChieu" />
				<div class="errors">${msg4}</div>
				<div class="errors">${msg6}</div>
			</div>
			<div class="form-group">
				<form:label path="ngayKetThuc">Ngày Kết Thúc<span
						class="rq">*</span> :</form:label>
				<form:input type="date" class="form-control" path="ngayKetThuc" />
				<form:errors path="ngayKetThuc" cssClass="errors" />
				<div class="errors">${msg}</div>
				<div class="errors">${msg1}</div>
			</div>
			<div class="form-group">
				<form:label path="thoiLuong">Thời Lượng<span
						class="rq">*</span> :</form:label>
				<form:input class="form-control" path="thoiLuong" />
				<div class="errors">${msg2}</div>
			</div>
			<div class="form-group">
				<label>Poster<span class="rq">*</span>
						:
				</label>
				<div>
				<button class="btn btn-info" style="color: black;"
						id="btn-file" type="button">
						<i class='bx bx-image-add' ></i> Hình Ảnh
					</button> <input name="shutdown" id="input-file" type="file"
					class="form-control hidden" accept="image/png, image/jpeg"
					style="display: none;">
					</div>
					<div id="mydiv" class="col-lg-4 col-sm-6 mb-4"></div>
				 <div class="errors" id="errors">${msg5}</div>
				</div>
			<div align="center" class="col-auto">
				<form:button class="btn btn-success" type="submit">Thêm Phim</form:button>
				</div>
			</table>
		</form:form>
		<!-- Form add -->
	</div>
	<script src="<c:url value="/resources/js/scripts.js"/>">
		
	</script>
</body>
<script type="text/javascript">
	$("#btn-file").click(function() {
		$("#input-file").trigger('click');
	});

	$("#input-file").change(function() {
		const mydiv = document.getElementById('mydiv');
		var image_x = document.getElementsByTagName('img');
        if(image_x.length>0){    
        image_x[0].parentNode.removeChild(image_x[0]);
            }
		var file = $(this)[0].files[0].name;
		var urlfile = $(this)[0].files[0];
		$("#btn-file").html('<i class="bx bx-image" ></i>' + file);
		const img = document.createElement('img');
		const div = document.getElementById('errors');
		mydiv.appendChild(img);
		img.src = URL.createObjectURL(urlfile);
		img.alt = file;
		img.style.maxWidth = '400px';
		img.style.maxHeight = '200px';
		div.style.display = 'none';
	});
</script>
</html>