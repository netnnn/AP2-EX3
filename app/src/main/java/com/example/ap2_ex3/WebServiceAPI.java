package com.example.ap2_ex3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @POST("api/Users")
    Call<Void> createUser(@Body User user);

    @GET("api/Users/{username}")
    Call<User> getUser(@Path("username") String username);

    @POST("api/Tokens")
    Call<String> getToken(User user);

    @GET("api/Chats")
    Call<List<Chat>> getAllMyChats();

    @POST("api/Chats")
    Call<Void> createChat(Chat chat);

    @GET("api/Chats/{id}")
    Call<Chat> getChatById(@Path("id") int id);

    @DELETE("api/Chats/{id}")
    Call<Void> deleteChatById(@Path("id") int id);

    @POST("api/Chats/{id}/Messages")
    Call<Message> sendMessage(@Path("id") int id, Message message);

    @GET("api/Chats/{id}/Messages")
    Call<List<Message>> getAllMessages(@Path("id") int id);

}
