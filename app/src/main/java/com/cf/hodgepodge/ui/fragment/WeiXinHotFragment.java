package com.cf.hodgepodge.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.cf.hodgepodge.R;
import com.cf.hodgepodge.adapter.WeiXinHotAdapter;
import com.cf.hodgepodge.bean.WeiXinHotBean;
import com.cf.hodgepodge.component.home.DaggerWeiXinHotComponent;
import com.cf.hodgepodge.component.home.WeiXinHotComponent;
import com.cf.hodgepodge.module.home.WeiXinHotFragmentModule;
import com.cf.hodgepodge.presenter.home.WeiXinHotPresenter;
import com.cf.hodgepodge.ui.base.BaseFragment;
import com.cf.hodgepodge.ui.iview.IWeiXinHotView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class WeiXinHotFragment extends BaseFragment<WeiXinHotPresenter> implements IWeiXinHotView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.rv_wei_xin_hot)
    RecyclerView mRvWeiXinHot;
    @BindView(R.id.sl_refresh)
    SwipeRefreshLayout mSlRefresh;


    private int page;
    private int num;
    private WeiXinHotAdapter mHotAdapter;

    @Override
    protected void loadData() {

    }

    @Override
    protected void lazyLoad() {

        mPresenter.loadWeiXinHot(num, page, true);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_wei_xin_hot;
    }

    @Override
    public void initView() {

        page = 1;
        num = 10;
        mHotAdapter = new WeiXinHotAdapter(R.layout.item_top_news, new ArrayList<WeiXinHotBean.NewslistBean>());
        mRvWeiXinHot.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRvWeiXinHot.setAdapter(mHotAdapter);

        mSlRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSlRefresh.setOnRefreshListener(this);

        mHotAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //自定义底部加载视图
        mHotAdapter.setLoadMoreView(new LoadMoreView() {
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

        mHotAdapter.setOnLoadMoreListener(this,mRvWeiXinHot);
        mHotAdapter.disableLoadMoreIfNotFullPage();

    }

    @Override
    public void initInject() {
        WeiXinHotComponent component = DaggerWeiXinHotComponent
                .builder()
                .appComponent(mAppComponent)
                .weiXinHotFragmentModule(new WeiXinHotFragmentModule(this))
                .build();
        component.inject(this);
        component.inject(mPresenter);
    }


    /**
     * 上拉加载
     *
     * @param newslist
     */
    @Override
    public void loadMoreData(List<WeiXinHotBean.NewslistBean> newslist) {
        mHotAdapter.addData(newslist);
        mHotAdapter.loadMoreComplete();
        mSlRefresh.setEnabled(true);
    }

    /**
     * 下拉刷新
     *
     * @param newslist
     */
    @Override
    public void refreshData(List<WeiXinHotBean.NewslistBean> newslist) {
        mHotAdapter.setNewData(newslist);
        mSlRefresh.setRefreshing(false);
        mHotAdapter.setEnableLoadMore(true);
    }

    @Override
    public void showError() {
        mSlRefresh.setRefreshing(false);
        mHotAdapter.setEnableLoadMore(true);
        mSlRefresh.setEnabled(true);
        mHotAdapter.setEmptyView(R.layout.empty_view);
    }


    /**
     * OnRefreshListener刷新回调
     */
    @Override
    public void onRefresh() {
        page=1;
        mHotAdapter.setEnableLoadMore(false);
        mPresenter.loadWeiXinHot(num, page, true);

    }

    /**
     * 加载回调方法
     */
    @Override
    public void onLoadMoreRequested() {

        if (page>=5){//加载完数据
            mHotAdapter.loadMoreEnd();
            return;
        }
        mSlRefresh.setEnabled(false);
        mPresenter.loadWeiXinHot(num, page, false);
        page+=1;


    }
}
