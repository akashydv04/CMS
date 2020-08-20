package com.example.cms.model;

public class EmployeeModel {
    String id, name, email, phone, credit;

    public EmployeeModel(String id, String name, String email, String phone, String credit) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credit = credit;
    }

    public EmployeeModel(String id) {
        this.name = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
