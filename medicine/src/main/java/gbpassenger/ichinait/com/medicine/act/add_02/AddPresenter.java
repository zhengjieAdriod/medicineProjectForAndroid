package gbpassenger.ichinait.com.medicine.act.add_02;

import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import java.io.File;

import gbpassenger.ichinait.com.medicine.netbean.Detail;
import gbpassenger.ichinait.com.medicine.netbean.Picture;
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

import static gbpassenger.ichinait.com.medicine.act.add_02.CenterFragment.POST_IMAGE_ERROR;
import static gbpassenger.ichinait.com.medicine.act.add_02.CenterFragment.POST_IMAGE_SUCCESS;
import static gbpassenger.ichinait.com.medicine.act.subject_00.SubjectFragment.SUBJECTS_LIST_ERROR;

public class AddPresenter extends BasePresenter {
    public void addSubject(final Message message, Detail.SubjectBean bean) {
        String res = new Gson().toJson(bean);

        Observable<Response<ResponceSubjects>> observable =
                OkGo.<ResponceSubjects>post("http://192.168.1.97:8000/addSubject/")
                        .isMultipart(true)
                        .params("res", res)
                        .converter(new JsonCallBack<ResponceSubjects>(ResponceSubjects.class))
                        .adapt(new ObservableResponse<ResponceSubjects>());

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
//                            message.what = POST_IMAGE_SUCCESS;
//                            message.HandleMessageToTargetUnrecycle();
                        } else {
//                            message.what = POST_IMAGE_ERROR;
//                            message.HandleMessageToTargetUnrecycle();
                        }
                        Log.e("okgo", "onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        message.what = POST_IMAGE_ERROR;
//                        message.HandleMessageToTargetUnrecycle();
                        Log.e("okgo", "Throwable");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("okgo", "onComplete");
                    }
                });

    }


    /*
    上传一张图片
    param: 消息, 用户id, 文件描述,文件路径
    * */
    public void postImage(final Message message, String userPk, String key, String path) {
        Observable<Response<Picture>> observable =
                OkGo.<Picture>post("http://192.168.1.97:8000/postPic/")
                        .isMultipart(true)
                        .params("userPk", userPk)
                        .params(key, new File(path))
                        .converter(new JsonCallBack<Picture>(Picture.class))
                        .adapt(new ObservableResponse<Picture>());

        observable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
//                        showLoding();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Picture>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<Picture> stringResponse) {
                        Picture body = stringResponse.body();
                        if ("200".equals(body.getCode())) {
                            message.obj = body.getPicUrl();
                            message.what = POST_IMAGE_SUCCESS;
                            message.HandleMessageToTargetUnrecycle();
                        } else {
                            message.what = POST_IMAGE_ERROR;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }
}


