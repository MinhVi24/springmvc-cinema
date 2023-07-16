<!--* Project: Cinema WebApp
	* Trang danh sách tìm kiếm phim đang chiếu-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/header.jsp"%>



	<div class="main-body"
		style="margin-top: 100px; margin-bottom: 100px; width: 80%">
		<h1 style="color: white;">Danh Sách Phim</h1>
		<div class="mb-3">
			<a href="them" class="btn" id="button">Thêm Mới Phim</a> <a
				href="listPhimDangChieu" class="btn" id="button">Phim Đang
				Chiếu</a> <a href="listPhimSapChieu" class="btn" id="button">Phim
				Sắp Chiếu</a>
		</div>
		<div>
			<form class="d-flex"
				action="${pageContext.request.contextPath}/admin/phim/searchPhimDangChieu"
				method="get">
				<input type="search" aria-label="Search"
					aria-describedby="search-addon" value="${searchKey}"
					name="searchKey" class="form-control" placeholder="Tên Phim">
				<input type="submit" value="Search" class="btn mx-2" id="button">
			</form>
		</div>

		<hr>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">MÃ PHIM</th>
					<th scope="col">TÊN PHIM</th>
					<th scope="col">MÔ TẢ PHIM</th>
					<th scope="col">ĐẠO DIỄN</th>
					<th scope="col">NGÀY KHỞI CHIẾU</th>
					<th scope="col">NGÀY KẾT THÚC</th>
					<th scope="col">THỜI LƯỢNG</th>
					<th scope="col">EDIT</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${listThongTinPhim}" varStatus="status">
					<tr style="border: 1px black solid;">
						<td scope="col">${c.maPhim}</td>
						<td scope="col">${c.tenPhim}</td>
						<td scope="col">${c.moTaPhim}</td>
						<td scope="col">${c.daoDien}</td>
						<td scope="col">${c.ngayKhoiChieu}</td>
						<td scope="col">${c.ngayKetThuc}</td>
						<td scope="col">${c.thoiLuong}</td>
						<td scope="col"><form
								action="${pageContext.request.contextPath}/admin/phim/phimupdatedangchieu/${c.maPhim}">
								<button type="submit" class="btn btn-primary">Edit</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<br>
		<div align="center" style="color: red;">${msg1}</div>
		<div class="pagination">
			<c:forEach begin="1" end="${totalPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class="active active-red"> ${i} </a>
					</c:when>
					<c:otherwise>
							<a href="searchPhimDangChieu?page=${i}&searchKey=${searchKey}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>



</body>
</html>