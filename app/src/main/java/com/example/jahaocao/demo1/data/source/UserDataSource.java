package com.example.jahaocao.demo1.data.source;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.Info;
import com.example.jahaocao.demo1.data.Relevant;
import com.example.jahaocao.demo1.data.Titles;
import com.example.jahaocao.demo1.data.User;
import com.example.jahaocao.demo1.sou.Search;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;

/*
 * created by taofu on 2018/8/29
 **/
public interface UserDataSource {
   void login(RxFragment fragment, Map<String, String> params, Observer<User> observer);
   void sendVerificationCode(RxFragment fragment, Map<String, String> params, Observer<Object> observer);
   void getData(RxFragment fragment, Map<String, String> params, Observer<Datas> observer);
   void getTitle(RxFragment fragment, Observer<Titles> observer);
   void getAdd(RxFragment fragment,Map<String, String> params, Observer<Datass> observer);
   void getSearch(RxFragment fragment, Observer<Search> observer);

   void getInfo(RxAppCompatActivity activity, Map<String, String> params, Observer<Info> observer);
   void getComment(RxAppCompatActivity activity, Map<String, String> params, Observer<Comment> observer);
   void getAddComment(RxAppCompatActivity activity, Map<String, String> params, Observer<Object> observer);

   void getRelevant(RxAppCompatActivity activity, Map<String, String> params, Observer<List<Relevant>> observer);
}
