package com.speedoring.ui.banner_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.utils.BaseFragment;

/**
 * Created by xmuSistone on 2016/9/18.
 */
public class CommonFragment extends BaseFragment /*implements DragLayout.GotoDetailListener*/ {
    private ImageView imageView;
    private View address1, address2, address3, address4, address5;
    private RatingBar ratingBar;
    private View head1, head2, head3, head4;
    private String imageUrl;
    ImageView imageBanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common, null);
        //DragLayout dragLayout = (DragLayout) rootView.findViewById(R.id.drag_layout);
        imageView = rootView.findViewById(R.id.image);
        mContext = getActivity();
        /*address1 = dragLayout.findViewById(R.id.address1);
        address2 = dragLayout.findViewById(R.id.address2);
        address3 = dragLayout.findViewById(R.id.address3);
        address4 = dragLayout.findViewById(R.id.address4);
        address5 = dragLayout.findViewById(R.id.address5);
        ratingBar = (RatingBar) dragLayout.findViewById(R.id.rating);

        head1 = dragLayout.findViewById(R.id.head1);
        head2 = dragLayout.findViewById(R.id.head2);
        head3 = dragLayout.findViewById(R.id.head3);
        head4 = dragLayout.findViewById(R.id.head4);

        dragLayout.setGotoDetailListener(this);*/
        return rootView;
    }

/*
    @Override
    public void gotoDetail() {
        Activity activity = (Activity) getContext();
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                new Pair(imageView, DetailActivity.IMAGE_TRANSITION_NAME),
                new Pair(address1, DetailActivity.ADDRESS1_TRANSITION_NAME),
                new Pair(address2, DetailActivity.ADDRESS2_TRANSITION_NAME),
                new Pair(address3, DetailActivity.ADDRESS3_TRANSITION_NAME),
                new Pair(address4, DetailActivity.ADDRESS4_TRANSITION_NAME),
                new Pair(address5, DetailActivity.ADDRESS5_TRANSITION_NAME),
                new Pair(ratingBar, DetailActivity.RATINGBAR_TRANSITION_NAME),
                new Pair(head1, DetailActivity.HEAD1_TRANSITION_NAME),
                new Pair(head2, DetailActivity.HEAD2_TRANSITION_NAME),
                new Pair(head3, DetailActivity.HEAD3_TRANSITION_NAME),
                new Pair(head4, DetailActivity.HEAD4_TRANSITION_NAME)
        );
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_IMAGE_URL, imageUrl);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
*/

    public void bindData(String imageUrl, Context mContext) {
        this.imageUrl = imageUrl;
        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.ic_default_photo)
                .into(imageView);
    }
}
