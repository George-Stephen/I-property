package com.moringa.services;

import com.moringa.services.objects.Property;
import com.moringa.services.objects.PropertyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PropertyApi {

    @GET("api/properties/")
    Call<PropertyResponse> getProperties();

    @GET("api/search/")
    Call<List<Property>> searchProperties(@Query("search") String search);

}
