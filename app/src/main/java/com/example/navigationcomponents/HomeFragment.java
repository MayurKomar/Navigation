package com.example.navigationcomponents;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationcomponents.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment {

    FragmentHomeBinding homeBinding;
    HomeFragmentViewModel homeFragmentViewModel;
    int page = 1, perPageRecords = 10;
    AuctionListAdapter auctionListAdapter;
    public static final String TAG = "HomeFrag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        homeFragmentViewModel = new ViewModelProvider(getActivity()).get(HomeFragmentViewModel.class);
        homeFragmentViewModel.getAuctions(page, perPageRecords).observe(getActivity(), new Observer<List<Auction>>() {
            @Override
            public void onChanged(List<Auction> auctions) {
                initRecyclerView(page, perPageRecords);
            }
        });
        initRecyclerView(page, perPageRecords);
        return homeBinding.getRoot();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored: restore");
        if(savedInstanceState != null){
            int savedState = savedInstanceState.getInt("Recycler");
            homeBinding.auctionsRecycler.setScrollY(savedState);
        }
    }

    private void initRecyclerView(int page, int perPageRecords) {
        auctionListAdapter = new AuctionListAdapter(getContext(), homeFragmentViewModel.getAuctions(page, perPageRecords).getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        homeBinding.auctionsRecycler.setLayoutManager(layoutManager);
        homeBinding.auctionsRecycler.setAdapter(auctionListAdapter);
    }
}