const validity = { email: false, password: false };
const API_BASE = "http://localhost:9090/api/v1/auth";

document.getElementById("signinForm").addEventListener("submit", function (e) {
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

function updateButton() {
	const btn = document.getElementById("signinBtn");
	const allValid = Object.values(validity).every(v => v);
	btn.disabled = !allValid;
	btn.classList.toggle("enabled", allValid);
}

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
