package com.moringa.i_property.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.moringa.i_property.R;
import com.moringa.i_property.adapters.PropertyAdapter;
import com.moringa.i_property.adapters.SearchAdapter;
import com.moringa.services.PropertyApi;
import com.moringa.services.PropertyService;
import com.moringa.services.objects.Property;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    @BindView(R.id.property_view) RecyclerView recyclerView;
    @BindView(R.id.search_text) SearchView mSearchView;
    @BindView(R.id.search_progress) ProgressBar mSearch_progress;
    @BindView(R.id.search_error) TextView mSearchText;

     List<Property> propertyList;
     SearchAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String search = mSearchView.getQuery().toString();
                if (search != null || !search.equals("")){
                    PropertyApi client = PropertyService.getUser();
                    Call<List<Property>> call = client.searchProperties(search);
                    call.enqueue(new Callback<List<Property>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Property>> call, @NonNull Response<List<Property>> response) {
                            if (response.isSuccessful()){
                                propertyList = response.body();
                                adapter = new SearchAdapter(propertyList,getContext());
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            }else{
                                showUnsuccessfulMessage(search);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Property>> call, Throwable t) {
                            showFailureMessage();
                        }
                    });

                    return  true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return  view;
    }

    void showResults(){
        mSearch_progress.setVisibility(View.GONE);
        mSearchView.setVisibility(View.VISIBLE);
    }
    void showFailureMessage(){
        mSearch_progress.setVisibility(View.GONE);
        mSearchText.setText("You don't have an active internet connection,try again after sometime");
        mSearchText.setVisibility(View.VISIBLE);
    }
    void showUnsuccessfulMessage(String search){
        mSearch_progress.setVisibility(View.GONE);
        mSearchText.setText("We don't have lands at " + search +" currently ; try another location ");
        mSearchText.setVisibility(View.VISIBLE);
    }
}