// =====================
// Global Variables
// =====================
const apiBase = "/api/v1/employees";
let employees = [];
let currentPage = 1;
const pageSize = 10;

// =====================
// Form Handling
// =====================
document.addEventListener("DOMContentLoaded", () => {
	const form = document.getElementById("employeeForm");
	const cancelBtn = document.getElementById("cancelBtn");

	if (form) {
		form.addEventListener("submit", handleFormSubmit);
	}
	if (cancelBtn) {
		cancelBtn.addEventListener("click", () => window.location.href = "employee-list.jsp");
	}

	const dobInput = document.getElementById("dateOfBirth");
	if (dobInput) {
		dobInput.addEventListener("blur", calculateAge);
	}

	// Load employee list if page contains table
	if (document.getElementById("employeeTableBody")) {
		fetchEmployees();
		document.getElementById("searchBox").addEventListener("input", handleSearch);
	}
});

// =====================
// Form Validation & Submission
// =====================
function handleFormSubmit(e) {
	e.preventDefault();
	const id = document.getElementById("employeeId").value;

	if (!validateForm()) return;

	const employee = {
		firstName: document.getElementById("firstName").value.trim(),
		lastName: document.getElementById("lastName").value.trim(),
		dateOfBirth: document.getElementById("dateOfBirth").value,
		mobile: document.getElementById("mobile").value.trim(),
		email: document.getElementById("email").value.trim(),
		gender: document.querySelector('input[name="gender"]:checked')?.value,
		address1: document.getElementById("address1").value.trim(),
		address2: document.getElementById("address2").value.trim()
	};

	if (id) {
		axios.put(`${apiBase}/${id}`, employee)
			.then(() => window.location.href = "employee-list.jsp")
			.catch(handleError);
	} else {
		axios.post(apiBase, employee)
			.then(() => window.location.href = "employee-list.jsp")
			.catch(handleError);
	}
}

function validateForm() {
	let valid = true;
	const firstName = document.getElementById("firstName");
	const lastName = document.getElementById("lastName");
	const dob = document.getElementById("dateOfBirth");
	const mobile = document.getElementById("mobile");
	const email = document.getElementById("email");
	const gender = document.querySelector('input[name="gender"]:checked');
	const address1 = document.getElementById("address1");

	// Reset errors
	document.querySelectorAll(".invalid-feedback").forEach(el => el.textContent = "");

	// Name validation
	const nameRegex = /^[A-Za-z ]{2,50}$/;
	if (!nameRegex.test(firstName.value.trim())) {
		firstName.nextElementSibling.textContent = "First name must be 2-50 alphabets and spaces";
		valid = false;
	}
	if (!nameRegex.test(lastName.value.trim())) {
		lastName.nextElementSibling.textContent = "Last name must be 2-50 alphabets and spaces";
		valid = false;
	}

	// DOB validation
	if (!dob.value) {
		dob.nextElementSibling.textContent = "Date of birth is required";
		valid = false;
	} else {
		const age = calculateAge();
		if (age < 18 || age > 110) {
			dob.nextElementSibling.textContent = "Age must be between 18 and 110";
			valid = false;
		}
	}

	// Mobile
	if (!/^[1-9][0-9]{9}$/.test(mobile.value.trim())) {
		mobile.nextElementSibling.textContent = "Mobile must be 10 digits and not start with 0";
		valid = false;
	}

	// Email
	const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	if (!emailPattern.test(email.value.trim())) {
		email.nextElementSibling.textContent = "Invalid email format";
		valid = false;
	}

	// Gender
	if (!gender) {
		document.getElementById("genderError").textContent = "Gender is required";
		valid = false;
	}

	// Address1
	if (!address1.value.trim()) {
		address1.nextElementSibling.textContent = "Address1 is required";
		valid = false;
	}

	return valid;
}

function calculateAge() {
	const dobInput = document.getElementById("dateOfBirth");
	if (!dobInput.value) return;
	const dob = new Date(dobInput.value);
	const diff = new Date(Date.now() - dob.getTime());
	const age = Math.abs(diff.getUTCFullYear() - 1970);
	document.getElementById("age").value = age;
	return age;
}

function handleError(error) {
	if (error.response && error.response.data) {
		alert(JSON.stringify(error.response.data));
	} else {
		alert(error.message);
	}
}

// =====================
// Employee List
// =====================
function fetchEmployees() {
	axios.get(apiBase).then(res => {
		employees = res.data;
		currentPage = 1;
		renderTable();
		renderPagination();
	}).catch(handleError);
}

function renderTable() {
	const tbody = document.getElementById("employeeTableBody");
	tbody.innerHTML = "";
	const start = (currentPage - 1) * pageSize;
	const end = start + pageSize;
	employees.slice(start, end).forEach(emp => {
		const row = document.createElement("tr");
		row.innerHTML = `
            <td>${emp.id}</td>
            <td>${emp.firstName} ${emp.lastName}</td>
            <td>${emp.dateOfBirth}</td>
            <td>${emp.age}</td>
            <td>${emp.mobile}</td>
            <td>${emp.email}</td>
            <td>${emp.gender}</td>
            <td>${emp.address1} ${emp.address2 ? ', ' + emp.address2 : ''}</td>
            <td>
                <a href="employee-form.jsp?id=${emp.id}" class="btn btn-sm btn-primary me-1">Edit</a>
                <button class="btn btn-sm btn-danger" onclick="deleteEmployee(${emp.id})">Delete</button>
            </td>
        `;
		tbody.appendChild(row);
	});
}

function renderPagination() {
	const totalPages = Math.ceil(employees.length / pageSize);
	const pagination = document.getElementById("pagination");
	pagination.innerHTML = "";
	for (let i = 1; i <= totalPages; i++) {
		const li = document.createElement("li");
		li.className = `page-item ${i === currentPage ? 'active' : ''}`;
		li.innerHTML = `<a class="page-link" href="#">${i}</a>`;
		li.addEventListener("click", (e) => { e.preventDefault(); currentPage = i; renderTable(); renderPagination(); });
		pagination.appendChild(li);
	}
}

function deleteEmployee(id) {
	if (confirm("Are you sure to delete?")) {
		axios.delete(`${apiBase}/${id}`)
			.then(() => fetchEmployees())
			.catch(handleError);
	}
}

function handleSearch(e) {
	const query = e.target.value.toLowerCase();
	currentPage = 1;
	axios.get(apiBase).then(res => {
		employees = res.data.filter(emp =>
			emp.firstName.toLowerCase().includes(query) ||
			emp.lastName.toLowerCase().includes(query) ||
			(emp.firstName + ' ' + emp.lastName).toLowerCase().includes(query)
		);
		renderTable();
		renderPagination();
	}).catch(handleError);
}
