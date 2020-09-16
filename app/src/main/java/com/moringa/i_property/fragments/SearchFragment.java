package com.moringa.i_property.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.moringa.i_property.R;
import com.moringa.i_property.adapters.PropertyAdapter;
import com.moringa.i_property.adapters.SearchAdapter;
import com.moringa.services.objects.Property;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment {

    @BindView(R.id.property_view) RecyclerView recyclerView;
    @BindView(R.id.search_text) SearchView mSearchView;
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
        return  view;
    }
}