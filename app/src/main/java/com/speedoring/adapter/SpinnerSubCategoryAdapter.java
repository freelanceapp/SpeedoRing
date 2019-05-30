package com.speedoring.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.modal.user.product_sub_category.ProductSubCategory;

import java.util.List;

public class SpinnerSubCategoryAdapter extends ArrayAdapter<ProductSubCategory> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<ProductSubCategory> items;
    private final int mResource;

    public SpinnerSubCategoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ProductSubCategory> objects) {
        super(context, 0, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);

        TextView txtMchId = view.findViewById(R.id.tvCategory);

        ProductSubCategory urlModal = items.get(position);
        txtMchId.setText(urlModal.getSubCatName());
        return view;
    }
}