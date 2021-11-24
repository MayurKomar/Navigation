package com.example.navigationcomponents;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Apis {
    @Headers("x-api-key: FT@V2sb2#dYPa$zN")
    @GET("auctions?auction=past")
    Call<AuctionsResponse> getAuctions(@Query("page")int page, @Query("per_page")int perPage);
}
