package com.example.ap2_ex3;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebServiceAPI {

    @POST("api/Users")
    Call<Void> createUser(@Body User user);
}
