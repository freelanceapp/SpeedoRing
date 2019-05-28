package com.speedoring.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.modal.user.service_list.ServiceList;
import com.speedoring.ui.user.activity.UserServiceDetailActivity;

import java.util.List;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyViewHolder> {

    private List<ServiceList> serviceLists;
    private Context context;
    private View.OnClickListener onClickListener;

    public ServiceListAdapter(List<ServiceList> serviceLists, Context context, View.OnClickListener onClickListener) {
        this.serviceLists = serviceLists;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_service_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtServiceDes.setText(serviceLists.get(position).getServiceDescription());
        holder.txtServiceTitle.setText(serviceLists.get(position).getServiceTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserServiceDetailActivity.class);
                intent.putExtra("id", serviceLists.get(position).getServiceTitle());
                intent.putExtra("title", serviceLists.get(position).getServiceTitle());
                intent.putExtra("image", serviceLists.get(position).getServiceImage());
                intent.putExtra("description", serviceLists.get(position).getServiceDescription());
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .load(serviceLists.get(position).getServiceImage())
                .placeholder(R.drawable.ic_default_photo).into(holder.imgService);
    }

    @Override
    public int getItemCount() {
        return serviceLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgService;
        private TextView txtServiceTitle, txtServiceDes;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardView);
            imgService = view.findViewById(R.id.imgService);
            txtServiceTitle = view.findViewById(R.id.txtServiceTitle);
            txtServiceDes = view.findViewById(R.id.txtServiceDes);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
