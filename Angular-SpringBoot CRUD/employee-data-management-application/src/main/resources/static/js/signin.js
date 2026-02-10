const validity = { email: false, password: false };
const API_BASE = "http://localhost:9090/api/v1/auth";

document.getElementById("signinForm").addEventListener("submit", function(e) {
	e.preventDefault();

	const data = {
		email: document.getElementById("email").value.trim(),
		password: document.getElementById("password").value
	};

	axios.post(API_BASE + "/login", data, { withCredentials: true })
		.then(res => {
			// session is now stored on backend
			window.location.href = "employee-form";
		})
		.catch(err => {
			alert("Invalid email or password");
		});
});

/**
 * Update the sign-in button state to reflect current validation flags.
 *
 * Enables the element with id "signinBtn" when all entries in the global
 * `validity` object are true; otherwise disables it. Also adds or removes
 * the "enabled" CSS class on the button to match the enabled state.
 */
function updateButton() {
	const btn = document.getElementById("signinBtn");
	const allValid = Object.values(validity).every(v => v);
	btn.disabled = !allValid;
	btn.classList.toggle("enabled", allValid);
}

/**
 * Validate the value of the email input, update the email error message and the `validity.email` flag, and refresh the sign-in button state.
 *
 * Reads the element with id "email", writes validation text to the element with id "emailError", sets `validity.email` to `true` or `false`, and calls `updateButton()` to apply the new state.
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
 * Validate the password input, display an inline error when it's too short, and refresh the form button state.
 *
 * If the password in the element with id "password" has fewer than 6 characters, sets the element
 * with id "passwordError" to "Minimum 6 characters required" and sets `validity.password` to `false`;
 * otherwise clears the error text and sets `validity.password` to `true`. Calls `updateButton()` to apply the new validity state.
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

document.getElementById("email").addEventListener("input", validateEmail);
document.getElementById("password").addEventListener("input", validatePassword);