package com.model.java;

public class Contact {
    // Database columns
    private int id;  // primary key
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    private String notes;
    
    // Default constructor
    public Contact() {
    }
    
    // Parameterized constructor
    public Contact(String first_name, String last_name, String email, String phone, String address, String notes) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.notes = notes;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirst_name() {
        return first_name;
    }
    
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLast_name() {
        return last_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    // toString method for debugging and display purposes
    @Override
    public String toString() {
        return "Contact [id=" + id + 
               ", first_name=" + first_name + 
               ", last_name=" + last_name + 
               ", email=" + email + 
               ", phone=" + phone + 
               ", address=" + address + 
               ", notes=" + notes + "]";
    }
}
