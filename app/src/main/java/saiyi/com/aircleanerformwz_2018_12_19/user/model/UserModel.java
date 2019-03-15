package saiyi.com.aircleanerformwz_2018_12_19.user.model;

import android.content.Context;

import com.lib.fast.common.http.BaseHttpObserver;
import com.lib.fast.common.http.BaseResponse;
import com.lib.fast.common.http.ResponseListener;
import com.lib.fast.common.mvp.ModelImpl;

import java.util.ArrayList;
import java.util.List;

import saiyi.com.aircleanerformwz_2018_12_19.core.http.error.ResponseError;
import saiyi.com.aircleanerformwz_2018_12_19.core.http.service.HttpServiceApi;
import saiyi.com.aircleanerformwz_2018_12_19.user.UserBean;

/**
 * Created by 陈姣姣 on 2018/12/28.
 */
public class UserModel extends ModelImpl{

    List <UserBean > beanList ;
    public UserModel(Context context) {
        super(context);
        beanList= new ArrayList<>();
    }
    // 查询用户信息
    public void getUserInfo(String phone, ResponseListener<UserBean> listener) {
        excuteRetorfitRequest(HttpServiceApi.instance().getUserInfo(phone), new BaseHttpObserver<UserBean>(getCompositeDisposable(),listener) {
            @Override
            public void onResponse(BaseResponse<UserBean> response) {
                if (response.isSuccess()) {
                    dispatchListenerResponse(response.getData());
                } else {
                    dispatchListenerFaild(ResponseError.handleError(response.getCode()));
                }
            }
        });
    }
}
