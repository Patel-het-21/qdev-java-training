var API_URL = "http://localhost:9090/api/v1/employees";
var employeeData = [];
var currentSortField = "id";
var currentSortOrder = "asc";
var currentPage = 1;
var rowsPerPage = 10;

/**
 * Load employees from the API, update the global employee list, and refresh the table view.
 *
 * Fetches employee data, stores it in the global `employeeData`, and re-applies the current sort to refresh the displayed table.
 * If the request returns a 401 status the user is alerted that the session expired and the page redirects to "signin".
 * On other failures, displays "Failed to load employees" in the table.
 */
function loadEmployees() {
	axios.get(API_URL, { withCredentials: true })
		.then(function(res) {
			employeeData = res.data;
			sortTable(currentSortField, true);
		})
		.catch(function() { 
			if (err.response && err.response.status === 401) {
				alert("Session expired. Please sign in again.");
				window.location.href = "signin";
			} else {
				showMessage("Failed to load employees");
			}
		});
}

/**
 * Populate the employee table and pagination UI from the provided list of employees.
 *
 * Updates the table body with rows for the current page, sets the pagination info text,
 * and rebuilds pagination buttons. If `list` is empty or falsy, displays a "No employees found"
 * message and clears pagination controls.
 *
 * @param {Array<Object>} list - Array of employee objects (expected fields: id, firstName, lastName, gender, dateOfBirth, age, mobile, email, address1). When omitted or empty, the table is cleared and a "No employees found" message is shown.
 */
function renderTable(list) {
	var tbody = document.getElementById("employeeTableBody");
	tbody.innerHTML = "";
	if (!list || list.length === 0) {
		showMessage("No employees found");
		document.getElementById("paginationInfo").innerText = "";
		document.getElementById("paginationButtons").innerHTML = "";
		return;
	}
	var start = (currentPage - 1) * rowsPerPage;
	var end = start + rowsPerPage;
	var paginatedItems = list.slice(start, end);
	var totalPages = Math.ceil(list.length / rowsPerPage);
	var displayEnd = end > list.length ? list.length : end;
	document.getElementById("paginationInfo").innerText = (start + 1) + " - " + displayEnd + " of " + list.length;

	for (var i = 0; i < paginatedItems.length; i++) {
		var e = paginatedItems[i];
		var serialNo = (currentSortOrder === "desc") ? (list.length - (start + i)) : (start + i + 1);
		var fullName = e.firstName + " " + e.lastName;
		var row = "<tr>"
			+ "<td data-label='ID'>" + serialNo + "</td>"
			+ "<td data-label='Name' title='" + fullName + "'>" + fullName + "</td>"
			+ "<td data-label='Gender' title='" + e.gender + "'>" + e.gender + "</td>"
			+ "<td data-label='DOB' title='" + e.dateOfBirth + "'>" + e.dateOfBirth + "</td>"
			+ "<td data-label='Age' title='" + e.age + "'>" + e.age + "</td>"
			+ "<td data-label='Mobile' title='" + e.mobile + "'>" + e.mobile + "</td>"
			+ "<td data-label='Email' title='" + e.email + "'>" + e.email + "</td>"
			+ "<td data-label='Address1' title='" + (e.address1 || "") + "'>" + (e.address1 || "") + "</td>"
			+ "<td data-label='Actions'>"
			+ "<a href='employee-form?id=" + e.id + "' class='action-btn btn-edit'>Edit</a>"
			+ "<button class='action-btn btn-delete' onclick='deleteEmployee(" + e.id + ")'>Delete</button>"
			+ "</td>"
			+ "</tr>";
		tbody.innerHTML += row;
	}
	setupPagination(totalPages);
}

/**
 * Render pagination controls into the element with id "paginationButtons".
 *
 * Updates the container to show Prev/Next buttons and numbered page buttons centered around the current page,
 * inserts ellipsis where ranges are skipped, disables Prev/Next when on the first/last page, and highlights the active page.
 *
 * @param {number} totalPages - Total number of pages available; if less than or equal to 1 the container is cleared and no controls are rendered.
 */
function setupPagination(totalPages) {
	var btnContainer = document.getElementById("paginationButtons");
	btnContainer.innerHTML = "";
	if (totalPages <= 1) return;

	btnContainer.innerHTML += '<button class="page-link ' + (currentPage === 1 ? 'disabled' : '') + '" onclick="changePage(' + (currentPage - 1) + ')">Prev</button>';
	var range = 2;
	for (var i = 1; i <= totalPages; i++) {
		if (i === 1 || i === totalPages || (i >= currentPage - range && i <= currentPage + range)) {
			btnContainer.innerHTML += '<button class="page-link ' + (currentPage === i ? 'active' : '') + '" onclick="changePage(' + i + ')">' + i + '</button>';
		} else if (i === currentPage - range - 1 || i === currentPage + range + 1) {
			btnContainer.innerHTML += '<span class="dots">...</span>';
		}
	}

	btnContainer.innerHTML += '<button class="page-link ' + (currentPage === totalPages ? 'disabled' : '') + '" onclick="changePage(' + (currentPage + 1) + ')">Next</button>';
}

/**
 * Navigate to the specified page of the employee table and re-render the table.
 * @param {number} page - 1-based page index to display; if outside the valid range, no changes are made.
 */
function changePage(page) {
	var totalPages = Math.ceil(employeeData.length / rowsPerPage);
	if (page < 1 || page > totalPages) return;
	currentPage = page;
	renderTable(employeeData);
}

