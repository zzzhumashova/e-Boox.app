package com.example.boox;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
//    TextView t ;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        t = (TextView)findViewById(R.id.signUpTv);
//        t.setOnClickListener(this);
//    }
//
//    public void onClick(View arg0) {
//        Intent i;
//        i = new Intent(LoginActivity.this, RegistrationActivity.class);
//        startActivity(i);
//    }
//}
import android.content.Intent;
import android.util.Patterns;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity{
        EditText email, password;
        Button login;
        TextView register;
        boolean isEmailValid, isPasswordValid;
        TextInputLayout emailError, passError;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            email = (EditText) findViewById(R.id.emailEt);
            password = (EditText) findViewById(R.id.passwordEt);
            login = (Button) findViewById(R.id.loginBtn);
            register = (TextView) findViewById(R.id.registerBtn);
            emailError = (TextInputLayout) findViewById(R.id.emailTil);
            passError = (TextInputLayout) findViewById(R.id.passwordTil);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SetValidation();
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                    startActivity(intent);
                }
            });
        }

        public void SetValidation() {
            // Check for a valid email address.
            if (email.getText().toString().isEmpty()) {
                emailError.setError(getResources().getString(R.string.email_error));
                isEmailValid = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                emailError.setError(getResources().getString(R.string.error_invalid_email));
                isEmailValid = false;
            } else  {
                isEmailValid = true;
                emailError.setErrorEnabled(false);
            }

            // Check for a valid password.
            if (password.getText().toString().isEmpty()) {
                passError.setError(getResources().getString(R.string.password_error));
                isPasswordValid = false;
            } else if (password.getText().length() < 6) {
                passError.setError(getResources().getString(R.string.error_invalid_password));
                isPasswordValid = false;
            } else  {
                isPasswordValid = true;
                passError.setErrorEnabled(false);
            }

            if (isEmailValid && isPasswordValid) {
                Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        }
}
