package gbpassenger.ichinait.com.medicine.act.subject_00;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gbpassenger.ichinait.com.medicine.R;
import me.jessyan.art.base.BaseFragment;
import me.jessyan.art.mvp.IPresenter;
import me.jessyan.art.mvp.IView;
import me.jessyan.art.mvp.Message;

/**
 * Created by DawnOct on 2018/2/1.
 */

public class DetailTaskFragment extends BaseFragment implements IView {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.detail_task, container, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleMessage(Message message) {

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
    protected IPresenter getPresenter() {
        return null;
    }
}
