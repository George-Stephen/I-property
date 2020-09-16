package com.moringa.services;

import com.moringa.services.objects.Property;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PropertyApi {

    @GET("api/properties/")
    Call<List<Property>> getProperties();

    @GET("api/search/")
    Call<List<Property>> searchProperties(@Query("search") String search);

}
