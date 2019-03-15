package saiyi.com.aircleanerformwz_2018_12_19.user.model;

import android.content.Context;

import com.lib.fast.common.http.BaseHttpObserver;
import com.lib.fast.common.http.BaseResponse;
import com.lib.fast.common.http.BaseResponseListener;
import com.lib.fast.common.mvp.ModelImpl;

import saiyi.com.aircleanerformwz_2018_12_19.core.http.error.ResponseError;
import saiyi.com.aircleanerformwz_2018_12_19.core.http.service.HttpServiceApi;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class ReigstModel extends ModelImpl {
    public ReigstModel(Context context) {
        super(context);
    }



    public  void   sendCode(String phone, BaseResponseListener<Void> listener){
          excuteRetorfitRequest(HttpServiceApi.instance().sendCode(phone), new BaseHttpObserver<Void>(getCompositeDisposable(), listener) {
            @Override
            public void onResponse(BaseResponse<Void> response) {
                if (response.isSuccess()) {
                    dispatchListenerResponse(response.getData());
                } else {
                    dispatchListenerFaild(ResponseError.handleError(response.getCode()));
                }
            }
        });

      }

    public  void   reigst(String phone, String password, String name, String code,  BaseResponseListener<Void> listener){
          excuteRetorfitRequest(HttpServiceApi.instance().register(phone ,password,name,code), new BaseHttpObserver<Void>(getCompositeDisposable(), listener) {
            @Override
            public void onResponse(BaseResponse<Void> response) {
                if (response.isSuccess()) {
                    dispatchListenerResponse(response.getData());
                } else {
                    dispatchListenerFaild(ResponseError.handleError(response.getCode()));
                }
            }
        });

      }





}
