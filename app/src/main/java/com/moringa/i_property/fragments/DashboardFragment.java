package com.moringa.i_property.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moringa.i_property.R;
import com.moringa.i_property.adapters.DashboardAdapter;
import com.moringa.i_property.objects.Property;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment {
    @BindView(R.id.dashboard_view) RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private List<Property> properties;


    public DashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this,view);
        properties = new ArrayList<>();
        properties.add(new Property("Kangundo road 50 by 100","50 by 100",0,0,"5 schools, 3 hospitals and close to the tarmac road","Kangundo","Ksh.500,000"));
        properties.add(new Property("Waiyaki 50 by 100","50 by 100",0,0,"5 schools, 3 hospitals and close to the tarmac road","Westlands","Ksh.500,000"));
        properties.add(new Property("Kiambu 50 by 100","50 by 100",0,0,"5 schools, 3 hospitals and close to the tarmac road","Kiambu","Ksh.500,000"));
        properties.add(new Property("Kikuyu 50 by 100","50 by 100",0,0,"5 schools, 3 hospitals and close to the tarmac road","Kikuyu","Ksh.500,000"));
        properties.add(new Property("Nairobi 50 by 100","50 by 100",0,0,"5 schools, 3 hospitals and close to the tarmac road","Nairobi","Ksh.500,000"));
        properties.add(new Property("Westlands 50 by 100","50 by 100",0,0,"5 schools, 3 hospitals and close to the tarmac road","Westlands","Ksh.500,000"));
        adapter = new DashboardAdapter(properties);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}