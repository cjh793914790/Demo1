package com.example.jahaocao.demo1.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.data.News;

import java.util.List;

public class TitleAdapter extends BaseMultiItemQuickAdapter<News, BaseViewHolder> {

    private RequestOptions options;

    public TitleAdapter(List data) {
        super(data);
        addItemType(0, R.layout.title_view);
        addItemType(1, R.layout.title_view3);
        addItemType(2, R.layout.zixun_item_bottom_big_photo);
        addItemType(3, R.layout.title_view2);

    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.mTextView1_view,item.getTitle());
                helper.setText(R.id.mTextView2_view,item.getOrigin()+item.getPageviews()+"跟帖   时间 "+item.getPublishTime());
                break;
            case 1:
                 options = new RequestOptions()
                        .placeholder(R.drawable.tx_st_h)	//加载成功之前占位图
                        .error(R.drawable.tx_st_h)	//加载错误之后的错误图
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
                helper.setText(R.id.mTextView1_view3,item.getTitle());
                helper.setText(R.id.mTextView2_view3,item.getOrigin()+item.getPageviews()+"跟帖   时间 "+item.getPublishTime());
                Glide.with(mContext).load(item.getImageListThumb().get(0)).apply(options).into((ImageView) helper.getView(R.id.mImageView1_view3));
                break;
            case 2:
                 options = new RequestOptions()
                        .placeholder(R.drawable.tx_st_h)	//加载成功之前占位图
                        .error(R.drawable.tx_st_h)	//加载错误之后的错误图
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
                helper.setText(R.id.title1,item.getTitle());
                Glide.with(mContext).load(item.getImageListThumb().get(0)).apply(options).into((ImageView) helper.getView(R.id.img1));
                break;
                case 3:
                    options = new RequestOptions()
                            .placeholder(R.drawable.tx_st_h)	//加载成功之前占位图
                            .error(R.drawable.tx_st_h)	//加载错误之后的错误图
                            //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                            .fitCenter()
                            //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
                            .centerCrop()
                            //.circleCrop()//指定图片的缩放类型为centerCrop （圆形）
                            .skipMemoryCache(true)	//跳过内存缓存
                            .diskCacheStrategy(DiskCacheStrategy.ALL)	//缓存所有版本的图像
                            .diskCacheStrategy(DiskCacheStrategy.NONE)	//跳过磁盘缓存
                            .diskCacheStrategy(DiskCacheStrategy.DATA)	//只缓存原来分辨率的图片
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                    helper.setText(R.id.mTextView1_view2,item.getTitle());
                    Glide.with(mContext).load(item.getImageListThumb().get(0)).apply(options).into((ImageView) helper.getView(R.id.mImageView1_view2));
                    Glide.with(mContext).load(item.getImageListThumb().get(0)).apply(options).into((ImageView) helper.getView(R.id.mImageView2_view2));
                    Glide.with(mContext).load(item.getImageListThumb().get(0)).apply(options).into((ImageView) helper.getView(R.id.mImageView3_view2));
                    break;
        }
    }


}



