package com.cf.hodgepodge.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.caofu.hodgepodge.R;
import com.cf.hodgepodge.bean.NewsBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cf on 2018/1/16.
 */

public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsBean,BaseViewHolder> {


    private  onItemClickListener mItemClickListener;
    public NewsAdapter(List<NewsBean> data) {
        super(data);
        addItemType(0,R.layout.item_zhihu_title);
        addItemType(1, R.layout.item_zhihu_daily);
        addItemType(2, R.layout.item_zhihu_theme);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final NewsBean item) {

        if (helper.getAdapterPosition()==0){

        }
        switch (item.getItemType()){
            case 0:
                helper.setText(R.id.tv_title,item.getTitle());
                break;
            case 1:
                helper.setText(R.id.tv_daily_dec,item.getStoriesBean().getTitle());
                Glide.with(this.mContext)
                        .load(item.getStoriesBean().getImages().get(0))
                        .transition(new DrawableTransitionOptions().crossFade(1000))
                        .into((ImageView) helper.getView(R.id.iv_daily));
                helper.getView(R.id.ll_section).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener!=null){
                            mItemClickListener.onItem(helper.getView(R.id.iv_daily),item.getStoriesBean().getId());
                        }
                    }
                });

                break;
            case 2:
                helper.setText(R.id.tv_one_title,item.getOthersBeanList().get(0).getName());
                helper.setText(R.id.tv_two_title,item.getOthersBeanList().get(1).getName());
                Glide.with(this.mContext)
                        .load(item.getOthersBeanList().get(0).getThumbnail())
                        .transition(new DrawableTransitionOptions().crossFade(1000))
                        .into((ImageView) helper.getView(R.id.iv_one));

                Glide.with(this.mContext)
                        .load(item.getOthersBeanList().get(1).getThumbnail())
                        .transition(new DrawableTransitionOptions().crossFade(1000))
                        .into((ImageView) helper.getView(R.id.iv_two));
                break;
        }
    }


    public void setItemClickListener(onItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface onItemClickListener{
        void onItem(View view,int id);
    }
}
