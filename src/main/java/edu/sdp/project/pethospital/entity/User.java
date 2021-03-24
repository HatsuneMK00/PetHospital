package edu.sdp.project.pethospital.entity;

public class User {
    private int userId=0;
    private String account ;
    private String name ;
    private String password ;
    private String role ;

    public User() {
    }

    public User(int userId, String account, String name, String password, String role) {
        this.userId = userId;
        this.account = account;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
