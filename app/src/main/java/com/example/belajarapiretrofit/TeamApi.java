package com.example.belajarapiretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeamApi {
    @GET("search_all_teams.php?l=English%20Premier%20League")
    Call<TeamResponse> getEnglishPremierLeague(@Query("l") String league);

    @GET("/api/v1/json/3/search_all_teams.php?l=Spanish%20La%20Liga")
    Call<TeamResponse> getLaliga(@Query("l") String league);
}
