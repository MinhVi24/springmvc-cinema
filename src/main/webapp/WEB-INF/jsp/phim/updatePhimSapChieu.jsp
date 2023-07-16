<!--* Project: Cinema WebApp
	* Trang cập nhật phim sắp chiếu-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập Nhật Phim Sắp Chiếu</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style type="text/css">
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
			<!-- Form update -->
				<h1>Cập Nhật Phim</h1>
				<form:form
					action="${pageContext.request.contextPath}/admin/phim/updatephimsapchieu"
					method="post" enctype="multipart/form-data"
					modelAttribute="themphim">
					<div class="form-group">
						<form:label path="maPhim">Mã Phim<span
										class="rq">*</span> :</form:label>
						<form:input class="form-control"
									path="maPhim" readonly="true" />
							<form:errors path="maPhim"
									cssClass="errors" />
						</div>
						<div class="form-group">
							<form:label path="tenPhim">Tên Phim<span
										class="rq">*</span> :</form:label>
							<form:input class="form-control"
									path="tenPhim"  />
							<form:errors path="tenPhim"
									cssClass="errors" />
						</div>
						<div class="form-group">
							<form:label path="moTaPhim">Mô Tả Phim<span
										class="rq">*</span> :</form:label>
							<td class="col-6"><form:input class="form-control"
									path="moTaPhim"  />
							<td class="col-3"><form:errors path="moTaPhim"
									cssClass="errors" />
						</div>
						<div class="form-group">
							<form:label path="noiDungPhim">Nội Dung Phim<span
										class="rq">*</span> :</form:label>
							<form:textarea rows="9" cols="70" class="form-control"
									path="noiDungPhim" />
							<form:errors path="noiDungPhim"
									cssClass="errors" />
						</div>
						<div class="form-group">
							<form:label path="daoDien">Đạo Diễn<span
										class="rq">*</span> :</form:label>
							<form:input class="form-control"
									path="daoDien" />
							<form:errors path="daoDien"
									cssClass="errors" />
						</div>
						<div class="form-group">
							<form:label path="ngayKhoiChieu">Ngày Khởi Chiếu<span
										class="rq">*</span> :</form:label>
							<form:input type="date"
									class="form-control" path="ngayKhoiChieu" />
							<form:errors path="ngayKhoiChieu"
									cssClass="errors" />
								<div class="errors">${msg4}</div>
								<div class="errors">${msg7}</div>
						</div>
						<div class="form-group">
						<form:label path="ngayKetThuc">Ngày Kết Thúc<span
										class="rq">*</span> :</form:label>
							<form:input type="date"
									class="form-control" path="ngayKetThuc" />
							<form:errors path="ngayKetThuc"
									cssClass="errors" />
								<div class="errors">${msg}</div>
								<div class="errors">${msg1}</div>
						</div>
						<div class="form-group">
							<form:label path="thoiLuong">Thời Lượng<span
										class="rq">*</span> :</form:label>
							<form:input class="form-control"
									path="thoiLuong" />
							<div class="errors">${msg2}</div>
						</div>
						<div class="form-group">
							<label> Cập Nhật Poster<span
									class="rq">*</span> :
							</label>
							<select name="luachon" id="luachon" onchange="genderChanged(this)" class="form-select"
								aria-label="Default select example">
									<option value="">Vui Lòng Chọn</option>
									<option value="co">Có</option>
									<option value="khong">Không</option>
							</select>
							<p style="color: red" id="show_message">${msg5}</p>
						</div>
						<div class="form-group">
							<div id="loadfile" style="display: none;"><button class="btn btn-info mt-3"
									style="color: black" id="btn-file"
									type="button">
									<i class='bx bx-image-add' ></i> Hình Ảnh
								</button> <input name="shutdown" id="input-file" type="file"
								class="form-control hidden" accept="image/png, image/jpeg"
								style="display: none;">
								<div id="mydiv" class="col-lg-4 col-sm-6 mb-4"></div>
								</div>
								<div class="errors" id="errors">${msg6}</div>
						</div>
						<div align="center" class="col-auto mt-5">
							<form:button class="btn" type="submit">Cập Nhật Phim</form:button>
						</div>
				</form:form>
			<!-- Form update -->
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
<script type="text/javascript">
function genderChanged(obj)
{
    var ketqua = document.getElementById('loadfile');
    const div = document.getElementById('show_message');
    const divErrors = document.getElementById('errors');
    var value = obj.value;
    if (value === 'co'){
    	ketqua.style.display = 'block';
    	div.style.display = 'none';
    	divErrors.style.display = 'none';
    }
    else if (value === 'khong'||value === ''){
    	ketqua.style.display = 'none';
    	div.style.display = 'none';
    	divErrors.style.display = 'none';
    }
}
</script>
</html>