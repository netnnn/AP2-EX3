package com.example.ap2_ex3;

import com.example.ap2_ex3.Chats.ChatData;
import com.example.ap2_ex3.Chats.ChatTileData;
import com.example.ap2_ex3.Messages.MsgData;
import com.example.ap2_ex3.Tokens.TokenRes;
import com.example.ap2_ex3.Users.GetUserRes;
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

    @POST("/api/Users")//V
    Call<Void> createUser(@Body JsonObject data);

    @GET("/api/Users/{username}")//V
    Call<GetUserRes> getUser(@Path("username") String username, @Header("Authorization") String authToken);

    @POST("/api/Tokens")//V
    Call<TokenRes> createToken(@Body JsonObject requestBody);

    @GET("/api/Chats")//V
    Call<List<ChatTileData>> getChats(@Header("Authorization") String authToken);

    @POST("/api/Chats")//V
    Call<Void> createChat(@Body JsonObject requestBody, @Header("Authorization") String authToken);

    @GET("/api/Chats/{id}")
    Call<ChatData> getChatById(@Path("id") int id, @Header("Authorization") String authToken);

    @DELETE("/api/Chats/{id}")//V
    Call<Void> deleteChatById(@Path("id") int id, @Header("Authorization") String authToken);

    @POST("/api/Chats/{id}/Messages")//V
    Call<MsgData> sendMessage(@Path("id") int id, @Body JsonObject requestBody , @Header("Authorization") String authToken);

    @GET("/api/Chats/{id}/Messages")//V
    Call<List<MsgData>> getAllMessages(@Path("id") int id, @Header("Authorization") String authToken);

}
