<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Employee List</title>	
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee-list.css">
</head>
<body>
	<div class="container">
		<h1>Employee List</h1>
	
		<div class="top-actions">
			<div class="search-container">
			<input type="text" id="searchBox" placeholder="Search by name..." oninput="this.value = this.value.replace(/[^a-zA-Z\s]/g, '')" onkeyup="searchEmployees()">
			</div>
			<div><a href="employee-form" class="btn-add">+ Add Employee</a></div>
		</div>
	
		<div class="table-responsive">
			<table>
				<thead>
					<tr id="tableHeader">
						<th onclick="sortTable('id')">ID <span class="sort-icon" data-field="id"></span></th>
						<th onclick="sortTable('name')">Name <span class="sort-icon" data-field="name"></span></th>
						<th onclick="sortTable('gender')">Gender <span class="sort-icon" data-field="gender"></span></th>
						<th onclick="sortTable('dob')">DOB <span class="sort-icon" data-field="dob"></span></th>
						<th onclick="sortTable('age')">Age <span class="sort-icon" data-field="age"></span></th>
						<th onclick="sortTable('mobile')">Mobile <span class="sort-icon" data-field="mobile"></span></th>
						<th onclick="sortTable('email')">Email <span class="sort-icon" data-field="email"></span></th>
						<th onclick="sortTable('address1')">Address1 <span class="sort-icon" data-field="address1"></span></th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id="employeeTableBody">
					<tr><td colspan="9" class="no-data">Loading...</td></tr>
				</tbody>
			</table>
		</div>
	
		<div class="pagination-wrapper">
			<div id="paginationInfo" class="pagination-info"></div>
			<div id="paginationButtons" class="pagination-buttons"></div>
		</div>
	</div>
	
	<div id="topCenterPopup"></div>
	<script src="${pageContext.request.contextPath}/js/employee-list.js"></script>
</body>
</html>
  