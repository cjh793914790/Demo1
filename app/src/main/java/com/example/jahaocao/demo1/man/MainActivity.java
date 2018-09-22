package com.example.jahaocao.demo1.man;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.base.BaseActivity;
import com.example.jahaocao.demo1.data.source.UserDataRepository;
import com.example.jahaocao.demo1.data.source.UserDataSource;
import com.example.jahaocao.demo1.login.LoginFragment;
import com.example.jahaocao.demo1.login.LoginPresenter;
import com.example.jahaocao.demo1.man.information.InformationFragment;
import com.example.jahaocao.demo1.man.information.InformationPresenter;

public class MainActivity extends BaseActivity  {

    private FrameLayout news_content;
    private RadioButton news_but_chicke;
    private RadioGroup rg1;
    private RadioButton topic_but_chicke;
    private RadioButton me_but_chicke;
    private FragmentManager manager;
    private InformationFragment aFragment ;
    private TopicFragment bFragment ;
    private MyFragment cFragment ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //操作Fragment
        manager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        news_content = (FrameLayout) findViewById(R.id.news_content);
        // news_content.setOnClickListener(this);
        news_but_chicke = (RadioButton) findViewById(R.id.news_but_chicke);
        // news_but_chicke.setOnClickListener(this);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        //rg1.setOnClickListener(this);
        topic_but_chicke = (RadioButton) findViewById(R.id.topic_but_chicke);
        //topic_but_chicke.setOnClickListener(this);
        me_but_chicke = (RadioButton) findViewById(R.id.me_but_chicke);
        //  me_but_chicke.setOnClickListener(this);
        setClick(0);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.news_but_chicke:
                        setClick(0);
                        break;
                    case R.id.topic_but_chicke:
                        setClick(1);
                        break;
                    case R.id.me_but_chicke:
                        setClick(2);
                        break;

                }
            }
        });
    }

    private void setClick(int type) {
//操作Fragment
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        switch (type){
            case 0 :
                InformationPresenter informationPresenter = new InformationPresenter(UserDataRepository.getInstance());
                addFragment(InformationFragment.class, informationPresenter, R.id.news_content, null, null);
            case 1 :
                if (bFragment==null){
                    bFragment = new TopicFragment();
                    transaction.add(R.id.news_content,bFragment);
                }else {
                    transaction.show(bFragment);
                }
                break;
            case 2 :
                if (cFragment==null){
                    cFragment = new MyFragment();
                    transaction.add(R.id.news_content,cFragment);
                }else {
                    transaction.show(cFragment);
                }
                break;

        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (aFragment != null){
            transaction.hide(aFragment);
        }
        if (bFragment != null){
            transaction.hide(bFragment);
        }
        if (cFragment != null){
            transaction.hide(cFragment);
        }
    }

}

