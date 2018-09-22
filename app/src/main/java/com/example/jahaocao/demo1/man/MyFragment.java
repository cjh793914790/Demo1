package com.example.jahaocao.demo1.man;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }

}
