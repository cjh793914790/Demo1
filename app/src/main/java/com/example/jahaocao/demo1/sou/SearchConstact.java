package com.example.jahaocao.demo1.sou;

import com.example.jahaocao.demo1.base.BasePresenter;
import com.example.jahaocao.demo1.base.BaseView;

public interface SearchConstact {
    interface View extends BaseView<SearchConstact.Presenter>{
        void getSearch(Search search);
    }
    interface Presenter extends BasePresenter<SearchConstact.View> {
        void setSearch();

    }
}
