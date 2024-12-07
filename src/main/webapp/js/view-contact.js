// Search functionality
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

// Modal handling
var modal = document.getElementById("shareModal");
var span = document.getElementsByClassName("close")[0];
var currentContact = null;

function showShareModal(contactId, firstName, lastName) {
    currentContact = {
        id: contactId,
        firstName: firstName,
        lastName: lastName
    };
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function shareVCard() {
    if (currentContact) {
        window.location.href = contextPath + "/contact?action=downloadVCard&id=" + currentContact.id;
    }
}

function shareEmail() {
    if (currentContact) {
        window.location.href = contextPath + "/contact?action=shareEmail&id=" + currentContact.id;
    }
}

function copyContactInfo() {
    if (currentContact) {
        fetch(contextPath + "/contact?action=getContactInfo&id=" + currentContact.id)
            .then(response => response.text())
            .then(text => {
                navigator.clipboard.writeText(text).then(() => {
                    var copySuccess = document.getElementById("copySuccess");
                    copySuccess.style.display = "block";
                    setTimeout(() => {
                        copySuccess.style.display = "none";
                    }, 2000);
                });
            });
    }
}
