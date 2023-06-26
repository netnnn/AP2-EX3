//package com.example.ap2_ex3;
//
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class UserAPI {
//
////    private MutableLiveData<List<User>> postListData;
//    private UserDao dao;
//
//    private Retrofit retrofit;
//
//    private WebServiceAPI webServiceAPI;
//
//    public UserAPI(MutableLiveData<List<User>> postListData, UserDao dao) {
//        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        webServiceAPI = retrofit.create(WebServiceAPI.class);
//
//        this.dao = dao;
////        this.postListData = postListData;
//    }
//
//
//    public void getUserByName(String name) {
//        Call<User> call = webServiceAPI.getUser(name);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//                new Thread(() -> {
//                    dao.clear();
//                    dao.insertList(response.body());
////                    postListData.postValue(dao.get(name));
//                }).start();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t)
//
//
//        });
//
//
//    }
//}
