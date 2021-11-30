package com.login_v1.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.login_v1.ListUsersActivity;
import com.login_v1.EditUserActivity;
import com.login_v1.database.DbHelper;
import com.login_v1.databinding.UserItemBinding;
import com.login_v1.entities.UserEntity;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<UserEntity> arrayListUsers;
    private Context context;
    private UserItemBinding userItemBinding;
    private DbHelper dbConnection;
    public UserAdapter(ArrayList<UserEntity> users, Context context){
        this.arrayListUsers = users;
        this.context = context;
        dbConnection = new DbHelper(context);
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        userItemBinding = UserItemBinding.inflate(LayoutInflater.from(context));
        return new UserViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        Log.d("DEBUG", "--- onBindViewHolder ---");
        UserEntity user = arrayListUsers.get(position);
        holder.itemBinding.tvName.setText(user.getName());
        holder.itemBinding.tvEmail.setText(user.getEmail());
        holder.itemBinding.tvRol.setText(user.getRole());
        holder.itemBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUserActivity.class);
                intent.putExtra("usuario", user);
                context.startActivity(intent);
            }
        });
        holder.itemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbConnection.getWritableDatabase();
                db.delete("users","id=" + user.getId(), null);
                Intent intent = new Intent(context, ListUsersActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding itemBinding;
        public UserViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
