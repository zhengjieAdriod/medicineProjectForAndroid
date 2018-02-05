package gbpassenger.ichinait.com.medicine.act.add_02;

import android.text.Spannable;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import net.nightwhistler.htmlspanner.HtmlSpanner;

import java.io.File;

import gbpassenger.ichinait.com.medicine.netbean.Detail;
import gbpassenger.ichinait.com.medicine.netbean.Picture;
import gbpassenger.ichinait.com.medicine.netbean.ResponceSubjects;
import gbpassenger.ichinait.com.medicine.okgo.JsonCallBack;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.art.mvp.BasePresenter;
import me.jessyan.art.mvp.Message;

import static gbpassenger.ichinait.com.medicine.act.add_02.EditorActivity.POST_IMAGE_ERROR;
import static gbpassenger.ichinait.com.medicine.act.add_02.EditorActivity.POST_IMAGE_SUCCESS;
import static gbpassenger.ichinait.com.medicine.act.add_02.EditorActivity.POST_SUBJECT_ERROR;
import static gbpassenger.ichinait.com.medicine.act.add_02.EditorActivity.POST_SUBJECT_SUCCESS;
import static gbpassenger.ichinait.com.medicine.act.add_02.ShowActivity.HTML_PARSER_ERROR;
import static gbpassenger.ichinait.com.medicine.act.add_02.ShowActivity.HTML_PARSER_SUCCESS;
import static gbpassenger.ichinait.com.medicine.act.add_02.ShowActivity.SUBJECT_DETAIL_ERROR;
import static gbpassenger.ichinait.com.medicine.act.add_02.ShowActivity.SUBJECT_DETAIL_SUCCESS;
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
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponceSubjects> stringResponse) {
                        ResponceSubjects body = stringResponse.body();
                        if ("200".equals(body.getCode())) {
                            message.what = POST_SUBJECT_SUCCESS;
                            message.obj = body.getMsg();
                            message.HandleMessageToTargetUnrecycle();
                        } else {
                            message.what = POST_SUBJECT_ERROR;
                            message.HandleMessageToTargetUnrecycle();
                        }
                        Log.e("okgo", "onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        message.what = POST_SUBJECT_ERROR;
                        message.HandleMessageToTargetUnrecycle();
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
                        addSubscribe(d);
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

    /*获得subject详情*/
    public void requestDetail(final Message message, String subjectPk) {
        Observable<Response<Detail>> observable = OkGo.<Detail>get("http://192.168.1.97:8000/subject/" + subjectPk)
                .converter(new JsonCallBack<Detail>(Detail.class))
                .adapt(new ObservableResponse<Detail>());
        observable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
//                        showLoding();
                Log.e("okgo", "showLoding");
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Detail>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<Detail> stringResponse) {
                        Detail body = stringResponse.body();
                        String code = body.getCode();
                        if ("200".equals(code)) {
                            Detail.SubjectBean subject = body.getSubject();
                            message.what = SUBJECT_DETAIL_SUCCESS;
                            message.obj = subject;
                            message.HandleMessageToTargetUnrecycle();
                        } else {
                            message.what = SUBJECT_DETAIL_ERROR;
                            message.HandleMessageToTargetUnrecycle();
                        }

                        Log.e("okgo", "onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        message.what = SUBJECT_DETAIL_ERROR;
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

    public void parseHtml(final Message message, final HtmlSpanner htmlSpanner, final String html) {

        //被观察者
        Observable<Spannable> spannableObservable = Observable.create(new ObservableOnSubscribe<Spannable>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Spannable> e) throws Exception {
                Spannable spannable = htmlSpanner.fromHtml(html); //耗时操作
                e.onNext(spannable);
                e.onComplete();
            }
        });
        //观察者
        DisposableObserver<Spannable> disposableObserver = new DisposableObserver<Spannable>() {
            @Override
            public void onNext(@NonNull Spannable spannable) {
                message.obj = spannable;
                message.what = HTML_PARSER_SUCCESS;
                message.HandleMessageToTargetUnrecycle();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                message.what = HTML_PARSER_ERROR;
                message.HandleMessageToTargetUnrecycle();
            }

            @Override
            public void onComplete() {

            }
        };
        spannableObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }
}


