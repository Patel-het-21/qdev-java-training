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

/**
 * Retrieve the value of a query parameter from the current page URL.
 * @param {string} name - The query parameter name to look up.
 * @returns {string|null} The parameter value if present, or `null` if absent.
 */
function getParam(name) {
	return new URLSearchParams(window.location.search).get(name);
}

/**
 * Update the submit button's disabled state and "enabled" CSS class based on form validity and mode.
 *
 * In edit mode, the button is enabled only when all fields are valid and the form values have changed; in create mode, it is enabled when all fields are valid. The function also toggles the "enabled" class to reflect the button's enabled state.
 */
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

/**
 * Mark a form input as errored, display an error message, and update form validity state.
 *
 * Adds an error visual state to the input identified by `id`, sets the error text on the element
 * with `errorId`, marks the corresponding validity flag as false, and refreshes the submit button state.
 *
 * @param {string} id - The DOM id of the input element to mark as errored.
 * @param {string} errorId - The DOM id of the element where the error message should be shown.
 * @param {string} msg - The error message to display.
 */
function setError(id, errorId, msg) {
	document.getElementById(id).classList.add("input-error");
	document.getElementById(errorId).innerText = msg;
	validity[id] = false;
	updateSubmitButton();
}

/**
 * Clear the validation error UI for a form field and refresh the submit button state.
 *
 * Removes the "input-error" class from the input with the given id, clears the text
 * of the error message element identified by errorId, marks the field as valid in the
 * validity map, and calls updateSubmitButton().
 *
 * @param {string} id - ID of the input element to clear error styling from.
 * @param {string} errorId - ID of the element that displays the field's error message.
 */
function clearError(id, errorId) {
	document.getElementById(id).classList.remove("input-error");
	document.getElementById(errorId).innerText = "";
	validity[id] = true;
	updateSubmitButton();
}

/**
 * Validate that a form input satisfies requiredness and then trigger the appropriate field validation.
 *
 * If the input is empty and `isMandatory` is true, marks the field with an error message.
 * If the input is empty and `isMandatory` is false, clears any error and marks the field valid.
 * If the input has content, invokes the field's specific validation logic, updates validity state, and refreshes the submit button state.
 *
 * @param {HTMLInputElement|HTMLTextAreaElement|HTMLSelectElement} input - The form control to validate.
 * @param {string} errorId - The id of the element used to display the field's error message.
 * @param {string} fieldName - Human-friendly name of the field used in error text (e.g., "First name").
 * @param {boolean} [isMandatory=true] - Whether the field must be non-empty; optional fields will be cleared and marked valid when empty.
 */
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

/**
 * Validate a name input field and update its validity state and error UI.
 *
 * Enforces a minimum of 2 characters, a maximum of MAX_NAME_LENGTH (shows a max-length message when reached),
 * and allows only letters and spaces. An empty trimmed value marks the field invalid and triggers submit-button update.
 *
 * @param {HTMLInputElement} input - The text input element containing the name to validate.
 * @param {string} errorId - The id of the element used to display validation messages for this field.
 */
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

/**
 * Validate an address input's length and update its error state and the form's validity.
 * 
 * Checks optional/mandatory status, enforces minimum and maximum address length limits,
 * sets or clears the visible error message for the field, updates the corresponding
 * validity flag, and refreshes the submit button state.
 * 
 * @param {HTMLInputElement|HTMLTextAreaElement} input - The address input element to validate.
 * @param {string} errorId - The DOM id of the element where validation messages are displayed.
 * @param {boolean} isMandatory - Whether the address field is required (true = required).
 */
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

/**
 * Calculate the age from the "dob" input, enforce age bounds, and update related UI state.
 *
 * Reads the value of the #dob input, computes the age, and writes it to the #age input.
 * If the DOB is empty, clears the age field, clears the DOB error state, marks the DOB as invalid, and updates the submit button.
 * If the computed age is less than 18 or greater than 110, clears the age field and sets a DOB error message; otherwise clears any DOB error and displays the computed age.
 */
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

