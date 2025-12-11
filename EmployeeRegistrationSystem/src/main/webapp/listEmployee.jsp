<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Of Employee</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<style>
	/* Floating alert styles */
	.floating-alert {
		position: fixed;
		top: 8%;
		left: 50%;
		transform: translate(-50%, -50%);
		z-index: 9999;
		max-width: 80%;
		padding: 15px 25px;
		text-align: center;
		opacity: 1;
		transition: opacity 0.8s ease;
		border-radius: 8px;
		box-shadow: 0 4px 20px rgba(0, 0, 0, 0.25);
		font-size: 18px;
	}

	/* Fixed width columns */
	table {
		table-layout: fixed; /* FIXED table layout */
		width: 100%;
	}
	
	th, td {
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		vertical-align: middle;
		padding: 8px;
	}
	
	/* Set fixed widths for columns (adjust px as you want) */
	th:nth-child(1), td:nth-child(1) { width: 50px; }      /* ID */
	th:nth-child(2), td:nth-child(2) { width: 150px; }     /* First Name */
	th:nth-child(3), td:nth-child(3) { width: 150px; }     /* Last Name */
	th:nth-child(4), td:nth-child(4) { width: 150px; }     /* Username */
	th:nth-child(5), td:nth-child(5) { width: 200px; }     /* Address */
	th:nth-child(6), td:nth-child(6) { width: 130px; }     /* Contact No */
	th:nth-child(7), td:nth-child(7) { width: 130px; }     /* Action */
	
	/* Tooltip spans full width */
	td span.tooltip-text {
		display: inline-block;
		width: 100%;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		cursor: default;
	}
	.tooltip-inner {
		max-width: 400px !important; /* allow large tooltip */
		white-space: normal !important; /* allow multiple lines */
	}
</style>
</head>
<body>
	<div class="container mt-5">
		<h2>Employee List</h2>
		<!-- Success Message -->
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success floating-alert" id="successMessage">${sessionScope.successMessage}</div>
			<c:remove var="successMessage" scope="session" />
		</c:if>
		<!-- Error Message -->
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger floating-alert" id="errorMessage">${sessionScope.errorMessage}</div>
			<c:remove var="errorMessage" scope="session" />
		</c:if>

		<c:choose>
			<c:when test="${not empty employeeList}">
				<table class="table table-bordered table-striped mt-3 align-middle">
					<thead class="table-dark">
						<tr>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Username</th>
							<th>Address</th>
							<th>Contact No</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="emp" items="${employeeList}" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td><span class="tooltip-text" data-bs-toggle="tooltip" title="${emp.firstName}">${emp.firstName}</span></td>
								<td><span class="tooltip-text" data-bs-toggle="tooltip" title="${emp.lastName}">${emp.lastName}</span></td>
								<td><span class="tooltip-text" data-bs-toggle="tooltip" title="${emp.userName}">${emp.userName}</span></td>
								<td><span class="tooltip-text" data-bs-toggle="tooltip" title="<c:out value='${emp.address}' />"><c:out value="${emp.address}" /></span></td>
								<td><span class="tooltip-text" data-bs-toggle="tooltip" title="${emp.contactNo}">${emp.contactNo}</span></td>
								<td>
									<a href="viewemployee?id=${emp.id}" class="text-primary me-2" title="View"><i class="bi bi-eye-fill"></i></a> 
									<a href="editemployee?id=${emp.id}" class="text-warning me-2" title="Edit"><i class="bi bi-pencil-square"></i></a>
									<a href="deleteemployee?id=${emp.id}" class="text-danger" onclick="return confirm('Are you sure you want to delete this employee?');" title="Delete"><i class="bi bi-trash-fill"></i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div class="alert alert-warning mt-3">No employee data available.</div>
			</c:otherwise>
		</c:choose>
		<a href="index.jsp" class="btn btn-primary mt-3">Back to Registration</a>
	</div>

	<!-- Bootstrap JS and Popper for tooltip -->
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
	<script>
		// Initialize Bootstrap tooltips
		var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
		var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
			return new bootstrap.Tooltip(tooltipTriggerEl)
		})
		// Fade out floating alerts after 3 seconds
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