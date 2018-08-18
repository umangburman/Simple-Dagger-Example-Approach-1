package com.example.umangburman.mvvm_di_databinding.ViewModel;

import javax.inject.Inject;

public class LoginViewModel {

    private String strUsername;
    private String strPassword;

    @Inject
    public LoginViewModel() {}

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }
}
