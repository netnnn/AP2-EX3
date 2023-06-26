package com.example.ap2_ex3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    private Retrofit retrofit;

    private WebServiceAPI webServiceAPI;

    public UserAPI(){
        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }


}
