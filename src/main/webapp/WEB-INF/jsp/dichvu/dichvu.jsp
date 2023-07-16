<!-- Project: Cinema WebApp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý dịch vụ</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/fontawesome/css/all.min.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style>
label {
	font-weight: bold;
}

small {
	color: red;
}

h2 {
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="row">
		<div class="col-4">
			<%@ include file="/common/sidebarDichVu.jsp"%>
		</div>
		<div class="col-8" style="margin-top: 140px; text-align: center;">
			<h2>Chào mừng bạn đến với trang quản lý dịch vụ</h2>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>
</body>
</html>