package com.moringa.i_property.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.moringa.i_property.R;
import com.moringa.i_property.adapters.PropertyAdapter;
import com.moringa.i_property.objects.Property;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment {

    @BindView(R.id.propery_view) RecyclerView recyclerView;
    private PropertyAdapter adapter;
    private List<Property> propertyList;

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
        propertyList = new ArrayList<>();
        propertyList.add(new Property("Airview","50 by 100",0,0,"near the tarmac road,5 schools and 3 Hospitals","Nairobi","Ksh.500,000"));
        propertyList.add(new Property("Airview","50 by 100",0,0,"near the tarmac road,5 schools and 3 Hospitals","Nairobi","Ksh.500,000"));
        propertyList.add(new Property("Airview","50 by 100",0,0,"near the tarmac road,5 schools and 3 Hospitals","Nairobi","Ksh.500,000"));
        propertyList.add(new Property("Airview","50 by 100",0,0,"near the tarmac road,5 schools and 3 Hospitals","Nairobi","Ksh.500,000"));
        propertyList.add(new Property("Airview","50 by 100",0,0,"near the tarmac road,5 schools and 3 Hospitals","Nairobi","Ksh.500,000"));
        propertyList.add(new Property("Airview","50 by 100",0,0,"near the tarmac road,5 schools and 3 Hospitals","Nairobi","Ksh.500,000"));
        propertyList.add(new Property("Airview","50 by 100",0,0,"near the tarmac road,5 schools and 3 Hospitals","Nairobi","Ksh.500,000"));
        adapter = new PropertyAdapter(propertyList,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        return  view;
    }
}