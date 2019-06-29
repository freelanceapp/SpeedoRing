package com.speedoring.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.modal.user.product_list_home.HomeProductListing;
import com.speedoring.ui.user.activity.UserProductDetailActivity;

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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtProductTitle.setText(reviewModelList.get(position).getListingName());

        String contact = reviewModelList.get(position).getCendorMobileNumber();
        if (!reviewModelList.get(position).getCendorMobileNumber().isEmpty()) {
            contact = reviewModelList.get(position).getCendorMobileNumber();
        } else if (!reviewModelList.get(position).getVendorMobileOne().isEmpty()) {
            contact = reviewModelList.get(position).getCendorMobileNumber();
        } else if (!reviewModelList.get(position).getVendorMobileTwo().isEmpty()) {
            contact = reviewModelList.get(position).getCendorMobileNumber();
        } else if (!reviewModelList.get(position).getVendorLandlineOne().isEmpty()) {
            contact = reviewModelList.get(position).getCendorMobileNumber();
        } else if (!reviewModelList.get(position).getVendorLandlineTwo().isEmpty()) {
            contact = reviewModelList.get(position).getCendorMobileNumber();
        }

        holder.txtProductDes.setText(contact);

        holder.imgCall.setTag(position);
        holder.imgCall.setOnClickListener(onClickListener);

        holder.imgAddress.setTag(position);
        holder.imgAddress.setOnClickListener(onClickListener);

        holder.llTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserProductDetailActivity.class);
                intent.putExtra("product_id", reviewModelList.get(position).getListingId());
                intent.putExtra("from", "user");
                context.startActivity(intent);
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

        public ImageView imgProduct, imgCall, imgAddress;
        private TextView txtProductTitle, txtProductDes;
        private LinearLayout llTop;

        public MyViewHolder(View view) {
            super(view);
            imgCall = view.findViewById(R.id.imgCall);
            imgAddress = view.findViewById(R.id.imgAddress);
            llTop = view.findViewById(R.id.llTop);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductTitle = view.findViewById(R.id.txtProductTitle);
            txtProductDes = view.findViewById(R.id.txtProductDes);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
