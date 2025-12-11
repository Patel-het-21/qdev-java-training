<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Employee Register Form</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<style>
	/* Add red border when input has error */
	.input-error {
		border-color: red !important;
		box-shadow: 0 0 5px rgba(255, 0, 0, 0.4) !important;
	}
	</style>
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<h2 class="mb-4 text-center">
					<c:choose>
						<c:when test="${isEdit}">
							Update ${employee.userName}
						</c:when>
						<c:otherwise>
							Employee Registration System
						</c:otherwise>
					</c:choose>
				</h2>

				<c:if test="${not empty successMessage}">
					<div class="alert alert-success">${successMessage}</div>
				</c:if>
				<c:set var="isEdit" value="${not empty employee}" />

				<form action="${isEdit ? 'updateEmployee' : 'registeremployee'}" method="post" id="employeeRegister">

					<c:if test="${isEdit}">
						<input type="hidden" name="id" id="empIdHidden" value="${employee.id}">
					</c:if>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">First Name <span class="text-danger required-star"> *</span> </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="firstName" id="firstNameInput" value="${isEdit ? employee.firstName : firstName}"> 
							<span class="text-danger error-span" id="firstNameAjaxError">${errors.firstNameError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Last Name <span class="text-danger required-star"> *</span> </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="lastName" id="lastNameInput" value="${isEdit ? employee.lastName : lastName}"> 
							<span class="text-danger error-span" id="lastNameAjaxError">${errors.lastNameError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">User Name <span class="text-danger required-star"> *</span> </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="userName" id="userNameInput" value="${isEdit ? employee.userName : userName}"> 
							<span class="text-danger error-span" id="userNameAjaxError">${errors.userNameError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label"> Password <span class="text-danger required-star"> *</span></label>
						<div class="col-sm-9">
							<div class="input-group">
								<input type="password" class="form-control" id="password" name="password" value="${isEdit ? employee.password : password}"> 
								<span class="input-group-text" style="cursor: pointer;" onclick="togglePassword()"> <i class="bi bi-eye" id="toggleIcon"></i></span>
							</div>
							<span class="text-danger error-span" id="passwordAjaxError">${errors.passwordError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Address</label>
						<div class="col-sm-9">
							<textarea rows="3" cols="40" class="form-control" name="address" id="addressInput">${isEdit ? employee.address : address}</textarea>
							<span class="text-danger error-span" id="addressErrorSpan">${errors.addressError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Contact No<span class="text-danger required-star"> *</span></label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="contactNoInput" name="contactNo" value="${isEdit ? employee.contactNo : contactNo}" pattern="\d{10}" inputmode="numeric" maxlength="10" title="Please enter digits only"> 
							<span class="text-danger error-span" id="contactNoAjaxError">${errors.contactNoError}</span>
						</div>
					</div>
					<div class="d-flex justify-content-center mt-3">
						<input type="submit" value="${isEdit ? 'Update' : 'Submit'}" class="btn btn-primary"id="submitBtn" disabled>
						<!-- Show Clear button ONLY in registration mode -->
						<c:if test="${not isEdit}">
							<button type="button" class="btn btn-warning ms-2" onclick="clearForm()">Clear</button>
						</c:if>
						<c:if test="${isEdit}">
							<a href="listemployee" class="btn btn-secondary ms-2">Cancel</a>
						</c:if>
					</div>
				</form>
				<h5>All Employees</h5>
				<a href="listemployee">View All Employees</a>
			</div>
		</div>
	</div>
	<script>
		const empIdHidden = "${isEdit ? employee.id : ''}";
		const isEditMode = ${isEdit ? 'true' : 'false'};
	</script>
	<script src="js/script.js"></script>
</html>