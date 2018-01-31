package com.cf.hodgepodge.ui.iview;


import com.cf.hodgepodge.bean.GankITBean;
import com.cf.hodgepodge.ui.base.IBaseView;

import java.util.List;

/**
 * Created by cf on 2018/1/19.
 */

public interface IGankITView extends IBaseView {
    void loadMoreData( List<GankITBean.ResultsBean> newslist);
    void refreshData( List<GankITBean.ResultsBean> newslist);
    void showError();


}
