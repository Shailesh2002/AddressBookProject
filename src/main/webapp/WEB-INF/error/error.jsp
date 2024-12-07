<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="error-container">
        <h1>Oops! An Error Occurred</h1>
        <p>Sorry, an error has occurred: ${pageContext.exception.message}</p>
        <a href="${pageContext.request.contextPath}/index.jsp" class="button">Return to Home</a>
    </div>
</body>
</html>
