package com.login_v1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.login_v1.database.DbHelper;
import com.login_v1.databinding.ProductItemBinding;
import com.login_v1.entities.ProductEntity;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<ProductEntity> arrayListUsers;
    private Context context;
    private ProductItemBinding productItemBinding;
    private DbHelper dbConnection;
    public ProductAdapter(ArrayList<ProductEntity> users, Context context){
        this.arrayListUsers = users;
        this.context = context;
        dbConnection = new DbHelper(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productItemBinding = ProductItemBinding.inflate(LayoutInflater.from(context));
        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductEntity user = arrayListUsers.get(position);
        //holder.itemBinding.lblName.setText(user.getName());
        holder.itemBinding.tvName.setText(user.getName());
        //holder.itemBinding.lblPrice.setText(Integer.toString(user.getPrice()));
        holder.itemBinding.tvPrice.setText(Integer.toString(user.getPrice()));
        //holder.itemBinding.lblShop.setText(user.getShop());
        holder.itemBinding.tvShop.setText(user.getShop());
        holder.itemBinding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Comprado", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(context, EditUserActivity.class);
                //intent.putExtra("usuario", user);
                //context.startActivity(intent);
            }
        });
        /*
        holder.itemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbConnection.getWritableDatabase();
                db.delete("users","id=" + user.getId(), null);
                Intent intent = new Intent(context, ListUsersActivity.class);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayListUsers.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding itemBinding;
        public ProductViewHolder(@NonNull ProductItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
