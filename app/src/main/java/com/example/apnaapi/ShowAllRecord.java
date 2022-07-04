package com.example.apnaapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.apnaapi.api.AdatperRetroFit;
import com.example.apnaapi.api.ApiUtilis;
import com.example.apnaapi.api.PostModel;
import com.example.apnaapi.databinding.ActivityShowAllRecordBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllRecord extends AppCompatActivity implements AdatperRetroFit.UserClickListener {

    ActivityShowAllRecordBinding binding;
    //    List<PostModel> list = new ArrayList<>();
    List<PostModel> filterPostModelList;
    AdatperRetroFit adatperRetroFit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getPost();
        //Binding RecycleView
        binding.recycle1.setLayoutManager(new LinearLayoutManager(this));
        binding.AddRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowAllRecord.this, AddPostActivity.class));
            }
        });
    }

    public void getPost() {
        ApiUtilis.getApiInterface().getAllPost().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    List<PostModel> postModelList = response.body();
                    adatperRetroFit = new AdatperRetroFit(postModelList, ShowAllRecord.this, ShowAllRecord.this::selectedPost);
                    binding.recycle1.setAdapter(adatperRetroFit);
                    adatperRetroFit.notifyDataSetChanged();
                } else {
                    Toast.makeText(ShowAllRecord.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(ShowAllRecord.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void selectedPost(PostModel postModel) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id", postModel.getId());
        intent.putExtra("Title", postModel.getTitle());
        intent.putExtra("Description", postModel.getDescription());
        startActivity(intent);
        Toast.makeText(this, "Selected PostModel : " + postModel.getId(), Toast.LENGTH_SHORT).show();
    }

}