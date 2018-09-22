package com.example.jahaocao.demo1.info;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Barrier;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.StatusBarManager;
import com.example.jahaocao.demo1.adapter.MyAdapter;
import com.example.jahaocao.demo1.adapter.RunnableAdapter;
import com.example.jahaocao.demo1.base.BaseActivity;
import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Info;
import com.example.jahaocao.demo1.data.Relevant;
import com.example.jahaocao.demo1.data.source.UserDataRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends BaseActivity implements InfoContract.View {


    private InfoContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private WebView mWebView;
    private Toolbar tb;
    private TextView title;
    private Barrier barrier3;
    private ImageView btn_shoucang;
    private TextView tv_time;
    private RadioButton fenxiang;
    private RadioButton pinglun;
    private RadioButton shoucang;
    private RadioGroup radioGroup2;
    private WebView webView;
    private TextView mTextView;
    private View view1;
    private TextView textview_address;
    private TextView tv_title;
    private TextView tv_address;
    private TextView textView;
    private ImageView btn_hen_zhuan_ye;
    private View view2;
    private TextView textView_correlation_news;
    private View view3;
    private RecyclerView recyc_xiangguan;
    private TextView textView_gentie;
    private RecyclerView recyc_remen;
    private ImageView img_read_more_gentie;
    private ScrollView scrollView;
    private List<Comment.CommentListBean> lits=new ArrayList<>();
    private RunnableAdapter runnableAdapter;
    private ImageView mDian3;
    private  int a=2;
    private  int s=0;
    private MyAdapter myAdapter;
    private ImageView mXgd;
    private ImageView mFx;
    private ImageView mSc;
    private ImageView mXx;
    private List<Comment.CommentListBean> commentList;
    private ImageView mFhh;
    private ImageView mDian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        StatusBarManager.lightStatusBar(this);
        StatusBarManager.setStatusBarColor(this, ContextCompat.getColor(this,R.color.colorBs));

        setPresenter(new InfoPresenter(UserDataRepository.getInstance()));
        initView();
        initToobar();
        Intent intent = getIntent();
        String name = intent.getStringExtra("NewsId");
        Log.e("@@@@@@@@@", name);
        mPresenter.setInfo(name, "d56ea66e7ee741f498ca51242c8c6394");
        mPresenter.setRelevant(name);
        mPresenter.setComment("d56ea66e7ee741f498ca51242c8c6394","0","0");
        initBottom();

    }

    private void initToobar() {
        mFhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initBottom() {
        mXgd.setOnClickListener(new View.OnClickListener() {

            private PopupWindow window;
            private EditText ppp_neirong;
            private String ss;

            @Override
            public void onClick(View v) {
                window = new PopupWindow();
                View inflate = getLayoutInflater().inflate(R.layout.popupwindow, null);
                //  创建PopupWindow对象，指定宽度和高度
                window = new PopupWindow(inflate,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                final PopupWindow finalWindow = window;
                //给内部控件添加点击事件
                TextView ppp_quxiao = inflate.findViewById(R.id.ppp_quxiao);
                TextView ppp_fabu = inflate.findViewById(R.id.ppp_fabu);
                ppp_neirong = inflate.findViewById(R.id.ppp_neirong);
                ppp_neirong.setFocusable(true);
                ppp_neirong.setFocusableInTouchMode(true);
                ppp_neirong.requestFocus();
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                ppp_fabu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ss = ppp_neirong.getText().toString();
                        mPresenter.setAddComment("d56ea66e7ee741f498ca51242c8c6394","d56ea66e7ee741f498ca51242c8c6394","0", ss);
                        window.dismiss();
                    }
                });
                ppp_quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });
                // TODO:  设置背景颜色
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BBBBBC")));
                // TODO:  设置可以获取焦点
                window.setFocusable(true);
                // TODO:  设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);
                // TODO：更新popupwindow的状态
                //  window.update();
                // TODO:  以下拉的方式显示，并且可以设置显示的位置

                window.showAtLocation(inflate, Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);

            }

        });
        mXx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mFx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }


    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        Log.d("VACK", doc.toString());
        return doc.toString();
    }


    @Override
    public void getInfo(Info info) {

        WebSettings settings = mWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String html = "<html><header>" + "</header>" + info.getContent() + "</body><ml>";
        mWebView.loadDataWithBaseURL(null, getNewContent(html), "text/html", "utf-8", null);
        tv_time.setText(info.getTitle());
        mTextView.setText(info.getPublishTime());

    }

    @Override
    public void getRelevant(List<Relevant> relevant) {
        List<Relevant> list=new ArrayList<>();
        list.addAll(relevant);
        runnableAdapter = new RunnableAdapter(R.layout.runnable_itim, list);
        recyc_xiangguan.setAdapter(runnableAdapter);
        Log.e("!!!!!!!!",relevant.get(0).getTitle());
        runnableAdapter.notifyDataSetChanged();

    }
    @Override
    public void getComment(Comment relevant) {
        final List<Comment.CommentListBean> commentList = relevant.getCommentList();
        if (commentList!=null) {
            for (int i = 0; i < 2; i++) {
                lits.add(commentList.get(i));
            }
            myAdapter = new MyAdapter(R.layout.dapter_itim, lits);
            recyc_remen.setAdapter(myAdapter);
        }

        img_read_more_gentie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               s+=a;
               Log.e("s1",s+"");
                if (commentList!=null) {
                    for (int i = s; i <commentList.size(); i++) {
                        if (i==s+2) {
                           return;
                        }else if (i==commentList.size()){
                           return;
                        }else {
                            lits.add(commentList.get(i));
                            myAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }
        });

    }

    @Override
    public void getAddComment(String relevant) {
        lits.clear();
        mPresenter.setComment("d56ea66e7ee741f498ca51242c8c6394","0","0");
    }

    @Override
    public void getChengGong(String s1) {

    }

    private void initView() {
        recyc_xiangguan = (RecyclerView) findViewById(R.id.recyc_xiangguan);
        recyc_xiangguan.setNestedScrollingEnabled(true);
        recyc_xiangguan.setLayoutManager(new LinearLayoutManager(this));
        mWebView = findViewById(R.id.webView);
        tv_time = (TextView) findViewById(R.id.tv_title_x);
        mTextView = findViewById(R.id.tv_time);
        recyc_remen = (RecyclerView) findViewById(R.id.recyc_remen);
        recyc_remen.setNestedScrollingEnabled(true);
        btn_hen_zhuan_ye = (ImageView) findViewById(R.id.btn_shoucang);
        recyc_remen.setLayoutManager(new LinearLayoutManager(this));
        img_read_more_gentie = (ImageView) findViewById(R.id.img_read_more_gentie);
        mXgd = (ImageView) findViewById(R.id.mXgd);
        mFx = (ImageView) findViewById(R.id.mFx);
        mSc = (ImageView) findViewById(R.id.mSc);
        mXx = (ImageView) findViewById(R.id.mXx);
        mFhh = (ImageView) findViewById(R.id.mFhh);
        mDian = (ImageView) findViewById(R.id.mDian);

    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivity() {
        return null;
    }
}
