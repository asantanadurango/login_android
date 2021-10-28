package com.login_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Estas en el login", Toast.LENGTH_LONG).show();
        // La actividad esta creada.
    }

    // to signup
    public void toSignupActivity(View view){
        Intent toSignup = new Intent(this, SecondActivity.class);
        startActivity(toSignup);
    }

    // to home
    public void toHomeActivity(View view){
        Intent toHome = new Intent(this, HomeActivity.class);
        startActivity(toHome);
    }




}