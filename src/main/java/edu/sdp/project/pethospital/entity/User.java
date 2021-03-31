package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class User {
    private Integer userId;

    private String account;

    private String name;

    private String password;

    private String role;

    public User(Integer userId, String account, String name, String password, String role) {
        this.userId = userId;
        this.account = account;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User() {
        super();
    }

    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
    public void updateUser(Map params) {
        if (params.containsKey("userId"))
            this.userId = Integer.valueOf(params.get("userId").toString());
        if (params.containsKey("name"))
            this.name = params.get("name").toString();
        if (params.containsKey("password"))
            this.password = params.get("password").toString();
        if (params.containsKey("account"))
            this.account = params.get("account").toString();
        if (params.containsKey("role"))
            this.role = params.get("role").toString();

    }
}