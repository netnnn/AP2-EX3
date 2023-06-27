package com.example.ap2_ex3;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.ap2_ex3.Users.UserRegisterReqAndRes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ChatsAPI {


    private MutableLiveData<List<Chat>> chatsListData;
    private UserDao userDao;

    private Retrofit retrofit;

    private WebServiceAPI webServiceAPI;

    public ChatsAPI(MutableLiveData<List<Chat>> chatsListData, UserDao userDao) {
        retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);

        this.userDao = userDao;
        this.chatsListData = chatsListData;
    }


    public void getChatsListByName(String myName) {
        String theToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJDaGF0QXBwIiwianRpIjoiM2UzMzU1NWYtM2JiNC00MjM0LTg5MjQtMGMzYmViODJhMzM3IiwiaWF0IjoiMjcvMDYvMjAyMyAxMzoyMDozMyIsIlVzZXJOYW1lIjoiYWFhIiwiZXhwIjoxNjg3ODcyNjMzLCJpc3MiOiJDaGF0U2VydmVyIiwiYXVkIjoiQ2hhdENsaWVudCJ9.m8oYN2DVYFwv6_6p2A2SwUh9a0cX2M3PKt8LGKQhHPU";
        Call <List<NewChat>> call = webServiceAPI.getChats(theToken);
        call.enqueue(new Callback<List<NewChat>>() {
            @Override
            public void onResponse(Call<List<NewChat>> call, Response<List<NewChat>> response) {

                if (response.isSuccessful()) {
                    List<NewChat> chats = response.body();
                    int a = 1;
                } else {
                    int b = 1;
                }

                List<NewChat> chats = response.body();

//                new Thread(() -> {

//                    User me = userDao.get(myName);
//                    me.setChatList(response.body());
//                    userDao.update(me);
//                    chatsListData.setValue(me.getChatList());

//                }).start();
            }

            @Override
            public void onFailure(Call<List<NewChat>> call, Throwable t){}



        });


    }

    public void createUser(String myName) {


        webServiceAPI.getJson()
                .enqueue(new Callback<JsonExample>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonExample> call, @NonNull Response<JsonExample> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonExample> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });

    }




    public void getToken(String myName) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", "aaa");
        requestBody.addProperty("password", "aaa");

        Call<JsonObject> call = webServiceAPI.createToken(requestBody);
        System.out.println("tokennnnn");

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                String token;
                if (response.isSuccessful()) {
                    JsonObject responseBody = response.body();
                    if (responseBody == null){
                        return;
                    }
                    token = responseBody.get("token").getAsString();
                    // Handle the response
                    int a = 1;
                    System.out.println(token);
                    Log.d("tokenkkk", token);
                } else {
                    // Handle error
                    int b = 1;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("tokenkkk", "faileDDD");
                // ...
            }
        });

    }
}
