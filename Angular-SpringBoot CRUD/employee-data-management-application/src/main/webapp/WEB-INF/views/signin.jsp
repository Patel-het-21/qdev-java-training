<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css">
</head>
<body>
<div class="container">
    <h1>Sign In</h1>

    <form id="signinForm">
        <div class="form-group">
            <label>Email<span class="required-asterisk">*</span></label>
            <input type="email" id="email" required>
            <div class="error" id="emailError"></div>
        </div>

        <div class="form-group">
            <label>Password<span class="required-asterisk">*</span></label>
            <input type="password" id="password" required>
            <div class="error" id="passwordError"></div>
        </div>

        <div class="center">
            <button type="submit" id="signinBtn" class="btn btn-submit" disabled>Sign In</button>
            <p>Don’t have an account? <a href="signup">Sign Up</a></p>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/signin.js" defer></script>

</body>
</html>
