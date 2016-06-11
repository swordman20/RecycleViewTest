package com.example.xwf.recycleviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hsia on 16/6/11.
 * E-mail: xiaweifeng@live.cn
 * //TODO:文件描述
 */
public class PBLActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    public List<ImageView> mData;
    public List<Integer> mHight;
    public MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mRecyclerView = ((RecyclerView) findViewById(R.id.rv));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
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
        /**
         * 生成一个随机值
         */
        mHight = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            mHight.add((int) (200+Math.random()*300));
        }

    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHodle>{
        @Override
        public MyViewHodle onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = View.inflate(getApplicationContext(), R.layout.recyclerview_item, null);
            MyViewHodle myViewHodle = new MyViewHodle(inflate);

            return myViewHodle;
        }

        @Override
        public void onBindViewHolder(MyViewHodle holder, final int position){
            /**
             * 设置item的边距
             */
            ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
            layoutParams.height = mHight.get(position);
            holder.iv.setLayoutParams(layoutParams);

            /**
             * 点击事件
             */
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView iv = (ImageView) findViewById(R.id.iv);
                    iv.setImageResource(R.drawable.icon);
                    mData.add(mData.size(),iv);
                    myRecyclerViewAdapter.notifyItemChanged(mData.size());
                    Toast.makeText(PBLActivity.this, "添加一个view"+position, Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 长按事件
             */
            holder.iv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    mData.remove(mData.size()-1);
                    myRecyclerViewAdapter.notifyItemChanged(mData.size());

                    Toast.makeText(PBLActivity.this, "删除一个View"+position, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyViewHodle extends RecyclerView.ViewHolder{

            ImageView iv;

            public MyViewHodle(View itemView) {
                super(itemView);
                iv = ((ImageView) itemView.findViewById(R.id.iv));
            }
        }
    }
}
