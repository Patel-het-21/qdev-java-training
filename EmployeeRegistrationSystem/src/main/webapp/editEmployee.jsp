<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Employee</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center mb-4">Edit Employee</h2>
		<!-- General error message -->
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger text-center">${errorMessage}</div>
		</c:if>

		<form action="updateEmployee" method="post" class="card p-4 shadow-sm">
			<input type="hidden" name="id" value="${not empty id ? id :employee.id}">
			<!-- First Name -->
			<div class="mb-3">
				<label class="form-label">First Name</label> <input type="text" class="form-control" name="firstName" value="${not empty firstName ? firstName : employee.firstName}">
				<c:if test="${not empty errors.firstNameError}">
					<div class="text-danger mt-1">${errors.firstNameError}</div>
				</c:if>
			</div>
			<!-- Last Name -->
			<div class="mb-3">
				<label class="form-label">Last Name</label> <input type="text" class="form-control" name="lastName" value="${not empty lastName ? lastName : employee.lastName}">
				<c:if test="${not empty errors.lastNameError}">
					<div class="text-danger mt-1">${errors.lastNameError}</div>
				</c:if>
			</div>
			<!-- Username -->
			<div class="mb-3">
				<label class="form-label">Username</label> <input type="text" class="form-control" name="userName" value="${not empty userName ? userName : employee.userName}">
				<c:if test="${not empty errors.userNameError}">
					<div class="text-danger mt-1">${errors.userNameError}</div>
				</c:if>
			</div>
			<!-- Password -->
			<div class="mb-3">
				<label class="form-label">Password</label> <input type="password" class="form-control" name="password" value="${not empty password ? password : employee.password}">
				<c:if test="${not empty errors.passwordError}">
					<div class="text-danger mt-1">${errors.passwordError}</div>
				</c:if>
			</div>
			<!-- Address -->
			<div class="mb-3">
				<label class="form-label">Address</label>
				<textarea class="form-control" name="address" rows="3">${not empty address ? address : employee.address}</textarea>
			</div>
			<!-- Contact Number -->
			<div class="mb-3">
				<label class="form-label">Contact No</label> <input type="text" class="form-control" name="contactNo" value="${not empty contactNo ? contactNo : employee.contactNo}">
				<c:if test="${not empty errors.contactNoError}">
					<div class="text-danger mt-1">${errors.contactNoError}</div>
				</c:if>
			</div>
			<!-- Buttons -->
			<div class="text-center">
				<button type="submit" class="btn btn-primary">Update</button>
				<a href="listemployee" class="btn btn-secondary">Cancel</a>
			</div>
		</form>
	</div>
</body>
</html>