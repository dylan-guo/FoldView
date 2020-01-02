package com.example.foldview;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

public class ExpandViewHolder extends BaseViewHolder implements ExpandableViewHoldersUtil.Expandable {

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ImageView imageView;

    public ExpandViewHolder(View view) {
        super(view);

        recyclerView = itemView.findViewById(R.id.expandItem_recycleView);
        relativeLayout = itemView.findViewById(R.id.expandItem_relative);
        imageView = itemView.findViewById(R.id.expandItem_img);
        ExpandActivity.keepOne = ExpandableViewHoldersUtil.getInstance().getKeepOneHolder();

        recyclerView.setVisibility(View.GONE);
        recyclerView.setAlpha(0);
    }


    @Override
    public View getExpandView() {
        return recyclerView;
    }

    @Override
    public void doCustomAnim(boolean isOpen) {
        if (isOpen) {
            ExpandableViewHoldersUtil.getInstance().rotateExpandIcon(imageView, 90, 0);
        } else {
            ExpandableViewHoldersUtil.getInstance().rotateExpandIcon(imageView, 0, 90);
        }
    }
}
