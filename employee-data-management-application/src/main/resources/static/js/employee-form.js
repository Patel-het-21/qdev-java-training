const MAX_NAME_LENGTH = 50;
const MOBILE_LENGTH = 10;
const MIN_ADDR_LENGTH = 4;
const MAX_ADDR_LENGTH = 255;
const MAX_EMAIL_LENGTH = 254;

const validity = {
	firstName: false,
	lastName: false,
	dob: false,
	mobile: false,
	email: false,
	address1: false,
	address2: true,
	gender: false
};
const formInputs = ['firstName', 'lastName', 'dob', 'mobile', 'email', 'address1', 'address2'];

let isEditMode = false;
let originalData = {};
const API_URL = "http://localhost:9090/api/v1/employees";

// -------- HELPER TO GET QUERY PARAM --------
function getParam(name) {
	return new URLSearchParams(window.location.search).get(name);
}

// -------- UPDATE BUTTON LOGIC --------
function updateSubmitButton() {
	const allValid = Object.values(validity).every(v => v);
	const btn = document.getElementById("submitBtn");
	if (isEditMode) {
		const changed = hasChanges();
		btn.disabled = !(allValid && changed);
		btn.classList.toggle("enabled", allValid && changed);
	} else {
		btn.disabled = !allValid;
		btn.classList.toggle("enabled", allValid);
	}
}

// -------- ERROR HANDLING --------
function setError(id, errorId, msg) {
	document.getElementById(id).classList.add("input-error");
	document.getElementById(errorId).innerText = msg;
	validity[id] = false;
	updateSubmitButton();
}

function clearError(id, errorId) {
	document.getElementById(id).classList.remove("input-error");
	document.getElementById(errorId).innerText = "";
	validity[id] = true;
	updateSubmitButton();
}

// -------- VALIDATION FUNCTIONS --------
function validateRequired(input, errorId, fieldName, isMandatory = true) {
	const trimmedValue = input.value.trim();
	const id = input.id;
	if (trimmedValue.length === 0 || (id === 'dob' && !trimmedValue)) {
		if (isMandatory)
			setError(id, errorId, fieldName + " is required");
		else {
			clearError(id, errorId);
			validity[id] = true;
			updateSubmitButton();
		}
		return;
	}
	if (id === 'firstName' || id === 'lastName') validateName(input, errorId);
	else if (id === 'dob') calculateAge();
	else if (id === 'mobile') validateMobile();
	else if (id === 'email') validateEmail();
	else if (id === 'address1') validateAddress(input, errorId, true);
	else if (id === 'address2') validateAddress(input, errorId, false);
}

function validateName(input, errorId) {
	const value = input.value.trim();
	const id = input.id;
	if (value.length === 0) {
		validity[id] = false;
		updateSubmitButton();
		return;
	}
	if (value.length < 2) {
		setError(id, errorId, "Minimum 2 characters required");
		return;
	}
	if (value.length === MAX_NAME_LENGTH) {
		document.getElementById(id).classList.remove("input-error");
		document.getElementById(errorId).innerText = `Maximum length of ${MAX_NAME_LENGTH} characters reached.`;
		validity[id] = true;
		updateSubmitButton();
		return;
	}
	if (!/^[A-Za-z\s]+$/.test(value)) {
		setError(id, errorId, "Only letters and spaces allowed");
		return;
	}
	clearError(id, errorId);
}

function validateAddress(input, errorId, isMandatory) {
	const value = input.value.trim();
	const id = input.id;
	if (value.length === 0) {
		validity[id] = !isMandatory;
		updateSubmitButton();
		return;
	}
	if (value.length === MAX_ADDR_LENGTH) {
		document.getElementById(id).classList.remove("input-error");
		document.getElementById(errorId).innerText = `Maximum limit of ${MAX_ADDR_LENGTH} characters reached.`;
		validity[id] = true;
		updateSubmitButton();
		return;
	}
	if (value.length < MIN_ADDR_LENGTH) {
		setError(id, errorId, "Minimum " + MIN_ADDR_LENGTH + " characters required.");
		return;
	}
	clearError(id, errorId);
}

function calculateAge() {
	const dobInput = document.getElementById("dob");
	const dob = dobInput.value;
	if (!dob) {
		document.getElementById("age").value = "";
		clearError("dob", "dobError");
		validity.dob = false;
		updateSubmitButton();
		return;
	}
	const birth = new Date(dob),
		today = new Date();
	let age = today.getFullYear() - birth.getFullYear();
	if (today.getMonth() < birth.getMonth() || (today.getMonth() === birth.getMonth() && today.getDate() < birth.getDate()))
		age--;
	if (age < 18 || age > 110) {
		setError("dob", "dobError", "Age must be between 18 and 110");
		document.getElementById("age").value = "";
	} else {
		document.getElementById("age").value = age;
		clearError("dob", "dobError");
	}
}

