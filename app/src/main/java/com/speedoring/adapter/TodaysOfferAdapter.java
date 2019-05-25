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
import com.speedoring.modal.coupon_model.CouponDatum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodaysOfferAdapter extends RecyclerView.Adapter<TodaysOfferAdapter.MyViewHolder> {

    private List<CouponDatum> reviewModelList;
    private Context context;
    private View.OnClickListener onClickListener;
    String str = null;

    public TodaysOfferAdapter(List<CouponDatum> reviewModelList, Context context, View.OnClickListener onClickListener) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todays_offers, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDescription.setText(reviewModelList.get(position).getCouponName());

        String strDate = reviewModelList.get(position).getEndDate();
        parseDateToddMMyyyy(strDate);
        holder.tvDateTime.setText(str);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.startActivity(new Intent(context, UserServicesActivity.class));
            }
        });

        Glide.with(context)
                .load("https://www.grabbuddy.in/admin/images/company_picture/" + reviewModelList.get(position).getCompany_logo())
                .placeholder(R.drawable.ic_default_photo).into(holder.offer_img);
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img, imgExclusive;
        private TextView tvDescription, tvDateTime;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardView);
            imgExclusive = view.findViewById(R.id.imgExclusive);
            offer_img = view.findViewById(R.id.img);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvDateTime = view.findViewById(R.id.tvDateTime);

        }

        @Override
        public void onClick(View v) {

        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;


        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
