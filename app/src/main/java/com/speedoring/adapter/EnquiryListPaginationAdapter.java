package com.speedoring.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.modal.vendor.enquiry_list.VendorEnquiryList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EnquiryListPaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VendorEnquiryList> productListings;
    private Context context;
    private View.OnClickListener onClickListener;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;

    public EnquiryListPaginationAdapter(Context context, View.OnClickListener onClickListener) {
        productListings = new ArrayList<>();
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public List<VendorEnquiryList> getProductList() {
        return productListings;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.vendor_row_enquiry_list, viewGroup, false);
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
                viewHolder.txtName.setText(productListings.get(position).getName());
                viewHolder.txtPhoneNo.setText(productListings.get(position).getMobileNo());
                viewHolder.txtEmailId.setText(productListings.get(position).getEmailId());
                viewHolder.txtProductName.setText(productListings.get(position).getListingName());
                viewHolder.txtComment.setText(productListings.get(position).getEnquiryAbout());
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

        private TextView txtName, txtPhoneNo, txtEmailId, txtProductName, txtComment;
        private LinearLayout llTop;

        public MyViewHolder(View view) {
            super(view);
            llTop = view.findViewById(R.id.llTop);
            txtName = view.findViewById(R.id.txtName);
            txtPhoneNo = view.findViewById(R.id.txtPhoneNo);
            txtEmailId = view.findViewById(R.id.txtEmailId);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtComment = view.findViewById(R.id.txtComment);
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
    public void add(VendorEnquiryList r) {
        productListings.add(r);
        notifyItemInserted(productListings.size() - 1);
    }

    public void addAll(List<VendorEnquiryList> moveResults) {
        for (VendorEnquiryList result : moveResults) {
            add(result);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new VendorEnquiryList());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = productListings.size() - 1;
        VendorEnquiryList result = getItem(position);

        if (result != null) {
            productListings.remove(position);
            notifyItemRemoved(position);
        }
    }

    public VendorEnquiryList getItem(int position) {
        return productListings.get(position);
    }

}
