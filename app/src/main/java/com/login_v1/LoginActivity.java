package com.login_v1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.login_v1.database.DbHelper;
import com.login_v1.databinding.ActivityLoginBinding;
import com.login_v1.entities.UserEntity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DbHelper bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "on create LOGIN");
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        bdHelper = new DbHelper(this);
    }

    // to signup
    public void toSignupActivity(View view) {
        Intent toSignup = new Intent(this, SignupActivity.class);
        String text_user = binding.txtEmail.toString();
        String text_password = binding.txtPassword.getText().toString();
        toSignup.putExtra("text_user", text_user);
        toSignup.putExtra("text_password", text_password);
        startActivity(toSignup);
    }

    // to home


    public void toGo(View view) {

        if(binding.txtEmail.getText().toString().length() < 1 && binding.txtPassword.getText().toString().length() < 1){
            binding.txtEmail.setText("");
            binding.txtPassword.setText("");
            Toast.makeText(this, "Ingrese un email y password", Toast.LENGTH_SHORT).show();
        }else if(binding.txtEmail.getText().toString().length() < 1){
            binding.txtEmail.setText("");
            Toast.makeText(this, "Ingrese un email", Toast.LENGTH_SHORT).show();
        }else if(binding.txtPassword.getText().toString().length() < 1){
            binding.txtPassword.setText("");
            Toast.makeText(this, validation().getRole() + "", Toast.LENGTH_SHORT).show();
        }else if(validation().getRole().equals("Admin")){
            Toast.makeText(this, validation().getRole(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CreteProductActivity.class);
            intent.putExtra("usuario", validation());
            startActivity(intent);
        }else if(validation().getRole().equals("User")){
            Toast.makeText(this, validation().getRole(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListProductsActivity.class);
            intent.putExtra("usuario", validation());
            startActivity(intent);
        }else{
                Toast.makeText(this, "Email y/o Password incorrectos", Toast.LENGTH_SHORT).show();
                binding.txtEmail.setText("");
                binding.txtPassword.setText("");
            }
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Email y/o Password incorrectos", Toast.LENGTH_SHORT).show();
        binding.txtEmail.setText("");
        binding.txtPassword.setText("");
    }

    /*
    public void toList(View view) {
        Intent toHome = new Intent(this, ListUsersActivity.class);
        startActivity(toHome);
    }

     */

        public UserEntity validation(){
            SQLiteDatabase db = bdHelper.getWritableDatabase();
            String email = binding.txtEmail.getText().toString();
            String password = binding.txtPassword.getText().toString();
            String query = "SELECT * FROM users where (email = '" + email +"') and (password = '" + password +"')";
            Cursor cursor = db.rawQuery(query, null);
            Log.d("DEBUG", String.valueOf(cursor.getColumnCount()));
            cursor.moveToFirst();
            Log.d("TAG", cursor.getCount() + "");
            UserEntity values = new UserEntity();

            if(cursor.getCount()>0){
                cursor.moveToFirst();
                int id_value=Integer.parseInt(cursor.getString(0));
                String name_value=cursor.getString(1);
                String email_value=cursor.getString(2);
                String password_value=cursor.getString(3);
                String rol_value=cursor.getString(4);
                String shop_value=cursor.getString(5);

                //String numCadena = "1";
                //int numEntero = Integer.parseInt(numCadena);

                values.setId(id_value);
                values.setName(name_value);
                values.setEmail(email_value);
                values.setPassword(password_value);
                values.setRole(rol_value);
                values.setShop(shop_value);

                /*
                Log.d("id", cursor.getString(0));
                Log.d("name", cursor.getString(1));
                Log.d("email", cursor.getString(2));
                Log.d("password", cursor.getString(3));
                Log.d("rol", cursor.getString(4));
                Log.d("shop", cursor.getString(5));
                */
            }

            return  values;


        }

}