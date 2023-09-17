package com.example.lab2_20191792.Services;

import com.example.lab2_20191792.dto.Person;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/api")
    Call<Person> getPerson();
}
