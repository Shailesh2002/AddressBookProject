<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-contact.css">
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
            <div class="form-container">
                <h2>Add New Contact</h2>
                <form action="${pageContext.request.contextPath}/contact" method="POST">
                    <input type="hidden" name="action" value="add">
                    
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" required 
                               placeholder="Enter first name">
                    </div>
                    
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" required
                               placeholder="Enter last name">
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" id="email" name="email" required
                               placeholder="Enter email address">
                    </div>
                    
                    <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <input type="tel" id="phone" name="phone" required
                               placeholder="Enter phone number">
                    </div>
                    
                    <div class="form-group">
                        <label for="address">Address</label>
                        <textarea id="address" name="address" required
                                  placeholder="Enter full address"></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="notes">Notes (Optional)</label>
                        <textarea id="notes" name="notes"
                                  placeholder="Add any additional notes"></textarea>
                    </div>
                    
                    <div class="btn-group">
                        <button type="reset" class="btn btn-danger">Reset</button>
                        <button type="submit" class="btn btn-primary">Add Contact</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
    <footer>
        <p>&copy; 2024 Address Book. All rights reserved.</p>
    </footer>
</body>
</html>
