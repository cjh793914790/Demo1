package com.example.jahaocao.demo1.base;

import android.content.Context;
import com.trello.rxlifecycle2.components.support.RxFragment;

public class BaseFragment extends RxFragment {

    protected boolean isNeedAddToBackStack() {
        return true;
    }

    protected boolean isNeedHidePreFragment() {
        return true;
    }

    protected int getEnterAnimId() {
        return 0;
    }

    protected int getPreExistAnimId() {
        return 0;
    }

    protected int getPopExistAnimId() {
        return 0;
    }

    protected int getPopPreEnterAnimId() {
        return 0;
    }
}
