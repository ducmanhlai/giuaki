package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.data.ProductDb;
import com.example.data.ProductModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ProductModel> ListItem;
    private Button btnAdd,btnShow,btnModify,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        Init();
        addEvent();
    }
    void Init(){
        ListItem = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listProduct);
        ProductDb productDb = new ProductDb(getApplicationContext(),null,null,1);
        ListItem.addAll(productDb.getData());
        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, R.layout.item_product, ListItem);
        listView.setAdapter(productAdapter);
        btnAdd = findViewById(R.id.add);
    }
    void addEvent(){
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Intent modifyScreen = new Intent(getApplicationContext(),ManageProduct.class);
             modifyScreen.putExtra("product",ListItem.get(i));
             startActivity(modifyScreen);
         }
     });
     btnAdd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent modifyScreen = new Intent(getApplicationContext(),ManageProduct.class);
             startActivity(modifyScreen);
         }
     });
    }
    @Override
    protected void onResume() {
        super.onResume();
        ProductDb productDb = new ProductDb(getApplicationContext(),null,null,1);
        ListItem.clear();
        ListItem.addAll(productDb.getData());
        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, R.layout.item_product, ListItem);
        listView.setAdapter(productAdapter);
    }
}
