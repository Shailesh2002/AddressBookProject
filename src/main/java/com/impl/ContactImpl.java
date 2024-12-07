package com.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.interfaces.ContactInterface;
import com.model.java.Contact;
import com.util.DatabaseConnection;

public class ContactImpl implements ContactInterface {
    private Connection connection;

    public ContactImpl() {
        this.connection = DatabaseConnection.connect();
    }

    @Override
    public int insert(Contact contact) {
        String sql = "INSERT INTO contacts (first_name, last_name, email, phone, address, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.connect();
            }
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getFirst_name());
            ps.setString(2, contact.getLast_name());
            ps.setString(3, contact.getEmail());
            ps.setString(4, contact.getPhone());
            ps.setString(5, contact.getAddress());
            ps.setString(6, contact.getNotes());
            
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        contact.setId(generatedKeys.getInt(1));
                    }
                }
            }
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Contact> fetchAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Database connection is null or closed. Attempting to reconnect...");
                this.connection = DatabaseConnection.connect();
            }
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("contact_id"));
                contact.setFirst_name(rs.getString("first_name"));
                contact.setLast_name(rs.getString("last_name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setAddress(rs.getString("address"));
                contact.setNotes(rs.getString("notes"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            System.err.println("Error in fetchAll: " + e.getMessage());
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public Contact fetchOne(int contactId) {
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("contact_id"));
                contact.setFirst_name(rs.getString("first_name"));
                contact.setLast_name(rs.getString("last_name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setAddress(rs.getString("address"));
                contact.setNotes(rs.getString("notes"));
                return contact;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateContact(Contact contact) {
        String sql = "UPDATE contacts SET first_name=?, last_name=?, email=?, phone=?, address=?, notes=? WHERE contact_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contact.getFirst_name());
            ps.setString(2, contact.getLast_name());
            ps.setString(3, contact.getEmail());
            ps.setString(4, contact.getPhone());
            ps.setString(5, contact.getAddress());
            ps.setString(6, contact.getNotes());
            ps.setInt(7, contact.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteContact(int contactId) {
        String sql = "DELETE FROM contacts WHERE contact_id=?";
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.connect();
            }
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
