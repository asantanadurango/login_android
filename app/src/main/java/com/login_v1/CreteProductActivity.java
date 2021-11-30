package com.login_v1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.login_v1.database.DbHelper;
import com.login_v1.databinding.ActivityCreteProductBinding;
import com.login_v1.entities.ProductEntity;
import com.login_v1.entities.UserEntity;

public class CreteProductActivity extends AppCompatActivity {

    ActivityCreteProductBinding binding;
    UserEntity dataUser;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreteProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dbHelper = new DbHelper(this);
        this.dataUser = (UserEntity) getIntent().getSerializableExtra("usuario");
        Toast.makeText(this, dataUser.getShop(), Toast.LENGTH_SHORT).show();
    }

    public void insertProduct(View view){
        /*
        Log.d("id", Integer.toString(createProduct().getId()));
        Log.d("name", createProduct().getName());
        Log.d("price", Integer.toString(createProduct().getPrice()));
        Log.d("units", Integer.toString(createProduct().getUnits()));
        Log.d("shop", createProduct().getShop());
        */

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        ProductEntity product = createProduct();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("units", product.getUnits());
        values.put("shop", product.getShop());
         db.insert("products", null, values);
        Cursor cursor = db.rawQuery("select * from products", null);
        Toast.makeText(this, cursor.getCount()+"", Toast.LENGTH_SHORT).show();
        if(cursor.moveToFirst()){
            Log.d("0", cursor.getString(0));
            Log.d("1", cursor.getString(1));
            Log.d("2", cursor.getString(2));
        }
        clearFields();
    }

    public ProductEntity createProduct(){
        ProductEntity product = new ProductEntity();
        product.setName(binding.txtName.getText().toString());
        product.setPrice(Integer.parseInt(binding.txtPrice.getText().toString()));
        product.setUnits(1);
        product.setShop(dataUser.getShop());
        return product;
    }

    public void clearFields(){
        binding.txtName.setText("");
        binding.txtPrice.setText("");
    }

    public  void toGoListProducts(View view){
        Intent intent = new Intent(this, ListProductsActivity.class);
        startActivity(intent);
    }

    public  void toGoListUsers(View view){
        Intent intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
    }
}