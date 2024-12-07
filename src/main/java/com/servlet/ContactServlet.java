package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.impl.ContactImpl;
import com.interfaces.ContactInterface;
import com.model.java.Contact;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactInterface contactService;

    public ContactServlet() {
        contactService = new ContactImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "viewAll";
        }
        
        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "viewAll":
                listContacts(request, response);
                break;
            case "downloadVCard":
                downloadVCard(request, response);
                break;
            case "shareEmail":
                shareEmail(request, response);
                break;
            case "getContactInfo":
                getContactInfo(request, response);
                break;
            default:
                // Handle direct URL access
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch (action) {
            case "add":
                addContact(request, response);
                break;
            case "update":
                updateContact(request, response);
                break;
            case "delete":
                deleteContact(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void listContacts(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Contact> contacts = contactService.fetchAll();
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("/view-contact.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactService.fetchOne(id);
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("/edit-contact.jsp").forward(request, response);
    }

    private void addContact(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");

        Contact contact = new Contact();
        contact.setFirst_name(firstName);
        contact.setLast_name(lastName);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setNotes(notes);

        contactService.insert(contact);
        response.sendRedirect("contact?action=viewAll");
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");

        Contact contact = new Contact();
        contact.setId(id);
        contact.setFirst_name(firstName);
        contact.setLast_name(lastName);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setNotes(notes);

        contactService.updateContact(contact);
        response.sendRedirect("contact?action=viewAll");
    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        contactService.deleteContact(id);
        response.sendRedirect("contact?action=viewAll");
    }

    private void downloadVCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactService.fetchOne(id);
        
        if (contact != null) {
            response.setContentType("text/vcard");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + contact.getFirst_name() + "_" + contact.getLast_name() + ".vcf\"");
            
            try (PrintWriter out = response.getWriter()) {
                out.println("BEGIN:VCARD");
                out.println("VERSION:3.0");
                out.println("FN:" + contact.getFirst_name() + " " + contact.getLast_name());
                out.println("N:" + contact.getLast_name() + ";" + contact.getFirst_name() + ";;;");
                if (contact.getEmail() != null && !contact.getEmail().isEmpty()) {
                    out.println("EMAIL:" + contact.getEmail());
                }
                if (contact.getPhone() != null && !contact.getPhone().isEmpty()) {
                    out.println("TEL:" + contact.getPhone());
                }
                if (contact.getAddress() != null && !contact.getAddress().isEmpty()) {
                    out.println("ADR:;;" + contact.getAddress() + ";;;");
                }
                if (contact.getNotes() != null && !contact.getNotes().isEmpty()) {
                    out.println("NOTE:" + contact.getNotes());
                }
                out.println("END:VCARD");
            }
        }
    }
    
    private void shareEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactService.fetchOne(id);
        
        if (contact != null) {
            String subject = "Contact Information: " + contact.getFirst_name() + " " + contact.getLast_name();
            String body = "Name: " + contact.getFirst_name() + " " + contact.getLast_name() + "%0D%0A" +
                         "Email: " + contact.getEmail() + "%0D%0A" +
                         "Phone: " + contact.getPhone() + "%0D%0A" +
                         "Address: " + contact.getAddress() + "%0D%0A" +
                         "Notes: " + contact.getNotes();
            
            String mailtoUrl = "mailto:?subject=" + subject + "&body=" + body;
            response.sendRedirect(mailtoUrl);
        }
    }
    
    private void getContactInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactService.fetchOne(id);
        
        if (contact != null) {
            String contactInfo = String.format(
                "Contact Information:%n" +
                "Name: %s %s%n" +
                "Email: %s%n" +
                "Phone: %s%n" +
                "Address: %s%n" +
                "Notes: %s",
                contact.getFirst_name(), contact.getLast_name(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getAddress(),
                contact.getNotes()
            );
            
            response.setContentType("text/plain");
            response.getWriter().write(contactInfo);
        }
    }
}