/**
 * Validate the mobile input field and update related form error state and controls.
 *
 * Reads the value of the #mobile input, enforces presence, disallows a leading '0',
 * requires exactly the configured number of digits, and ensures the value matches
 * the numeric mobile pattern. On validation failure it sets a field error and marks
 * the mobile field invalid; on success it clears any error and triggers a server-side
 * duplicate check.
 *
 * Side effects: updates `validity.mobile`, calls `updateSubmitButton()`, uses
 * `setError()` / `clearError()` to show/hide messages, and calls `checkMobileDuplicate()`
 * when the value is valid.
 */
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

/**
 * Validate the email input, update its error state and form validity, and trigger a duplicate check when valid.
 *
 * Performs these observable behaviours:
 * - If the field is empty, marks email as invalid and refreshes the submit button state.
 * - If the field has reached the configured maximum length, clears input error, shows a max-length message, marks email as valid, and refreshes the submit button state.
 * - If the field has an invalid email format, sets an error message and marks the field invalid.
 * - If the field is valid, clears any error and invokes a server-side duplicate check.
 */
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

/**
 * Mark the gender field valid and update the submit button state.
 *
 * Sets the gender validity flag and re-evaluates whether the form submit control should be enabled.
 */
function setGender() {
	validity.gender = true;
	updateSubmitButton();
}

/**
 * Reset the employee form to its initial state and refresh validation state.
 *
 * Clears all form inputs, removes input error styling, clears visible error messages, resets internal validity flags (marks the optional address2 as valid), and updates the submit button state.
 */
function clearForm() {
	document.getElementById('employeeForm').reset();

	formInputs.forEach(id => document.getElementById(id).classList.remove("input-error"));
	document.getElementById('age').value = '';
	['fnError', 'lnError', 'dobError', 'mobileError', 'emailError', 'addr1Error', 'addr2Error', 'genderError'].forEach(e => document.getElementById(e).innerText = '');
	Object.keys(validity).forEach(k => validity[k] = false);
	validity.address2 = true;
	updateSubmitButton();
}

/**
 * Navigate the browser to the employee list page.
 */
function goToList() {
	window.location.href = "employee-list";
}

/**
 * Load an employee by id and populate the form for editing.
 *
 * Fetches the employee record from the server, switches the UI into edit mode, fills the form inputs (including age and gender),
 * saves the fetched values to `originalData` for change detection, marks all validity flags as true, updates the form heading, and re-renders action buttons.
 * @param {number|string} id - Employee identifier to retrieve from the API.
 */
function loadEmployeeForEdit(id) {
	axios.get(API_URL + "/" + id, { withCredentials: true })
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

/**
 * Determine whether any tracked form field value differs from the originally loaded employee data.
 *
 * Checks firstName, lastName, dob, mobile, email, address1, address2, and gender for differences.
 * @returns {boolean} `true` if any tracked field has changed compared to `originalData`, `false` otherwise.
 */
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

/**
 * Checks whether the current email is already in use by another employee.
 *
 * If the email input is non-empty, queries the server for duplicates and, based on the response,
 * marks the email field as errored with the message "Email already exists" when a duplicate is found
 * or clears any existing email error when the email is unique. In edit mode, the current employee's
 * id is included so the check excludes the employee being edited.
 */
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
		params: params,
		withCredentials: true
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

/**
 * Checks whether the current mobile input value is already used by another employee and updates the mobile field's error state.
 *
 * If the mobile input is empty the function returns immediately. When in edit mode the current employee `id` is included from the URL query to exclude that record from the duplicate check. On a positive duplicate result the mobile field is marked with an error message; otherwise the mobile error is cleared.
 */
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
		params: params,
		withCredentials: true
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

/**
 * Render the form action buttons appropriate for the current mode (create or edit).
 *
 * In edit mode this displays Cancel and Update Employee buttons; in create mode it displays Clear Form and Register Employee buttons.
 * The submit button is initialized disabled and action buttons are wired to their respective handlers (navigation, form reset, or submit).
 */
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
				axios.put(API_URL + "/" + id, data, { withCredentials: true })
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
				axios.post(API_URL, data, { withCredentials: true })
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