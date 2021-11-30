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

import com.login_v1.database.DbHelper;
import com.login_v1.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivitySignupBinding binding;
    private DbHelper bdHelper;
    private String rol_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.spinnerSignup.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerSignup.setAdapter(adapter);



        //SQL
        bdHelper = new DbHelper(this);

    }

    // to login
    public void toLoginActivity(View view) {
        String text_username = binding.txtUsername.getText().toString();
        String text_user = binding.txtEmail.getText().toString();
        String text_password = binding.txtPassword.getText().toString();
        Intent toLogin = new Intent(this, LoginActivity.class);

        //Envio datos
        toLogin.putExtra("text_username", text_username);
        toLogin.putExtra("text_user", text_user);
        toLogin.putExtra("text_password", text_password);
        startActivity(toLogin);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        rol_value = parent.getItemAtPosition(position).toString();
        if (rol_value.equals("Admin")) {
            binding.lblNameShop.setVisibility(View.VISIBLE);
        } else {
            binding.lblNameShop.setText("undefine");
            binding.lblNameShop.setVisibility(View.GONE);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //SQL

    public void insertSQL (View view){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String username_value=binding.txtUsername.getText().toString();
        String email_value=binding.txtEmail.getText().toString();
        String password_value=binding.txtPassword.getText().toString();
        String shop_value=binding.lblNameShop.getText().toString();
        values.put("name", username_value);
        values.put("email", email_value);
        values.put("password", password_value);
        values.put("rol", rol_value);
        if(rol_value.equals("Admin")){
            String shopName = binding.lblNameShop.getText().toString();
            values.put("shop", shopName);
        }else{
            values.put("shop", "undefine");
        }


        if(binding.txtUsername.getText().toString().length() < 1 ||
                binding.txtEmail.getText().toString().length() < 1 ||
                binding.txtPassword.getText().toString().length() < 1 ||
                binding.lblNameShop.getText().toString().length() < 1 ||
                binding.spinnerSignup.getSelectedItem().toString().length() < 1)
        {
            Toast.makeText(this, "todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }else if(specialCharacter(password_value)==true && password_value.length() > 7){
            if(email_value.contains("@")){
                long newUser = db.insert("users", null, values);
                Toast.makeText(this, ""+newUser, Toast.LENGTH_SHORT).show();
                Intent toList = new Intent(this, ListUsersActivity.class);
                startActivity(toList);
            }else{
                Toast.makeText(this, "el email debe contener al menos un '@'", Toast.LENGTH_SHORT).show();
            }
        }else{
            clearFields();
            Toast.makeText(this, "contraseña no valida", Toast.LENGTH_SHORT).show();
        }

    }

    public void clearFields(){
        binding.txtUsername.setText("");
        binding.txtEmail.setText("");
        binding.txtPassword.setText("");
    }

    public boolean specialCharacter(String password){
        String[] matches = new String[] {"¿", "¡", "=", ")", "(", "/", "&", "%", "$", "#", "!", "*", "-", "+"};

        Boolean found = false;
        for (String s : matches)
        {
            if (password.contains(s))
            {
                return true;
            }
        }
        return found;
    }
}