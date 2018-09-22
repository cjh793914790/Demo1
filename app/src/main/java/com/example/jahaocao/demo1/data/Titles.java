package com.example.jahaocao.demo1.data;

import java.util.List;

public class Titles {

    private List<NewsChannelList> newsChannelList;

    public Titles() {
    }

    public Titles(List<NewsChannelList> newsChannelList) {
        this.newsChannelList = newsChannelList;
    }

    public List<NewsChannelList> getNewsChannelList() {
        return newsChannelList;
    }

    public void setNewsChannelList(List<NewsChannelList> newsChannelList) {
        this.newsChannelList = newsChannelList;
    }

    @Override
    public String toString() {
        return "Titles{" +
                "newsChannelList=" + newsChannelList +
                '}';
    }

    public class NewsChannelList {
        private String channelId;
        private String channelName;

        public NewsChannelList(String channelId, String channelName) {
            this.channelId = channelId;
            this.channelName = channelName;
        }

        @Override
        public String toString() {
            return "NewsChannelList{" +
                    "channelId='" + channelId + '\'' +
                    ", channelName='" + channelName + '\'' +
                    '}';
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelId() {
            return channelId;
        }

        public String getChannelName() {
            return channelName;
        }
    }

}
