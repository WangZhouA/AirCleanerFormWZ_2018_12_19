package saiyi.com.aircleanerformwz_2018_12_19.user.model;

import android.content.Context;

import com.lib.fast.common.http.BaseHttpObserver;
import com.lib.fast.common.http.BaseResponse;
import com.lib.fast.common.http.ResponseListener;
import com.lib.fast.common.mvp.ModelImpl;

import saiyi.com.aircleanerformwz_2018_12_19.core.bean.User;
import saiyi.com.aircleanerformwz_2018_12_19.core.http.error.ResponseError;
import saiyi.com.aircleanerformwz_2018_12_19.core.http.service.HttpServiceApi;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class LoginModel extends ModelImpl {
    public LoginModel(Context context) {
        super(context);
    }


    /**
     * 登陆
     */
    public void login(String phone, String pwd, ResponseListener<User> listener) {
        excuteRetorfitRequest(HttpServiceApi.instance().login(phone, pwd), new BaseHttpObserver<User>(getCompositeDisposable(), listener) {
            @Override
            public void onResponse(BaseResponse<User> response) {
                if (response.isSuccess()) {
                    dispatchListenerResponse(response.getData());
                } else {
                    dispatchListenerFaild(ResponseError.handleError(response.getCode()));
                }
            }
        });
    }


}

