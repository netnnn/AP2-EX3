package com.example.ap2_ex3;

import androidx.annotation.NonNull;

import com.example.ap2_ex3.Chats.ChatData;
import com.example.ap2_ex3.Chats.ChatTileData;
import com.example.ap2_ex3.Messages.MsgData;
import com.example.ap2_ex3.Tokens.TokenRes;
import com.example.ap2_ex3.Users.GetUserRes;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequests {

    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    private String token;


    public ApiRequests() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.155.24:5000")
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void createUser(User user) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", user.getUsername());
        requestBody.addProperty("password", user.getPassword());
        requestBody.addProperty("displayName", user.getDisplayName());
        requestBody.addProperty("profilePic", user.getBase64());


        webServiceAPI.createUser(requestBody)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });

    }

    public void getUser(String username) {


        webServiceAPI.getUser(username, this.token)
                .enqueue(new Callback<GetUserRes>() {
                    @Override
                    public void onResponse(@NonNull Call<GetUserRes> call, @NonNull Response<GetUserRes> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<GetUserRes> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });

    }

    public void getToken(String username, String password) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", username);
        requestBody.addProperty("password", password);

        webServiceAPI.createToken(requestBody)
                .enqueue(new Callback<TokenRes>() {
                    @Override
                    public void onResponse(@NonNull Call<TokenRes> call, @NonNull Response<TokenRes> response) {
                        TokenRes s = response.body();
                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<TokenRes> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });

    }


    public void getMyChats() {

        webServiceAPI.getChats(this.token)
                .enqueue(new Callback<List<ChatTileData>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ChatTileData>> call, @NonNull Response<List<ChatTileData>> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<ChatTileData>> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });

    }

    public void createNewChat(String name) {

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", name);

        webServiceAPI.createChat(requestBody, this.token)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });
    }

    public void getChatById(int id) {

        webServiceAPI.getChatById(id, this.token)
                .enqueue(new Callback<ChatData>() {
                    @Override
                    public void onResponse(@NonNull Call<ChatData> call, @NonNull Response<ChatData> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<ChatData> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });
    }

    public void sendMsg(int chatId, String content) {

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("msg", content);

        webServiceAPI.sendMessage(chatId, requestBody, this.token)
                .enqueue(new Callback<MsgData>() {
                    @Override
                    public void onResponse(@NonNull Call<MsgData> call, @NonNull Response<MsgData> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<MsgData> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });
    }

    public void getMyMsg(int chatId) {

        webServiceAPI.getAllMessages(chatId, this.token)
                .enqueue(new Callback<List<MsgData>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<MsgData>> call, @NonNull Response<List<MsgData>> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<MsgData>> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });
    }


}
