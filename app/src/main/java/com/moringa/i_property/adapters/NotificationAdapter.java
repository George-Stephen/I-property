package com.moringa.i_property.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.i_property.R;
import com.moringa.i_property.objects.Property;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>  {

    private List<Property>properties;

    public NotificationAdapter(List<Property> properties) {
        this.properties = properties;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notifications_item,parent,false);
        NotificationViewHolder viewHolder = new NotificationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Property property = properties.get(position);
        holder.bindNotifications(property);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.notification_text) TextView mNotificationText;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @SuppressLint("SetTextI18n")
        void bindNotifications(Property property){
            mNotificationText.setText("Hotstep Holdings has added new land at "+ property.getLocation());
        }
    }
}
