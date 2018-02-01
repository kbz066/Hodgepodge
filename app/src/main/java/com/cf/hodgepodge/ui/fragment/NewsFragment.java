package com.cf.hodgepodge.ui.fragment;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import com.cf.hodgepodge.R;
import com.cf.hodgepodge.adapter.NewsAdapter;
import com.cf.hodgepodge.bean.NewsBean;
import com.cf.hodgepodge.bean.ZhiHuDailyDetailsBean;
import com.cf.hodgepodge.component.news.DaggerNewsComponent;
import com.cf.hodgepodge.component.news.NewsComponent;
import com.cf.hodgepodge.module.news.NewsFragmentModule;
import com.cf.hodgepodge.presenter.news.NewsPresenter;
import com.cf.hodgepodge.ui.activity.ZhiHuDetailActivity;
import com.cf.hodgepodge.ui.base.BaseFragment;
import com.cf.hodgepodge.ui.custom.MultipleStatusView;
import com.cf.hodgepodge.ui.iview.INewsView;
import com.cf.hodgepodge.utils.EventBusUtils;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsView {


    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;


    private Banner mBanner;
    private NewsAdapter mNewsAdapter;
    private RecyclerView zhihu_news_list;

   // http://cg01.hrtn.net:9090/live/fyyy.m3u8

    @Override
    public int getContentViewResId() {

        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        mBanner= (Banner) mMultipleStatusView.findView(R.id.banner);
        zhihu_news_list= (RecyclerView) mMultipleStatusView.findView(R.id.news_list);
        mNewsAdapter=new NewsAdapter(new ArrayList<NewsBean>());
        zhihu_news_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        zhihu_news_list.setAdapter(mNewsAdapter);
        mNewsAdapter.setItemClickListener(new NewsAdapter.onItemClickListener() {
            @Override
            public void onItem(View view, int id) {

                mPresenter.loadZhiHuDailyDetails(id,view);
                Logger.e("id----------------->"+id);
            }
        });

    }

    @Override
    protected void loadData() {

        mPresenter.loadData();

    }

    @Override
    protected void lazyLoad() {

    }

    /**
     * 注入方法
     */
    @Override
    public void initInject() {
        NewsComponent newsComponent= DaggerNewsComponent
                .builder()
                .appComponent(mAppComponent)
                .newsFragmentModule(new NewsFragmentModule(this))
                .build();
        newsComponent.inject(this);
        newsComponent.inject(mPresenter);
    }

    @Override
    public void showEmpty() {
        mMultipleStatusView.showEmpty();
    }

    @Override
    public void showError() {
        if (mMultipleStatusView!=null){
            mMultipleStatusView.showError();
        }

    }

    @Override
    public void showNoNetwork() {
        mMultipleStatusView.showNoNetwork();
    }

    @Override
    public void showContent(List<NewsBean> data) {
        mMultipleStatusView.showContent();


        mNewsAdapter.setNewData(data);

    }

    /**
     * 轮播图
     * @param list
     */
    @Override
    public void showBanner(List<String> list) {
        //设置图片加载器
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).transition(new DrawableTransitionOptions().crossFade(1000)).into(imageView);
            }
        });
        //设置图片集合
        mBanner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void showLoading() {
        mMultipleStatusView.showLoading();
    }

    @Override
    public void openDetails(ZhiHuDailyDetailsBean zhiHuDailyDetailsBean, View view) {
        EventBusUtils.postSticky(zhiHuDailyDetailsBean);
        Intent intent=new Intent(NewsFragment.this.getActivity(), ZhiHuDetailActivity.class);
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,"image");
        startActivity(intent,options.toBundle() );
    }


    @Override
    public void refreshData() {

    }


}
