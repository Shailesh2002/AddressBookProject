<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Server Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="error-container">
        <h1>500 - Server Error</h1>
        <p>Sorry, something went wrong on our end.</p>
        <a href="${pageContext.request.contextPath}/index.jsp" class="button">Return to Home</a>
    </div>
</body>
</html>
