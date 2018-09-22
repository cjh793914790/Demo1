package com.example.jahaocao.demo1.sou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.base.BaseActivity;
import com.example.jahaocao.demo1.data.source.UserDataRepository;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchPresenter searchPresenter = new SearchPresenter(UserDataRepository.getInstance());
        addFragment(SearchFragment.class,searchPresenter,R.id.login_containers,null,null);
    }
}
