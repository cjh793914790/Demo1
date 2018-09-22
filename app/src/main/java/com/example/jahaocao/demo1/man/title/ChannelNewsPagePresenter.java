package com.example.jahaocao.demo1.man.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.jahaocao.demo1.base.MyObserver;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.source.NewsDataSource;
import com.example.jahaocao.demo1.utils.IoSetGet;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChannelNewsPagePresenter implements NewsContract.ChannelNewPagePresenter {

    private NewsDataSource mNewsDataRepository;
    private NewsContract.ChannelNewsPageView mView;

    public ChannelNewsPagePresenter(NewsDataSource mNewsDataRepository) {
        this.mNewsDataRepository = mNewsDataRepository;
    }

    @Override
    public void getAdd(String channelId, String Cursor) {
        Map<String,String> params = new HashMap<>();
        params.put("channelId", channelId);
        params.put("cursor", Cursor);
        mNewsDataRepository.getAdd((RxFragment) mView, params, new Observer<Datass>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(Datass datas) {
                mView.setAdd(datas);

            }
            @Override
            public void onError(Throwable e) {
                Log.e("onError222",e.getMessage());
            }
            @Override
            public void onComplete() {

            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getNewsByChannel(String channelId, String Cursor, Context context) {
        IoSetGet ioSetGet = new IoSetGet();
        File diskCacheDir = IoSetGet.getDiskCacheDir(context, channelId);
        final   NewsData sdCache = ioSetGet.getSdCache(diskCacheDir);
        Observable<NewsData> newsDataObservable = Observable.create(new ObservableOnSubscribe<NewsData>() {
            @Override
            public void subscribe(ObservableEmitter<NewsData> emitter) throws Exception {
                if (sdCache != null) {
                    emitter.onNext(sdCache);
                }
                emitter.onComplete();
            }
        });
        Map<String,String> parmas = new HashMap<>();

        parmas.put(NewsContract.PARAMS_CHANNER_ID, channelId);
        parmas.put("cursor", Cursor);

        newsDataObservable.concatWith( mNewsDataRepository.getNewsByChannel(parmas))

                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose((mView instanceof RxAppCompatActivity) ?(((RxAppCompatActivity)mView).<NewsData>bindUntilEvent(ActivityEvent.DESTROY)) :  (((RxFragment)mView).<NewsData>bindUntilEvent(FragmentEvent.DETACH)))
                .subscribe(new MyObserver<NewsData>(){
                    @Override
                    public void onNext(NewsData data) {
                        mView.onNewsListSuccess(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onNewsListFail(e.getMessage());
                        Log.e("sssssssss",e.getMessage());
                    }
                });

    }

    @Override
    public void attachView(NewsContract.ChannelNewsPageView baseView) {
        mView = baseView;
    }
}
