package com.cf.hodgepodge.ui.iview;


import com.cf.hodgepodge.bean.WeiXinHotBean;
import com.cf.hodgepodge.ui.base.IBaseView;

import java.util.List;

/**
 * Created by cf on 2018/1/19.
 */

public interface IWeiXinHotView extends IBaseView {
    void loadMoreData( List<WeiXinHotBean.NewslistBean> newslist);
    void refreshData( List<WeiXinHotBean.NewslistBean> newslist);
    void showError();
}
