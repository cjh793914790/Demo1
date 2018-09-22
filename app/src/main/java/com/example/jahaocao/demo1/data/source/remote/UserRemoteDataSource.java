package com.example.jahaocao.demo1.data.source.remote;


import android.support.v7.app.AppCompatActivity;

import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.HttpResult;
import com.example.jahaocao.demo1.data.Info;
import com.example.jahaocao.demo1.data.Relevant;
import com.example.jahaocao.demo1.data.Titles;
import com.example.jahaocao.demo1.data.User;
import com.example.jahaocao.demo1.data.source.UserDataSource;
import com.example.jahaocao.demo1.data.source.remote.retrofit.DataRetrofit;
import com.example.jahaocao.demo1.data.source.remote.retrofit.FirstgaService;
import com.example.jahaocao.demo1.exception.ServerException;
import com.example.jahaocao.demo1.sou.Search;
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

public class UserRemoteDataSource implements UserDataSource {

    private FirstgaService service;


    public UserRemoteDataSource() {
        service = DataRetrofit.getRetrofitService();

    }




    @Override
    public void sendVerificationCode(RxFragment fragment, Map<String, String> params, Observer<Object> observer) {
        Observable<HttpResult<Object>> observable = service.sendVerificationCode(params);

        observable.flatMap(
                new Function<HttpResult<Object>, ObservableSource<Object>>() {
                    @Override
                    public ObservableSource<Object> apply(HttpResult<Object> userHttpResult) throws Exception {

                        if (userHttpResult.getCode() == 0) {
                            return Observable.just(new Object());
                        }
                        return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
                    }
                }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<Object>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);

    }

    @Override
    public void getData(RxFragment fragment, Map<String, String> params, Observer<Datas> observer) {
        Observable<HttpResult<Datas>> observable = service.getData(params);

        observable.flatMap(new Function<HttpResult<Datas>, ObservableSource<Datas>>() {
            @Override
            public ObservableSource<Datas> apply(HttpResult<Datas> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<Datas>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);

    }

    @Override
    public void getTitle(RxFragment fragment, Observer<Titles> observer) {
        Observable<HttpResult<Titles>> observable = service.Title();

        observable.flatMap(new Function<HttpResult<Titles>, ObservableSource<Titles>>() {
            @Override
            public ObservableSource<Titles> apply(HttpResult<Titles> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<Titles>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);
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
    public void getSearch(RxFragment fragment, Observer<Search> observer) {
        Observable<HttpResult<Search>> observable = service.getSou();

        observable.flatMap(new Function<HttpResult<Search>, ObservableSource<Search>>() {
            @Override
            public ObservableSource<Search> apply(HttpResult<Search> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<Search>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);

    }

    @Override
    public void getInfo(RxAppCompatActivity fragment, Map<String, String> params, Observer<Info> observer) {
        Observable<HttpResult<Info>> observable = service.getInfo(params);

        observable.flatMap(new Function<HttpResult<Info>, ObservableSource<Info>>() {
            @Override
            public ObservableSource<Info> apply(HttpResult<Info> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }

    @Override
    public void getComment(RxAppCompatActivity activity, Map<String, String> params, Observer<Comment> observer) {
        Observable<HttpResult<Comment>> observable = service.getComment(params);
        observable.flatMap(new Function<HttpResult<Comment>, ObservableSource<Comment>>() {
            @Override
            public ObservableSource<Comment> apply(HttpResult<Comment> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getAddComment(RxAppCompatActivity activity, Map<String, String> params, Observer<Object> observer) {
        Observable<HttpResult<Object>> observable = service.getCommentAdd(params);
        observable.flatMap(new Function<HttpResult<Object>, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(HttpResult<Object> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getRelevant(RxAppCompatActivity activity, Map<String, String> params, Observer<List<Relevant>> observer) {
        Observable<HttpResult<List<Relevant>>> relevant = service.getRelevant(params);
        relevant.flatMap(new Function<HttpResult<List<Relevant>>, ObservableSource<List<Relevant>>>() {
            @Override
            public ObservableSource<List<Relevant>> apply(HttpResult<List<Relevant>> listHttpResult) throws Exception {
                if (listHttpResult.getCode()==0){
                    return Observable.just(listHttpResult.getData());
                }
                return Observable.error(new ServerException(listHttpResult.getCode(),listHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    @Override
    public void login(RxFragment fragment, Map<String, String> params, Observer<User> observer) {
        Observable<HttpResult<User>> observable = service.login(params);

        observable.flatMap(new Function<HttpResult<User>, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(HttpResult<User> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<User>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);

    }

}

