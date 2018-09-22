package com.example.jahaocao.demo1.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.example.jahaocao.demo1.data.News;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.Titles;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IoSetGet {
    public static File getDiskCacheDir(Context mContext, String urlid) { //获取手机SD卡缓存文件对象
        File cachePath;
        File cacheid ;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            cachePath = mContext.getExternalCacheDir();
        else {
            cachePath = mContext.getCacheDir();
        }
        if (cachePath!=null&&cachePath.exists()){
            cacheid=new File(cachePath,urlid);
            return cacheid;
        }else {
            return null;
        }

    }
    public NewsData getSdCache(File file) {  //取
        if (file==null){
            return null;
        }
        FileInputStream fileInputStream = null;
        StringBuffer sb = new StringBuffer();
        try {
            fileInputStream = new FileInputStream(file);
            byte[] b=new byte[1024];
            int len=0;
            while ((len=fileInputStream.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            String s = sb.toString();
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                NewsData listNewNr = gson.fromJson(s, NewsData.class);
                return listNewNr;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void setSdCache(NewsData lr,File file)  { //存
        if (file==null){
            return;
        }
        Gson gson = new Gson();
        String s= gson.toJson(lr);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file,false);
            fileOutputStream.write(s.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileOutputStream!=null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Titles getTitle(File file) {  //取
        if (file==null){
            return null;
        }
        FileInputStream fileInputStream = null;
        StringBuffer sb = new StringBuffer();
        try {
            fileInputStream = new FileInputStream(file);
            byte[] b=new byte[1024];
            int len=0;
            while ((len=fileInputStream.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            String s = sb.toString();
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                Titles titles = gson.fromJson(s, Titles.class);
                return titles;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void setTitle(Titles lr, File file)  { //存
        if (file==null){
            return;
        }
        Gson gson = new Gson();
        String s= gson.toJson(lr);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file,false);
            fileOutputStream.write(s.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileOutputStream!=null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
