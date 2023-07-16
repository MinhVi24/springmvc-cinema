
<!-- 
	 * Project: Cinema WebApp
	 * Team: 2
	 * Author : Ducnm74
	 * Method: màn hình hoàn tất đặt vé
-->
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
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<style type="text/css">
</style>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>

	<p> Số tiền thanh toán : ${soTien }</p>
	<p>${tinhTrang }</p>
	<p> Mã ghế: 
	<c:forEach var="c" items="${maGhe }">
		<span>${c}</span>
	</c:forEach>
	</p>
	<p>${maSuatChieu }</p>
	<!-- Bootstrap core JS-->
	<%-- <script src="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"/>"> --%>
	<!-- Core theme JS-->
	<script src="<c:url value="/resources/js/scripts.js"/>"></script>

	<%-- <script src="<c:url value="https://cdn.startbootstrap.com/sb-forms-latest.js"/>"></script> --%>
</body>
</html>