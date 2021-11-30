package com.login_v1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.login_v1.adapters.UserAdapter;
import com.login_v1.database.DbHelper;
import com.login_v1.databinding.ActivityListUsersBinding;
import com.login_v1.entities.UserEntity;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity {

    private ActivityListUsersBinding binding;
    private DbHelper dbConnection;
    private ArrayList<UserEntity> users;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListUsersBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        dbConnection = new DbHelper(this);
        users = new ArrayList<>();
        userAdapter = new UserAdapter(users, this);
        binding.rvListUsers.setHasFixedSize(true);
        binding.rvListUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListUsers.setAdapter(userAdapter);
        listUsers();

    }

    public void listUsers(){
        SQLiteDatabase db = dbConnection.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                String role = cursor.getString(4);
                String shop = cursor.getString(5);
                UserEntity user = new UserEntity();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setShop(shop);
                user.setRole(role);
                users.add(user);
            }
            userAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this, "No hay datos en la db", Toast.LENGTH_SHORT).show();
        }
    }

}