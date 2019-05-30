package com.speedoring.adapter.multi_image_picker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.speedoring.R;
import com.speedoring.utils.ImageUtil;
import com.speedoring.utils.multi_image_picker.CustomGallery;

import java.util.ArrayList;

/**
 * Created by Kartum Infotech (Bhavesh Hirpara) on 06-Jul-18.
 */
public class ImageListRecyclerAdapter extends RecyclerView.Adapter<ImageListRecyclerAdapter.VerticalItemHolder> {

    private final Context mContext;
    private ArrayList<CustomGallery> mItems = new ArrayList<>();
    private boolean isActionMultiplePick;
    private EventListener mEventListener;

    public ImageListRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isMultiSelected() {
        return isActionMultiplePick;
    }

    public interface EventListener {
        void onItemClickListener(int position, VerticalItemHolder v);
    }

    public ArrayList<CustomGallery> getSelected() {
        ArrayList<CustomGallery> dataT = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).isSeleted) {
                dataT.add(mItems.get(i));
            }
        }
        return dataT;
    }

    public void addAll(ArrayList<CustomGallery> files) {
        try {
            this.mItems.clear();
            this.mItems.addAll(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public void changeSelection(VerticalItemHolder v, int position) {
        mItems.get(position).isSeleted = !mItems.get(position).isSeleted;
        v.imgQueueMultiSelected.setSelected(mItems.get(position).isSeleted);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void setMultiplePick(boolean isMultiplePick) {
        this.isActionMultiplePick = isMultiplePick;
    }

    @Override
    public VerticalItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.gallery_item, container, false);
        return new VerticalItemHolder(root, this);
    }

    @Override
    public void onBindViewHolder(final VerticalItemHolder holder, final int position) {
        CustomGallery item = mItems.get(position);
        holder.setImage(item.sdcardPath);
        if (isActionMultiplePick) {
            holder.imgQueueMultiSelected.setVisibility(View.VISIBLE);
        } else {
            holder.imgQueueMultiSelected.setVisibility(View.GONE);
        }
        if (isActionMultiplePick) {
            holder.imgQueueMultiSelected.setSelected(item.isSeleted);
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEventListener != null) {
                    mEventListener.onItemClickListener(position, holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public CustomGallery getItem(int position) {
        return mItems.get(position);
    }

    public class VerticalItemHolder extends RecyclerView.ViewHolder {

        private ImageView imgQueue;
        private ImageView imgQueueMultiSelected;
        private View container;

        public VerticalItemHolder(View itemView, ImageListRecyclerAdapter adapter) {
            super(itemView);
            imgQueue = itemView.findViewById(R.id.imgQueue);
            imgQueueMultiSelected = itemView.findViewById(R.id.imgQueueMultiSelected);
            container = itemView.findViewById(R.id.container);
        }

        public void setImage(String url) {
            ImageUtil.Glide(mContext, imgQueue, "file://" + url);
        }
    }

    public void setEventListner(EventListener eventListner) {
        mEventListener = eventListner;
    }
}

