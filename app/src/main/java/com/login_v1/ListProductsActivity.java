package com.login_v1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.login_v1.adapters.ProductAdapter;
import com.login_v1.database.DbHelper;
import com.login_v1.databinding.ActivityListProductsBinding;
import com.login_v1.entities.ProductEntity;

import java.util.ArrayList;

public class ListProductsActivity extends AppCompatActivity {

    private ActivityListProductsBinding binding;
    private DbHelper dbConnection;
    private ArrayList<ProductEntity> products;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProductsBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        dbConnection = new DbHelper(this);
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(products, this);
        binding.rvListProducts.setHasFixedSize(true);
        binding.rvListProducts.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListProducts.setAdapter(productAdapter);
        Toast.makeText(this, "Estas en productsList", Toast.LENGTH_SHORT).show();
        List();

    }

    public void List(){
        SQLiteDatabase db = dbConnection.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products", null);
        //Toast.makeText(this, cursor.getCount() + "", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, cursor.getColumnCount() + "", Toast.LENGTH_SHORT).show();

       /* if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
        Log.d("Columnas", cursor.getColumnCount() + "");
        Log.d("Registros", cursor.getCount() + "");
        //cursor.moveToFirst();
        Log.d("0", cursor.getString(0) + "");
        Log.d("1", cursor.getString(1) + "");
        Log.d("2", cursor.getString(2) + "");
        Log.d("3", cursor.getString(3) + "");
        Log.d("4", cursor.getString(4) + "");
            }
        }*/




        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int price = Integer.parseInt(cursor.getString(2));
                int units = Integer.parseInt(cursor.getString(3));
                String shop = cursor.getString(4);
                ProductEntity prod = new ProductEntity();
                prod.setId(id);
                prod.setName(name);
                prod.setPrice(price);
                prod.setUnits(units);
                prod.setShop(shop);

                products.add(prod);
            }
            Log.d("DEBUG", "should render view");
            productAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this, "No hay datos en la db", Toast.LENGTH_SHORT).show();
        }


    }


}