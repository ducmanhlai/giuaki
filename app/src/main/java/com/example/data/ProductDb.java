package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ProductDb extends SQLiteOpenHelper {
    public ProductDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"ManagerProduct",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE  IF NOT EXISTS PRODUCT (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,IMG TEXT, DESCRIPTION TEXT,PRICE INTEGER,ISDELETE INTEGER,QUANTITY INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Integer update(ProductModel product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME",product.getName());
        values.put("DESCRIPTION",product.getDescription());
        values.put("IMG",product.getLinkImg());
        values.put("PRICE",product.getPrice());
        values.put("ISDELETE",0);
        values.put("QUANTITY",product.getQuantity());
        return db.update("PRODUCT",values,"ID="+product.getId(),null);
    }
    public Long insertData(ProductModel product)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME",product.getName());
        values.put("DESCRIPTION",product.getDescription());
        values.put("IMG",product.getLinkImg());
        values.put("PRICE",product.getPrice());
        values.put("ISDELETE",0);
        values.put("QUANTITY",product.getQuantity());
        long newRowId = db.insert("PRODUCT", null, values);
        return newRowId;
    }
    public ArrayList<ProductModel> getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ProductModel> listProduct = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            ProductModel product = new ProductModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getInt(5)==1,cursor.getInt(6));
            listProduct.add(product);
        }
        return listProduct;
    }
}
