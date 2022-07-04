package com.example.apnaapi.api;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

// static constants and abstract methods
// The interface in Java is a mechanism to achieve abstraction.

public interface Api {

    @GET("blogapi")
    Call<List<PostModel>> getAllPost();
    // Call is Retrofit method that sends request to webserver and returns response.
    // Each call yields its own HTTP request and response pair.

    @Multipart
    @POST("blogapi/")
    Call<PostModel> createPost(@Part("Title") RequestBody Title, @Part("Description") RequestBody Description);

    @PUT("blogapi/{id}")
    Call<PostModel> updatePutPost(@Path("id") int id, @Body PostModel postModel);

    @Multipart
    @PATCH("blogapi/{id}")
    Call<PostModel> updatePatchPost(@Path("id") int id, @Part("Title") RequestBody Title, @Part("Description") RequestBody Description);

    @DELETE("blogapi/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