function validateMobile() {
	const mobile = document.getElementById("mobile").value;
	if (mobile.length === 0) {
		validity.mobile = false;
		updateSubmitButton();
		return;
	}
	if (mobile.startsWith('0')) {
		setError("mobile", "mobileError", "Mobile number cannot start with 0.");
		return;
	}

	if (mobile.length < MOBILE_LENGTH) {
		setError("mobile", "mobileError", `Must be exactly ${MOBILE_LENGTH} digits. (${mobile.length}/${MOBILE_LENGTH})`);
		return;
	}

	if (mobile.length > MOBILE_LENGTH) {
		setError("mobile", "mobileError", `Maximum ${MOBILE_LENGTH} digits allowed.`);
		return;
	}
	if (!/^[1-9]\d{9}$/.test(mobile)) {
		setError("mobile", "mobileError", "Invalid mobile number");
		return;
	}
	clearError("mobile", "mobileError");
	checkMobileDuplicate(); // call duplicate check
}

function validateEmail() {
	const email = document.getElementById("email").value;
	if (email.length === 0) {
		validity.email = false;
		updateSubmitButton();
		return;
	}
	if (email.length === MAX_EMAIL_LENGTH) {
		document.getElementById("email").classList.remove("input-error");
		document.getElementById("emailError").innerText = `Maximum standard length of ${MAX_EMAIL_LENGTH} characters reached.`;
		validity.email = true;
		updateSubmitButton();
		return;
	}
	if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
		setError("email", "emailError", "Invalid email");
		return;
	}
	clearError("email", "emailError");
	checkEmailDuplicate(); // call duplicate check
}

function setGender() {
	validity.gender = true;
	updateSubmitButton();
}

// -------- FORM RESET / CANCEL --------
function clearForm() {
	document.getElementById('employeeForm').reset();

	formInputs.forEach(id => document.getElementById(id).classList.remove("input-error"));
	document.getElementById('age').value = '';
	['fnError', 'lnError', 'dobError', 'mobileError', 'emailError', 'addr1Error', 'addr2Error', 'genderError'].forEach(e => document.getElementById(e).innerText = '');
	Object.keys(validity).forEach(k => validity[k] = false);
	validity.address2 = true;
	updateSubmitButton();
}

function goToList() {
	window.location.href = "employee-list";
}

// -------- EDIT MODE --------
function loadEmployeeForEdit(id) {
	axios.get(API_URL + "/" + id)
		.then(res => {
			const emp = res.data;
			isEditMode = true;
			document.getElementById("firstName").value = emp.firstName;
			document.getElementById("lastName").value = emp.lastName;
			document.getElementById("dob").value = emp.dateOfBirth;
			document.getElementById("mobile").value = emp.mobile;
			document.getElementById("email").value = emp.email;
			document.getElementById("address1").value = emp.address1;
			document.getElementById("address2").value = emp.address2 || "";
			document.getElementById("age").value = emp.age;
			document.querySelector('input[name="gender"][value="' + emp.gender + '"]').checked = true;
			originalData = {
				firstName: emp.firstName,
				lastName: emp.lastName,
				dob: emp.dateOfBirth,
				mobile: emp.mobile,
				email: emp.email,
				address1: emp.address1,
				address2: emp.address2 || "",
				gender: emp.gender
			};
			Object.keys(validity).forEach(k => validity[k] = true);
			document.getElementById("formHeading").innerText = "Update " + emp.firstName + " " + emp.lastName;
			renderButtons();
		});
}

function hasChanges() {
	return (document.getElementById("firstName").value !== originalData.firstName ||
		document.getElementById("lastName").value !== originalData.lastName ||
		document.getElementById("dob").value !== originalData.dob ||
		document.getElementById("mobile").value !== originalData.mobile ||
		document.getElementById("email").value !== originalData.email ||
		document.getElementById("address1").value !== originalData.address1 ||
		document.getElementById("address2").value !== originalData.address2 ||
		document.querySelector('input[name="gender"]:checked')?.value !== originalData.gender);
}

