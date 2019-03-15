package saiyi.com.aircleanerformwz_2018_12_19.user.p;

import android.content.Context;
import android.text.TextUtils;

import com.lib.fast.common.http.BaseResponseListener;
import com.lib.fast.common.http.exception.ErrorStatus;
import com.lib.fast.common.mvp.PresenterImpl;

import saiyi.com.aircleanerformwz_2018_12_19.user.UserActivity;
import saiyi.com.aircleanerformwz_2018_12_19.user.UserBean;
import saiyi.com.aircleanerformwz_2018_12_19.user.model.UserModel;

/**
 * Created by 陈姣姣 on 2018/12/28.
 */
public class UserInfoP extends PresenterImpl<UserActivity,UserModel> {

    public UserInfoP(Context context) {
        super(context);
    }

    @Override
    public UserModel initModel(Context context) {
        return  new UserModel(context);
    }


    public  boolean getUserInfo(String phone){

        if (TextUtils.isEmpty(phone)){
            getView().toast("请填写正确的号码");
            return  false;
        }else {

            getModel().getUserInfo(phone, new BaseResponseListener<UserBean>(){

                @Override
                public void onResponse(UserBean data) {
                    super.onResponse(data);

                     getView().showPhone(data);

                }
                @Override
                public void onFaild(ErrorStatus e) {
                    super.onFaild(e);
                    getView().toast("用户不存在,用户数据无");

                }
            });

        }

        return  true ;
    }

}
