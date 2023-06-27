package com.example.ap2_ex3;

import com.example.ap2_ex3.Users.UserRegisterReqAndRes;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @POST("/api/Users")
    Call<Void> createUser(@Body UserRegisterReqAndRes data);

    @GET("/api/Users/{username}")
    Call<User> getUser(@Path("username") String username);

    @POST("/api/Tokens")
    Call<String> getToken(User user);

//    @GET("api/Chats")
//    Call<List<Chat>> getAllMyChats();

    @GET("/api/Chats")
    Call<List<NewChat>> getChats(@Header("Authorization") String authToken);

    @POST("/api/Tokens")
    Call<JsonObject> createToken(@Body JsonObject requestBody);

    @POST("/api/Chats")
    Call<Void> createChat(Chat chat);

    @GET("/api/Chats/{id}")
    Call<Chat> getChatById(@Path("id") int id);

    @DELETE("/api/Chats/{id}")
    Call<Void> deleteChatById(@Path("id") int id);

    @POST("/api/Chats/{id}/Messages")
    Call<Message> sendMessage(@Path("id") int id, Message message);

    @GET("/api/Chats/{id}/Messages")
    Call<List<Message>> getAllMessages(@Path("id") int id);


    @POST("/api/Tokens")
    Call<String> getToken(@Path("name") String name);


    @GET("/todos/1")
    Call<JsonExample> getJson();
}
