package gbpassenger.ichinait.com.medicine.okgo;

import android.util.TypedValue;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * Created by DawnOct on 2018/1/30.
 */

public class JsonCallBack<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallBack(Type type) {
        this.type = type;
    }

    public JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onSuccess(Response response) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) data = gson.fromJson(jsonReader, type);
        if (clazz != null) data = gson.fromJson(jsonReader, clazz);
        return data;
    }
}
