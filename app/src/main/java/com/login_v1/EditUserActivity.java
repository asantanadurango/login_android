package com.login_v1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.login_v1.database.DbHelper;
import com.login_v1.databinding.ActivityEditUserBinding;
import com.login_v1.entities.UserEntity;

public class EditUserActivity extends AppCompatActivity {

    private ActivityEditUserBinding binding;
    private DbHelper dbConnection;
    private UserEntity user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "conchudo");
        super.onCreate(savedInstanceState);
        binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        this.user = (UserEntity) getIntent().getSerializableExtra("usuario");
        Toast.makeText(this, ""+ user.getId() + ": " + user.getName(), Toast.LENGTH_SHORT).show();
        binding.txtName.setText(user.getName());
        binding.txtEmail.setText(user.getEmail());
        binding.lblRol.setText(user.getRole());
       dbConnection = new DbHelper(this);

    }

    public void toGoBack(View view){
        Intent intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
        Toast.makeText(this, "toBack", Toast.LENGTH_SHORT).show();
    }

    public void Update(View view){
        ContentValues values = new ContentValues();
        values.put("name", binding.txtName.getText().toString());
        values.put("email", binding.txtEmail.getText().toString());
        SQLiteDatabase db = dbConnection.getWritableDatabase();
        db.update("users", values, "id = ?", new String[]{String.valueOf(this.user.getId())});
        Intent intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
    }
}