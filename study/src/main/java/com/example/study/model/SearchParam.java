package com.example.study.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SearchParam {
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private String account;
    private String email;
    private int page;
}
