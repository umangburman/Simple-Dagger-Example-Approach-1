# simple-dagger2-android-login-example

This is a very simple **Login Example of Dependency Injection using Dagger 2** in Android. 

Login Example is specially for those beginners who wish to have a very basic understanding of what Dependency Injection 
(we'll call it **DI** for short) is all about.


So Lets Begin:

Topics:
1. What is Dependency Injection?
2. What is Dagger 2?
3. Important Annotations or Components
4. Real-time Coding
5. Conclusion


## **What is Dependency Injection?**

**Answer :** Any business application is made up of two or more classes and these classes collaborate with each other to perform some operation. Traditionally, we create the instance of dependent class object and do the required operations. When applying Dependency Injection, the objects are given their dependencies at creation time by some external entity that coordinates each object in the system. It means dependencies are injected into objects.

In other words, Dependency injection is a style of object creation in which an objects are created by an external entity, or technique whereby one object supplies the dependencies of another object.

It is built upon the concept of Inversion of Control which says “Rather than low level code calling up to high level code, high level code can receive lower level code that it can call down to. This inverts the typical control pattern seen in procedural programming.”

## **What is Dagger 2?**

**Answer :** Dagger 2 is one of the open source DI frameworks which generates a lot of boilerplate for us. But why is it better than the others? Right now it’s the only one DI framework which generates fully traceable source code in Java which mimics the code that user may write by hand. It means that there is no magic in dependencies graph construction. Dagger 2 is less dynamic than the others (no reflection usage at all) but simplicity and performance of the generated code are on the same level as the hand-written code. In short, Dagger 2 generates all the dependency injection boilerplate for you.

## **Important Annotations or Components**

<img src="https://pli.io/EcZlH.png" alt="EcZlH.png" border="0" />

## **Real-time Coding**

Before Adding The Code In Your Practice Project, Make Sure You Have The Depenedencies Added In Your Gradle (app):

```java
// Libraries For Dependency Injection Using Dagger 2
implementation 'com.google.dagger:dagger:2.11'
implementation 'com.google.dagger:dagger-android:2.11'
annotationProcessor 'com.google.dagger:dagger-android-processor:2.11'
annotationProcessor 'com.google.dagger:dagger-compiler:2.11'
```

Also, add below lines for DataBinding
```Java
android {
    ...
    dataBinding {
        enabled true
    }
}
```

###### Step 1 : Login View Model Class

```java
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
```

###### Step 2 : Login Module Class

```java
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
```

###### Step 3 : Login Component Class

```java
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
```

###### Step 4 : Application Class

```java
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
```

###### Step 4 : MainActivity Class

```java
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
```

## **Conclusion**

Hopefully that helped you see how much simpler it is to use Dagger2 than the nightmarish “learning curve” that people talk about.

It’s really just:

   - Apply @Singleton and @Inject
   - Create @Module where you can’t do @Inject
   - Create @Componentand use component

That’s not so hard, is it?

Feel free to reach me at any time on [LinkedIn](https://www.linkedin.com/in/umangburman/)
