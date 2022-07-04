package com.example.apnaapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaapi.api.ApiUtilis;
import com.example.apnaapi.api.PostModel;
import com.example.apnaapi.databinding.ActivityAddPostBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    ActivityAddPostBinding binding;
    String Title, Description;
    RequestBody RqTitle, RqDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.BlogTitle.getText().toString().isEmpty() || binding.BlogDesc.getText().toString().isEmpty()) {
                    Toast.makeText(AddPostActivity.this, "Please Enter Name and Description", Toast.LENGTH_SHORT).show();
                } else {
                    Title = binding.BlogTitle.getText().toString();
                    Description = binding.BlogDesc.getText().toString();
                    RqTitle = RequestBody.create(MediaType.parse("text/plain"), Title);
                    RqDescription = RequestBody.create(MediaType.parse("text/plain"), Description);
                    createPost();
                }
            }
        });
    }

    // Asynchronously send request and notify callback of its response or if an error occurred talking to server,
    // creating request, or processing response

    public void createPost() {
        ApiUtilis.getApiInterface().createPost(RqTitle, RqDescription).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful()) {
                    List<PostModel> postModelList = new ArrayList<>();
                    postModelList.add(response.body());
                    Toast.makeText(AddPostActivity.this, "Create Successfully : code - " + response.code(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddPostActivity.this, ShowAllRecord.class));
                } else {
                    Toast.makeText(AddPostActivity.this, response.message() + response.code(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddPostActivity.this, ShowAllRecord.class));
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(AddPostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddPostActivity.this, ShowAllRecord.class));
            }
        });
    }





    /*


    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                binding.ProdImgNm.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    */
}

