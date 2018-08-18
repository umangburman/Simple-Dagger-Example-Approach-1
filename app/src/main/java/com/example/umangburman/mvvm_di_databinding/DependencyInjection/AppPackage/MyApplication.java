package com.example.umangburman.mvvm_di_databinding.DependencyInjection.AppPackage;

import android.app.Application;

import com.example.umangburman.mvvm_di_databinding.DependencyInjection.Component.DaggerLoginComponent;
import com.example.umangburman.mvvm_di_databinding.DependencyInjection.Component.LoginComponent;

public class MyApplication extends Application {

    public static LoginComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerLoginComponent
                .builder()
                .build();

    }
}
