package com.example.navigationcomponents;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AuctionsData {
    @SerializedName("paging")
    private ArrayList<com.example.navigationcomponents.PagingData> pagingData;
    @SerializedName("auctions")
    private ArrayList<com.example.navigationcomponents.Auction> auctions;

    public ArrayList<com.example.navigationcomponents.PagingData> getPagingData() {
        return pagingData;
    }

    public ArrayList<com.example.navigationcomponents.Auction> getAuctions() {
        return auctions;
    }
}
