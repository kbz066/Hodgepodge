package com.cf.hodgepodge.http;


import com.cf.hodgepodge.bean.GankITBean;
import com.cf.hodgepodge.bean.WeiXinHotBean;
import com.cf.hodgepodge.bean.ZhiHuDailyBean;
import com.cf.hodgepodge.bean.ZhiHuDailyDetailsBean;
import com.cf.hodgepodge.bean.ZhiHuThemesBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * Created by cf on 2018/1/12.
 */
public interface ServerApi {



    @GET("news/latest")
    Observable<ZhiHuDailyBean> getZhiHuDaily();

    @GET("news/{id}")
    Observable<ZhiHuDailyDetailsBean> getZhiHuDailyDetails(@Path("id") int id);

    @GET("themes")
    Observable<ZhiHuThemesBean> getZhiHuThemes();


    @GET()
    Observable<WeiXinHotBean> getWeiXinHot(@Url String url, @Query("key") String key, @Query("num") int num, @Query("page") int page);



    @GET()
    Observable<GankITBean> getGankIT(@Url String url);


}
