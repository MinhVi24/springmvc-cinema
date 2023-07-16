
<!-- 
	 * Project: Cinema WebApp
	 * JSP: màn hình thống kê doanh thu bán vé theo phim
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin thống kê doanh thu theo phim</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/display.css" />"
	rel="stylesheet" type="text/css">

<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>

<body class="d-flex flex-row justify-content-center">
	<%@ include file="/common/header.jsp"%>
	<div class="main-body row col-10 d-flex flex-row justify-content-center">
	<h1 class = "text-center">THỐNG KÊ DOANH THU THEO PHIM</h1>
	<br>
		<div class="col-12">
			<table id="doanhThuPhim" class="table table-bordered">
				<thead>
					<th>MÃ PHIM</th>
					<th>TÊN PHIM</th>
					<th>DOANH THU</th>
				</thead>
				<tbody id="tablePaging">
					<c:forEach var="c" items="${listDoanhThuPhim }">
						<tr>
							<td>${c.maPhim }</td>
							<td>${c.tenPhim }</td>
							<td><fmt:formatNumber value="${c.doanhThu }" pattern="#,##0"/> VNĐ</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		<div id="pagination"></div>
	</div>


	<script>
		var table = document.getElementById("tablePaging");
		var rowsPerPage = 15; // Số dòng hiển thị trên mỗi trang
		let currentPage = 1; // Trang hiện tại

		function displayTable() {
			  var startIndex = (currentPage - 1) * rowsPerPage;
			  var endIndex = startIndex + rowsPerPage;
			  var rows = table.rows;
			  
			  // Ẩn tất cả các dòng của bảng
			  for (var i = 0; i < rows.length; i++) {
			    if (i >= startIndex && i < endIndex) {
			      rows[i].style.display = "table-row";
			    } else {
			      rows[i].style.display = "none";
			    }
			  }
			}

			function createPagination() {
			  var pageCount = Math.ceil((table.rows.length - 1) / rowsPerPage); // Tính tổng số trang
			  // Tạo nút điều hướng trang trước
			  var prevButton = document.createElement("button");
			  prevButton.classList.add("btn");
			  prevButton.classList.add("btn-direction");
			  prevButton.innerHTML = "Trang trước";
			  prevButton.addEventListener("click", function() {
			    if (currentPage > 1) {
			      currentPage--;
			      displayTable();
			      createPagination();
			    }
			  });
			  
			  // Tạo nút điều hướng trang tiếp theo
			  var nextButton = document.createElement("button");
			  nextButton.classList.add("btn");
			  nextButton.classList.add("btn-direction");
			  nextButton.innerHTML = "Trang tiếp";
			  nextButton.addEventListener("click", function() {
			    if (currentPage < pageCount) {
			    	currentPage++;
			      displayTable();
			      createPagination();
			    }
			  });
			  
			  // Hiển thị trang hiện tại và tổng số trang
			  var pageInfo = document.createElement("span");
			  pageInfo.innerHTML = " Trang " + currentPage + " / " + pageCount +" ";
			  
			  // Thêm các phần tử vào phân trang
			  var pagination = document.getElementById("pagination");
			  pagination.innerHTML = "";
			  pagination.appendChild(prevButton);
			  pagination.appendChild(pageInfo);
			  pagination.appendChild(nextButton);
			}

			displayTable();
			createPagination();
		</script>
</body>
</html>