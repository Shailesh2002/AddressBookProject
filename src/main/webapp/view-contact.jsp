<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Contacts</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/view-contact.css">
</head>
<body>
    <div class="wrapper">
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
            <div class="search-container">
                <input type="text" id="searchInput" class="search-input" placeholder="Search contacts by name..." onkeyup="searchContacts()">
            </div>

            <div class="table-container">
                <table id="contactsTable">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Notes</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="contact" items="${contacts}">
                            <tr>
                                <td>${contact.first_name}</td>
                                <td>${contact.last_name}</td>
                                <td>${contact.email}</td>
                                <td>${contact.phone}</td>
                                <td>${contact.address}</td>
                                <td>${contact.notes}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/contact?action=edit&id=${contact.id}" class="btn btn-primary">Edit</a>
                                    <a href="${pageContext.request.contextPath}/contact?action=delete&id=${contact.id}" class="btn btn-danger" 
                                       onclick="return confirm('Are you sure you want to delete this contact?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>

        <footer>
            <p>&copy; 2024 Address Book. All rights reserved.</p>
        </footer>
    </div>

    <script>
    function searchContacts() {
        var input = document.getElementById("searchInput");
        var filter = input.value.toLowerCase();
        var table = document.getElementById("contactsTable");
        var tr = table.getElementsByTagName("tr");

        for (var i = 1; i < tr.length; i++) {
            var firstName = tr[i].getElementsByTagName("td")[0];
            var lastName = tr[i].getElementsByTagName("td")[1];
            if (firstName && lastName) {
                var firstNameText = firstName.textContent || firstName.innerText;
                var lastNameText = lastName.textContent || lastName.innerText;
                var fullName = firstNameText + " " + lastNameText;
                if (fullName.toLowerCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
    </script>
</body>
</html>
