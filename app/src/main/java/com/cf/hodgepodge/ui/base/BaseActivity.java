package com.cf.hodgepodge.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.cf.hodgepodge.MyApplication;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.utils.EventBusUtils;


import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by cf on 2018/1/6.
 * Activity 基类
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    @Inject
    protected T mPresenter;

    public AppComponent mAppComponent;

    private Unbinder mUnbinder;

    private Fragment currentFragment;
    private FragmentTransaction mFragmentTransaction;


    @Override
    protected void onStart() {
        EventBusUtils.register(this);
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        mUnbinder= ButterKnife.bind(this);
        mAppComponent=((MyApplication)getApplication()).getAppComponent();
        initInject();
        initView();
    }
//
//    /**
//     * 添加fragment
//     * @param addFragment
//     * @param id
//     */
//    public void addFragment(FragmentManager manager,Fragment addFragment,int  id) {
//        //判断这个标签是否存在Fragment对象,如果存在则返回，不存在返回null
//        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(addFragment.getClass().getName());
//        // 如果这个fragment不存于栈中
//
//        if (fragment == null) {
//
//            //初始化Fragment事物
//            mFragmentTransaction = manager.beginTransaction();
//
//
//            //在添加之前先将上一个Fragment隐藏掉
//            if (currentFragment != null) {
//                mFragmentTransaction.hide(currentFragment);
//            }
//            mFragmentTransaction.add(id, addFragment, addFragment.getClass().getName());
//            mFragmentTransaction.commit();
//            //更新可见
//            currentFragment = fragment;
//        } else {
//
//
//            //如果添加的Fragment已经存在，则隐藏掉上一个Fragment
//            mFragmentTransaction = manager.beginTransaction();
//            mFragmentTransaction.show(fragment);
//            mFragmentTransaction.hide(currentFragment);
//            //更新可见
//            currentFragment = fragment;
//            mFragmentTransaction.commit();
//
//
//        }
//
//
//    }

    /**
     * 上一次按返回键的时间簇.
     */
     long mLastKeyDown = 0;

    /**
     * 如果有上一个Fragment的话，返回上一个Fragment; 如果没有提示要退出
     */
    @Override
    public void onBackPressed() {

        Toast.makeText(this,"双击退出程序",Toast.LENGTH_SHORT).show();
        long timeMillis = System.currentTimeMillis();

        // 判断当前按下的时间与上一次按下的间隔.
        if (timeMillis - mLastKeyDown >= 2000) {
            mLastKeyDown = timeMillis;

        } else {


            // 退出程序
            System.exit(0);
        }

    }



    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        EventBusUtils.unregister(this);
        super.onDestroy();
    }

}
