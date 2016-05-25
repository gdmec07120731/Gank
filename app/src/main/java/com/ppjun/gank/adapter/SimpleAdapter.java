package com.ppjun.gank.adapter;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppjun.gank.R;
import com.ppjun.gank.bean.BaseGankData;
import com.ppjun.gank.bean.GankDaily;
import com.ppjun.gank.gank.GankApi;
import com.ppjun.gank.utils.GlideUtils;



/**
 * @Package :com.ppjun.gank.adapter
 * @Description :
 * @Author :Rc3
 * @Created at :2016/4/19 10:42.
 */
public class SimpleAdapter extends GankRecyclerViewAdapter {


    public static final int LAYOUT_TYPE_DAILY = 0;
    public static final int LAYOUT_TYPE_WELFARE = 1;
    public static final int LAYOUT_TYPE_TECHNOLOGY = 2;


    public Activity mContext;
    public int LAYOUT_TYPE = 0;


    public SimpleAdapter( Activity context, String type) {


        this.mContext = context;

        if(GankApi.DATA_TYPE_DAILY==type){
            this.LAYOUT_TYPE = LAYOUT_TYPE_DAILY;
        }else if( GankApi.DATA_TYPE_WELFARE==type){
            this.LAYOUT_TYPE = LAYOUT_TYPE_WELFARE;
        }else{
            this.LAYOUT_TYPE = LAYOUT_TYPE_TECHNOLOGY;

        }

    }


    @Override
    public void onBindRecyclerViewHolder(GankRecyclerViewHolder viewHolder, int position) {
        //int layoutType = this.getRecyclerViewItemType(position);
        switch (LAYOUT_TYPE) {
            case LAYOUT_TYPE_DAILY:
                this.loadingDaily(viewHolder, position);
                break;
            case LAYOUT_TYPE_WELFARE:
                this.loadingWelfare(viewHolder ,position);
            case LAYOUT_TYPE_TECHNOLOGY:
                this.loadingTech(viewHolder,position);
        }
    }

    public void loadingDaily(GankRecyclerViewHolder viewHolder, int position){
        GankDaily dailyDaily = this.getItem(position);
        if (dailyDaily == null) return;
        TextView mTextView=viewHolder.findViewById(R.id.title_tv);
        ImageView mImageView=viewHolder.findViewById(R.id.icon_image);
        if (dailyDaily.results.videoData != null && dailyDaily.results.videoData.size() > 0)
            mTextView.setText(dailyDaily.results.videoData.get(0).desc);
        if (dailyDaily.results.welfareData != null && dailyDaily.results.welfareData.size() > 0)
          GlideUtils.display(mImageView, dailyDaily.results.welfareData.get(0).url);


    }
    public void loadingWelfare(GankRecyclerViewHolder viewHolder, int position){
        BaseGankData baseGankData=this.getItem(position);
        ImageView mImageView=viewHolder.findViewById(R.id.welfare_iamge);
        if(baseGankData!=null)
        GlideUtils.display(mImageView,baseGankData.url);


    }
    public void loadingTech(GankRecyclerViewHolder viewHolder, int position){
        BaseGankData baseGankData=this.getItem(position);
        TextView mDataTitle=viewHolder.findViewById(R.id.title_data_tv);
        TextView mDataVia=viewHolder.findViewById(R.id.author_data_tv);
        TextView mDataSource=viewHolder.findViewById(R.id.source_data_tv);
        TextView mDataDate=viewHolder.findViewById(R.id.date_data_tv);
        if(baseGankData!=null) {
            mDataTitle.setText(baseGankData.desc);
            mDataSource.setText("");
            mDataDate.setText(baseGankData.publishedAt.toString());
            mDataVia.setText("by:"+baseGankData.who);
        }



    }


    @Override
    public int getRecyclerViewItemType(int position) {
        switch (this.LAYOUT_TYPE) {

            case 0:
                return LAYOUT_TYPE_DAILY;

            case 1:

                return LAYOUT_TYPE_WELFARE;

            case 2:
                return LAYOUT_TYPE_TECHNOLOGY;

        }
        return LAYOUT_TYPE_DAILY;
    }






    @Override
    public int[] getItemLayouts() {
        return new int[]{

                R.layout.item_daily,
                R.layout.item_welfare,
                R.layout.item_data
        };
    }


}
