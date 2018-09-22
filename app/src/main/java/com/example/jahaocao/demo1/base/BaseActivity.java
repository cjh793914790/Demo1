package com.example.jahaocao.demo1.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

/*
 * created by taofu on 2018/8/29
 **/
public abstract  class BaseActivity extends RxAppCompatActivity {

    protected FragmentManager mFragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {   //判断Sdk版本
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags);
            //修改状态栏为透明色
        }
    }


    public <T extends  BaseFragment> void addFragment(@NonNull Class<T> tClass, BasePresenter presenter,int containerId, String tag, Bundle args)  {

        if(TextUtils.isEmpty(tag)){
            tag = tClass.getName();
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment addedFragment = mFragmentManager.findFragmentByTag(tag);

        BaseFragment targetFragment = null;

        if(addedFragment == null){
            try {
                targetFragment = tClass.newInstance();

                if(targetFragment instanceof BaseView){
                    ((BaseView) targetFragment).setPresenter(presenter);

                }
                fragmentTransaction.add(containerId,targetFragment, tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

            targetFragment = (BaseFragment) addedFragment;
            if(targetFragment.isHidden()){
                addFragmentAnimation(fragmentTransaction, targetFragment);
            }
            fragmentTransaction.show(targetFragment);
        }

        if(targetFragment != null){

            addFragmentAnimation(fragmentTransaction,  targetFragment);
            hidePreFragment(fragmentTransaction,  targetFragment);

            if( targetFragment.isNeedAddToBackStack()){
                fragmentTransaction.addToBackStack(tag);
            }
            fragmentTransaction.commit();
        }
    }



    private void addFragmentAnimation(FragmentTransaction transaction,BaseFragment baseFragment){

        transaction.setCustomAnimations(baseFragment.getEnterAnimId(), baseFragment.getPreExistAnimId() ,baseFragment.getPopPreEnterAnimId(),baseFragment.getPopExistAnimId());
    }

    private void hidePreFragment(FragmentTransaction transaction,BaseFragment baseFragment){
        if(baseFragment.isNeedHidePreFragment()){

            List<Fragment> addedFragments = mFragmentManager.getFragments();

            for(Fragment fragment: addedFragments){
                if(fragment != baseFragment){
                    transaction.hide(fragment);
                }
            }

        }
    }
}
