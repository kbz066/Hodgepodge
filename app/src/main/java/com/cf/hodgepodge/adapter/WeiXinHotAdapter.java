package com.cf.hodgepodge.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.caofu.hodgepodge.R;
import com.cf.hodgepodge.bean.WeiXinHotBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cf on 2018/1/19.
 */

public class WeiXinHotAdapter extends BaseQuickAdapter<WeiXinHotBean.NewslistBean,BaseViewHolder> {


    public WeiXinHotAdapter(int layoutResId, @Nullable List<WeiXinHotBean.NewslistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeiXinHotBean.NewslistBean item) {
        ImageView imageView=helper.getView(R.id.iv_item_top_news);
        helper.setText(R.id.tv_item_top_news,item.getTitle());
        Glide.with(this.mContext)
                .load(item.getPicUrl())
                .transition(new DrawableTransitionOptions().crossFade(1000))
                .apply(new RequestOptions().error(R.drawable.ic_error))
                .into(imageView);
    }
}
