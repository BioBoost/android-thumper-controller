package com.atomiclogic.thumpercontrol;

import com.atomiclogic.thumpercontrol.Rest.DriveRestAPI;

import retrofit2.Retrofit;

import android.util.Log;

import com.atomiclogic.thumpercontrol.Rest.TumperStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class TumperDriveService {


    private String url;
    private Retrofit retrofit;
    private DriveRestAPI api;

    public TumperDriveService(String url) {
        this.url = url;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(DriveRestAPI.class);
    }

    public void setDrive(int right, int left) {

        // Create a call instance for the request
        Call<TumperStatus> call = api.setDrive(right, left);

        //Make the asychronous call
        call.enqueue(new Callback<TumperStatus>() {
            @Override
            public void onResponse(Call<TumperStatus> call, Response<TumperStatus> response) {
                // This executes on GUI thread
                if (response.body() != null) {
                    Log.i("Status:",response.body().getStatus());
                }else{
                    Log.e("REST", "Response has no body");
                }
            }

            @Override
            public void onFailure(Call<TumperStatus> call, Throwable t) {
                Log.e("REST", "Could not make request: ");
            }
        });
    };
}
