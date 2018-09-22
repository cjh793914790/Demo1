package com.example.jahaocao.demo1.data.source.remote.retrofit;

import com.example.jahaocao.demo1.data.ChannelData;
import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.HttpResult;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.Relevant;
import com.example.jahaocao.demo1.data.Titles;
import com.example.jahaocao.demo1.data.User;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.Info;
import com.example.jahaocao.demo1.sou.Search;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/*
 * created by taofu on 2018/8/29
 **/
public interface FirstgaService {
    @Headers("Content-Type:application/json")
    @POST("users/phoneLogin")
    Observable<HttpResult<User>> login(@Body Map<String, String> params);

    @Headers("Content-Type:application/json")
    @POST("users/sendVerificationCode")
    Observable<HttpResult<Object>> sendVerificationCode(@Body Map<String, String> params);

    @Headers("Content-Type:application/json")
    @POST("news/upListNews")
    Observable<HttpResult<Datas>> getData(@Body Map<String, String> params);

    @Headers("Content-Type:application/json")
    @POST("news/downListNews")
    Observable<HttpResult<Datass>> getAddData(@Body Map<String, String> params);

    @Headers("Content-Type:application/json")
    @POST("comment/listComment")
    Observable<HttpResult<Comment>> getComment(@Body Map<String, String> params);

   @Headers("Content-Type:application/json")
    @POST("users/comment")
    Observable<HttpResult<Object>> getCommentAdd(@Body Map<String, String> params);





    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("news/listNewsChannel")
    Observable<HttpResult<Titles>> Title();

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("search/hot")
    Observable<HttpResult<Search>> getSou();

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("news/info")
    Observable<HttpResult<Info>> getInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("news/relevant")
    Observable<HttpResult<List<Relevant>>> getRelevant(@FieldMap Map<String, String> params);




    @Headers(" application/json;charset=UTF-8")
    @POST("users/uploadHeadImage")
    @Multipart
    Observable<HttpResult<Object>> uploadAvatar(@Part List<MultipartBody.Part> partList);

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("news/listNewsChannel")
    Observable<HttpResult<ChannelData>> loadChannels();

    @Headers("Content-Type:application/json")
    @POST("news/upListNews")
    Observable<HttpResult<NewsData>> fetchNewNews(@Body Map<String, String> params);



}
