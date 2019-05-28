package com.speedoring.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.modal.user.product_list_home.HomeProductListing;
import com.speedoring.ui.user.activity.UserProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductListPaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeProductListing> productListings;
    private Context context;
    private View.OnClickListener onClickListener;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;

    public ProductListPaginationAdapter(Context context, View.OnClickListener onClickListener) {
        productListings = new ArrayList<>();
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public List<HomeProductListing> getProductList() {
        return productListings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.user_row_product_list_home, viewGroup, false);
                viewHolder = new MyViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, viewGroup, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                final MyViewHolder viewHolder = (MyViewHolder) holder;
                viewHolder.txtProductTitle.setText(productListings.get(position).getListingName());
                viewHolder.txtProductDes.setText(productListings.get(position).getDescription());

                viewHolder.llTop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, UserProductDetailActivity.class);
                        intent.putExtra("product_id", productListings.get(position).getListingId());
                        context.startActivity(intent);
                        //Alerts.show(context, "Under development...!!!");
                    }
                });

                Glide.with(context)
                        .load(productListings.get(position).getProductImages())
                        .placeholder(R.drawable.ic_default_photo).into(viewHolder.imgProduct);
                break;
            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;

                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);

                    loadingVH.mErrorTxt.setText(
                            errorMsg != null ?
                                    errorMsg :
                                    context.getString(R.string.error_msg_unknown));

                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (productListings.size() > 0) {
            return (position == productListings.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        return productListings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgProduct;
        private TextView txtProductTitle, txtProductDes;
        private LinearLayout llTop;

        public MyViewHolder(View view) {
            super(view);
            llTop = view.findViewById(R.id.llTop);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductTitle = view.findViewById(R.id.txtProductTitle);
            txtProductDes = view.findViewById(R.id.txtProductDes);
        }

        @Override
        public void onClick(View v) {

        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:
                    break;
            }
        }
    }

    /*Helper pagination*/
    public void add(HomeProductListing r) {
        productListings.add(r);
        notifyItemInserted(productListings.size() - 1);
    }

    public void addAll(List<HomeProductListing> moveResults) {
        for (HomeProductListing result : moveResults) {
            add(result);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new HomeProductListing());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = productListings.size() - 1;
        HomeProductListing result = getItem(position);

        if (result != null) {
            productListings.remove(position);
            notifyItemRemoved(position);
        }
    }

    public HomeProductListing getItem(int position) {
        return productListings.get(position);
    }

}
