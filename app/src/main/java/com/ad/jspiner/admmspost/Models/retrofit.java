package com.ad.jspiner.admmspost.Models;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 12kd1004 on 2016. 3. 22..
 */
public interface retrofit {
    @GET("users/tpdbs953")
    Call<List> listRepos(@Path("user") String user);
}
