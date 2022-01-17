package com.example.sectionrv.retrofitpractice.listeners;

import com.example.sectionrv.retrofitpractice.model.Posts;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GetDataService {

    //to get relative URL
    @GET("posts")
    Call<List<Posts>> getPosts();

    @POST("posts")
    Call<List<Posts>> createPost(@Body Posts posts);

    @FormUrlEncoded
    @POST("posts")
    Call<Posts> createPosts(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );


    @Multipart
    @POST("/images/upload")
    Call<Posts> uploadImage(@Part MultipartBody.Part image);
}
