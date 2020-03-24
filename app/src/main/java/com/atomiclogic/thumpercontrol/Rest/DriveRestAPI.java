package com.atomiclogic.thumpercontrol.Rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Michel on 11/17/2017.
 */

public interface DriveRestAPI {

    @FormUrlEncoded
    @POST("/speed")
    Call<TumperStatus> setDrive(
            @Field("right_speed") int left,
            @Field("left_speed") int right
    );
}

