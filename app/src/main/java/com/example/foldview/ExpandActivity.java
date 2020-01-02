package com.example.foldview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpandActivity extends AppCompatActivity {

    private ExpandAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Expand> expandArrayList;
    public static ExpandableViewHoldersUtil.KeepOneHolder<ExpandViewHolder> keepOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        recyclerView = findViewById(R.id.expand_recycleView);
        ExpandableViewHoldersUtil.getInstance().init().setNeedExplanedOnlyOne(false);
        initView();
    }

    private void initView() {
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpandAdapter(R.layout.item_expand, expandArrayList);
        //清空记录展开还是关闭的缓存数据
        ExpandableViewHoldersUtil.getInstance().resetExpanedList();
        recyclerView.setAdapter(adapter);


        adapter.setOnItemChildItemClickListener(new ExpandAdapter.OnItemChildItemClickListener() {
            @Override
            public void onItemChildItemClick(String data) {
                Toast.makeText(ExpandActivity.this, "" + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        expandArrayList = new ArrayList<>();

        Expand expand = new Expand();
        expand.setItemType(1);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        expand.setNameList(list);
        expandArrayList.add(expand);


        Expand expand1 = new Expand();
        expand1.setItemType(2);
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list1.add("" + i);
        }
        expand1.setNameList(list1);
        expandArrayList.add(expand1);

        Expand expand2 = new Expand();
        expand2.setItemType(2);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list2.add("" + i);
        }
        expand2.setNameList(list2);
        expandArrayList.add(expand2);
    }
}
