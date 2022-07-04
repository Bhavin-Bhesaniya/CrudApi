package com.example.apnaapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaapi.api.ApiUtilis;
import com.example.apnaapi.api.PostModel;
import com.example.apnaapi.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    String Title, Description;
    int id;
    RequestBody RqTitle, RqDescription;

    //Custom
    List<PostModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.UpdateBtn.setOnClickListener(this);
        binding.DeleteBtn.setOnClickListener(this);
        binding.selectImg.setOnClickListener(this);

        id = getIntent().getIntExtra("id", -1);
        binding.BlogTitle.setText(getIntent().getStringExtra("Title"));
        binding.BlogDesc.setText(getIntent().getStringExtra("Description"));

    }

    public void putPostUpdate(int pid) {
        Title = binding.BlogTitle.getText().toString();
        Description = binding.BlogDesc.getText().toString();
        PostModel postModel = new PostModel(Title, Description);
        ApiUtilis.getApiInterface().updatePutPost(pid, postModel).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(@NonNull Call<PostModel> call, @NonNull Response<PostModel> response) {
                if (response.isSuccessful()) {
                    List<PostModel> postModelList = new ArrayList<>();
                    postModelList.add(response.body());
                    Toast.makeText(MainActivity.this, "Updated Successfully : code - " + response.code(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, ShowAllRecord.class));
                } else {
                    Toast.makeText(MainActivity.this, response.message() + response.code(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ShowAllRecord.class));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ShowAllRecord.class));
            }
        });

    }


    public void patchUpdate(int bid, RequestBody Title, RequestBody Description) {
        ApiUtilis.getApiInterface().updatePatchPost(bid, Title, Description).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(@NonNull Call<PostModel> call, @NonNull Response<PostModel> response) {
                if (response.isSuccessful()) {
                    List<PostModel> postModelList = new ArrayList<>();
                    postModelList.add(response.body());
                    Toast.makeText(MainActivity.this, "Updated Successfully : code - " + response.code(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, ShowAllRecord.class));
                } else {
                    Toast.makeText(MainActivity.this, response.message() + response.code(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ShowAllRecord.class));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ShowAllRecord.class));
            }
        });

    }


    public void deletePost(int deletePostId) {
        ApiUtilis.getApiInterface().deletePost(deletePostId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Data is " + response.message() + " : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(MainActivity.this, "Deleted Successfully : code - " + response.code(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, ShowAllRecord.class));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == binding.UpdateBtn) {
            int bid = id;
            Title = binding.BlogTitle.getText().toString();
            Description = binding.BlogDesc.getText().toString();
            RequestBody Titled = RequestBody.create(MediaType.parse("text/plain"), Title);
            RequestBody Descriptiond = RequestBody.create(MediaType.parse("text/plain"), Description);
//            patchUpdate(bid, Titled, Descriptiond);
            putPostUpdate(bid);
            clearField();

        }
        if (v == binding.DeleteBtn) {
            int delPid = id;
            deletePost(delPid);
            clearField();
        }
    }

    public void clearField() {
        binding.BlogTitle.setText("");
        binding.BlogDesc.setText("");
    }
}