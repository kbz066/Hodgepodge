package com.cf.hodgepodge.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by cf on 2018/1/17.
 */

public class NewsBean implements MultiItemEntity {

    private ZhiHuDailyBean.StoriesBean mStoriesBean;
    private List<ZhiHuThemesBean.OthersBean> mOthersBeanList;

    private String title;
    private int type;
    @Override
    public int getItemType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ZhiHuDailyBean.StoriesBean getStoriesBean() {
        return mStoriesBean;
    }

    public void setStoriesBean(ZhiHuDailyBean.StoriesBean storiesBean) {
        mStoriesBean = storiesBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ZhiHuThemesBean.OthersBean> getOthersBeanList() {
        return mOthersBeanList;
    }

    public void setOthersBeanList(List<ZhiHuThemesBean.OthersBean> othersBeanList) {
        mOthersBeanList = othersBeanList;
    }
}
