package com.cf.hodgepodge.ui.base;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cf.hodgepodge.APPConstant;
import com.cf.hodgepodge.MyApplication;
import com.cf.hodgepodge.component.application.AppComponent;
import com.cf.hodgepodge.utils.EventBusUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cf on 2018/1/16.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {



    @Inject
    public T mPresenter;
    private Unbinder mUnbinder;
    public boolean isPrepared;
    public boolean isFirst = true;

    private permissionResult mPermissionResult;//权限回调接口
    private String[] permissionArray;//需要申请的权限

    public AppComponent mAppComponent;

    public static <T extends BaseFragment> T newInstance(Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return t;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }


    }

    @Override
    public void onStart() {
        if (isRegisterEventBus()){
            EventBusUtils.register(this);
        }
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(getContentViewResId(),container,false);
        mUnbinder=ButterKnife.bind(this,view);


        mAppComponent=((MyApplication)getActivity().getApplication()).getAppComponent();

        initInject();
        initView();
        loadData();



        return view;
    }

    /**
     * 当fragment被用户可见时，setUserVisibleHint()会调用且传入true值，
     * 当fragment不被用户可见时，setUserVisibleHint()则得到false值
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        if (getUserVisibleHint()) {

            if (!isPrepared || !isFirst) {
                return;
            }

            lazyLoad();
            isFirst = false;

        }
    }
    public boolean isRegisterEventBus() {

        return false;
    }

    /**
     * 权限申请
     * @param permission
     */
    public void checkPermission(String[] permission, final permissionResult mResult){
        this.permissionArray=permission;
        this.mPermissionResult=mResult;
        AndPermission.with(this)
                .permission(permission)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(BaseFragment.this.getContext(), rationale)
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (mResult!=null){
                                            mResult.onFailed();
                                        }

                                    }
                                })
                                .show();


                    }
                })
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (mResult!=null){
                            mResult.onSucceed();
                        }


                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {


                        //存在某些永久禁用的特权
                        if (hasAlwaysDeniedPermission(deniedPermissions)) {


                            AndPermission.defaultSettingDialog(BaseFragment.this, APPConstant.REQUEST_CODE)
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (mResult!=null){
                                                mResult.onFailed();
                                            }

                                        }
                                    })

                                    .show();


                        }else {
                            if (mResult!=null){
                                mResult.onFailed();
                            }
                        }


                    }
                })
                .requestCode(APPConstant.REQUEST_CODE)
                .start();
    }


    /**
     * 检查存在某些永久禁用的特权
     * @param deniedPermissions
     * @return
     */
    public boolean hasAlwaysDeniedPermission(List<String> deniedPermissions){
        return AndPermission.hasAlwaysDeniedPermission(BaseFragment.this.getActivity(), deniedPermissions);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==APPConstant.REQUEST_CODE){
            if (permissionArray!=null&&mPermissionResult!=null){
                checkPermission(permissionArray,mPermissionResult);
            }
        }
    }


    protected abstract void loadData();


    /**
     * 懒加载接口
     */
    protected abstract void lazyLoad();

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        mPermissionResult=null;
        permissionArray=null;
        EventBusUtils.unregister(this);
        super.onDestroy();
    }

    public interface  permissionResult{
        public void onSucceed();

        public void onFailed();
    }

}
