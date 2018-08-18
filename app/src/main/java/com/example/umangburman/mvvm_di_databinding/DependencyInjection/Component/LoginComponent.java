package com.example.umangburman.mvvm_di_databinding.DependencyInjection.Component;

import com.example.umangburman.mvvm_di_databinding.DependencyInjection.Module.LoginModule;
import com.example.umangburman.mvvm_di_databinding.ViewModel.CredentialViewModel;
import com.example.umangburman.mvvm_di_databinding.ViewModel.LoginViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LoginModule.class})
public interface LoginComponent {

    // Declare Objects To Use
    // LoginViewModel Object
    LoginViewModel loginViewModel();

    // CredentialsViewModel Object
    CredentialViewModel credentialViewModel();

}
