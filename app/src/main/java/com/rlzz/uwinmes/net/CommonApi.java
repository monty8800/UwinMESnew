package com.rlzz.uwinmes.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by monty on 2017/8/11.
 */

public interface CommonApi {
    @GET("{path}")
    Call<ResponseModel> get(@Path("path") String path, @QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("{path}")
    Call<ResponseModel> post(@Path("path") String path, @FieldMap Map<String, String> params);
}
