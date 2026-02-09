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
/**
 * Handle employee form submission: validate fields, send a create or update request to the employees API, and navigate back to the list on success.
 * Prevents the form's default submission, builds an employee payload from form fields, and issues a POST (create) when no id is present or a PUT (update) when an id is present.
 * On request failure, `handleError` is invoked.
 * @param {Event} e - The submit event from the employee form.
 */
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

/**
 * Validate employee form fields and display inline error messages for any invalid inputs.
 *
 * Validates: firstName and lastName (2–50 letters/spaces), dateOfBirth (required; age between 18 and 110),
 * mobile (10 digits, not starting with 0), email format, gender selection, and address1 (required).
 * Sets per-field error text in adjacent ".invalid-feedback" elements or a dedicated gender error element.
 *
 * @returns {boolean} `true` if all validations pass, `false` otherwise.
 */
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

/**
 * Compute the age from the date-of-birth input, update the #age field with the result, and return it.
 *
 * @returns {number|undefined} The computed age in years, or `undefined` if the date-of-birth input is empty or invalid.
 */
function calculateAge() {
	const dobInput = document.getElementById("dateOfBirth");
	if (!dobInput.value) return;
	const dob = new Date(dobInput.value);
	const diff = new Date(Date.now() - dob.getTime());
	const age = Math.abs(diff.getUTCFullYear() - 1970);
	document.getElementById("age").value = age;
	return age;
}

/**
 * Displays an error to the user via alert, preferring `error.response.data` when present.
 * @param {object} error - The error object (may be an HTTP or generic error); shows `error.response.data` if available, otherwise shows `error.message`.
 */
function handleError(error) {
	if (error.response && error.response.data) {
		alert(JSON.stringify(error.response.data));
	} else {
		alert(error.message);
	}
}

// =====================
// Employee List
/**
 * Load employees from the API and update the list view.
 *
 * Fetches the employee collection from the server, replaces the local `employees` array,
 * resets `currentPage` to 1, and re-renders the table and pagination controls.
 */
function fetchEmployees() {
	axios.get(apiBase).then(res => {
		employees = res.data;
		currentPage = 1;
		renderTable();
		renderPagination();
	}).catch(handleError);
}

/**
 * Render the current page of employees into the table body with id "employeeTableBody".
 *
 * Clears the table body, slices the global `employees` array using `currentPage` and `pageSize`,
 * and appends a row per employee containing id, full name, date of birth, age, mobile, email,
 * gender, address, and action controls (Edit link and Delete button).
 */
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

/**
 * Render pagination controls for the employee list and wire page click handlers.
 *
 * Updates the element with id "pagination" to show page items computed from the current
 * employees array and pageSize, highlights the current page, and attaches click handlers
 * that set `currentPage` and re-render the table and pagination.
 */
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

/**
 * Prompt for confirmation and delete the employee with the given id, then refresh the employee list.
 * @param {number|string} id - The identifier of the employee to delete.
 */
function deleteEmployee(id) {
	if (confirm("Are you sure to delete?")) {
		axios.delete(`${apiBase}/${id}`)
			.then(() => fetchEmployees())
			.catch(handleError);
	}
}

/**
 * Filters the employee list by the search box query and updates the displayed page.
 *
 * Fetches the current employees from the API, filters them case-insensitively by first name, last name, or full name using the search input value, resets the current page to 1, and re-renders the table and pagination. On request failure, forwards the error to the centralized error handler.
 * @param {Event} e - Input event from the search box; its target's value is used as the search query.
 */
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