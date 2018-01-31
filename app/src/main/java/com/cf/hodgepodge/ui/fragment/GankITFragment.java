package com.cf.hodgepodge.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.caofu.hodgepodge.R;
import com.cf.hodgepodge.adapter.GankITAdapter;
import com.cf.hodgepodge.bean.GankITBean;
import com.cf.hodgepodge.component.home.DaggerGankITComponent;
import com.cf.hodgepodge.component.home.GankITComponent;
import com.cf.hodgepodge.module.home.GankITFragmentModule;
import com.cf.hodgepodge.presenter.home.GankITPresenter;
import com.cf.hodgepodge.ui.base.BaseFragment;
import com.cf.hodgepodge.ui.iview.IGankITView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

;

public class GankITFragment extends BaseFragment<GankITPresenter> implements IGankITView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_gank_it)
    RecyclerView mRvGankIt;
    @BindView(R.id.sl_gank_it)
    SwipeRefreshLayout mSlRefresh;


    private int page;
    private int num;
    private GankITAdapter mITAdapter;


    @Override
    protected void loadData() {


    }

    @Override
    protected void lazyLoad() {


        mPresenter.loadGankIT(num, page, true);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_wei_xin_it;
    }

    @Override
    public void initView() {


        page = 1;
        num = 10;
        mITAdapter = new GankITAdapter(R.layout.item_wechat, new ArrayList<GankITBean.ResultsBean>());
        mRvGankIt.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvGankIt.setAdapter(mITAdapter);


        mSlRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSlRefresh.setOnRefreshListener(this);

        mITAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //自定义底部加载视图
        mITAdapter.setLoadMoreView(new LoadMoreView() {
            @Override
            public int getLayoutId() {
                return R.layout.base_load_more;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.load_more_loading_view;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.load_more_load_fail_view;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.load_more_load_end_view;
            }
        });

        mITAdapter.setOnLoadMoreListener(this,mRvGankIt);
        mITAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void initInject() {
        GankITComponent component = DaggerGankITComponent.builder()
                .appComponent(mAppComponent)
                .gankITFragmentModule(new GankITFragmentModule(this))
                .build();
        component.inject(this);
        component.inject(mPresenter);
    }

    /**
     * 上拉加载
     * @param newslist
     */
    @Override
    public void loadMoreData(List<GankITBean.ResultsBean> newslist) {
        mITAdapter.addData(newslist);
        mITAdapter.loadMoreComplete();
        mSlRefresh.setEnabled(true);
    }

    /**
     * 下拉刷新
     * @param newslist
     */
    @Override
    public void refreshData(List<GankITBean.ResultsBean> newslist) {


        mITAdapter.setNewData(newslist);
        mSlRefresh.setRefreshing(false);
        mITAdapter.setEnableLoadMore(true);
    }

    @Override
    public void showError() {
        mSlRefresh.setRefreshing(false);
        mITAdapter.setEnableLoadMore(true);
        mSlRefresh.setEnabled(true);
        mITAdapter.setEmptyView(R.layout.empty_view);
    }

    @Override
    public void onRefresh() {
        page=1;
        mITAdapter.setEnableLoadMore(false);
        mPresenter.loadGankIT(num, page, true);
    }

    @Override
    public void onLoadMoreRequested() {
        if (page>=5){//加载完数据
            mITAdapter.loadMoreEnd();
            return;
        }
        mSlRefresh.setEnabled(false);
        mPresenter.loadGankIT(num, page, false);
        page+=1;
    }
}
