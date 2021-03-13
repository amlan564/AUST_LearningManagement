package com.example.austlm.Models;

public class Student {
    private String userId,name,email,pass,dept;

    public Student(String userId, String name, String email, String pass, String dept) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.dept=dept;
    }

    public Student() {
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}
