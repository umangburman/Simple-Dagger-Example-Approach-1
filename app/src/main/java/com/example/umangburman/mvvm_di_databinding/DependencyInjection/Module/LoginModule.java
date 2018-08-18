package com.example.umangburman.mvvm_di_databinding.DependencyInjection.Module;

import com.example.umangburman.mvvm_di_databinding.ViewModel.CredentialViewModel;
import com.example.umangburman.mvvm_di_databinding.ViewModel.LoginViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class LoginModule {

    @Singleton
    @Provides
    public LoginViewModel loginViewModel() {
        return new LoginViewModel();
    }

    @Singleton
    @Provides
    public CredentialViewModel CredentialViewModel() {
        return new CredentialViewModel();
    }

}

