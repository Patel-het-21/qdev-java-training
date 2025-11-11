<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<h2 class="text-center mb-4">Employee Details</h2>

		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger text-center">${errorMessage}</div>
		</c:if>

		<c:if test="${not empty employee}">
			<div class="card shadow-sm p-4">
				<h4 class="mb-3 text-primary">${employee.firstName}
					${employee.lastName}</h4>

				<p>
					<strong>Username:</strong> ${employee.userName}
				</p>
				<p>
					<strong>Address:</strong> ${employee.address}
				</p>
				<p>
					<strong>Contact No:</strong> ${employee.contactNo}
				</p>
			</div>
		</c:if>

		<div class="text-center mt-4">
			<a href="listemployee" class="btn btn-secondary">Back to Employee
				List</a>
		</div>
	</div>
</body>
</html>
