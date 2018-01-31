package com.cf.hodgepodge.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.caofu.hodgepodge.R;
import com.cf.hodgepodge.bean.ToHomeEvent;
import com.cf.hodgepodge.component.main.DaggerMianComponent;
import com.cf.hodgepodge.module.main.MianActivityModule;
import com.cf.hodgepodge.presenter.main.MainPresenter;
import com.cf.hodgepodge.ui.base.BaseActivity;
import com.cf.hodgepodge.ui.fragment.AudioFragment;
import com.cf.hodgepodge.ui.fragment.HomeFragment;
import com.cf.hodgepodge.ui.fragment.NewsFragment;
import com.cf.hodgepodge.ui.fragment.QRCodeFragment;
import com.cf.hodgepodge.ui.iview.IMainView;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;



public class MainActivity extends BaseActivity<MainPresenter> implements IMainView,CodeUtils.AnalyzeCallback ,NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_show)
    FrameLayout mMainShow;
    @BindView(R.id.main_navigationView)
    NavigationView mMainNavigationView;
    @BindView(R.id.main_drawerLayout)
    DrawerLayout mMainDrawerLayout;


    private LottieAnimationView mAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
//        getWindow().setEnterTransition(new Explode().setDuration(500));
//        getWindow().setExitTransition(new Explode().setDuration(500));
    }

    @Override
    public void initView() {



        mAnimationView=mMainNavigationView.getHeaderView(0).findViewById(R.id.animation_view);

        setSupportActionBar(mToolbar);

        mMainNavigationView.setItemIconTintList(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mMainNavigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mToggle= new ActionBarDrawerToggle(this,mMainDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);

        mToggle.syncState();
        mMainDrawerLayout.addDrawerListener(mToggle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_show, HomeFragment.newInstance(HomeFragment.class)).commitNow();

    }


    @Override
    public void initInject() {
        DaggerMianComponent.builder()
                .mianActivityModule(new MianActivityModule(this))
                .appComponent(mAppComponent)
                .build()
                .inject(this);
    }

    @Override
    public int getContentViewResId() {
        // 此处返回你Activity的contentViewId
        return R.layout.activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.option_1:
                Toast.makeText(this,"点击option_1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_2:
                Toast.makeText(this,"点击option_2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_3:
                Toast.makeText(this,"点击option_3",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }





    /**
     * 二维码解析成功
     * @param mBitmap
     * @param result
     */
    @Override
    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
        Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
    }

    /**
     * 二维码解析失败
     */
    @Override
    public void onAnalyzeFailed() {
        Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
    }



    /**
     * 侧滑按钮事件
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {





        switch (menuItem.getItemId()){
            case R.id.menu_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_show, HomeFragment.newInstance(HomeFragment.class)).commitNow();
                break;

            case R.id.menu_2://二维码
                QRCodeFragment codeFragment = new QRCodeFragment();
                codeFragment.setAnalyzeCallback(MainActivity.this);

                getSupportFragmentManager().beginTransaction().replace(R.id.main_show, codeFragment).commitNow();

                break;
            case R.id.menu_3://新闻

                getSupportFragmentManager().beginTransaction().replace(R.id.main_show,  NewsFragment.newInstance(NewsFragment.class)).commitNow();

                break;
            case R.id.menu_4://新闻

                getSupportFragmentManager().beginTransaction().replace(R.id.main_show, AudioFragment.newInstance(AudioFragment.class)).commitNow();

                break;
        }


        mMainDrawerLayout.closeDrawers();//关闭侧边菜单栏

        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(ToHomeEvent event) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_show, HomeFragment.newInstance(HomeFragment.class)).commitNow();
    }
}
