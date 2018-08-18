package com.example.umangburman.mvvm_di_databinding.View;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.umangburman.mvvm_di_databinding.DependencyInjection.AppPackage.MyApplication;
import com.example.umangburman.mvvm_di_databinding.R;
import com.example.umangburman.mvvm_di_databinding.ViewModel.CredentialViewModel;
import com.example.umangburman.mvvm_di_databinding.ViewModel.LoginViewModel;
import com.example.umangburman.mvvm_di_databinding.databinding.ActivityMainBinding;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public LoginViewModel loginViewModel;

    @Inject
    public CredentialViewModel credentialViewModel;

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        loginViewModel = MyApplication.component.loginViewModel();
        credentialViewModel = MyApplication.component.credentialViewModel();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                loginViewModel.setStrUsername(binding.txtUsername.getText().toString().trim());
                loginViewModel.setStrPassword(binding.txtPassword.getText().toString().trim());

                binding.lblResult.setText("Username : " + loginViewModel.getStrUsername() + "\n\n" + "Password : " + loginViewModel.getStrPassword());

                binding.lblCredential.setText(credentialViewModel.getLoginCreds());

            }
        });

    }
}
