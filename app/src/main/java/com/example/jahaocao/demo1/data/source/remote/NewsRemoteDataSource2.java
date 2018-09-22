package com.example.jahaocao.demo1.data.source.remote;

import com.example.jahaocao.demo1.data.Channel;
import com.example.jahaocao.demo1.data.ChannelData;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.HttpResult;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.source.NewsDataSource;
import com.example.jahaocao.demo1.data.source.remote.retrofit.DataRetrofit;
import com.example.jahaocao.demo1.data.source.remote.retrofit.FirstgaService;
import com.example.jahaocao.demo1.exception.ServerException;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsRemoteDataSource2 implements NewsDataSource {

    private FirstgaService service;

    private static NewsRemoteDataSource2 INSTANCE;

    private NewsRemoteDataSource2() {
        service = DataRetrofit.getRetrofitService();

    }


    public static synchronized NewsRemoteDataSource2 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new NewsRemoteDataSource2();
        }
        return INSTANCE;
    }
    //网络请求频道
    @Override
    public void loadChannels(LifecycleProvider lifecycleProvider, Observer<List<Channel>> observer) {
        Observable<HttpResult<ChannelData>> observable = service.loadChannels();

        buildObserver(observable.flatMap(new Function<HttpResult<ChannelData>, ObservableSource<List<Channel>>>() {
            @Override
            public ObservableSource<List<Channel>> apply(HttpResult<ChannelData> channelDataHttpResult) throws Exception {
                if(channelDataHttpResult.getCode() == 0 ){
                    if(channelDataHttpResult.getData() != null && channelDataHttpResult.getData().getNewsChannelList() != null){
                        return Observable.just(channelDataHttpResult.getData().getNewsChannelList());
                    }else{
                        return Observable.error(new ServerException(1001,"服务器内部异常"));
                    }

                }
                return Observable.error(new ServerException(channelDataHttpResult.getCode(),channelDataHttpResult.getMessage()));
            }
        }), observer,lifecycleProvider );
    }
    //网络请求item数据
    @Override
    public void loadNewsByChannel(LifecycleProvider lifecycleProvider, Map<String, String> params, Observer<NewsData> observer) {

    }

    @Override
    public void getAdd(RxFragment fragment, Map<String, String> params, Observer<Datass> observer) {
        Observable<HttpResult<Datass>> observable = service.getAddData(params);
        observable.flatMap(new Function<HttpResult<Datass>, ObservableSource<Datass>>() {
            @Override
            public ObservableSource<Datass> apply(HttpResult<Datass> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<Datass>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);


    }

    @Override
    public Observable<NewsData> getNewsByChannel(Map<String, String> params) {
        Observable<HttpResult<NewsData>> observable = service.fetchNewNews(params);

        return observable.flatMap(new Function<HttpResult<NewsData>, ObservableSource<NewsData>>() {
            @Override
            public ObservableSource<NewsData> apply(HttpResult<NewsData> newsDataHttpResult) throws Exception {

                if(newsDataHttpResult.getCode() == 0){
                    NewsData newsData = newsDataHttpResult.getData();
                    if(newsData != null && newsData.getNewList()!= null && newsData.getNewList().size() >0){
                        return Observable.just(newsData);
                    }

                    return Observable.error(new ServerException(1001,"服务器内部异常"));
                }

                return Observable.error(new ServerException(newsDataHttpResult.getCode(),newsDataHttpResult.getMessage()));
            }
        });

    }

    @Override
    public void saveNews(String channelId, NewsData data) {

    }


    private <T> void buildObserver(Observable<T> observable, Observer<T> observer, LifecycleProvider lifecycleProvider){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose((lifecycleProvider instanceof RxAppCompatActivity) ?(((RxAppCompatActivity)lifecycleProvider).<T>bindUntilEvent(ActivityEvent.DESTROY)) :  (((RxFragment)lifecycleProvider).<T>bindUntilEvent(FragmentEvent.DETACH)))
                .subscribe(observer);
    }
}