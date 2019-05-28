package com.speedoring.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.modal.user.service_category.ServicesCategory;
import com.speedoring.ui.user.activity.UserServicesActivity;

import java.util.List;

public class ServiceCategoryAdapter extends RecyclerView.Adapter<ServiceCategoryAdapter.MyViewHolder> {

    private List<ServicesCategory> servicesCategoryList;
    private Context context;
    private View.OnClickListener onClickListener;

    public ServiceCategoryAdapter(List<ServicesCategory> servicesCategoryList, Context context, View.OnClickListener onClickListener) {
        this.servicesCategoryList = servicesCategoryList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_service_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtServiceCategory.setText(servicesCategoryList.get(position).getServiceCateName());
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserServicesActivity.class);
                intent.putExtra("category_name", servicesCategoryList.get(position).getServiceCateName());
                intent.putExtra("category_id", servicesCategoryList.get(position).getServiceCatId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesCategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private TextView txtServiceCategory;

        public MyViewHolder(View view) {
            super(view);
            txtServiceCategory = view.findViewById(R.id.txtServiceCategory);
            cardView = view.findViewById(R.id.cardViewPopular);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
