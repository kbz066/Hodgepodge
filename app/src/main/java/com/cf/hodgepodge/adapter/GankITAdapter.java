package com.cf.hodgepodge.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import com.cf.hodgepodge.R;
import com.cf.hodgepodge.bean.GankITBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cf on 2018/1/19.
 */

public class GankITAdapter extends BaseQuickAdapter<GankITBean.ResultsBean,BaseViewHolder> {


    public GankITAdapter(int layoutResId, @Nullable List<GankITBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankITBean.ResultsBean item) {
        ImageView imageView = helper.getView(R.id.iv_android_pic);

        helper.setText(R.id.tv_android_des, item.getDesc());
        helper.setText(R.id.tv_android_who, item.getWho());
        helper.setText(R.id.tv_android_time, item.getPublishedAt());


        if (item.getImages() != null
                && item.getImages().size() > 0
                && !TextUtils.isEmpty(item.getImages().get(0))) {
            imageView.setVisibility(View.VISIBLE);

        Glide.with(this.mContext)
                .load(item.getImages().get(0))
                .transition(new DrawableTransitionOptions().crossFade(1000))
                .apply(new RequestOptions().error(R.drawable.ic_error))
                .into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }

    }
}
