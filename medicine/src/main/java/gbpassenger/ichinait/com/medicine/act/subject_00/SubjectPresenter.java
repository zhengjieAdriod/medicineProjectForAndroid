package gbpassenger.ichinait.com.medicine.act.subject_00;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import java.util.List;

import gbpassenger.ichinait.com.medicine.netbean.Detail;
import gbpassenger.ichinait.com.medicine.netbean.ResponceSubjects;
import gbpassenger.ichinait.com.medicine.okgo.JsonCallBack;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.jessyan.art.mvp.BasePresenter;
import me.jessyan.art.mvp.Message;

import static gbpassenger.ichinait.com.medicine.act.subject_00.SubjectFragment.SUBJECTS_LIST_ERROR;
import static gbpassenger.ichinait.com.medicine.act.subject_00.SubjectFragment.SUBJECTS_LIST_SUCCESS;

/**
 * Created by DawnOct on 2018/1/30.
 */

public class SubjectPresenter extends BasePresenter {
    //
    public void requestSubjectList(final Message message, int page) {
        Observable<Response<ResponceSubjects>> observable =
                OkGo.<ResponceSubjects>get("http://192.168.1.97:8000/getSubjects/")
                        .params("page", page)
                        .converter(new JsonCallBack<ResponceSubjects>(ResponceSubjects.class)).adapt(new ObservableResponse<ResponceSubjects>());
        observable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
//                        showLoding();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponceSubjects>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponceSubjects> stringResponse) {
                        ResponceSubjects body = stringResponse.body();
                        if ("200".equals(body.getCode())) {
//                            List<Detail.SubjectBean> subject_list = body.getSubject_list();
                            message.obj = body;
                            message.what = SUBJECTS_LIST_SUCCESS;
                            message.HandleMessageToTargetUnrecycle();
                        } else {
                            message.what = SUBJECTS_LIST_ERROR;
                            message.HandleMessageToTargetUnrecycle();
                        }
                        Log.e("okgo", "onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        message.what = SUBJECTS_LIST_ERROR;
                        message.HandleMessageToTargetUnrecycle();
                        Log.e("okgo", "Throwable");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("okgo", "onComplete");
                    }
                });

    }

    public void requestDetail(Message message) {
        Observable<Response<Detail>> observable = OkGo.<Detail>get("http://192.168.1.97:8000/subject/19")
                .converter(new JsonCallBack<Detail>(Detail.class)).adapt(new ObservableResponse<Detail>());
        observable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
//                        showLoding();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Detail>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<Detail> stringResponse) {
                        Detail body = stringResponse.body();
                        Log.e("okgo", "onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("okgo", "Throwable");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("okgo", "onComplete");
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }
}
