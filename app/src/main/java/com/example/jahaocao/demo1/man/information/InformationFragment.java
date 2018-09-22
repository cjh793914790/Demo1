package com.example.jahaocao.demo1.man.information;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.adapter.FragmentAdapter;
import com.example.jahaocao.demo1.base.BaseFragment;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.Titles;
import com.example.jahaocao.demo1.data.source.UserDataRepository;
import com.example.jahaocao.demo1.login.LogActivity;
import com.example.jahaocao.demo1.login.login2.Login2Presenter;
import com.example.jahaocao.demo1.login.login2.LoginFragment2;
import com.example.jahaocao.demo1.man.MainActivity;
import com.example.jahaocao.demo1.man.title.TitleFragment;
import com.example.jahaocao.demo1.sou.SearchActivity;
import com.example.jahaocao.demo1.sou.SearchConstact;
import com.example.jahaocao.demo1.sou.SearchFragment;
import com.example.jahaocao.demo1.sou.SearchPresenter;

import java.util.ArrayList;
import java.util.List;



public class InformationFragment extends BaseFragment implements InformationContract.View{

    private ImageView add;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private InformationContract.Presenter mPresenter;
    private List<String> mName=new ArrayList<>();
    private List<Fragment> mFragment=new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    public List<Titles.NewsChannelList> newsChannelList;
    private TitleFragment titleFragment;
    private Toolbar mToolbar1;
    private ImageView mSou;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        mPresenter.getTitle();


        setPresenter(new InformationPresenter(UserDataRepository.getInstance()));
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        mPresenter.setTitle();
        mTabLayout = view.findViewById(R.id.mTableLayout);
        mViewPager = view.findViewById(R.id.mViewPager);
        mToolbar1 = view.findViewById(R.id.mToolbar1);
        mSou = view.findViewById(R.id.mSou);
        add = view.findViewById(R.id.Add);
        initAdd();
        initToolbar();
        return view;
    }

    private void initToolbar() {
        mSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAdd() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "我是可以用的", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getCodeSuccess() {

    }

    @Override
    public void getCodeFail() {

    }

    @Override
    public void getTitles(Titles titles) {
        newsChannelList = titles.getNewsChannelList();
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),newsChannelList);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void getData(Datas datas) {

    }

    @Override
    public void getAdd(Datass datas) {

    }

    @Override
    public void setPresenter(InformationContract.Presenter presenter) {

        mPresenter = presenter;
        mPresenter.attachView(this);
    }

}
