package com.example.sectionrv.retrofitpractice.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
   // private static final String BASE_URL = "http://10.0.2.2:8080/";

    //to run the build on different we provide the private ip address
    //private static final String BASE_URL = "http://192.168.2.106:8080/";
    private static final String BASE_URL = "https://tastebuddy.xanthops.com/api/v1/";


    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
