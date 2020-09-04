package com.moringa.i_property.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.i_property.R;
import com.moringa.i_property.objects.Property;
import com.moringa.i_property.ui.LocationActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashBoardViewHolder> {

    private List<Property>properties;


    public DashboardAdapter(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public DashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dashboard_property_item,parent,false);
        DashBoardViewHolder viewHolder = new DashBoardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardViewHolder holder, int position) {
        Property property = properties.get(position);
        holder.BindProperty(property);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    class DashBoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.property_title) TextView mTitle;
        @BindView(R.id.property_image) ImageView mImage;
        @BindView(R.id.property_size) TextView mSize;
        @BindView(R.id.property_description) TextView mDescription;
        @BindView(R.id.property_location) TextView mLocation;
        @BindView(R.id.property_price) TextView mPrice;
        @BindView(R.id.location_buttton) Button mLocationButton;
        Context context;
        public DashBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            context =  itemView.getContext();
            ButterKnife.bind(this,itemView);
        }
        private void BindProperty(Property property){
            mTitle.setText(property.getTitle());
            mSize.setText(property.getSize());
            mDescription.setText(property.getDescription());
            mLocation.setText(property.getLocation());
            mPrice.setText(property.getPrice());
            mLocationButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == mLocationButton){
                Intent intent = new Intent(context, LocationActivity.class);
                context.startActivity(intent);
            }

        }
    }
}
