<!-- 
	 * Project: Cinema WebApp
	 * JSP: màn hình thống kê 
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin thống kê</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<style>
#sidebar {
	margin: 145px 0 100px 40px;
}

.dropdown {
	position: relative;
	display: block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #212529;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: white;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="row col-12">
		<!-- Sidebar -->
		<%@ include file="/common/thongkesidebar.jsp"%>
	<!-- Sidebar -->
	<div class="col-8"></div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>
</body>
</html>