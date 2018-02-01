package com.cf.hodgepodge.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.cf.hodgepodge.R;
import com.cf.hodgepodge.bean.ZhiHuDailyDetailsBean;
import com.cf.hodgepodge.utils.EventBusUtils;
import com.cf.hodgepodge.utils.HtmlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ZhiHuDetailActivity extends AppCompatActivity {

    @BindView(R.id.wv_show_txt)
    WebView mWvShowTxt;
    @BindView(R.id.iv_show_img)
    ImageView iv_toolbar_img;
    @BindView(R.id.ct_title)
    CollapsingToolbarLayout ct_toolbar_title;

    private ZhiHuDailyDetailsBean mDetailsBean;

    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu_detail);
        mUnbinder=ButterKnife.bind(this);

        
        loadData();
    }

    private void loadData() {
        mDetailsBean= EventBusUtils.getStickyEvent(ZhiHuDailyDetailsBean.class);
        if (mDetailsBean!=null){

            mWvShowTxt.getSettings().setSupportZoom(true);
            mWvShowTxt.getSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
            mWvShowTxt.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
            mWvShowTxt.loadDataWithBaseURL(null, HtmlUtils.createHtmlData(mDetailsBean.getBody(),mDetailsBean.getCss(),mDetailsBean.getJs()), "text/html",  "charset=UTF-8",null);
            ct_toolbar_title.setTitle(mDetailsBean.getTitle());
            Glide.with(this).load(mDetailsBean.getImage()).into(iv_toolbar_img);
        }

    }

    @Override
    protected void onDestroy() {
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
        super.onDestroy();
    }
}
