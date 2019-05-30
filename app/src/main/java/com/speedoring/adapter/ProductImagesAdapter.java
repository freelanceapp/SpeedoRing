package com.speedoring.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.modal.user.product_detail.ProductImage;

import java.util.List;

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ViewHolder> {

    private List<ProductImage> productImages;
    private Context context;
    private View.OnClickListener onClickListener;

    public ProductImagesAdapter(List<ProductImage> productImages, Context context, View.OnClickListener onClickListener) {
        this.productImages = productImages;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(context);
        View viewt = li.inflate(R.layout.user_row_product_images, null);
        return new ViewHolder(viewt);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.cardViewProduct.setTag(i);
        viewHolder.cardViewProduct.setOnClickListener(onClickListener);
        Glide.with(context)
                .load(productImages.get(i).getProdcutImage())
                .placeholder(R.drawable.ic_default_photo_b)
                .into(viewHolder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return productImages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private RelativeLayout cardViewProduct;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewProduct = itemView.findViewById(R.id.cardViewProduct);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
