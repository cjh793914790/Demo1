package com.example.jahaocao.demo1.data.source;

import com.example.jahaocao.demo1.data.Channel;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.Titles;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/*
 * created by taofu on 2018/9/5
 **/
public interface NewsDataSource {

    void loadChannels(LifecycleProvider lifecycleProvider, Observer<List<Channel>> observer);

    //网络请求新闻频道
    void loadNewsByChannel(LifecycleProvider lifecycleProvider, Map<String, String> params, Observer<NewsData> observer);

    void getAdd(RxFragment fragment, Map<String, String> params, Observer<Datass> observer);


    Observable<NewsData> getNewsByChannel(Map<String, String> params);


    void saveNews(String channelId, NewsData data);
}
