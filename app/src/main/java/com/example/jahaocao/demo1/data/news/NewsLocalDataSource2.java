package com.example.jahaocao.demo1.data.news;

import android.content.Context;

import com.example.jahaocao.demo1.data.Channel;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.man.title.NewsContract;
import com.example.jahaocao.demo1.data.source.NewsDataSource;
import com.example.jahaocao.demo1.utils.FilesUtils;
import com.example.jahaocao.demo1.utils.SystemFacade;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

public class NewsLocalDataSource2 implements NewsDataSource {


    private static NewsLocalDataSource2 INSTANCE;

    private File mDataCacheFileDir;
    private Context context;

    private NewsLocalDataSource2(Context context) {
        File cacheDir = SystemFacade.getExternalCacheDir(context);
        if (cacheDir != null && cacheDir.exists()) {
            mDataCacheFileDir = new File(cacheDir, "news_files");
        }
    }


    public static synchronized NewsLocalDataSource2 getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NewsLocalDataSource2(context);
        }

        return INSTANCE;
    }
    @Override
    public void loadChannels(LifecycleProvider lifecycleProvider, Observer<List<Channel>> observer) {
    }

    @Override
    public void loadNewsByChannel(LifecycleProvider lifecycleProvider, Map<String, String> params, Observer<NewsData> observer) {

    }

    @Override
    public void getAdd(RxFragment fragment, Map<String, String> params, Observer<Datass> observer) {

    }

    @Override
    public Observable<NewsData> getNewsByChannel(Map<String, String> params) {
        final String channelId = params.get(NewsContract.PARAMS_CHANNER_ID);
        return Observable.create(new ObservableOnSubscribe<NewsData>() {
            @Override
            public void subscribe(ObservableEmitter<NewsData> emitter) throws Exception {

                NewsData newsData = FilesUtils.getNewsFromFile(createCacheFile(channelId));

                if(newsData != null){
                    emitter.onNext(newsData);
                }
                emitter.onComplete();
            }
        });



    }

    @Override
    public void saveNews(String channelId, NewsData data) {
        FilesUtils.writeNewsDataToFile(data, createCacheFile(channelId));
    }


    private File createCacheFile(String channelId) {
        if (mDataCacheFileDir != null) {
            if(!mDataCacheFileDir.exists()){
                mDataCacheFileDir.mkdirs();
            }
            return new File(mDataCacheFileDir, channelId);
        }

        return null;
    }
}
