package com.example.ap2_ex3;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatsAPI {


    private MutableLiveData<List<Chat>> chatsListData;
    private UserDao userDao;

    private Retrofit retrofit;

    private WebServiceAPI webServiceAPI;

    public ChatsAPI(MutableLiveData<List<Chat>> chatsListData, UserDao userDao) {
        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);

        this.userDao = userDao;
        this.chatsListData = chatsListData;
    }


    public void getChatsListByName(String myName) {
        Call <List<Chat>> call = webServiceAPI.getAllMyChats();
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {

//                new Thread(() -> {

                    User me = userDao.get(myName);
                    me.setChatList(response.body());
                    userDao.update(me);
                    chatsListData.setValue(me.getChatList());

//                }).start();
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t){}


        });


    }
}
