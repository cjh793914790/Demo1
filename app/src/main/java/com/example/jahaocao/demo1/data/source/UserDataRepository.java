package com.example.jahaocao.demo1.data.source;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.Info;
import com.example.jahaocao.demo1.data.Relevant;
import com.example.jahaocao.demo1.data.Titles;
import com.example.jahaocao.demo1.data.User;
import com.example.jahaocao.demo1.data.source.remote.UserRemoteDataSource;
import com.example.jahaocao.demo1.sou.Search;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.internal.Preconditions;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;

public class UserDataRepository  implements UserDataSource {


    @NonNull
    private UserDataSource mRemoteDataSource;

    @Nullable
    private static UserDataRepository INSTANCE = null;

    private UserDataRepository(@NonNull UserDataSource remoteDataSource){
        mRemoteDataSource = Preconditions.checkNotNull(remoteDataSource, "Login remote data source is not allowed null");
    }


    public static UserDataRepository getInstance(){

        if(INSTANCE == null){
            synchronized (UserDataRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new UserDataRepository(new UserRemoteDataSource());
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void login(RxFragment fragment, Map<String, String> params, Observer<User> observer) {

        mRemoteDataSource.login(fragment, params,observer);
    }

    @Override
    public void sendVerificationCode(RxFragment fragment, Map<String, String> params, Observer<Object> observer) {

        mRemoteDataSource.sendVerificationCode(fragment, params, observer);
    }

    @Override
    public void getData(RxFragment fragment, Map<String, String> params, Observer<Datas> observer) {
        mRemoteDataSource.getData(fragment, params, observer);

    }

    @Override
    public void getTitle(RxFragment fragment, Observer<Titles> observer) {
        mRemoteDataSource.getTitle(fragment, observer);

    }

    @Override
    public void getAdd(RxFragment fragment, Map<String, String> params, Observer<Datass> observer) {
        mRemoteDataSource.getAdd(fragment, params,observer);

    }

    @Override
    public void getSearch(RxFragment fragment, Observer<Search> observer) {
        mRemoteDataSource.getSearch(fragment,observer);
    }

    @Override
    public void getInfo(RxAppCompatActivity fragment, Map<String, String> params, Observer<Info> observer) {
        mRemoteDataSource.getInfo(fragment,params,observer);
    }

    @Override
    public void getComment(RxAppCompatActivity activity, Map<String, String> params, Observer<Comment> observer) {
        mRemoteDataSource.getComment(activity,params,observer);
    }

    @Override
    public void getAddComment(RxAppCompatActivity activity, Map<String, String> params, Observer<Object> observer) {
        mRemoteDataSource.getAddComment(activity,params,observer);
    }

    @Override
    public void getRelevant(RxAppCompatActivity activity, Map<String, String> params, Observer<List<Relevant>> observer) {
        mRemoteDataSource.getRelevant(activity,params,observer);
    }


}

