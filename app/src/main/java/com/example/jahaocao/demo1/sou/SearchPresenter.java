package com.example.jahaocao.demo1.sou;

import com.example.jahaocao.demo1.data.source.UserDataSource;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchPresenter implements SearchConstact.Presenter {

    private SearchConstact.View mView;
    private  UserDataSource source;

    public  SearchPresenter (UserDataSource source1){
        source = source1;
    }


    @Override
    public void setSearch() {
        source.getSearch((RxFragment) mView, new Observer<Search>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Search search) {

                mView.getSearch(search);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void attachView(SearchConstact.View baseView) {
        mView = baseView;

    }
}
