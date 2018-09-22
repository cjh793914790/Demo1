package com.example.jahaocao.demo1.man.title;


import android.content.Context;

import com.example.jahaocao.demo1.base.BasePresenter;
import com.example.jahaocao.demo1.base.BaseView;
import com.example.jahaocao.demo1.data.Channel;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.Titles;

import java.util.List;

public interface NewsContract {
    public static final String PARAMS_CHANNER_ID = "channelId";


    public interface NewsView extends BaseView<NewsPresenter> {

        void onChannelsSuccess(List<Channel> channels);
        void onChannelsFail(String msg);

    }


    public interface NewsPresenter extends BasePresenter<NewsView> {
        void getChannels();
    }

    public interface ChannelNewsPageView extends  BaseView<ChannelNewPagePresenter>{

        void onNewsListSuccess(NewsData news);
        void onNewsListFail(String msg);
        void setAdd(Datass datass);
    }

    public interface ChannelNewPagePresenter extends BasePresenter<ChannelNewsPageView>{

        void getAdd(String channelId,String Cursor);
        void getNewsByChannel(String channelId, String Cursor, Context context);
    }
}
