<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Address Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>Address Book</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/add-contact.jsp">Add Contact</a></li>
                <li><a href="${pageContext.request.contextPath}/contact?action=viewAll">View Contacts</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <div class="welcome">
            <h2>Welcome to the Address Book</h2>
            <p>Manage your contacts easily with our address book application.</p>
        </div>

        <div class="cards">
            <div class="card">
                <h3>Add Contact</h3>
                <p>Click here to add a new contact to your address book.</p>
                <a href="${pageContext.request.contextPath}/add-contact.jsp">
                    <button>Add Contact</button>
                </a>
            </div>
            <div class="card">
                <h3>View Contacts</h3>
                <p>Click here to view and manage your existing contacts.</p>
                <a href="${pageContext.request.contextPath}/contact?action=viewAll">
                    <button>View Contacts</button>
                </a>
            </div>
        </div>
    </main>

    <footer>
        <p>&copy; 2024 Address Book. All rights reserved.</p>
    </footer>
</body>
</html>
