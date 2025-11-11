<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Of Employee</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2>Employee List</h2>

		<!-- Success Message -->
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success text-center" id="successMessage">
				${successMessage}</div>
			<c:remove var="successMessage" scope="request" />
		</c:if>

		<!-- Error Message -->
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger text-center" id="errorMessage">
				${errorMessage}</div>
			<c:remove var="errorMessage" scope="request" />
		</c:if>

		<c:choose>
			<c:when test="${not empty employeeList}">
				<table class="table table-bordered table-striped mt-3">
					<thead class="table-dark">
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Username</th>
							<th>Address</th>
							<th>Contact No</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="emp" items="${employeeList}">
							<tr>
								<td>${emp.firstName}</td>
								<td>${emp.lastName}</td>
								<td>${emp.userName}</td>
								<td>${emp.address}</td>
								<td>${emp.contactNo}</td>
								<td><a href="viewemployee?id=${emp.id}">View</a> | <a
									href="editemployee?id=${emp.id}">Edit</a> | <a
									href="deleteemployee?id=${emp.id}"
									onclick="return confirm('Are you sure you want to delete this employee?');">
										Delete </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>

			<c:otherwise>
				<div class="alert alert-warning mt-3">No employee data
					available.</div>
			</c:otherwise>
		</c:choose>

		<a href="index.jsp" class="btn btn-primary mt-3">Back to
			Registration</a>
	</div>

	<!-- JavaScript to auto-hide messages after 3 seconds -->
	<script>
		setTimeout(function() {
		var successMsg = document.getElementById("successMessage");
			if (successMsg) {
				successMsg.style.transition = "opacity 1s";
				successMsg.style.opacity = "0";
				setTimeout(() => successMsg.style.display = "none", 1000);
			}
			var errorMsg = document.getElementById("errorMessage");
			if (errorMsg) {
				errorMsg.style.transition = "opacity 1s";
				errorMsg.style.opacity = "0";
				setTimeout(() => errorMsg.style.display = "none", 1000);
			}
		}, 3000); // Message visible for 3 seconds
	</script>
</body>
</html>
