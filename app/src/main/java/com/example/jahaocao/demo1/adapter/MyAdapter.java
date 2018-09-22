package com.example.jahaocao.demo1.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.Relevant;

import java.util.Date;
import java.util.List;

public class MyAdapter extends BaseQuickAdapter<Comment.CommentListBean, BaseViewHolder> {
    public MyAdapter(int layoutResId, List data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, Comment.CommentListBean item) {
    RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.tx_st_h)	//加载成功之前占位图
            .error(R.drawable.tx_st_h)	//加载错误之后的错误图
            .override(400,400)	//指定图片的尺寸
            //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
            .fitCenter()
            //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
            .centerCrop()
            //.circleCrop()//指定图片的缩放类型为centerCrop （圆形）
            .skipMemoryCache(true)	//跳过内存缓存
            .diskCacheStrategy(DiskCacheStrategy.ALL)	//缓存所有版本的图像
            .diskCacheStrategy(DiskCacheStrategy.NONE)	//跳过磁盘缓存
            .diskCacheStrategy(DiskCacheStrategy.DATA)	//只缓存原来分辨率的图片
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE);	//只缓存最终的图片
    Glide.with(mContext).load(item.getHeadImagePath())
            .apply(options)
            .into((ImageView) helper.getView(R.id.imageView4));
    helper.setText(R.id.textView13, item.getContent()+"");
    helper.setText(R.id.textView10, item.getCommentTime()+"");

}

}
