package com.moringa.i_property.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moringa.i_property.R;
import com.moringa.i_property.ui.MapsActivity;
import com.moringa.services.objects.Property;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>  {

     List<Property> properties;
      Context context;

    public PropertyAdapter(List<Property> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.property_item,parent,false);
        PropertyViewHolder viewHolder = new PropertyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
      Property property = properties.get(position);
      holder.bindProperty(property);

    }

    @Override
    public int getItemCount() {

        return properties.size();
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.property_name) TextView mPropertyName;
            @BindView(R.id.property_image) ImageView mPropertyImage;
            @BindView(R.id.property_location) TextView mPropertyLocation;
            @BindView(R.id.property_price) TextView mPropertyPrice;
            @BindView(R.id.property_description) TextView mPropertyDescription;
            @BindView(R.id.action_button) FloatingActionButton mActionButton;
            Context context;
        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this,itemView);
        }

        private void bindProperty(final Property property){
            Picasso.get().load("https://salemrest.herokuapp.com" + property.getImage()).into(mPropertyImage);
            mPropertyName.setText(property.getName());
            mPropertyLocation.setText(property.getLocation());
            mPropertyDescription.setText(property.getDescription());
            mPropertyPrice.setText(property.getPrice());
            mActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,MapsActivity.class);
                    intent.putExtra("location",property.getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
