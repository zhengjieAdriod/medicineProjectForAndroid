package gbpassenger.ichinait.com.medicine.act.add_02;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import net.nightwhistler.htmlspanner.HtmlSpanner;
import net.nightwhistler.htmlspanner.LinkMovementMethodExt;
import net.nightwhistler.htmlspanner.MyImageSpan;

import java.util.ArrayList;

import butterknife.BindView;
import byl.com.testprasehtml.ImgPreviewActivity;
import gbpassenger.ichinait.com.medicine.MainActivity;
import gbpassenger.ichinait.com.medicine.R;
import gbpassenger.ichinait.com.medicine.netbean.Detail;
import me.jessyan.art.base.BaseActivity;
import me.jessyan.art.mvp.IView;


//http://blog.csdn.net/baiyuliang2013/article/details/53538118
public class ShowActivity extends BaseActivity<AddPresenter> implements IView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    TextView tv;
    HtmlSpanner htmlSpanner;
    ArrayList<String> imglist;

    @Override
    protected int initView() {
        return R.layout.super_read_activity;
    }

    @Override
    protected void initData() {
        initActionBar("ff");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        imglist = new ArrayList<>();
        htmlSpanner = new HtmlSpanner(this, dm.widthPixels, handler);
        tv = (TextView) findViewById(R.id.tv);
        String subjectPk = getIntent().getStringExtra("subjectPk");
        if (!TextUtils.isEmpty(subjectPk))
            mPresenter.requestDetail(me.jessyan.art.mvp.Message.obtain(this), subjectPk);
    }


    public static final int SUBJECT_DETAIL_SUCCESS = 0;
    public static final int SUBJECT_DETAIL_ERROR = 1;
    public static final int HTML_PARSER_SUCCESS = 2;
    public static final int HTML_PARSER_ERROR = 3;
    Detail.SubjectBean bean;

    @Override
    public void handleMessage(me.jessyan.art.mvp.Message message) {
        switch (message.what) {
            case SUBJECT_DETAIL_SUCCESS:
                bean = (Detail.SubjectBean) message.obj;
                if (bean != null)
                    //成功获得详情数据之后,进行解析html的耗时操作: Spannable spannable = htmlSpanner.fromHtml(html); //耗时操作
                    mPresenter.parseHtml(me.jessyan.art.mvp.Message.obtain(this), htmlSpanner, bean.getContent());
                break;
            case SUBJECT_DETAIL_ERROR:
                break;
            case HTML_PARSER_SUCCESS:
                Spannable spannable = (Spannable) message.obj;
                tv.setText(spannable);
                tv.setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
                break;
            case HTML_PARSER_ERROR:
                break;

        }

    }

    @Override
    protected AddPresenter getPresenter() {
        return new AddPresenter();
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1://获取图片路径列表
                    String url = (String) msg.obj;
                    Log.e("jj", "url>>" + url);
                    imglist.add(url);
                    break;
                case 2://图片点击事件
                    int position = 0;
                    MyImageSpan span = (MyImageSpan) msg.obj;
                    for (int i = 0; i < imglist.size(); i++) {
                        if (span.getUrl().equals(imglist.get(i))) {
                            position = i;
                            break;
                        }
                    }
                    Log.e("jj", "position>>" + position);
                    Intent intent = new Intent(ShowActivity.this, ImgPreviewActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("position", position);
                    b.putStringArrayList("imglist", imglist);
                    intent.putExtra("b", b);
                    startActivity(intent);
                    break;
            }
        }

        ;
    };

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    //设置actionBar
    private void initActionBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_history:
                        Toast.makeText(ShowActivity.this, "action_history !", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_setting: //重新编辑
                        Intent intent = new Intent(ShowActivity.this, EditorActivity.class);
                        intent.putExtra("subject", bean);
                        startActivity(intent);
                        Toast.makeText(ShowActivity.this, "action_setting !", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    //title_bar的右侧点击时间
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_title, menu);
        return true;
    }
}