/**
 * Sorts the global employee list by the given field and updates the table display.
 *
 * When the same field is selected again (and `forceSort` is false) the sort direction toggles;
 * selecting a new field sets the direction to ascending. Updates the global sort state,
 * updates sort indicator icons, resets pagination to the first page, and re-renders the table.
 *
 * @param {string} field - The field name to sort by (e.g., "id", "name", "dob", "age", or any employee property).
 * @param {boolean} [forceSort=false] - If true, apply the provided `field` and keep the current sort order behaviour (do not toggle); used to reapply sorting after data changes.
 */
function sortTable(field, forceSort = false) {
	if (currentSortField === field && !forceSort) {
		currentSortOrder = currentSortOrder === "asc" ? "desc" : "asc";
	} else {
		if (!forceSort) currentSortField = field;
		if (!forceSort) currentSortOrder = "asc";
	}

	employeeData.sort(function(a, b) {
		var multiplier = currentSortOrder === "asc" ? 1 : -1;
		var valA, valB;
		switch (field) {
			case "id": valA = a.id; valB = b.id; return (valA - valB) * multiplier;
			case "name": valA = (a.firstName + " " + a.lastName).toLowerCase(); valB = (b.firstName + " " + b.lastName).toLowerCase(); break;
			case "dob": valA = new Date(a.dateOfBirth); valB = new Date(b.dateOfBirth); return (valA - valB) * multiplier;
			case "age": valA = a.age; valB = b.age; return (valA - valB) * multiplier;
			default: valA = (a[field] || "").toString().toLowerCase(); valB = (b[field] || "").toString().toLowerCase();
		}
		if (valA < valB) return -1 * multiplier;
		if (valA > valB) return 1 * multiplier;
		return 0;
	});
	document.querySelectorAll('.sort-icon').forEach(icon => icon.innerHTML = "");
	var activeIcon = document.querySelector('.sort-icon[data-field="' + currentSortField + '"]');
	if (activeIcon) activeIcon.innerHTML = currentSortOrder === "asc" ? " ▲" : " ▼";

	currentPage = 1;
	renderTable(employeeData);
}

/**
 * Prompts the user to confirm and, if confirmed, deletes the employee with the given id from the API.
 * Performs UI feedback on success or failure and reloads the employee list; redirects to sign-in if the session has expired.
 * @param {number|string} id - Employee identifier to delete.
 */
function deleteEmployee(id) {
	Swal.fire({
		title: 'Are you sure?', text: "You won't be able to revert this!", icon: 'warning',
		showCancelButton: true, confirmButtonColor: '#3085d6', cancelButtonColor: '#d33', confirmButtonText: 'Yes, delete it!'
	}).then(function(result) {
		if (result.isConfirmed) {
			axios.delete(API_URL + "/" + id, { withCredentials: true }).then(function(res) {
				Swal.fire('Deleted!', 'Employee has been deleted.', 'success');
				loadEmployees();
			}).catch(function(err) {
				//Swal.fire('Error!', 'Delete failed.', 'error');
				if (err.response && err.response.status === 401) {
					alert("Session expired. Please sign in again.");
					window.location.href = "signin";
				} else {
					Swal.fire('Error!', 'Delete failed.', 'error');
				}
			});
		}
	});
}

/**
 * Searches employees by the value in the search box and updates the displayed list.
 *
 * If the search box is empty and employee data already exists, reloads the full employee list.
 * Otherwise sends a request to the API's /search endpoint with the entered name, replaces the global `employeeData` with the results, resets `currentPage` to 1, and reapplies the current sort.
 * On a 401 response redirects the user to the sign-in page; on other failures shows an error dialog.
 */
function searchEmployees() {
	var name = document.getElementById("searchBox").value.trim();
	if (name === "" && employeeData.length > 0) { loadEmployees(); return; }
	if (name !== "") {
		axios.get(API_URL + "/search", { params: { name: name, withCredentials: true } })
			.then(function(res) {
				employeeData = res.data;
				currentPage = 1;
				sortTable(currentSortField, true);
			}).catch(function(err) { 
				if (err.response && err.response.status === 401) {
					alert("Session expired. Please sign in again.");
					window.location.href = "signin";
				} else {
					Swal.fire('Error', 'Search failed', 'error');
				}
				//Swal.fire('Error', 'Search failed', 'error'); 
			});
	}
}

/**
 * Display a full-width message row inside the employee table body.
 * Replaces the table body content with a single table row spanning 9 columns containing the provided message.
 * @param {string} msg - The message text to display in the table body.
 */
function showMessage(msg) {
	document.getElementById("employeeTableBody").innerHTML = "<tr><td colspan='9' class='no-data'>" + msg + "</td></tr>";
}

document.addEventListener("DOMContentLoaded", function() {
	loadEmployees();
	const msg = sessionStorage.getItem('successMessage');
	if (msg) {
		const popup = document.getElementById('topCenterPopup');
		popup.innerText = msg;
		popup.style.display = 'block';
		popup.style.background = (msg.includes('Deleted') || msg.includes('Failed')) ? '#d33' : '#4caf50';
		setTimeout(() => { popup.style.display = 'none'; sessionStorage.removeItem('successMessage'); }, 3000);
	}
	
	const btn = document.getElementById("logoutBtn");
	    if (!btn) return;

	    btn.addEventListener("click", function () {
	        axios.post("http://localhost:9090/api/v1/auth/logout", {}, { withCredentials: true })
	            .then(() => {
	                window.location.href = "signin";
	            })
	            .catch(() => {
	                alert("Logout failed");
	            });
	    });

});