package com.speedoring.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.modal.user.product_category.ProductCategoryList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.MyViewHolder> {

    private List<ProductCategoryList> categoryLists;
    private Context context;
    private View.OnClickListener onClickListener;
    private int vwType;

    public ProductCategoryAdapter(List<ProductCategoryList> categoryLists, Context context, View.OnClickListener onClickListener, int viewType) {
        this.categoryLists = categoryLists;
        this.context = context;
        this.onClickListener = onClickListener;
        this.vwType = viewType;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (vwType == 1) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_product_category, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_product_category_b, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtProductCategory.setText(categoryLists.get(position).getCatName());
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(onClickListener);

        Glide.with(context)
                .load(categoryLists.get(position).getCatImage())
                .placeholder(R.drawable.ic_default_photo)
                .into(holder.imgProductCategory);
    }

    @Override
    public int getItemCount() {
        if (vwType == 1 || vwType == 2) {
            return categoryLists.size();
        } else {
            if (categoryLists.size() > 8) {
                return 8;
            } else {
                return categoryLists.size();
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout cardView;
        private TextView txtProductCategory;
        private CircleImageView imgProductCategory;

        public MyViewHolder(View view) {
            super(view);
            txtProductCategory = view.findViewById(R.id.txtProductCategory);
            cardView = view.findViewById(R.id.cardViewPopular);
            imgProductCategory = view.findViewById(R.id.imgProductCategory);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
