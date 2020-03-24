package com.atomiclogic.thumpercontrol;

import android.util.Log;
import android.widget.TextView;

import com.atomiclogic.thumpercontrol.Rest.NeoPixelRestAPI;
import com.atomiclogic.thumpercontrol.Rest.TumperStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TumperLightService {


    private String url;
    private Retrofit retrofit;
    private NeoPixelRestAPI api;

    public TumperLightService(String url) {
        this.url = url;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(NeoPixelRestAPI.class);
    }



    public void setColor(int red, int green, int blue) {

        // Create a call instance for the request
        Call<TumperStatus> call = api.setColor(red,green,blue);

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

    public void setStrobe(int red, int green, int blue, int delay) {

        // Create a call instance for the request
        Call<TumperStatus> call = api.setStrobe(red,green,blue,delay);

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
