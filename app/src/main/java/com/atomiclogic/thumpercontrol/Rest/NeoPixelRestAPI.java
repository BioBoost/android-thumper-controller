package com.atomiclogic.thumpercontrol.Rest;

/**
 * Created by Michel on 10/23/2017.
 */

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NeoPixelRestAPI {

    @FormUrlEncoded
    @POST("neopixels/strings/0")
    Call<TumperStatus> setColor(
            @Field("red") int red,
            @Field("green") int green,
            @Field("blue") int blue
    );

    @FormUrlEncoded
    @POST("neopixels/effects/strobe/0")
    Call<TumperStatus> setStrobe(
            @Field("red") int red,
            @Field("green") int green,
            @Field("blue") int blue,
            @Field("delay") int delay
    );
}
