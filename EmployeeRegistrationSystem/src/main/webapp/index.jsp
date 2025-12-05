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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
				            Update Employee
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

				<form action="${isEdit ? 'updateEmployee' : 'registeremployee'}"
					method="post" id="employeeRegister">

					<c:if test="${isEdit}">
						<input type="hidden" name="id" id="empIdHidden"
							value="${employee.id}">
					</c:if>


					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">First Name <span
							class="text-danger"> *</span>
						</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="firstName"
								id="firstNameInput"
								value="${isEdit ? employee.firstName : firstName}"> <span
								class="text-danger" id="firstNameAjaxError">${errors.firstNameError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Last Name <span
							class="text-danger"> *</span>
						</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="lastName"
								id="lastNameInput"
								value="${isEdit ? employee.lastName : lastName}"> <span
								class="text-danger" id="lastNameAjaxError">${errors.lastNameError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">User Name <span
							class="text-danger"> *</span>
						</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="userName"
								id="userNameInput"
								value="${isEdit ? employee.userName : userName}"> <span
								class="text-danger" id="userNameAjaxError">${errors.userNameError}</span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label"> Password <span
							class="text-danger"> *</span>
						</label>

						<div class="col-sm-9">
							<div class="input-group">
								<input type="password" class="form-control" id="password"
									name="password"
									value="${isEdit ? employee.password : password}"> <span
									class="input-group-text" style="cursor: pointer;"
									onclick="togglePassword()"> <i class="bi bi-eye"
									id="toggleIcon"></i>
								</span>
							</div>
							<span class="text-danger" id="passwordAjaxError">${errors.passwordError}</span>
						</div>
					</div>
					<script>
						function togglePassword() {
							const pwd = document.getElementById("password");
							const icon = document.getElementById("toggleIcon");

							if (pwd.type === "password") {
								pwd.type = "text"; // show password
								icon.classList.remove("bi-eye");
								icon.classList.add("bi-eye-slash");
							} else {
								pwd.type = "password"; // hide password
								icon.classList.remove("bi-eye-slash");
								icon.classList.add("bi-eye");
							}
						}
					</script>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Address</label>
						<div class="col-sm-9">
							<textarea rows="3" cols="40" class="form-control" name="address"
								id="addressInput">${isEdit ? employee.address : address}</textarea>
							<span class="text-danger" id="addressErrorSpan">${errors.addressError}</span>
						</div>
					</div>

					<div class="mb-3 row align-items-center">
						<label class="col-sm-3 col-form-label">Contact No<span
							class="text-danger"> *</span></label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="contactNoInput"
								name="contactNo"
								value="${isEdit ? employee.contactNo : contactNo}"
								pattern="[0-9]+" inputmode="numeric" maxlength="10"
								title="Please enter digits only"> <span
								class="text-danger" id="contactNoAjaxError">${errors.contactNoError}</span>
						</div>
					</div>

					<script>
						document.querySelector('input[name="contactNo"]')
								.addEventListener('input', function() {
									this.value = this.value.replace(/\D/g, '');
								});
					</script>

					<div class="d-flex justify-content-center mt-3">

						<input type="submit" value="${isEdit ? 'Update' : 'Submit'}"
							class="btn btn-primary">

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
		/* -------------------- TOGGLE PASSWORD -------------------- */
		function togglePassword() {
			const pwd = document.getElementById("password");
			const icon = document.getElementById("toggleIcon");
	
			if (pwd.type === "password") {
				pwd.type = "text";
				icon.classList.replace("bi-eye", "bi-eye-slash");
			} else {
				pwd.type = "password";
				icon.classList.replace("bi-eye-slash", "bi-eye");
			}
		}
	
		/* -------------------- LIVE REQUIRED FIELD VALIDATION -------------------- */
		
		function showRequired(inputId, errorId, message) {
			const input = document.getElementById(inputId);
			const error = document.getElementById(errorId);
			const value = input.value.trim();
		
			if (value.length === 0) {
				error.innerHTML = message;
				input.classList.add("input-error"); // ADD RED BORDER
				return false;
			}
		
			error.innerHTML = "";
			input.classList.remove("input-error"); // REMOVE RED BORDER
			return true;
		}
		
		/* -------------------- LENGTH VALIDATION -------------------- */
		
		/**
		 * Validates length restrictions for inputs.
		 *
		 * @param inputId - the ID of the input element
		 * @param errorId - ID of the span to show error
		 * @param fieldName - readable field name for error messages
		 * @param min - minimum allowed characters
		 * @param max - maximum allowed characters
		 */
		function validateLength(inputId, errorId, fieldName, min, max) {
			const input = document.getElementById(inputId);
			const value = input.value.trim();
		
			if (value.length === 0) {
				return false; // required validation will handle message
			}
		
			if (value.length < min) {
				document.getElementById(errorId).innerHTML =
					fieldName + " must be at least " + min + " characters";
				input.classList.add("input-error");
				return false;
			}
		
			if (value.length >= max) {
				document.getElementById(errorId).innerHTML =
					fieldName + " cannot exceed " + max + " characters";
				input.classList.add("input-error");
				return false;
			}
		
			document.getElementById(errorId).innerHTML = "";
			input.classList.remove("input-error");
			return true;
		}
		
		/* -------------------- PASSWORD VALIDATION -------------------- */
		function validatePassword(inputId, errorId) {
			const pwd = document.getElementById(inputId).value.trim();
		
			const minLength = 8;
			const maxLength = 30;
		
			// Regex: at least 8 chars, 1 uppercase, 1 lowercase, 1 digit, 1 special char
			const pwdRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
		
			if (pwd.length === 0) {
				document.getElementById(errorId).innerHTML = "Please enter Password";
				document.getElementById(inputId).classList.add("input-error");
				return false;
			}
			if (pwd.length < minLength) {
				document.getElementById(errorId).innerHTML =
					"Password must be at least " + minLength + " characters";
				document.getElementById(inputId).classList.add("input-error");
				return false;
			}
			if (pwd.length > maxLength) {
				document.getElementById(errorId).innerHTML =
					"Password cannot exceed " + maxLength + " characters";
				document.getElementById(inputId).classList.add("input-error");
				return false;
			}
			if (!pwdRegex.test(pwd)) {
				document.getElementById(errorId).innerHTML =
					"Password must include uppercase, lowercase, digit, and special character.";
				document.getElementById(inputId).classList.add("input-error");
				return false;
			}
		
			// All validations passed
			document.getElementById(errorId).innerHTML = "";
			document.getElementById(inputId).classList.remove("input-error");
			return true;
		}
		
		/* -------------------- USERNAME AJAX FUNCTION -------------------- */
		async function checkUsernameExists(userName, empId) {
			try {
				const res = await fetch("CheckUserServlet?userName=" + encodeURIComponent(userName) + "&id=" + empId);
				const data = await res.json();
				return data.userNameExists; // TRUE if exists
			} catch (e) {
				console.error("Username AJAX error:", e);
				return false;
			}
		}
		
		/* -------------------- CONTACT AJAX FUNCTION -------------------- */
		async function checkContactExists(contact, empId) {
			try {
				const res = await fetch("CheckUserServlet?contactNo=" + encodeURIComponent(contact) + "&id=" + empId);
				const data = await res.json();
				return data.contactNoExists; // TRUE if exists
			} catch (e) {
				console.error("Contact AJAX error:", e);
				return false;
			}
		}
		
		document.getElementById("firstNameInput").addEventListener("input", function() {
			if (showRequired("firstNameInput", "firstNameAjaxError", "Please enter First Name")) {
				validateLength("firstNameInput", "firstNameAjaxError", "First Name", 2, 50);
			}
		});
		
		document.getElementById("lastNameInput").addEventListener("input", function() {
			if (showRequired("lastNameInput", "lastNameAjaxError", "Please enter Last Name")) {
				validateLength("lastNameInput", "lastNameAjaxError", "Last Name", 2, 50);
			}
		});
		
		/* ADDRESS max=200 (optional field) */
		document.querySelector("textarea[name='address']").addEventListener("input", function() {
			validateLength("addressInput", "addressErrorSpan", "Address", 0, 200);
		});
		
		document.getElementById("password").addEventListener("input", function() {
			validatePassword("password", "passwordAjaxError");
		});
		
		/* -------------------- USERNAME AJAX CHECK -------------------- */
		let usernameValid = false;
		
		document.getElementById("userNameInput").addEventListener("input", async function() {
			let userName = this.value.trim();
			let empId = document.getElementById("empIdHidden") ? document.getElementById("empIdHidden").value : "";
			const errorEl = document.getElementById("userNameAjaxError");
		
			if (!showRequired("userNameInput", "userNameAjaxError", "Please enter User Name")) {
				usernameValid = false;
				return;
			}
		
			if (!validateLength("userNameInput", "userNameAjaxError", "Username", 4, 30)) {
				usernameValid = false;
				return;
			}
		
			const exists = await checkUsernameExists(userName, empId);
		
			if (exists) {
				usernameValid = false;
				errorEl.innerHTML = userName + " already exists. Enter another.";
				document.getElementById("userNameInput").classList.add("input-error");
			} else {
				usernameValid = true;
				errorEl.innerHTML = "";
				document.getElementById("userNameInput").classList.remove("input-error");
			}
		});
		
		/* -------------------- CONTACT AJAX CHECK -------------------- */
		
		let contactValid = false;
		
		document.getElementById("contactNoInput").addEventListener("input", async function() {
		
			this.value = this.value.replace(/\D/g, '');
		
			let contact = this.value.trim();
			let empId = document.getElementById("empIdHidden") ? document.getElementById("empIdHidden").value : "";
			const errorEl = document.getElementById("contactNoAjaxError");
		
			if (!showRequired("contactNoInput", "contactNoAjaxError", "Please enter Contact Number")) {
				contactValid = false;
				return;
			}
		
			if (contact.length < 10) {
				errorEl.innerHTML = "Contact number must be 10 digits";
				document.getElementById("contactNoInput").classList.add("input-error");
				contactValid = false;
				return;
			}
		
			const exists = await checkContactExists(contact, empId);
		
			if (exists) {
				contactValid = false;
				errorEl.innerHTML = contact + " already exists. Enter another.";
				this.classList.add("input-error");
			} else {
				contactValid = true;
				errorEl.innerHTML = "";
				this.classList.remove("input-error");
			}
		});
		
		/* -------------------- HARD LIMIT ENFORCER -------------------- */
		
		function enforceHardLimit(inputId, max) {
			const input = document.getElementById(inputId);
		
			input.addEventListener('keydown', function(e) {
				const controlKeys = ["Backspace", "ArrowLeft", "ArrowRight", "Delete", "Tab"];
				if (!controlKeys.includes(e.key) && this.value.length >= max) {
					e.preventDefault();
				}
			});
		
			input.addEventListener('paste', function(e) {
				const pasteData = (e.clipboardData || window.clipboardData).getData('text');
				if (this.value.length + pasteData.length > max) {
					e.preventDefault();
					this.value = (this.value + pasteData).slice(0, max);
				}
			});
		
			input.addEventListener('input', function() {
				if (this.value.length > max) {
					this.value = this.value.slice(0, max);
				}
			});
		}
		
		/* Apply for fields */
		enforceHardLimit("firstNameInput", 50);
		enforceHardLimit("lastNameInput", 50);
		enforceHardLimit("userNameInput", 30);
		enforceHardLimit("addressInput", 200);
		enforceHardLimit("contactNoInput", 10);
		enforceHardLimit("password", 30);
		
		
		/* -------------------- FINAL FORM VALIDATION BEFORE SUBMIT -------------------- */
		
		document.getElementById("employeeRegister").addEventListener("submit", async function(e) {
			/* PREVENT DEFAULT FIRST */
			e.preventDefault();
			/* For required fields, lengths, password */
			let validFields = true;
			/* For username uniqueness */
			let usernameValid = false;
			/* For contact number uniqueness */
			let contactValid = false;
		
			const empId = document.getElementById("empIdHidden")?.value || "";
			const userName = document.getElementById("userNameInput").value.trim();
			const contactNo = document.getElementById("contactNoInput").value.trim();
		
			/* ----------------- REQUIRED FIELDS ----------------- */
			if (!showRequired("firstNameInput", "firstNameAjaxError", "Please enter First Name")) validFields = false;
			if (!showRequired("lastNameInput", "lastNameAjaxError", "Please enter Last Name")) validFields = false;
			if (!showRequired("userNameInput", "userNameAjaxError", "Please enter User Name")) validFields = false;
			if (!showRequired("contactNoInput", "contactNoAjaxError", "Please enter Contact Number")) validFields = false;
		
			/* ----------------- LENGTH VALIDATION ----------------- */
			if (!validateLength("firstNameInput", "firstNameAjaxError", "First Name", 2, 50)) validFields = false;
			if (!validateLength("lastNameInput", "lastNameAjaxError", "Last Name", 2, 50)) validFields = false;
			if (!validateLength("userNameInput", "userNameAjaxError", "Username", 4, 30)) validFields = false;
			validateLength("addressInput", "addressErrorSpan", "Address", 0, 200);
		
			/* ----------------- PASSWORD VALIDATION ----------------- */
			if (!validatePassword("password", "passwordAjaxError")) validFields = false;
		
			/* ----------------- USERNAME AJAX CHECK ----------------- */
			if (userName.length >= 4) {
				const usernameExists = await checkUsernameExists(userName, empId);
				if (usernameExists) {
					document.getElementById("userNameAjaxError").innerHTML = userName + " already exists. Enter another.";
					document.getElementById("userNameInput").classList.add("input-error");
					usernameValid = false;
				} else {
					document.getElementById("userNameAjaxError").innerHTML = "";
					document.getElementById("userNameInput").classList.remove("input-error");
					usernameValid = true;
				}
			}
		
			/* ----------------- CONTACT AJAX CHECK ----------------- */
			if (contactNo.length === 10) {
				const contactExists = await checkContactExists(contactNo, empId);
				if (contactExists) {
					document.getElementById("contactNoAjaxError").innerHTML = contactNo + " already exists. Enter another.";
					document.getElementById("contactNoInput").classList.add("input-error");
					contactValid = false;
				} else {
					document.getElementById("contactNoAjaxError").innerHTML = "";
					document.getElementById("contactNoInput").classList.remove("input-error");
					contactValid = true;
				}
			} else {
				document.getElementById("contactNoAjaxError").innerHTML = "Contact must be 10 digits";
				document.getElementById("contactNoInput").classList.add("input-error");
				contactValid = false;
			}
		
			/* ----------------- FINAL CHECK ----------------- */
			if (validFields && usernameValid && contactValid) {
				this.submit();
			}
		}); 
	</script>

</body>
</html>