package com.cf.hodgepodge.bean;

import java.util.List;

/**
 * Created by cf on 2018/1/18.
 */

public class WeiXinHotBean {



    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2018-01-19
         * title : 工作人员默认GAI退出《歌手》；陌陌也将撒币；微博之夜邓超杨幂分获得King&amp;Queen | 猬报
         * description : 刺猬公社
         * picUrl : https://zxpic.gtimg.com/infonew/0/wechat_pics_-61668833.jpg/640
         * url : https://mp.weixin.qq.com/s?src=16&ver=600&timestamp=1516323613&signature=UR8Q8bGHO6yb-pt5CwZvOHDGR6wsOZXvtLrsNpgVrqO3UoAc8ZCDjavGXwPK7vVyF8TSHDGRDjPLu*kGgXllTs1fWksSI0FeVB4hzuYtSP0=
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
