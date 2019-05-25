package com.speedoring.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.modal.product_list_home.HomeProductListing;
import com.speedoring.utils.Alerts;

import java.util.List;

public class HomeProductListAdapter extends RecyclerView.Adapter<HomeProductListAdapter.MyViewHolder> {

    private List<HomeProductListing> reviewModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public HomeProductListAdapter(List<HomeProductListing> reviewModelList, Context context, View.OnClickListener onClickListener) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_product_list_home, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtProductTitle.setText(reviewModelList.get(position).getListingName());
        holder.txtProductDes.setText(reviewModelList.get(position).getDescription());

        holder.cardViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alerts.show(context, "Under development...!!!");
            }
        });

        Glide.with(context)
                .load(reviewModelList.get(position).getProductImages())
                .placeholder(R.drawable.ic_default_photo).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgProduct;
        private TextView txtProductTitle, txtProductDes;
        private CardView cardViewProduct;

        public MyViewHolder(View view) {
            super(view);
            cardViewProduct = view.findViewById(R.id.cardViewProduct);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductTitle = view.findViewById(R.id.txtProductTitle);
            txtProductDes = view.findViewById(R.id.txtProductDes);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
