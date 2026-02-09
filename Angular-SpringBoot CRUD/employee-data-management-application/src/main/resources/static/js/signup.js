const validity = { company: false, email: false, password: false };
const API_BASE = "http://localhost:9090/api/v1/auth";

document.getElementById("signupForm").addEventListener("submit", function (e) {
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


function updateButton() {
	const btn = document.getElementById("signupBtn");
	const allValid = Object.values(validity).every(v => v);
	btn.disabled = !allValid;
	btn.classList.toggle("enabled", allValid);
}

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

document.getElementById("companyName").addEventListener("input", validateCompany);
document.getElementById("email").addEventListener("input", validateEmail);
document.getElementById("password").addEventListener("input", validatePassword);

