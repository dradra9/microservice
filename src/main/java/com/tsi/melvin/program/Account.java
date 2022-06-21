package com.tsi.melvin.program;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int accountId;

    String user_name;
    String password;
    Integer level;
    String name;

    public Account(String user_name, String password, String name, int level  )
    {
        this.user_name = user_name;
        this.password = password;
        this.level = level;
        this.name = name;
    }

    public Account()
    {}

    public int getAccountId() {
        return accountId;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return user_name;
    }

    public void setAccount_id(int accountId) {
        this.accountId = accountId;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
