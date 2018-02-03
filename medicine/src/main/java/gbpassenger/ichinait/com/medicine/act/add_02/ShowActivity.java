package gbpassenger.ichinait.com.medicine.act.add_02;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import net.nightwhistler.htmlspanner.HtmlSpanner;
import net.nightwhistler.htmlspanner.LinkMovementMethodExt;
import net.nightwhistler.htmlspanner.MyImageSpan;

import java.util.ArrayList;

import byl.com.testprasehtml.ImgPreviewActivity;
import gbpassenger.ichinait.com.medicine.R;
import gbpassenger.ichinait.com.medicine.utils.Constant;
import me.jessyan.art.base.BaseActivity;
import me.jessyan.art.mvp.IPresenter;
import me.jessyan.art.mvp.IView;

//http://blog.csdn.net/baiyuliang2013/article/details/53538118
public class ShowActivity extends BaseActivity<AddPresenter> implements IView {

    TextView tv;
    HtmlSpanner htmlSpanner;
    ArrayList<String> imglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_read_activity);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        imglist = new ArrayList<>();
        htmlSpanner = new HtmlSpanner(this, dm.widthPixels, handler);
        tv = (TextView) findViewById(R.id.tv);
        final String html = Constant.html;
// todo  首次添加新话题完成后, 跳转到该页(从后台获取数据,仅仅展示).
// todo 在该页面中点击编辑, 则跳转到编辑页(在编辑页,从后台获取数据,通过 mEditor.setHtml(h),完成再次编辑)
//todo  mPresenter.getSubjectDetail(me.jessyan.art.mvp.Message mesage);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Spannable spannable = htmlSpanner.fromHtml(html); //耗时操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(spannable);
                        tv.setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
                    }
                });
            }
        }).start();
    }

    @Override
    protected int initView() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleMessage(me.jessyan.art.mvp.Message message) {

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


}
