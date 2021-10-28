package com.login_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(this, "Estas en el Signup", Toast.LENGTH_LONG).show();
    }

    // to login
    public void toLoginActivity(View view){
        Intent toLogin = new Intent(this, MainActivity.class);
        startActivity(toLogin);
    }
}