package com.cf.hodgepodge.ui.iview;

import android.view.View;

import com.cf.hodgepodge.bean.NewsBean;
import com.cf.hodgepodge.bean.ZhiHuDailyDetailsBean;
import com.cf.hodgepodge.ui.base.IBaseView;

import java.util.List;

/**
 * Created by cf on 2018/1/16.
 */

public interface INewsView extends IBaseView {

    void showEmpty();
    void showError();
    void showNoNetwork();

    void showContent(List<NewsBean> list);
    void showBanner(List<String> pathList);
    void showLoading();
    void openDetails(ZhiHuDailyDetailsBean zhiHuDailyDetailsBean, View view);
    void refreshData();
}
