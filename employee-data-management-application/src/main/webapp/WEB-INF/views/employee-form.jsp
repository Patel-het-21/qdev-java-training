<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Employee Registration</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee-form.css">
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/employee-form.js" defer></script>
</head>
<body>
<div class="container">
	<h1 id="formHeading">Employee Registration</h1>
	<form id="employeeForm">
		<div class="row">
			<div class="form-group">
				<label for="firstName">First Name<span class="required-asterisk">*</span></label>
				<input id="firstName" name="firstName" maxlength="50" oninput="validateName(this,'fnError'); validateRequired(this,'fnError','First Name')" onblur="validateRequired(this,'fnError','First Name')" required>
				<div class="error" id="fnError"></div>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name<span class="required-asterisk">*</span></label>
				<input id="lastName" name="lastName" maxlength="50" oninput="validateName(this,'lnError'); validateRequired(this,'lnError','Last Name')" onblur="validateRequired(this,'lnError','Last Name')" required>
				<div class="error" id="lnError"></div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label for="dob">Date of Birth<span class="required-asterisk">*</span></label>
				<input type="date" id="dob" name="dob" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" oninput="calculateAge()" onblur="validateRequired(this,'dobError','Date of Birth')" required>
				<div class="error" id="dobError"></div>
			</div>
			<div class="form-group">
				<label for="age">Calculated Age</label>
				<input id="age" name="age" disabled placeholder="Age will show here">
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label for="mobile">Mobile Number<span class="required-asterisk">*</span></label>
				<input type="tel" id="mobile" name="mobile" maxlength="10" pattern="\d*" oninput="validateMobile(); validateRequired(this,'mobileError','Mobile Number')" onblur="validateRequired(this,'mobileError','Mobile Number'); checkMobileDuplicate()" required>
				<div class="error" id="mobileError"></div>
			</div>
			<div class="form-group">
				<label for="email">Email Address<span class="required-asterisk">*</span></label>
				<input type="email" id="email" name="email" oninput="validateEmail(); validateRequired(this,'emailError','Email Address')" onblur="validateRequired(this,'emailError','Email Address'); checkEmailDuplicate()" maxlength="254" required>
				<div class="error" id="emailError"></div>
			</div>
		</div>
		<div class="row full-width">
			<div class="form-group">
				<label for="address1">Address Line 1<span class="required-asterisk">*</span></label>
				<textarea id="address1" name="address1" maxlength="255" oninput="validateAddress(this,'addr1Error',true); validateRequired(this,'addr1Error','Address Line 1',true)" onblur="validateRequired(this,'addr1Error','Address Line 1',true)" required></textarea>
				<div class="error" id="addr1Error"></div>
			</div>
		</div>
		<div class="row full-width">
			<div class="form-group">
				<label for="address2">Address Line 2</label>
				<textarea id="address2" name="address2" maxlength="255" oninput="validateAddress(this,'addr2Error',false)" onblur="validateRequired(this,'addr2Error','Address Line 2',false)"></textarea>
				<div class="error" id="addr2Error"></div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label>Gender<span class="required-asterisk">*</span></label>
				<div class="gender-group">
					<label for="genderMale"><input type="radio" id="genderMale" name="gender" value="Male" onclick="setGender()"> Male</label>
					<label for="genderFemale"><input type="radio" id="genderFemale" name="gender" value="Female" onclick="setGender()"> Female</label>
					<label for="genderOther"><input type="radio" id="genderOther" name="gender" value="Other" onclick="setGender()"> Other</label>
				</div>
				<div class="error" id="genderError"></div>
			</div>
		</div>
		<div class="center">
			<div class="button-group" id="formButtons"></div>
			<a href="employee-list" class="view-all-link">View All Employees</a>
		</div>
	</form>
</div>
</body>
</html>