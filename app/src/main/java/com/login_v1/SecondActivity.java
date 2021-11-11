package com.login_v1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.login_v1.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivitySecondBinding binding;
    DbHelper bdHelper;
    String rol_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        assert binding.spinnerSignup != null;
        binding.spinnerSignup.setOnItemSelectedListener(this);
        Toast.makeText(this, "Estas en el Signup", Toast.LENGTH_LONG).show();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerSignup.setAdapter(adapter);

        //SQL
        bdHelper = new DbHelper(this);

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
        toLogin.putExtra("text_password", text_password);
        startActivity(toLogin);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        rol_value = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, rol_value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //SQL

    public void insertSQL (View view){

        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String username_value=binding.txtUsername.getText().toString();
        String email_value=binding.txtUser.getText().toString();
        String password_value=binding.txtPassword.getText().toString();
        values.put("username", username_value);
        values.put("email", email_value);
        values.put("password", password_value);
        values.put("rol", rol_value);
        long newUser = db.insert("users", null, values);
        Toast.makeText(this, ""+newUser, Toast.LENGTH_SHORT).show();
    }
}