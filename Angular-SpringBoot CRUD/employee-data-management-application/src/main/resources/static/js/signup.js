const validity = { company: false, email: false, password: false };
const API_BASE = "http://localhost:9090/api/v1/auth";

document.getElementById("signupForm").addEventListener("submit", function(e) {
	e.preventDefault();

	const data = {
		companyName: document.getElementById("companyName").value.trim(),
		email: document.getElementById("email").value.trim(),
		password: document.getElementById("password").value
	};

	axios.post(API_BASE + "/register", data, { withCredentials: true })
		.then(res => {
			alert("Registration successful. Please login.");
			window.location.href = "signin";
		})
		.catch(err => {
			const msg = err.response?.data?.message || "Registration failed";
			alert(msg);
		});
});


/**
 * Enable or disable the signup button and toggle its "enabled" CSS class based on current validity flags.
 *
 * Reads the global `validity` object, sets the button's `disabled` property to `false` when all flags are true (otherwise `true`), and ensures the "enabled" class reflects the same state.
 */
function updateButton() {
	const btn = document.getElementById("signupBtn");
	const allValid = Object.values(validity).every(v => v);
	btn.disabled = !allValid;
	btn.classList.toggle("enabled", allValid);
}

/**
 * Validate the company name input and reflect the result in the UI and form state.
 *
 * Reads the value of the element with id `companyName`; if the value has fewer than 3 characters,
 * sets the element with id `companyError` to "Company name too short" and sets `validity.company` to `false`.
 * Otherwise clears the error text and sets `validity.company` to `true`. Finally calls `updateButton()` to
 * refresh the submit button state.
 */
function validateCompany() {
	const val = document.getElementById("companyName").value.trim();
	const err = document.getElementById("companyError");
	if (val.length < 3) {
		err.innerText = "Company name too short";
		validity.company = false;
	} else {
		err.innerText = "";
		validity.company = true;
	}
	updateButton();
}

/**
 * Validate the email input, set the corresponding validation message and validity flag, and refresh the signup button state.
 *
 * Reads the value of the element with id "email", writes an error message to the element with id "emailError" when invalid, updates `validity.email` (`true` when valid, `false` otherwise), and calls `updateButton()` to update the signup button.
 */
function validateEmail() {
	const email = document.getElementById("email").value.trim();
	const err = document.getElementById("emailError");
	if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
		err.innerText = "Invalid email";
		validity.email = false;
	} else {
		err.innerText = "";
		validity.email = true;
	}
	updateButton();
}

/**
 * Validate the password input and update the UI and validity state.
 *
 * If the password length is less than 6, sets the element with id "passwordError"
 * to "Minimum 6 characters required" and marks `validity.password` as `false`.
 * Otherwise clears the error text and marks `validity.password` as `true`.
 * Calls `updateButton()` to refresh the signup button state.
 */
function validatePassword() {
	const pwd = document.getElementById("password").value;
	const err = document.getElementById("passwordError");
	if (pwd.length < 6) {
		err.innerText = "Minimum 6 characters required";
		validity.password = false;
	} else {
		err.innerText = "";
		validity.password = true;
	}
	updateButton();
}

document.getElementById("companyName").addEventListener("input", validateCompany);
document.getElementById("email").addEventListener("input", validateEmail);
document.getElementById("password").addEventListener("input", validatePassword);
