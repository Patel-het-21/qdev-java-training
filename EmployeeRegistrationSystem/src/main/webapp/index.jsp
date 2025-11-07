<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Register Form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<h2 class="mb-4 text-center">Employee Register System</h2>

				<c:if test="${not empty successMessage}">
					<div class="alert alert-success">${successMessage}</div>
				</c:if>

				<form action="register" method="post">

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">First Name</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="firstName"
								value="${firstName }"> <span class="text-danger">${errors.firstNameError}</span>
						</div>
					</div>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Last Name</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="lastName"
								value="${lastName}"> <span class="text-danger">${errors.lastNameError}</span>
						</div>
					</div>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">User Name</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="userName"
								value="${userName }"> <span class="text-danger">${errors.userNameError}</span>
						</div>
					</div>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Password</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" name="password"
								value="${password }"> <span class="text-danger">${errors.passwordError}</span>
						</div>
					</div>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Address</label>
						<div class="col-sm-9">
							<textarea rows="3" cols="40" class="form-control" name="address">${address }</textarea>
						</div>
					</div>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Contact No</label>
						<div class="col-sm-9">
							<input type="tel" class="form-control" name="contactNo"
								value="${contactNo }"> <span class="text-danger">${errors.contactNoError}</span>
						</div>
					</div>

					<div class="text-center">
						<input type="submit" value="Submit" class="btn btn-primary">
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>