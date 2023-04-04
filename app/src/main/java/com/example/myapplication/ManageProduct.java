package com.example.myapplication;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.data.ProductDb;
import com.example.data.ProductModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ManageProduct extends AppCompatActivity {
    EditText editName, editDescription, editPrice, editQuantity;
    Button btnUpdate;
    Boolean isUpdate = false;
    ImageButton btnImage;
    ProductModel productModel= new ProductModel();
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    btnImage.setImageURI(uri);
                    try {
                        productModel.setLinkImg(saveImage(uri));
                    } catch (IOException e) {
                        Toast.makeText(ManageProduct.this,"Có lỗi xảy ra",Toast.LENGTH_SHORT).show();
                        throw new RuntimeException(e);
                    }
                }
            });
    private final ProductDb productDb = new ProductDb(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        Init();
        setEvent();
    }

    private void Init() {
        editName = (EditText) findViewById(R.id.nameProduct);
        editDescription = (EditText) findViewById(R.id.descriptionProduct);
        editPrice = (EditText) findViewById(R.id.priceProduct);
        editQuantity = (EditText) findViewById(R.id.quantityProduct);
        btnImage = findViewById(R.id.imgProduct);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        Intent i = getIntent();
        if (i.getSerializableExtra("product") != null) {
            productModel = (ProductModel) i.getSerializableExtra("product");
            editName.setText(productModel.getName());
            editDescription.setText(productModel.getDescription());
            editPrice.setText(productModel.getPrice().toString());
            editQuantity.setText(productModel.getQuantity().toString());
            isUpdate = true;
        } else {
            btnUpdate.setText("Tạo mới");
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleCreate();
                }
            });
        }

    }
    private void setEvent(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCreate();
            }
        });
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });
        Picasso.get()
                .load(new File(productModel.getLinkImg()))
                .into(btnImage);
    }
    private void handleCreate() {
        if (isUpdate){
            productModel.setName(String.valueOf(editName.getText()));
            productModel.setDeleteFlag(false);
            productModel.setDescription(String.valueOf(editDescription.getText()));
            productModel.setPrice(Integer.valueOf(editPrice.getText().toString()));
            productModel.setQuantity(Integer.parseInt(editQuantity.getText().toString()));
            productDb.update(productModel);
            Toast.makeText(this,"Thành công", Toast.LENGTH_LONG).show();
        }
        else {
            productModel.setName(String.valueOf(editName.getText()));
            productModel.setDeleteFlag(false);
            productModel.setDescription(String.valueOf(editDescription.getText()));
            productModel.setPrice(Integer.valueOf(editPrice.getText().toString()));
            productModel.setQuantity(Integer.parseInt(editQuantity.getText().toString()));
            productDb.insertData(productModel);
            Toast.makeText(this,"Thành công", Toast.LENGTH_LONG).show();
        }


    }
    private String  saveImage(Uri uri) throws IOException {
        File dir = new File(Environment.getDataDirectory(),"Image");
        if(!dir.exists()){
            dir.mkdir();
        }
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//        Toast.makeText(ManageProduct.this,String.valueOf(bitmap.getHeight()),Toast.LENGTH_LONG).show();
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Image", Context.MODE_PRIVATE);
        // Create imageDir
        String uniqueID = UUID.randomUUID().toString();
        File mypath=new File(directory,uniqueID+".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getPath();
    }
}
