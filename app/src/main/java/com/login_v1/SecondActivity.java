package com.login_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.login_v1.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.spinnerSignup.setOnItemSelectedListener(this);
        Toast.makeText(this, "Estas en el Signup", Toast.LENGTH_LONG).show();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerSignup.setAdapter(adapter);
    }

    // to login
    public void toLoginActivity(View view){

        String text_username = binding.txtUsername.getText().toString();
        String text_user= binding.txtUser.getText().toString();
        String text_password = binding.txtPassword.getText().toString();
        Intent toLogin = new Intent(this, MainActivity.class);

        //Envio datos
        toLogin.putExtra("text_username", text_username);
        toLogin.putExtra("text_user", text_user);
        toLogin.putExtra("text_userpassword", text_password);
        startActivity(toLogin);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String rol = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, rol, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}