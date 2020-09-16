package com.moringa.services;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PropertyService {
    private static Retrofit retrofit;
    public static  PropertyApi getUser(){
        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .build();
                            return chain.proceed(request);
                        }
                    }).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.PROPERTY_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit.create(PropertyApi.class);
    }

}
