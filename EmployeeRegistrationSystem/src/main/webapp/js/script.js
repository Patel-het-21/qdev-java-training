/* TOGGLE PASSWORD */
function togglePassword() {
	const passwordInput = document.getElementById("password");
	const icon = document.getElementById("toggleIcon");
	if (passwordInput.type === "password") {
		passwordInput.type = "text";
		icon.classList.replace("bi-eye", "bi-eye-slash");
	} else {
		passwordInput.type = "password";
		icon.classList.replace("bi-eye-slash", "bi-eye");
	}
}

document.addEventListener("DOMContentLoaded", () => {
	/* GET ELEMENTS */
	const firstNameInput = document.getElementById("firstNameInput");
	const lastNameInput = document.getElementById("lastNameInput");
	const userNameInput = document.getElementById("userNameInput");
	const passwordInput = document.getElementById("password");
	const addressInput = document.getElementById("addressInput");
	const contactNoInput = document.getElementById("contactNoInput");
	const submitBtn = document.getElementById("submitBtn");
	/* empIdHidden and isEditMode come from JSP */
	const isEdit = isEditMode;
	/* ORIGINAL VALUES (For Update Mode) */
	const originalValues = {
		firstName: firstNameInput.value,
		lastName: lastNameInput.value,
		userName: userNameInput.value,
		password: passwordInput.value,
		address: addressInput.value,
		contactNo: contactNoInput.value
	};
	/* VALIDATION FLAGS */
	let validation = {
		firstName: false,
		lastName: false,
		userName: false,
		password: isEdit ? true : false,
		address: true,
		contactNo: false
	};
	/* SET / CLEAR ERROR */
	function setError(input, spanId, msg) {
		input.classList.add("input-error");
		document.getElementById(spanId).innerText = msg;
		validation[input.name] = false;
		updateButtons();
	}
	function clearError(input, spanId) {
		input.classList.remove("input-error");
		document.getElementById(spanId).innerText = "";
		validation[input.name] = true;
		updateButtons();
	}
	/* BUTTON ENABLE LOGIC */
	function updateButtons() {
		if (!isEdit) {
			// CREATE MODE: all fields must be valid
			submitBtn.disabled = !Object.values(validation).every(v => v);
			return;
		}
		// UPDATE MODE: button enabled only if:
		// 1) At least one field changed from original AND its validation is true
		// 2) AND all changed fields are valid
		let changedValid = false;
		const fields = ["firstName", "lastName", "userName", "address", "contactNo"];
		for (let key of fields) {
			const input = document.getElementById(key + "Input");
			// Field changed and valid
			if (input.value !== originalValues[key]) {
				if (!validation[key]) {
					// Field changed but invalid → disable submit
					submitBtn.disabled = true;
					return;
				}
				changedValid = true; // At least one changed and valid
			}
		}
		// Password field special case (optional in update)
		if (passwordInput.value.trim() !== "" && !validation.password) {
			submitBtn.disabled = true;
			return;
		}
		if (passwordInput.value.trim() !== "" && validation.password) {
			changedValid = true; // password changed and valid
		}
		// Enable button only if at least one change is valid and all changed fields are valid
		submitBtn.disabled = !changedValid;
	}
	/* AJAX FUNCTIONS */
	async function checkUsernameExists(userName, empIdHidden) {
		try {
			const res = await fetch("CheckUserServlet?userName=" + encodeURIComponent(userName) + "&id=" + empIdHidden);
			const data = await res.json();
			return data.userNameExists;
		} catch (e) {
			console.error(e);
			return false;
		}
	}
	async function checkContactExists(contact, empIdHidden) {
		try {
			const res = await fetch("CheckUserServlet?contactNo=" + encodeURIComponent(contact) + "&id=" + empIdHidden);
			const data = await res.json();
			return data.contactNoExists;
		} catch (e) {
			console.error(e);
			return false;
		}
	}
	/* VALIDATIONS */
	function validateName(input, spanId) {
		const val = input.value.trim();
		if (val.length === 0) {
			return setError(input, spanId, "Required");
		}
		if (val.length < 2) {
			return setError(input, spanId, "Min 2 characters");
		}
		if (val.length > 50) {
			input.value = val.slice(0, 50);
			return setError(input, spanId, "Max 50 characters");
		}
		const regex = /^[A-Za-z\s]+$/;
		if (!regex.test(val)) {
			return setError(input, spanId, "Only letters and spaces allowed");
		}
		clearError(input, spanId);
	}
	const blockedUsernames = ["admin", "root", "system", "support", "manager"];
	userNameInput.addEventListener("input", async () => {
		let val = userNameInput.value.trim();
		userNameInput.value = val; // prevent spaces
		const lowerVal = val.toLowerCase();
		// Block restricted usernames
		if (blockedUsernames.includes(lowerVal)) {
			return setError(userNameInput, "userNameAjaxError", "This username is not allowed");
		}
		// Minimum length
		if (val.length < 4)
			return setError(userNameInput, "userNameAjaxError", "Min 4 characters");
		// Maximum length
		if (val.length > 30) {
			userNameInput.value = val.slice(0, 30);
			return setError(userNameInput, "userNameAjaxError", "Max 30 characters");
		}
		// NEW RULE: Must start with a letter
		if (!/^[A-Za-z]/.test(val)) {
			return setError(userNameInput, "userNameAjaxError", "Username must start with a letter");
		}
		// NEW RULE: Allowed characters (letters, digits, underscore)
		const allowedRegex = /^[A-Za-z0-9_]+$/;
		if (!allowedRegex.test(val)) {
			return setError(userNameInput, "userNameAjaxError", "Only letters, digits, and underscore (_) allowed");
		}
		// Check uniqueness (AJAX)
		const exists = await checkUsernameExists(val, empIdHidden);
		if (exists)
			return setError(userNameInput, "userNameAjaxError", "Username already exists");

		clearError(userNameInput, "userNameAjaxError");
	});
	/* PASSWORD */
	passwordInput.addEventListener("input", () => {
		passwordInput.value = passwordInput.value.replace(/\s+/g, "");
		const val = passwordInput.value.trim();
		if (isEdit && val.length === 0) {
			validation.password = true;
			return clearError(passwordInput, "passwordAjaxError");
		}
		const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,30}$/;
		if (val.length < 8)
			return setError(passwordInput, "passwordAjaxError", "Min 8 characters");
		if (val.length > 30) {
			passwordInput.value = val.slice(0, 30);
			return setError(passwordInput, "passwordAjaxError", "Max 30 characters");
		}
		if (/\s/.test(val))
			return setError(passwordInput, "passwordAjaxError", "Spaces are not allowed");
		if (!regex.test(val))
			return setError(passwordInput, "passwordAjaxError", "Include upper, lower, digit, special char");
		clearError(passwordInput, "passwordAjaxError");
	});
	passwordInput.addEventListener("keydown", (e) => {
		if (e.key === " ") {
			e.preventDefault();  // Block space key
		}
	});
	/* CONTACT NO */
	contactNoInput.addEventListener("input", async () => {
		contactNoInput.value = contactNoInput.value.replace(/\D/g, "");
		const val = contactNoInput.value;
		if (val.length !== 10)
			return setError(contactNoInput, "contactNoAjaxError", "Must be 10 digits");
		const exists = await checkContactExists(val, empIdHidden);
		if (exists)
			return setError(contactNoInput, "contactNoAjaxError", "Contact already exists");
		clearError(contactNoInput, "contactNoAjaxError");
	});
	/* ADDRESS */
	addressInput.addEventListener("input", () => {
		const val = addressInput.value;
		if (val.length > 200) {
			addressInput.value = val.slice(0, 200); // optional: keep max 200 chars
			setError(addressInput, "addressErrorSpan", "Max 200 characters");
		} else {
			clearError(addressInput, "addressErrorSpan");
		}
		validation.address = val.length <= 200; // valid only if <= 200
		updateButtons();
	});

	/* FIRST & LAST NAME */
	firstNameInput.addEventListener("input", () => validateName(firstNameInput, "firstNameAjaxError"));
	lastNameInput.addEventListener("input", () => validateName(lastNameInput, "lastNameAjaxError"));
	/* BLUR VALIDATION */
	function addBlurRequired(input, spanId, field) {
		input.addEventListener("blur", () => {
			if (input.value.trim() === "")
				setError(input, spanId, "Please enter " + field);
		});
	}
	addBlurRequired(firstNameInput, "firstNameAjaxError", "First Name");
	addBlurRequired(lastNameInput, "lastNameAjaxError", "Last Name");
	addBlurRequired(userNameInput, "userNameAjaxError", "Username");
	addBlurRequired(passwordInput, "passwordAjaxError", "Password");
	addBlurRequired(contactNoInput, "contactNoAjaxError", "Contact No");
	/* PREVENT SUBMIT IF INVALID */
	document.getElementById("employeeRegister").addEventListener("submit", function(e) {
		if (submitBtn.disabled) e.preventDefault();
	});
});

/* CLEAR BUTTON */
function clearForm() {
	document.getElementById("employeeRegister").reset();
	document.querySelectorAll(".input-error").forEach(i => i.classList.remove("input-error"));
	document.querySelectorAll(".error-span").forEach(s => s.innerText = "");
	validation = {
		firstName: false,
		lastName: false,
		userName: false,
		password: false,
		address: true,
		contactNo: false
	};
	submitBtn.disabled = true;
}