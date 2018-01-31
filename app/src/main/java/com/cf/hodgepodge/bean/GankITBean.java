package com.cf.hodgepodge.bean;

import java.util.List;

/**
 * Created by cf on 2018/1/19.
 */

public class GankITBean {


    /**
     * error : false
     * results : [{"_id":"5a389723421aa90fe72536c5","createdAt":"2017-12-19T12:35:47.103Z","desc":"Cipher.so: A simple way to encrypt your secure datas into a native .so library.","publishedAt":"2018-01-16T08:40:08.101Z","source":"web","type":"Android","url":"https://github.com/MEiDIK/Cipher.so","used":true,"who":"drakeet"},{"_id":"5a41af49421aa90fef2035c5","createdAt":"2017-12-26T10:09:13.935Z","desc":"Android rtmp rtsp 推流客户端","publishedAt":"2018-01-16T08:40:08.101Z","source":"web","type":"Android","url":"https://github.com/pedroSG94/rtmp-rtsp-stream-client-java","used":true,"who":"蒋朋"},{"_id":"5a56cb26421aa91154899275","createdAt":"2018-01-11T10:25:42.34Z","desc":"Android：修图技术之滤镜效果","publishedAt":"2018-01-16T08:40:08.101Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489265&idx=1&sn=58bf6498c1b6d172964bf6dd00fa4325","used":true,"who":"陈宇明"},{"_id":"5a5a0c63421aa911577aa7c0","createdAt":"2018-01-13T21:40:51.632Z","desc":"简洁优雅可点击的toast控件，仿手机百度9.0，无BadTokenException风险。","images":["http://img.gank.io/375cce8b-6398-48a9-8107-8ac970f8c672"],"publishedAt":"2018-01-16T08:40:08.101Z","source":"chrome","type":"Android","url":"https://github.com/bboylin/UniversalToast","used":true,"who":"null"},{"_id":"5a4af266421aa90fe50c02b6","createdAt":"2018-01-02T10:45:58.490Z","desc":"一个简洁、实用、方便的Android异步处理库，已应用到百万日活的线上项目中","publishedAt":"2018-01-10T07:57:19.486Z","source":"web","type":"Android","url":"https://github.com/SilenceDut/TaskScheduler","used":true,"who":null},{"_id":"5a4df36c421aa90fe2f02d26","createdAt":"2018-01-04T17:27:08.992Z","desc":"一款好用、可自定义状态的数据状态切换布局","images":["http://img.gank.io/c141c075-afdf-4f8f-9d17-ec41faa10e75"],"publishedAt":"2018-01-10T07:57:19.486Z","source":"web","type":"Android","url":"https://github.com/Bakumon/StatusLayoutManager/blob/master/README.md","used":true,"who":"马飞"},{"_id":"5a531646421aa90fe725370a","createdAt":"2018-01-08T14:57:10.956Z","desc":"Android自定义 View - 仿淘宝 淘抢购进度条","images":["http://img.gank.io/b1a4758e-6fb1-42b7-b197-7dc2d36a20d0"],"publishedAt":"2018-01-10T07:57:19.486Z","source":"web","type":"Android","url":"https://github.com/zhlucky/SaleProgressView","used":true,"who":null},{"_id":"5a54667f421aa90fe50c02d0","createdAt":"2018-01-09T14:51:43.528Z","desc":"探究Android View 绘制流程，Xml 文件到 View 对象的转换过程","images":["http://img.gank.io/4dd0f9e4-a016-4579-a1cd-73f56f05a466"],"publishedAt":"2018-01-10T07:57:19.486Z","source":"web","type":"Android","url":"https://www.jianshu.com/p/eccd8ba87e8b","used":true,"who":"Kai Sun"},{"_id":"5a07b7fe421aa90fe7253624","createdAt":"2017-11-12T10:54:54.391Z","desc":"应用模块化和懒加载在 Instagram 中的实现","publishedAt":"2018-01-04T13:45:57.211Z","source":"chrome","type":"Android","url":"https://github.com/Instagram/ig-lazy-module-loader","used":true,"who":"vincgao"},{"_id":"5a07e798421aa90fef20351c","createdAt":"2017-11-12T14:18:00.758Z","desc":"带来高收入的 10 大开源技术","publishedAt":"2018-01-04T13:45:57.211Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIyMjQ0MTU0NA==&mid=2247484681&idx=1&sn=814c2799270911211c1ca2679b2b1dae&chksm=e82c3c2edf5bb538f929e1bad6d93af3c2a64f29125219f892875f9f3705a7809dee954b4859&mpshare=1&scene=1&srcid=1112Xqhm9lOPXS8nzaQQwBWn&key=bfc872d4a5d909acdba60130fbee50669b02772b3b48a1d612fdf0c4f16d2275b40855559612872361d1b8216a50791a9414df4f25ca3b1bbaac45b1fc2a5a6db68d60509170e222270da24f293c93af&ascene=0&uin=MjMzMzgwOTEwMQ%3D%3D&devicetype=iMac+MacBookPro12%2C1+OSX+OSX+10.10.5+build(14F27)&version=11020201&pass_ticket=ou7zYvjxcdHOv5jQYjvGT8YTTADmWIwriTFISUiYMatR1c7FfFAxWJTAwdm7Fc58","used":true,"who":"Tamic (码小白)"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5a389723421aa90fe72536c5
         * createdAt : 2017-12-19T12:35:47.103Z
         * desc : Cipher.so: A simple way to encrypt your secure datas into a native .so library.
         * publishedAt : 2018-01-16T08:40:08.101Z
         * source : web
         * type : Android
         * url : https://github.com/MEiDIK/Cipher.so
         * used : true
         * who : drakeet
         * images : ["http://img.gank.io/375cce8b-6398-48a9-8107-8ac970f8c672"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
