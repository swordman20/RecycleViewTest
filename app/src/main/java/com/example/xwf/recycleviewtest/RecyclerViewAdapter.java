package com.example.xwf.recycleviewtest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Hsia on 16/6/11.
 * E-mail: xiaweifeng@live.cn
 * //TODO:RecyclerViewAdapter
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHodler> {
    private List<ImageView> mData;
    private Context mContext;

    public RecyclerViewAdapter(Context context,List<ImageView> mData) {
        super();
        this.mData = mData;
        this.mContext = context;
    }

    @Override
    public RecyclerViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.recyclerview_item, null);
        RecyclerViewHodler viewHodler = new RecyclerViewHodler(view);
        return viewHodler;
    }

    /**
     * 绑定数据使用
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerViewHodler holder, final int position) {
        ImageView imageView = mData.get(position);
        Drawable drawable = imageView.getDrawable();
        holder.iv.setImageDrawable(drawable);

        /**
         * 点击事件的处理
         */
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "哈哈"+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class RecyclerViewHodler extends RecyclerView.ViewHolder{

        public final ImageView iv;

        public RecyclerViewHodler(View itemView) {
            super(itemView);
            iv = ((ImageView) itemView.findViewById(R.id.iv));
        }
    }
}
