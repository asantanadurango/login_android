package com.login_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.login_v1.databinding.ActivityLoginBinding;
import com.login_v1.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Toast.makeText(this, "Estas en el login", Toast.LENGTH_LONG).show();
    }

    // to signup
    public void toSignupActivity(View view) {
        Intent toSignup = new Intent(this, SignupActivity.class);
        String text_user = binding.txtUser.getText().toString();
        String text_password = binding.txtPassword.getText().toString();
        toSignup.putExtra("text_user", text_user);
        toSignup.putExtra("text_password", text_password);
        startActivity(toSignup);
    }

    // to home
    public void toHomeActivity(View view) {
        Intent toHome = new Intent(this, HomeActivity.class);
        startActivity(toHome);
    }
}