package mddemo.library.com.okhttp.callback;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月26日14:06:52
 * Description:
 */
public abstract class ResultCallback<T> {

    public Type mType;

    public ResultCallback(){
        mType=getSuperclassTypeParameter(getClass());
    }

    /**
     * 传入一个类得到父类
     * @param subclass 类
     * @return 父类？
     */
    static Type getSuperclassTypeParameter(Class<?> subclass){

        Type superclass=subclass.getGenericSuperclass();
        if (superclass instanceof Class){
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType=(ParameterizedType)superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    public void onBefore(Request request){

    }

    public void onAfter(){

    }

    public void inProgress(float progress){

    }

    public abstract void onError(Request request,Exception e);

    public abstract void onResponse(T resonse);

    public static final ResultCallback<String> DEFAULT_RESULT_CALLBACK=new ResultCallback<String>() {
        @Override
        public void onError(Request request, Exception e) {

        }

        @Override
        public void onResponse(String resonse) {

        }
    };


}
