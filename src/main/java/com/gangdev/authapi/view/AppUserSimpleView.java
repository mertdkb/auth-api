package com.gangdev.authapi.view;

public class AppUserSimpleView {

    private String login;

    public AppUserSimpleView() {
    }

    public AppUserSimpleView(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
