package gbpassenger.ichinait.com.medicine.act.subject_00;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.nightwhistler.htmlspanner.HtmlSpanner;
import net.nightwhistler.htmlspanner.LinkMovementMethodExt;
import net.nightwhistler.htmlspanner.MyImageSpan;

import java.util.ArrayList;

import byl.com.testprasehtml.ImgPreviewActivity;
import gbpassenger.ichinait.com.medicine.R;
import me.jessyan.art.base.BaseFragment;
import me.jessyan.art.mvp.IPresenter;
import me.jessyan.art.mvp.IView;
import me.jessyan.art.mvp.Message;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;
import static gbpassenger.ichinait.com.medicine.utils.Constant.html;

/**
 * Created by DawnOct on 2018/2/1.
 */

public class DetailContentFragment extends BaseFragment implements IView {
    TextView tv;
    HtmlSpanner htmlSpanner;
    ArrayList<String> imglist;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup ctainer) {
        View view = inflater.inflate(R.layout.super_read_activity, ctainer, false);
        tv = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    @Override
    protected void initData() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        imglist = new ArrayList<>();
        htmlSpanner = new HtmlSpanner(getContext(), dm.widthPixels, handler);
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
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void handleMessage(Message message) {

    }

    final Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
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
                    Intent intent = new Intent(getActivity(), ImgPreviewActivity.class);
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
}
