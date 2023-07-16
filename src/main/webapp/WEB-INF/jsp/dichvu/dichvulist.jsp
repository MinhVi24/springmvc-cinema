<!-- Project: Cinema WebApp-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách dịch vụ</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
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

h2 {
	
}

*:not(span) {
	font-size: 20px;
}

.popup {
	position: fixed;
	top: 5%;
	left: 90%;
	transform: translate(-50%, -50%);
	padding: 10px;
	background-color: #c0392b;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
	color: white;
	border-radius: 5px;
}
</style>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/header.jsp"%>
	<div
		class="main-body row col-10 d-flex flex-row justify-content-center">
		<div id="popup" style="display: none;">
			<p>Thêm dịch vụ thành công</p>
		</div>
		<div class="container">
			<div>
				<h2 class="section-heading text-uppercase fw-bold" align="center"
					style="font-size: 50px;">Danh sách dịch vụ</h2>
				<div class="">
					<div>
						<a href="${pageContext.request.contextPath}/admin/dichvu/them"
							class="btn" style="margin-bottom: -120px; font-size: 30px;">Thêm
							dịch vụ</a>
					</div>
					<div>
						<p>${messageUpdate}</p>
						<form
							action="${pageContext.request.contextPath}/admin/dichvu/timkiem"
							class="d-flex justify-content-end" id="searchForm">
							<input type="text" name="keySearch" value="${searchInput}" class="form-control"
								id="searchInput" style="width: 15%; height: 60px"
								placeholder="Nhập mã, tên hoặc đơn giá..."> <input
								type="submit" value="Tìm kiếm" style="font-size: 30px"
								class="btn btn-warning onclick="saveSearchValue()">
						</form>
					</div>
				</div>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th width="10%">MÃ DỊCH VỤ</th>
							<th width="10%">TÊN DỊCH VỤ</th>
							<th width="10%">MÔ TẢ</th>
							<th width="10%">ĐƠN GIÁ</th>
							<th width="10%">HÀNH ĐỘNG</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="abc" items="${dichvus}" varStatus="status">

							<tr>
								<td style="padding-top: 0.5%">${abc.maDichVu}</td>
								<td style="padding-top: 0.5%">${abc.tenDichVu}</td>
								<td style="padding-top: 0.5%">${abc.moTaDichVu}</td>
								<td style="padding-top: 0.5%">${abc.donGia}</td>
								<td><a
									href="${pageContext.request.contextPath}/admin/dichvu/capnhat/${abc.maDichVu}"
									onclick="return confirm('Bạn có muốn chỉnh sửa không?')"
									class="btn">CHỈNH SỬA</a> <a
									href="${pageContext.request.contextPath}/admin/dichvu/delete/${abc.maDichVu}"
									onclick="return confirm('Bạn có muốn xóa không?')" class="btn">XÓA</a></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<p style="color: red" align="center">${messageTimKiemDichVu}</p>
				<p style="color: green" align="center">${messageDeleteDichVu}</p>
				<p style="color: green" align="center">${messageUpdateDichVu}</p>
				<p style="color: green" align="center">${messageThemDichVu}</p>
				<p style="color: red" align="center">${DichVuDaDuocSuDung}</p>
				<p style="color: red" align="center">${DichVuDaDuocSuDung2}</p>
				<br />
				<div class="pagination">
					<%-- <c:if test="${currentPage > 1}">
						<a href="list?page=${currentPage-1}"
							class="btn btn-light btn-direction">&laquo; Previous</a>
					</c:if> --%>

					<c:forEach var="i" begin="1" end="${totalPages}" varStatus="status">
						<c:choose>
							<c:when test="${currentPage eq i}">
								<a class="active active-red">${i}</a>
							</c:when>
							<c:otherwise>
								<c:if
									test="${status.index <= 2 || status.index >= totalPages - 1}">
									<a href="list?page=${i}">${i}</a>
								</c:if>
								<%-- <c:if test="${status.index == 3 && totalPages > 5}">
							<span class="ellipsis">...</span>
						</c:if> --%>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<%-- <c:if test="${currentPage lt totalPages}">
						<a href="list?page=${currentPage+1}"
							class="btn btn-light btn-direction">Next &raquo;</a>
					</c:if> --%>
				</div>
			</div>

		</div>
	</div>

</body>
</html>