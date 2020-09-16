package com.moringa.i_property.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.i_property.R;
import com.moringa.services.Constants;
import com.moringa.services.objects.Property;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter  extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>  {
    List<Property> properties;
    Context context;

    public SearchAdapter(List<Property> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_result_item,parent,false);
        SearchViewHolder viewHolder = new SearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Property property = properties.get(position);
        holder.bindSearch(property);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.search_name) TextView mSearchName;
        @BindView(R.id.search_image) ImageView mSearchImage;
        @BindView(R.id.search_location) TextView mSearchLocation;
        @BindView(R.id.search_price) TextView mSearchPrice;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        void bindSearch(Property property){
            Picasso.get().load("https://salemrest.herokuapp.com" + property.getImage()).into(mSearchImage);
            mSearchName.setText(property.getName());
            mSearchLocation.setText(property.getLocation());
            mSearchPrice.setText(property.getPrice());
        }
    }
}
