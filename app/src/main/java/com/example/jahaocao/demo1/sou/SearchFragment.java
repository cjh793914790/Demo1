package com.example.jahaocao.demo1.sou;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.base.BaseFragment;
import com.example.jahaocao.demo1.data.source.UserDataRepository;
import com.example.jahaocao.demo1.man.MainActivity;

public class SearchFragment extends BaseFragment implements SearchConstact.View {

    private SearchView sv;
    private TextView mQxx;
    private ListView lv;
    private SearchConstact.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new SearchPresenter(UserDataRepository.getInstance()));
        View view = View.inflate(getContext(), R.layout.activity_sou, null);
        mPresenter.setSearch();
        sv =  view.findViewById(R.id.sv);
        mQxx = view.findViewById(R.id.mQxx);
        mQxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "娃哈哈", Toast.LENGTH_SHORT).show();
            }
        });
        lv =  view.findViewById(R.id.lv);
        String [] mString={"aaa","bbb","ccc"};
        lv.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,mString));
        //设置ListView使用过滤
        lv.setTextFilterEnabled(true);
        //设置刻SearchView默认是否自动缩小为图标
        sv.setIconifiedByDefault(false);
        //设置刻SearchView心事搜索按钮
        sv.setSubmitButtonEnabled(true);
        //设置刻SearchView默认显示的文本
        sv.setQueryHint("查找");
        //为刻SearchView组件设置监听事件
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮激发刻方法
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(getContext(), "你的选择是"+query, Toast.LENGTH_SHORT).show();

                return false;
            }
            //用户输入字符激发刻方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){

                    lv.clearTextFilter();
                }else {
                    lv.setFilterText(newText);
                }

                return true;
            }
        });

        return view;
    }

    @Override
    public void getSearch(Search search) {
        Toast.makeText(getContext(), search.getSearchList().get(0).getContent() , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SearchConstact.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }
}
