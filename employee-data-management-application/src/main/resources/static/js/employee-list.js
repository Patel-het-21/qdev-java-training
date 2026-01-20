var API_URL = "http://localhost:9090/api/v1/employees";
var employeeData = [];
var currentSortField = "id";
var currentSortOrder = "asc";
var currentPage = 1;
var rowsPerPage = 10;

function loadEmployees() {
	axios.get(API_URL)
		.then(function(res) {
			employeeData = res.data;
			sortTable(currentSortField, true);
		})
		.catch(function() { showMessage("Failed to load employees"); });
}

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

function changePage(page) {
	var totalPages = Math.ceil(employeeData.length / rowsPerPage);
	if (page < 1 || page > totalPages) return;
	currentPage = page;
	renderTable(employeeData);
}

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

function deleteEmployee(id) {
	Swal.fire({
		title: 'Are you sure?', text: "You won't be able to revert this!", icon: 'warning',
		showCancelButton: true, confirmButtonColor: '#3085d6', cancelButtonColor: '#d33', confirmButtonText: 'Yes, delete it!'
	}).then(function(result) {
		if (result.isConfirmed) {
			axios.delete(API_URL + "/" + id).then(function(res) {
				Swal.fire('Deleted!', 'Employee has been deleted.', 'success');
				loadEmployees();
			}).catch(function(err) {
				Swal.fire('Error!', 'Delete failed.', 'error');
			});
		}
	});
}

function searchEmployees() {
	var name = document.getElementById("searchBox").value.trim();
	if (name === "" && employeeData.length > 0) { loadEmployees(); return; }
	if (name !== "") {
		axios.get(API_URL + "/search", { params: { name: name } })
			.then(function(res) {
				employeeData = res.data;
				currentPage = 1;
				sortTable(currentSortField, true);
			}).catch(function(err) { Swal.fire('Error', 'Search failed', 'error'); });
	}
}

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
});