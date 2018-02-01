package com.cf.hodgepodge.ui.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


import com.cf.hodgepodge.R;
import com.cf.hodgepodge.adapter.HomeVpAdapter;
import com.cf.hodgepodge.component.home.DaggerHomeComponent;
import com.cf.hodgepodge.component.home.HomeComponent;
import com.cf.hodgepodge.module.home.HomeModule;
import com.cf.hodgepodge.presenter.home.HomePresenter;
import com.cf.hodgepodge.ui.base.BaseFragment;
import com.cf.hodgepodge.ui.iview.IHomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {


    @BindView(R.id.vp_tab)
    TabLayout mVpTab;
    @BindView(R.id.vp_home)
    ViewPager mVpHome;

    private HomeVpAdapter mVpAdapter;


    @Override
    protected void loadData() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {



        List<BaseFragment> list = new ArrayList<>();
        list.add(GankITFragment.newInstance(GankITFragment.class));
        list.add(WeiXinHotFragment.newInstance(WeiXinHotFragment.class));


        String[] title=new String[]{"IT资讯","精选热门"};
        mVpAdapter=new HomeVpAdapter(getChildFragmentManager(),list,title);
        mVpHome.setAdapter(mVpAdapter);
        mVpTab.setupWithViewPager(mVpHome);
    }

    @Override
    public void initInject() {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(mAppComponent)
                .homeModule(new HomeModule(this))
                .build();
        homeComponent.inject(this);
        homeComponent.inject(mPresenter);
    }


}
