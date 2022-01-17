package com.example.sectionrv.retrofitpractice.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sectionrv.R;
import com.example.sectionrv.retrofitpractice.adapter.CustomAdapter;
import com.example.sectionrv.retrofitpractice.listeners.GetDataService;
import com.example.sectionrv.retrofitpractice.model.Posts;
import com.example.sectionrv.retrofitpractice.network.RetrofitClientInstance;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitPractice extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    public static final String TAG = RetrofitPractice.class.getSimpleName();

    private static final int INTENT_REQUEST_CODE = 100;

    private Button mBtImageSelect;
    private Button mBtImageShow;
    private String mImageUrl = "";
   // public static final String URL = "http://10.0.2.2:8080";
    //public static final String URL = "http://192.168.2.106:8080";
   public static final String URL = "https://tastebuddy.xanthops.com/api/v1/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrofit_practice);

        progressDialog = new ProgressDialog(RetrofitPractice.this);
        progressDialog.setMessage("Loading.......");
       // progressDialog.show();

        createPost();
        createGet();
       // initViews();

    }

    private void initViews() {

//        mBtImageSelect = findViewById(R.id.select_btn);
//        mBtImageShow = findViewById(R.id.show_btn);

        mBtImageSelect.setOnClickListener((View view) -> {

            mBtImageShow.setVisibility(View.GONE);

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/jpeg");

            try {
                startActivityForResult(intent, INTENT_REQUEST_CODE);

            } catch (ActivityNotFoundException e) {

                e.printStackTrace();
            }

        });

        mBtImageShow.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mImageUrl));
            startActivity(intent);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                try {

                    InputStream is = getContentResolver().openInputStream(data.getData());

                    uploadImage(getBytes(is));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploadImage(byte[] imageBytes) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
        Call<Posts> call = service.uploadImage(body);
        progressDialog.show();

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()) {

                    Posts postsBody = response.body();
                    mBtImageShow.setVisibility(View.VISIBLE);
                    mImageUrl = URL + postsBody.getPath();
                    //Snackbar.make(findViewById(R.id.content), postsBody.getMessage(),Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(RetrofitPractice.this, postsBody.getMessage(), Toast.LENGTH_LONG).show();


                } else {

                    ResponseBody errorBody = response.errorBody();

                    Gson gson = new Gson();

                    try {

                        Posts errorResponse = gson.fromJson(errorBody.string(), Posts.class);
                        //Snackbar.make(findViewById(R.id.content), errorResponse.getMessage(),Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(RetrofitPractice.this, errorResponse.getMessage(), Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(RetrofitPractice.this, "onFailure: "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }



    private void createPost(){

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Posts> call = service.createPosts(25, "New Title", "New Text");
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                progressDialog.dismiss();
                if(!response.isSuccessful()){
                    Toast.makeText(RetrofitPractice.this, "Code: " +response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RetrofitPractice.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createGet(){
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Posts>> call = service.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                progressDialog.dismiss();
                if(!response.isSuccessful()){
                    Toast.makeText(RetrofitPractice.this, "Code: " +response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
               // generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RetrofitPractice.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(Posts postsList) {
        recyclerView = findViewById(R.id.retrofit_rv);
        List<Posts> posts = new ArrayList<>();
        posts.add(postsList);
        adapter = new CustomAdapter(this,posts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RetrofitPractice.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}