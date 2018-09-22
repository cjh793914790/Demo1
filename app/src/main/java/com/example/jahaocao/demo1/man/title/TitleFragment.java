package com.example.jahaocao.demo1.man.title;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.adapter.TitleAdapter;
import com.example.jahaocao.demo1.base.BaseFragment;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.News;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.source.NewsDataRepository2;
import com.example.jahaocao.demo1.data.news.NewsLocalDataSource2;
import com.example.jahaocao.demo1.data.source.remote.NewsRemoteDataSource2;
import com.example.jahaocao.demo1.info.InfoActivity;
import com.example.jahaocao.demo1.utils.IoSetGet;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class TitleFragment extends BaseFragment implements NewsContract.ChannelNewsPageView {
    private String mChannelId;
    private String mChannelName;
    private int position = 0;
    private int scorllY = 0;
    private NewsContract.ChannelNewPagePresenter mPresenter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private TitleAdapter titleAdapter;

    @SuppressLint("ValidFragment")
    public TitleFragment(String mChannelId,String mChannelName) {
        this.mChannelId = mChannelId;
        this.mChannelName = mChannelName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new ChannelNewsPagePresenter(NewsDataRepository2.getInstance(NewsRemoteDataSource2.getInstance(), NewsLocalDataSource2.getInstance(getContext()))));
        Log.e("sada",mChannelId);
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView1);
        mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.mrefreshLayout1);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getNewsByChannel(mChannelId,"0",getContext());
    }

    private void getItemPositionAndScrollY(){
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int fistVP = linearLayoutManager.findFirstVisibleItemPosition();
        View v = linearLayoutManager.findViewByPosition(fistVP);
        if(v != null){
            position = fistVP;
            scorllY = v.getTop();
        }

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        getItemPositionAndScrollY();
        outState.putInt("position", position);
        outState.putInt("scrollY", scorllY);


    }
    private List<News> list =new ArrayList<>();
    private List<News> lists =new ArrayList<>();
    @Override
    public void onNewsListSuccess(final NewsData news) {
        for(News newss : news.getNewList()){
            if(newss.getLayoutType() == 1){
                ArrayList arrayList = new ArrayList();
                arrayList.add(newss.getImageListThumb().get(0));
                arrayList.add(newss.getImageListThumb().get(0));
                arrayList.add(newss.getImageListThumb().get(0));
                newss.setLayoutType(3);
                newss.setImageListThumb(arrayList);
                break;
            }
        }

        final List<News> newList = news.getNewList();
        Log.e("sssssssssss",newList.size()+"");
        if (newList!=null){
        if (newList.size()<=7){
            for (int i = 0; i <newList.size(); i++) {
                lists.add(newList.get(i));

            }
        }else {
            for (int i = 0; i < 7; i++) {
                lists.add(newList.get(i));

            }
        }}else {
            Toast.makeText(getContext(), "搜索", Toast.LENGTH_SHORT).show();
        }

        titleAdapter = new TitleAdapter(lists);
        mRecyclerView.setAdapter(titleAdapter);
        titleAdapter.notifyDataSetChanged();
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getAdd(mChannelId,news.getMaxCursor());

                refreshlayout.finishLoadmore(2000/*,false*/ );//传入false表示加载失败
                lists.addAll(list);
                Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                titleAdapter.notifyDataSetChanged();
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();

                mPresenter.getAdd(mChannelId,news.getMaxCursor());
                lists.clear();
                lists.addAll(list);
                Log.e("!!!!!!!!!",news.getMinCursor());
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                titleAdapter.notifyDataSetChanged();
            }
        });
        titleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                String newsId = lists.get(position).getNewsId();
                intent.putExtra("NewsId",newsId);
                startActivity(intent);

            }
        });

    }
    private void scrollToTargetPosition(){
        if(position != 0 && scorllY != 0){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            linearLayoutManager.scrollToPositionWithOffset(position, scorllY);
        }
    }
    @Override
    public void onNewsListFail(String msg) {

    }

    @Override
    public void setAdd(Datass datass) {
        List<News> newList = datass.getNewList();
        list.addAll(newList);
    }

    @Override
    public void setPresenter(NewsContract.ChannelNewPagePresenter presenter) {
        mPresenter =presenter;
        mPresenter.attachView(this);

    }


}