package com.example.xwf.recycleviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int menu1 = 1;
    private static final int menu2 = 2;
    private static final int menu3 = 3;
    private static final int menu4 = 4;
    private static final int menu5 = 5;
    private static final int menu6 = 6;
    private static final int menu7 = 7;
    public RecyclerView mRecyclerView;
    public List<ImageView> mData;
    public RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mRecyclerView = ((RecyclerView) findViewById(R.id.rv));

        //设置数据
        recyclerViewAdapter = new RecyclerViewAdapter(this, mData);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        int[] imagerRes ={R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,};
        ImageView imageView = null;
        mData = new ArrayList<>();
        for (int i = 0; i < imagerRes.length; i++) {
            imageView = new ImageView(this);
            imageView.setImageResource(imagerRes[i]);
            mData.add(imageView);
            for (int j = 0; j < imagerRes.length; j++) {
                mData.add(imageView);
            }
        }

    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        menu.add(0, menu1, 0, "listview_横向");
        menu.add(0, menu2, 0, "GridView_默认");
        menu.add(0, menu3, 0, "GridView_横向");
        menu.add(0, menu4, 0, "瀑布流");
        menu.add(0, menu5, 0, "默认RecyclerView");
        menu.add(0, menu6, 0, "添加一个View");
        menu.add(0, menu7, 0, "移除一个View");
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case menu1:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                break;
            case menu2:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case menu3:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false));
                break;
            case menu4:
                startActivity(new Intent(this,PBLActivity.class));
                break;
            case menu6:
                ImageView iv = (ImageView) findViewById(R.id.iv);
                iv.setImageResource(R.drawable.icon);
                mData.add(mData.size(),iv);
//                recyclerViewAdapter.notifyDataSetChanged();
                recyclerViewAdapter.notifyItemChanged(mData.size());
                break;
            case menu7:
                mData.remove(mData.size()-1);
//                recyclerViewAdapter.notifyDataSetChanged();
                recyclerViewAdapter.notifyItemChanged(mData.size());
                break;
            default:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
        }
        return true;
    }
}
