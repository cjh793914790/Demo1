package com.example.jahaocao.demo1.man.information;

import com.example.jahaocao.demo1.base.BasePresenter;
import com.example.jahaocao.demo1.base.BaseView;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.Titles;

public interface InformationContract {
    interface View extends BaseView<InformationContract.Presenter>{
        void getCodeSuccess();
        void getCodeFail();
        void getTitles(Titles titles);
        void getData(Datas datas);
        void getAdd(Datass datas);
    }
    interface Presenter extends BasePresenter<InformationContract.View> {
        void  setAdd(String s1,String s3);
        void setTitle();
        void setView(String s2);

    }
}
