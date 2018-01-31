package com.cf.hodgepodge.module.application;


import com.cf.hodgepodge.APPConstant;
import com.cf.hodgepodge.http.ServerApi;
import com.cf.hodgepodge.utils.FastJsonConvertFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by cf on 2018/1/12.
 */
@Module
public class AppModule {
//    private Context mContext;
//
//    public AppModule(Context context) {
//        mContext = context;
//    }


    @Provides
    @Singleton
    ServerApi provideServerApi(){
        //日志拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APPConstant.ZHIHU_HOST)
                .addConverterFactory(FastJsonConvertFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               // .client(client)
                .build();
        return retrofit.create(ServerApi.class);
    }



}
