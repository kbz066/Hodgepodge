package com.cf.hodgepodge.ui.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.cf.hodgepodge.APPConstant;
import com.cf.hodgepodge.bean.ToHomeEvent;
import com.cf.hodgepodge.utils.EventBusUtils;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

/**
 * Created by cf on 2018/1/31.
 */

public class QRCodeFragment extends CaptureFragment {



    private String[] permissionArray;//需要申请的权限
    @Override
    public void onStart() {
        checkPermission(new String[]{Manifest.permission.CAMERA});
        super.onStart();
    }

    /**
     * 权限申请
     * @param permission
     */
    public void checkPermission(String[] permission){
        this.permissionArray=permission;

        AndPermission.with(this)
                .permission(permission)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        setRationaleDialog(rationale);

                    }
                })
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {


                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {


                        setSettingDialog(deniedPermissions);


                    }
                })
                .requestCode(APPConstant.REQUEST_CODE)
                .start();
    }

    public void setRationaleDialog(Rationale rationale){
        AndPermission.rationaleDialog(QRCodeFragment.this.getContext(), rationale)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBusUtils.post(new ToHomeEvent());
                    }
                })
                .show();
    }

    public void setSettingDialog( List<String> deniedPermissions){
        //存在某些永久禁用的特权
        if (hasAlwaysDeniedPermission(deniedPermissions)) {


            AndPermission.defaultSettingDialog(QRCodeFragment.this, APPConstant.REQUEST_CODE)
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EventBusUtils.post(new ToHomeEvent());

                        }
                    })

                    .show();


        }else {
            EventBusUtils.post(new ToHomeEvent());
        }
    }

    /**
     * 检查存在某些永久禁用的特权
     * @param deniedPermissions
     * @return
     */
    public boolean hasAlwaysDeniedPermission(List<String> deniedPermissions){
        return AndPermission.hasAlwaysDeniedPermission(QRCodeFragment.this.getActivity(), deniedPermissions);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==APPConstant.REQUEST_CODE){
            if (permissionArray!=null){
                checkPermission(permissionArray);
            }
        }
    }
}
