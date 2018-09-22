package com.example.jahaocao.demo1.info;

import com.example.jahaocao.demo1.base.BasePresenter;
import com.example.jahaocao.demo1.base.BaseView;
import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Info;
import com.example.jahaocao.demo1.data.Relevant;

import java.util.List;

public interface InfoContract  {
    interface View extends BaseView<InfoContract.Presenter>{
        void getInfo(Info info);
        void getRelevant(List<Relevant> relevant);
        void getComment(Comment relevant);
        void getAddComment(String relevant);
        void getChengGong(String s1);
    };
    interface Presenter extends BasePresenter<InfoContract.View> {
        void setInfo(String s1,String s2);
        void setRelevant(String s1);
        void setComment(String s1,String s2,String s3);
        void setAddComment(String userId,String objectId,String objectType,String content);
    };
}
