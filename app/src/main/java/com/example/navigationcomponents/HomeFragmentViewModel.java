package com.example.navigationcomponents;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Auction>> auctions;
    public static final String BASE_URL = "https://ft.webwingz.com.au/api-v2/";
    public static final String TAG = "HOMEFRAGMENTVIEWMODEL";
    public LiveData<List<Auction>> getAuctions (int page,int perPage) {
        if(auctions == null){
            auctions = new MutableLiveData<List<Auction>>();
            loadAuctions(page,perPage);
        }
        return auctions;
    }

    private void loadAuctions (int page, int perPage) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Apis apis = retrofit.create(Apis.class);
        Call<AuctionsResponse> call = apis.getAuctions(page,perPage);
        call.enqueue(new Callback<AuctionsResponse>() {
            @Override
            public void onResponse(Call<AuctionsResponse> call, Response<AuctionsResponse> response) {
                if(page<response.body().getAuctionsData().getPagingData().get(0).getTotalpages()){
                    auctions.postValue(response.body().getAuctionsData().getAuctions());
                    Log.d(TAG, "onResponse: "+response.body().getAuctionsData().getAuctions());
                }
            }

            @Override
            public void onFailure(Call<AuctionsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed");
            }
        });
    }
}
