package com.moringa.i_property.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringa.i_property.R;
import com.moringa.i_property.adapters.PropertyAdapter;
import com.moringa.services.PropertyApi;
import com.moringa.services.PropertyService;
import com.moringa.services.objects.Property;
import com.moringa.services.objects.PropertyResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    @BindView(R.id.dashboard_view) RecyclerView recyclerView;
    @BindView(R.id.dashboard_progress) ProgressBar mDashboardProgress;
    @BindView(R.id.dashboard_error) TextView mErrorText;

     PropertyAdapter adapter;
     List<Property> propertyList;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        PropertyApi client = PropertyService.getUser();
        Call<PropertyResponse> call = client.getProperties();
        call.enqueue(new Callback<PropertyResponse>() {
            @Override
            public void onResponse(Call<PropertyResponse> call, @NonNull Response<PropertyResponse> response) {
                if (response.isSuccessful()) {
                    propertyList = response.body().getProperties();
                    adapter = new PropertyAdapter(propertyList, getContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    showProperties();
                }
            }

            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {
                showErrorMessage();
            }
        });
        return view;
    }
    private void showProperties(){
        mDashboardProgress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage(){
        mDashboardProgress.setVisibility(View.GONE);
        mErrorText.setText("You don't have an active internet connection,try again later");
        mErrorText.setVisibility(View.GONE);
    }
}