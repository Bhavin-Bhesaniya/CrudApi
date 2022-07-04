package com.example.apnaapi.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaapi.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdatperRetroFit extends RecyclerView.Adapter<AdatperRetroFit.PostViewHolder> {
    List<PostModel> postModelList;
    Context context;
    UserClickListener userClickListener;

    public AdatperRetroFit(List<PostModel> postModels, Context context, UserClickListener userClickListener) {
        this.postModelList = postModels;
        this.context = context;
        this.userClickListener = userClickListener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel postModel = postModelList.get(position);
        holder.id.setText(String.valueOf(postModel.getId()));
        holder.Title.setText(postModel.getTitle());
        holder.Description.setText(postModel.getDescription());


        Picasso.with(context.getApplicationContext())
                .load(postModelList.get(position).getPostImage())
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(holder.PostImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClickListener.selectedPost(postModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }


    public interface UserClickListener {
        void selectedPost(PostModel postModel);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView id, Title, Description;
        ImageView PostImage;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textView2);
            Title = itemView.findViewById(R.id.textView3);
            Description = itemView.findViewById(R.id.textView4);
            PostImage = itemView.findViewById(R.id.ProdImg);
        }
    }
}
