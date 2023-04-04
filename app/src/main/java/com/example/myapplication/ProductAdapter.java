package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.data.ProductModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;


public class ProductAdapter extends ArrayAdapter<ProductModel> {
    private Context context;
    private ArrayList<ProductModel> listProduct = new ArrayList<>();
    private int layoutResource;

    public ProductAdapter(Context context, int resource, ArrayList<ProductModel> listProduct) {
        super(context, resource, listProduct);
        this.context = context;
        this.layoutResource = resource;
        this.listProduct = listProduct;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<ProductModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<ProductModel> listProduct) {
        this.listProduct = listProduct;
    }

    public int getLayoutResource() {
        return layoutResource;
    }

    public void setLayoutResource(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResource, null);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(listProduct.get(position).getName());
        return convertView;
    }
}
