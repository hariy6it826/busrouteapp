package com.example.busroute.data.remote;

import com.example.busroute.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dasari on 05/11/17.
 */

public class RetrofitManager {

    private static Retrofit sRetrofit;

    private RetrofitManager() {
    }

    public static synchronized Retrofit getRetrofitInstance() {

        if (sRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.END_POINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

}
