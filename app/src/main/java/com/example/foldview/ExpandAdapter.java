package com.example.foldview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ExpandAdapter extends BaseQuickAdapter<Expand, ExpandViewHolder> {
    private BottomAdapter bottomAdapter;
    private int itemType;
    private OnItemChildItemClickListener listener;

    public ExpandAdapter(int layoutResId, @Nullable List<Expand> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull final ExpandViewHolder helper, Expand item) {
        ExpandActivity.keepOne.bind(helper, helper.getLayoutPosition());
        helper.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpandActivity.keepOne.toggle(helper);
//                ExpandableViewHoldersUtil.getInstance().setNeedExplanedOnlyOne(true); //点击关闭前一个
            }
        });
        RecyclerView recyclerView = helper.recyclerView;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
//        // 1:列表  2:宫格

        if (item.getItemType() == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            bottomAdapter = new BottomAdapter(R.layout.item_rightmenu, item.getNameList());
        } else if (item.getItemType() == 2) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            bottomAdapter = new BottomAdapter(R.layout.item_img, item.getNameList());
        }

        recyclerView.setAdapter(bottomAdapter);
        bottomAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (listener != null) {
                    listener.onItemChildItemClick(position + "");
                }
            }
        });
    }

    public class BottomAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public BottomAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, String item) {
            TextView textView = helper.getView(R.id.rightmenuItem_txt_title);
            ImageView imageView = helper.getView(R.id.item_img);

            if (textView != null) {
                helper.setText(R.id.rightmenuItem_txt_title, helper.getLayoutPosition() + "").addOnClickListener(R.id.rightmenuItem_txt_title);
            }
            if (imageView != null) {
                helper.addOnClickListener(R.id.item_img);
            }
        }
    }

    //监听
    public void setOnItemChildItemClickListener(OnItemChildItemClickListener listener) {
        this.listener = listener;
    }


    //接口
    public interface OnItemChildItemClickListener {
        //列表的子列表的点击事件
        void onItemChildItemClick(String data);
    }
}