// -------- DUPLICATE CHECK API --------
function checkEmailDuplicate() {
	const email = document.getElementById("email").value.trim();
	if (email.length === 0) return;
	let params = {
		email: email
	};
	if (isEditMode) {
		const id = getParam("id");
		if (id)
			params.id = id;
	}
	axios.get(API_URL + "/check-email", {
		params: params
	})
		.then(res => {
			if (res.data === true) {
				setError("email", "emailError", "Email already exists");
			} else {
				clearError("email", "emailError");
			}
		})
		.catch(err => console.error("Email check failed", err));
}

function checkMobileDuplicate() {
	const mobile = document.getElementById("mobile").value.trim();
	if (mobile.length === 0)
		return;
	let params = {
		mobile: mobile
	};
	if (isEditMode) {
		const id = getParam("id");
		if (id)
			params.id = id;
	}
	axios.get(API_URL + "/check-mobile", {
		params: params
	})
		.then(res => {
			if (res.data === true) {
				setError("mobile", "mobileError", "Mobile number already exists");
			} else {
				clearError("mobile", "mobileError");
			}
		})
		.catch(err => console.error("Mobile check failed", err));
}

// -------- DYNAMIC BUTTON RENDER --------
function renderButtons() {
	const btnContainer = document.getElementById("formButtons");
	btnContainer.innerHTML = "";
	if (isEditMode) {
		const cancelBtn = document.createElement("button");
		cancelBtn.type = "button";
		cancelBtn.className = "btn btn-clear";
		cancelBtn.innerText = "Cancel";
		cancelBtn.onclick = goToList;
		const updateBtn = document.createElement("button");
		updateBtn.type = "submit";
		updateBtn.id = "submitBtn";
		updateBtn.className = "btn btn-submit";
		updateBtn.disabled = true;
		updateBtn.innerText = "Update Employee";
		btnContainer.appendChild(cancelBtn);
		btnContainer.appendChild(updateBtn);
	} else {
		const clearBtn = document.createElement("button");
		clearBtn.type = "button";
		clearBtn.className = "btn btn-clear";
		clearBtn.innerText = "Clear Form";
		clearBtn.onclick = clearForm;
		const registerBtn = document.createElement("button");
		registerBtn.type = "submit";
		registerBtn.id = "submitBtn";
		registerBtn.className = "btn btn-submit";
		registerBtn.disabled = true;
		registerBtn.innerText = "Register Employee";
		btnContainer.appendChild(clearBtn);
		btnContainer.appendChild(registerBtn);
	}
}

document.addEventListener("DOMContentLoaded", function() {
	const id = getParam("id");
	if (id)
		loadEmployeeForEdit(id);
	else
		renderButtons();

	const mobileInput = document.getElementById('mobile');

	mobileInput.addEventListener('keypress', function(event) {
		if (event.key < '0' || event.key > '9') {
			if (event.keyCode > 31 && (event.keyCode < 48 || event.keyCode > 57)) {
				event.preventDefault();
			}
		}
	});
	mobileInput.addEventListener('input', function() {
		this.value = this.value.replace(/\D/g, '');
		validateMobile();
		if (isEditMode)
			updateSubmitButton();
	});

	document.querySelectorAll('input,textarea').forEach(inp => inp.addEventListener('input', () => {
		if (isEditMode) updateSubmitButton();
	}));
});

document.addEventListener("DOMContentLoaded", function() {
	const form = document.getElementById("employeeForm");
	if (form) {
		form.addEventListener("submit", function(e) {
			e.preventDefault();

			if (!Object.values(validity).every(v => v)) {
				alert("Please fix validation errors before submitting");
				return;
			}

			const data = {
				firstName: document.getElementById("firstName").value.trim(),
				lastName: document.getElementById("lastName").value.trim(),
				dateOfBirth: document.getElementById("dob").value,
				mobile: document.getElementById("mobile").value.trim(),
				email: document.getElementById("email").value.trim(),
				address1: document.getElementById("address1").value.trim(),
				address2: document.getElementById("address2").value.trim(),
				gender: document.querySelector('input[name="gender"]:checked')?.value
			};

			if (isEditMode) {
				const id = getParam("id");
				console.log(API_URL + "/" + id);
				axios.put(API_URL + "/" + id, data)
					.then(res => {
						sessionStorage.setItem('successMessage', 'Employee updated successfully');
						window.location.href = "employee-list";
					})
					.catch(err => {
						console.error(err);
						alert("Update failed. Please check the form.");
					});
			} else {
				console.log(API_URL);
				axios.post(API_URL, data)
					.then(res => {
						sessionStorage.setItem('successMessage', 'Employee registered successfully');
						window.location.href = "employee-list";
					})
					.catch(err => {
						console.error(err);
						alert("Registration failed. Please check the form.");
					});
			}

		});
	}
});