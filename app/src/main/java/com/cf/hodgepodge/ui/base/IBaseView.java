package com.cf.hodgepodge.ui.base;

/**
 * Created by cf on 2018/1/11.
 */

public interface IBaseView {


    int getContentViewResId();

    /**
     * 初始化的方法
     */
    void initView();

    /**
     * 注入方法
     */
    void initInject();
}
