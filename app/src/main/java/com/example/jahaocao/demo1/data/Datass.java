package com.example.jahaocao.demo1.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class Datass {
    private String cursor;
    private List<News> newList;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<News> getNewList() {
        return newList;
    }

    public void setNewList(List<News> newList) {
        this.newList = newList;
    }

    public static class NewListBean implements MultiItemEntity {
        /**
         * imageListThumb : ["http://www.lyunx.com/data/attachment/portal/201805/11/101758x39d9d9fi9ef9bip.jpg.thumb.jpg"]
         * isTop : 0
         * layoutType : 2
         * newsId : 7e71495cbe0e4b68bd36a1f53ea5d1bd
         * origin :
         * pageviews : 27
         * publishTime : 2018-05-10
         * title : 民航局简化和调整部分通用航空适航审定政策
         */

        private int isTop;
        private String layoutType;
        private String newsId;
        private String origin;
        private int pageviews;
        private String publishTime;
        private String title;
        private List<String> imageListThumb;

        public int getIsTop() {
            return isTop;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }

        public String getLayoutType() {
            return layoutType;
        }

        public void setLayoutType(String layoutType) {
            this.layoutType = layoutType;
        }

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getPageviews() {
            return pageviews;
        }

        public void setPageviews(int pageviews) {
            this.pageviews = pageviews;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImageListThumb() {
            return imageListThumb;
        }

        public void setImageListThumb(List<String> imageListThumb) {
            this.imageListThumb = imageListThumb;
        }

        public static final int IMGGG = 0;
        public static final int TEXT = 1;
        public static final int IMG = 2;
        public static final int IMGG = 3;
        private int itemType;

        public NewListBean(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            if ("0".equals(layoutType)) {
                itemType = 0;
                return itemType;
            } else if ("1".equals(layoutType)) {
                itemType = 1;
                return itemType;
            } else if ("2".equals(layoutType)) {
                itemType = 2;
                return itemType;
            } else {
                itemType = 3;
                return itemType;
            }

        }
    }
}
