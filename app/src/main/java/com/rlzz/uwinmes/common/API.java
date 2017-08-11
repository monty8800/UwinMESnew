package com.rlzz.uwinmes.common;


import com.rlzz.uwinmes.net.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by monty on 2017/8/10.
 */

public interface API {
    /**
     * history/content/day/2016/05/11
     * @param date
     * @return
     */
    @GET("history/content/day/{date}")
//    Call<String> getContentFromDay(@Path("date") String date);
    Call<ResponseModel<String>> getContentFromDay(@Path("date") String date);
}
