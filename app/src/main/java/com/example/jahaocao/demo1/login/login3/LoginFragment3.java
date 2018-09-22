package com.example.jahaocao.demo1.login.login3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jahaocao.demo1.man.MainActivity;
import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.base.BaseFragment;
import com.example.jahaocao.demo1.login.LogActivity;
import com.example.jahaocao.demo1.login.login2.Login2Presenter;
import com.example.jahaocao.demo1.login.login2.LoginFragment2;


public class LoginFragment3 extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login3, container, false);
        Button mButton_qrdl = view.findViewById(R.id.mButton_qrdl);
        Button mButton_qx = view.findViewById(R.id.mBt_Qx);
        mButton_qrdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();

            }
        });
        mButton_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login2Presenter presenter=new Login2Presenter();
                ((LogActivity)getActivity()).addFragment(LoginFragment2.class,presenter,R.id.login_container,null,null);


            }
        });
        return view;
    }
}
